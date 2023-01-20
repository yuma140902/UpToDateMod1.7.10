package yuma140902.uptodatemod.launch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.config.ModConfigCore;
import yuma140902.uptodatemod.launch.archives.ArchiveRegistry;
import yuma140902.uptodatemod.launch.download.DownloadCandidate;
import yuma140902.uptodatemod.launch.download.DownloaderWithDisplay;
import yuma140902.uptodatemod.launch.download.IDownloadDisplay;
import yuma140902.uptodatemod.launch.download.IDownloader;
import yuma140902.uptodatemod.launch.download.SwingDownloadDisplay;
import yuma140902.uptodatemod.launch.model.Archive;
import yuma140902.uptodatemod.launch.model.Copy;
import yuma140902.uptodatemod.launch.model.Setting;
import yuma140902.uptodatemod.launch.model.Sound;
import yuma140902.uptodatemod.launch.organize.IOrganizeDisplay;
import yuma140902.uptodatemod.launch.organize.IOrganizer;
import yuma140902.uptodatemod.launch.organize.OrganizerWithDisplay;
import yuma140902.uptodatemod.launch.organize.SwingOrganizeDisplay;
import yuma140902.uptodatemod.launch.sounddownload.ISoundDownloadDisplay;
import yuma140902.uptodatemod.launch.sounddownload.ISoundDownloader;
import yuma140902.uptodatemod.launch.sounddownload.SoundDownloaderWithDisplay;
import yuma140902.uptodatemod.launch.sounddownload.SwingSoundDownloadDisplay;

public class VanillaResourceLoader {
	
	private final Logger log = LogManager.getLogger(ModUpToDateMod.MOD_NAME + "-ResourceLoader");
	
	private final Path cacheDir;
	private final Path archiveDir;
	private final Path assetsDir;
	private final Path versionCheckFile;
	private final ArchiveRegistry archiveRegistry;
	
	public VanillaResourceLoader(Path cacheDir, Path archiveDir, Path assetsDir){
		this.cacheDir = cacheDir;
		this.archiveDir = archiveDir;
		this.assetsDir = assetsDir;
		this.versionCheckFile = assetsDir.resolve("settings.json.md5");
		this.archiveRegistry = new ArchiveRegistry();
	}
	
	public void load() throws VanillaResourceLoadingException, IOException {
		log.info("Starting loading vanilla resources");
		
		Class<ModUpToDateMod> clazz = ModUpToDateMod.class;
		String settingFileName = "/settings.json";
		String settingJsonHash = DigestUtils.md5Hex(clazz.getResourceAsStream(settingFileName));
		
		if(/*needUpdate(settingJsonHash)*/true) {
			log.info("Loading settings.json");
			
			Gson gson = new Gson();
			InputStreamReader reader = new InputStreamReader(clazz.getResourceAsStream(settingFileName));
			Setting setting = gson.fromJson(reader, Setting.class);
			log.info("Successfully loaded settings.json");
			
			Files.createDirectories(cacheDir);
			Files.createDirectories(archiveDir);
			Files.createDirectories(assetsDir);
			
			log.info("Starting jar downloader");
			tryDownloadArchives(setting.archives);
			registerArchives(setting.archives);
			log.info("Starting organizer");
			organize(setting.copies);
			log.info("Starting sound downloader");
			setupSounds(setting.sounds);
			this.archiveRegistry.closeAll();
			
			makeVersionCheckFile(settingJsonHash);
		}
		log.info("Finished loading vanilla resources");
	}
	
	/**
	 * 以前に{@link VanillaResourceLoader}が実行されたときから、
	 * settings.jsonの内容が変更されているかどうかをチェックし、
	 * 今{@link VanillaResourceLoader}を実行する必要があるかどうかを判断する。
	 * @param currentHash 現在のsettings.jsonのハッシュ値
	 * @return
	 * @throws IOException {@link #versionCheckFile}が開けなかったとき
	 */
	private boolean needUpdate(String currentHash) throws IOException {
		if(!Files.exists(versionCheckFile)) {
			return true;
		}
		List<String> lines = Files.readAllLines(versionCheckFile);
		if(lines.size() <= 0) {
			return true;
		}
		
		String savedHash = lines.get(0);
		if(!StringUtils.equals(savedHash, currentHash)) {
			return true;
		}
		return false;
	}
	
