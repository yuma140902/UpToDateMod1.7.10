package yuma140902.uptodatemod.launch;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.launch.archives.ArchiveRegistry;
import yuma140902.uptodatemod.launch.download.DownloadCandidate;
import yuma140902.uptodatemod.launch.download.DownloaderWithDisplay;
import yuma140902.uptodatemod.launch.download.IDownloadDisplay;
import yuma140902.uptodatemod.launch.download.IDownloader;
import yuma140902.uptodatemod.launch.download.SwingDownloadDisplay;
import yuma140902.uptodatemod.launch.model.Archive;
import yuma140902.uptodatemod.launch.model.Setting;
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
	
	public static void load(Path caches, Path archives, Path assets) throws IOException {
		log.info("Starting loading vanilla resources");
		
		Class<ModUpToDateMod> clazz = ModUpToDateMod.class;
		String settingFileName = "/settings.json";
		
		if(needUpdate(assets, clazz.getResourceAsStream(settingFileName))) {
			log.info("Loading settings.json");
			
			Gson gson = new Gson();
			InputStreamReader reader = new InputStreamReader(clazz.getResourceAsStream(settingFileName));
			Setting setting = gson.fromJson(reader, Setting.class);
			log.info("Successfully loaded settings.json");
			
			Files.createDirectories(caches);
			Files.createDirectories(archives);
			Files.createDirectories(assets);
			
			log.info("Starting jar downloader");
			download(setting, caches, archives);
			registerArchives(setting, archives);
			log.info("Starting organizer");
			organize(setting, assets);
			log.info("Starting sound downloader");
			setupSounds(setting, caches, assets);
			ArchiveRegistry.closeAll();
			
			makeVersionCheckFile(assets, clazz.getResourceAsStream(settingFileName));
		}
		log.info("Finished loading vanilla resources");
	}
	
	private static boolean needUpdate(Path assetsDir, InputStream settingJson) throws IOException {
		Path versionCheckFile = assetsDir.resolve("settings.json.sha512");
		if(!Files.exists(versionCheckFile)) {
			return true;
		}
		List<String> lines = Files.readAllLines(versionCheckFile);
		if(lines.size() <= 0) {
			return true;
		}
		
		String savedHash = lines.get(0);
		String currentHash = Sha512.calcSha512(settingJson);
		if(savedHash == null || !savedHash.equals(currentHash)) {
			return true;
		}
		return false;
	}
	
	private static void makeVersionCheckFile(Path assetsDir, InputStream settingJson) throws IOException {
		String currentHash = Sha512.calcSha512(settingJson);
		List<String> lines = new ArrayList<String>();
		lines.add(currentHash);
		Files.write(assetsDir.resolve("settings.json.sha512"), lines, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
	}
	
	private static void download(Setting setting, Path caches, Path archives) {
		List<DownloadCandidate> candidates = new LinkedList<DownloadCandidate>();
		for(Archive archive : setting.archives) {
			candidates.add(new DownloadCandidate(archive));
		}
		
		IDownloadDisplay display = new SwingDownloadDisplay();
		
		IDownloader downloader = new DownloaderWithDisplay(display, caches, archives);
		downloader.downloadFiles(candidates);
	}
	
	private static void registerArchives(Setting setting, Path archives) throws IOException {
		for(Archive archive : setting.archives) {
			String id = archive.slug;
			Path filePath = archives.resolve(archive.filename);
			ArchiveRegistry.register(id, filePath);
			
			log.info("Archives:");
			log.info("- id: " + id);
			log.info("  path: " + filePath);
		}
	}
	
	private static void organize(Setting setting, Path assets) {
		IOrganizeDisplay display = new SwingOrganizeDisplay();
		
		IOrganizer organizer = new OrganizerWithDisplay(display, assets);
		organizer.organize(setting.copies);
	}
	
	private static void setupSounds(Setting setting, Path caches, Path assets) {
		ISoundDownloadDisplay display = new SwingSoundDownloadDisplay();
		
		ISoundDownloader downloader = new SoundDownloaderWithDisplay(display, caches, assets);
		downloader.downloadSounds(setting.sounds);
	}
}
