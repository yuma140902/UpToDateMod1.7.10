package yuma140902.uptodatemod.vrl.archives;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.HashMap;
import java.io.Closeable;
import java.util.List;

public class ArchiveFileSystemStore implements Closeable {

    private final HashMap<String, FileSystem> registry = new HashMap<String, FileSystem>();

    public ArchiveFileSystemStore(List<Archive> archives) throws IOException {
        for (final Archive archive : archives) {
            FileSystem fs = FileSystems.newFileSystem(archive.getPath(), null);
            registry.put(archive.getId(), fs);
        }
    }

    public FileSystem getFileSystem(String id) {
        return registry.get(id);
    }

    public void close() throws IOException {
        for (FileSystem fs : registry.values()) {
            fs.close();
        }
        registry.clear();
    }
}
