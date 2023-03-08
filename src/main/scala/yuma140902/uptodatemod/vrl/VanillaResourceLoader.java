package yuma140902.uptodatemod.vrl;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yuma140902.uptodatemod.ModUpToDateMod;
import yuma140902.uptodatemod.vrl.archives.Archive;
import yuma140902.uptodatemod.vrl.download.*;
import yuma140902.uptodatemod.vrl.archives.ArchiveFileSystemStore;
import yuma140902.uptodatemod.vrl.extract.EnumTextureStyle;
import yuma140902.uptodatemod.vrl.extract.Extractor;
import yuma140902.uptodatemod.vrl.model.VRLSetting;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * VRL。jarに埋め込まれた設定ファイルを読み込み、Mojangのサーバーからリソースをダウンロードし、ローカルファイルシステムの適切な場所に配置する。
 */
public class VanillaResourceLoader {

    private final Logger log = LogManager.getLogger(ModUpToDateMod.MOD_NAME + "-ResourceLoader");

    private final Path uptodatemodDir;

    public VanillaResourceLoader(Path uptodatemodDir) {
        this.uptodatemodDir = uptodatemodDir;
    }

    public void load() throws VRLException {
        log.info("Starting loading vanilla resources");

        try {
            FileUtils.deleteDirectory(this.uptodatemodDir.resolve("dl-cache").toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        VRLSettingFileLoader vrlSettingFileLoader = new VRLSettingFileLoader(
                "/vrl-settings.json");
        String settingJsonHash = vrlSettingFileLoader.loadSettingHash();

        System.out.println("[VRL] Loading vrl setting json");
        VRLSetting setting = vrlSettingFileLoader.loadSetting();
        System.out.println("[VRL] Loaded vrl setting json");

        System.out.println("[VRL] Downloading resources");
        SwingDownloader downloader = new SwingDownloader(
                setting.steamDownloadTasks(this.uptodatemodDir).collect(Collectors.toList()));
        downloader.start();
        System.out.println("[VRL] Downloaded resources");

        System.out.println("[VRL] Extracting resources");
        List<Archive> archives = setting.archives.stream().map(a ->
                new Archive(a.id, this.uptodatemodDir.resolve(a.path))).collect(Collectors.toList());
        try (ArchiveFileSystemStore archiveStore = new ArchiveFileSystemStore(archives)) {
            try (FileHashPersistence settingJsonPersistence = new FileHashPersistence(
                    ModUpToDateMod.class.getResourceAsStream("/vrl-settings.json"),
                    this.uptodatemodDir.resolve("vrl-settings.json.md5"))) {
                if (!settingJsonPersistence.hashMatches()) {
                    Extractor extractor = new Extractor(
                            setting.extraction.createExtractionTaskList(EnumTextureStyle.BEFORE12, this.uptodatemodDir),
                            archiveStore);
                    extractor.start();
                }
            }
        } catch (IOException e) {
            throw new VRLException("Failed to open archives", e);
        }
        System.out.println("[VRL] Extracted resources");

        log.info("Finished loading vanilla resources");
    }
}