	/**
	 * settings.jsonのハッシュ値を計算し、{@link #versionCheckFile}に書き込む。<br>
	 * この情報は次に{@link VanillaResourceLoader}が実行されたときに{@link #needUpdate(String)}によって利用される。
	 * @param currentHash 現在のsettings.jsonのハッシュ値
	 * @throws IOException settings.jsonが開けなかったとき。{@link #versionCheckFile}に書き込めなかったとき。
	 */
	private void makeVersionCheckFile(String currentHash) throws IOException {
		List<String> lines = new ArrayList<String>();
		lines.add(currentHash);
		Files.write(versionCheckFile, lines, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
	}
	
	/**
	 * バニラJarのダウンロードを行い、その後ファイルが破損していないかどうか確認する。<br>
	 * 破損していた場合はダウンロードをやり直す。
	 * @param archives
	 * @throws VanillaResourceLoadingException ダウンロードを何回かやり直してもファイルが破損したままだったとき。
	 * @throws IOException バニラJarとそのキャッシュを削除できなかったとき。
	 */
	private void tryDownloadArchives(List<Archive> archives) throws VanillaResourceLoadingException, IOException {
		int trials = 0;
		final int maxTrials = 3;
		boolean needReDownload = false;
		do {
			++trials;
			log.info("Downloading trial " + trials);
			downloadArchives(archives);
			needReDownload = needReDownloadArchives(archives);
		}while(needReDownload && trials < maxTrials);
		if(needReDownload){
			// 3回ダウンロードを試行しても失敗したら例外を投げる。
			// こうなった場合は対処不能なので、この例外によってクライアントがクラッシュすることを期待する。
			throw new VanillaResourceLoadingException("[UpToDateMod] Failed to download resources! You seems to have bad internet connection.");
		}
	}
	
	/**
	 * バニラJarのダウンロードをする。その際にウィンドウを開き進捗状況を表示する。
	 * @param archives
	 */
	private void downloadArchives(List<Archive> archives) {
		List<DownloadCandidate> candidates = new LinkedList<DownloadCandidate>();
		for(Archive archive : archives) {
			candidates.add(new DownloadCandidate(archive));
		}
		
		IDownloadDisplay display = new SwingDownloadDisplay();
		
		IDownloader downloader = new DownloaderWithDisplay(display, cacheDir, archiveDir);
		downloader.downloadFiles(candidates);
	}
	
	/**
	 * バニラJarが破損しているかどうかチェックして、ダウンロードし直す必要があるかどうか判断する。<br>
	 * 破損していた場合はバニラJarとそのキャッシュを削除する。
	 * @param archives
	 * @return
	 * @throws IOException バニラJarとそのキャッシュを削除できなかったとき。
	 */
	private boolean needReDownloadArchives(List<Archive> archives) throws IOException {
		if(!ModConfigCore.General.validateVanillaJar()){
			return false;
		}
		
		boolean needReDownload = false;
		for(final Archive archive : archives){
			Path archivePath = archiveDir.resolve(archive.filename);
			Path archiveCachedPath = cacheDir.resolve(archive.filename);
			InputStream input;
			String md5;
			try {
				input = new FileInputStream(archivePath.toFile());
				md5 = DigestUtils.md5Hex(input);
			} catch (IOException e) {
				needReDownload = true;
				continue;
			}
			input.close();
			
			if(!StringUtils.equals(md5, archive.hash)){
				log.error("Archive: " + archive.filename + ", expected hash: " + archive.hash + ", but got: " + md5);
				needReDownload = true;
				Files.deleteIfExists(archivePath);
				Files.deleteIfExists(archiveCachedPath);
			}
		}
		
		return needReDownload;
	}
	
	/**
	 * バニラJarを{@link #archiveRegistry}に登録する。
	 * @param archives
	 * @throws IOException バニラJarが見つからなかったとき。
	 */
	private void registerArchives(List<Archive> archives) throws IOException {
		if(archives.size() <= 0) {
			log.info("No archives.");
			return;
		}
		log.info("Archives:");
		for(Archive archive : archives) {
			String id = archive.slug;
			Path filePath = archiveDir.resolve(archive.filename);
			this.archiveRegistry.register(id, filePath);
			
			log.info(" - id: " + id);
			log.info(" - path: " + filePath);
		}
	}
	
	/**
	 * バニラJarからテクスチャファイルを取り出して指定された場所にコピーする。
	 * その際にウィンドウを開き進捗状況を表示する。<br>
	 * (既存のファイルは上書きされる)
	 * @param copies
	 */
	private void organize(List<Copy> copies) {
		IOrganizeDisplay display = new SwingOrganizeDisplay();
		
		IOrganizer organizer = new OrganizerWithDisplay(display, assetsDir, archiveRegistry);
		organizer.organize(copies);
	}
	
	/**
	 * 効果音のファイルをダウンロードする。
	 * その際にウィンドウを開き進捗状況を表示する。
	 * @param sounds
	 */
	private void setupSounds(List<Sound> sounds) {
		ISoundDownloadDisplay display = new SwingSoundDownloadDisplay();
		
		ISoundDownloader downloader = new SoundDownloaderWithDisplay(display, cacheDir, assetsDir);
		downloader.downloadSounds(sounds);
	}
}
