package yuma140902.uptodatemod.launch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	
	public static final Logger log = LogManager.getLogger(ModUpToDateMod.MOD_NAME + "-ResourceLoader");
	
	public static void load(Path cacheDir, Path archiveDir, Path assetsDir) throws Exception {
		log.info("Starting loading vanilla resources");
		
		Class<ModUpToDateMod> clazz = ModUpToDateMod.class;
		String settingFileName = "/settings.json";
		
		if(needUpdate(assetsDir, clazz.getResourceAsStream(settingFileName))) {
			log.info("Loading settings.json");
			
			Gson gson = new Gson();
			InputStreamReader reader = new InputStreamReader(clazz.getResourceAsStream(settingFileName));
			Setting setting = gson.fromJson(reader, Setting.class);
			log.info("Successfully loaded settings.json");
			
			Files.createDirectories(cacheDir);
			Files.createDirectories(archiveDir);
			Files.createDirectories(assetsDir);
			
			log.info("Starting jar downloader");
			tryDownloadArchives(setting.archives, cacheDir, archiveDir);
			registerArchives(setting.archives, archiveDir);
			log.info("Starting organizer");
			organize(setting.copies, assetsDir);
			log.info("Starting sound downloader");
			setupSounds(setting.sounds, cacheDir, assetsDir);
			ArchiveRegistry.closeAll();
			
			makeVersionCheckFile(assetsDir, clazz.getResourceAsStream(settingFileName));
		}
		log.info("Finished loading vanilla resources");
	}
	
	/**
	 * 以前に{@link VanillaResourceLoader}が実行されたときから、
	 * settings.jsonの内容が変更されているかどうかをチェックし、
	 * 今{@link VanillaResourceLoader}を実行する必要があるかどうかを判断する。
	 * @param assetsDir
	 * @param settingJson
	 * @return
	 * @throws IOException settings.jsonが開けなかったとき
	 */
	private static boolean needUpdate(Path assetsDir, InputStream settingJson) throws IOException {
		Path versionCheckFile = assetsDir.resolve("settings.json.md5");
		if(!Files.exists(versionCheckFile)) {
			return true;
		}
		List<String> lines = Files.readAllLines(versionCheckFile);
		if(lines.size() <= 0) {
			return true;
		}
		
		String savedHash = lines.get(0);
		String currentHash = DigestUtils.md5Hex(settingJson);
		if(savedHash == null || !savedHash.equals(currentHash)) {
			return true;
		}
		return false;
	}
	
	/**
	 * settings.jsonのハッシュ値を計算し、settings.json.md5に書き込む。<br>
	 * この情報は次に{@link VanillaResourceLoader}が実行されたときに{@link #needUpdate(Path, InputStream)}によって利用される。
	 * @param assetsDir
	 * @param settingJson
	 * @throws IOException settings.jsonが開けなかったとき。settings.json.md5に書き込めなかったとき。
	 */
	private static void makeVersionCheckFile(Path assetsDir, InputStream settingJson) throws IOException {
		String currentHash = DigestUtils.md5Hex(settingJson);
		List<String> lines = new ArrayList<String>();
		lines.add(currentHash);
		Files.write(assetsDir.resolve("settings.json.md5"), lines, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
	}
	
	/**
	 * バニラJarのダウンロードを行い、その後ファイルが破損していないかどうか確認する。<br>
	 * 破損していた場合はダウンロードをやり直す。
	 * @param archives
	 * @param cacheDir
	 * @param archiveDir
	 * @throws Exception ダウンロードを何回かやり直してもファイルが破損したままだったとき。
	 */
	private static void tryDownloadArchives(List<Archive> archives, Path cacheDir, Path archiveDir) throws Exception {
		int trials = 0;
		final int maxTrials = 3;
		boolean needReDownload = false;
		do {
			++trials;
			log.info("Downloading trial " + trials);
			downloadArchives(archives, cacheDir, archiveDir);
			needReDownload = needReDownloadArchives(archives, cacheDir, archiveDir);
		}while(needReDownload && trials < maxTrials);
		if(needReDownload){
			// 3回ダウンロードを試行しても失敗したら例外を投げる。
			// こうなった場合は対処不能なので、この例外によってクライアントがクラッシュすることを期待する。
			throw new Exception("[UpToDateMod] Failed to download resources! You seems to have bad internet connection.");
		}
	}
	
	/**
	 * バニラJarのダウンロードをする。その際にウィンドウを開き進捗状況を表示する。
	 * @param archives
	 * @param cacheDir
	 * @param archiveDir
	 */
	private static void downloadArchives(List<Archive> archives, Path cacheDir, Path archiveDir) {
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
	 * @param cacheDir
	 * @param archiveDir
	 * @return
	 * @throws IOException バニラJarとそのキャッシュを削除できなかったとき。
	 */
	private static boolean needReDownloadArchives(List<Archive> archives, Path cacheDir, Path archiveDir) throws IOException {
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
			} catch (FileNotFoundException e) {
				needReDownload = true;
				continue;
			} catch (IOException e){
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
	 * バニラJarを{@link ArchiveRegistry}に登録する。
	 * @param archives
	 * @param archiveDir
	 * @throws IOException バニラJarが見つからなかったとき。
	 */
	private static void registerArchives(List<Archive> archives, Path archiveDir) throws IOException {
		if(archives.size() <= 0) {
			log.info("No archives.");
			return;
		}
		log.info("Archives:");
		for(Archive archive : archives) {
			String id = archive.slug;
			Path filePath = archiveDir.resolve(archive.filename);
			ArchiveRegistry.register(id, filePath);
			
			log.info(" - id: " + id);
			log.info(" - path: " + filePath);
		}
	}
	
	/**
	 * バニラJarからテクスチャファイルを取り出して指定された場所にコピーする。
	 * その際にウィンドウを開き進捗状況を表示する。<br>
	 * (既存のファイルは上書きされる)
	 * @param copies
	 * @param assetsDir
	 */
	private static void organize(List<Copy> copies, Path assetsDir) {
		IOrganizeDisplay display = new SwingOrganizeDisplay();
		
		IOrganizer organizer = new OrganizerWithDisplay(display, assetsDir);
		organizer.organize(copies);
	}
	
	/**
	 * 効果音のファイルをダウンロードする。
	 * その際にウィンドウを開き進捗状況を表示する。
	 * @param sounds
	 * @param cacheDir
	 * @param assetDir
	 */
	private static void setupSounds(List<Sound> sounds, Path cacheDir, Path assetDir) {
		ISoundDownloadDisplay display = new SwingSoundDownloadDisplay();
		
		ISoundDownloader downloader = new SoundDownloaderWithDisplay(display, cacheDir, assetDir);
		downloader.downloadSounds(sounds);
	}
}
