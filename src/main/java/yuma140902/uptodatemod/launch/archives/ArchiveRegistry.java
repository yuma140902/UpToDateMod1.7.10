package yuma140902.uptodatemod.launch.archives;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

public class ArchiveRegistry {
	
	private static HashMap<String, FileSystem> registry = new HashMap<String, FileSystem>();
	
	public static void register(String id, Path archivePath) throws IOException {
		FileSystem archive = FileSystems.newFileSystem(archivePath, null);
		registry.put(id, archive);
	}
	
	public static FileSystem getArchive(String id) {
		return registry.get(id);
	}
}
