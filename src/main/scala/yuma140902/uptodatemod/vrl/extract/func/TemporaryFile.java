package yuma140902.uptodatemod.vrl.extract.func;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemporaryFile implements Closeable {
    private final Path path;
    public TemporaryFile(Path companion) {
        this.path = companion.getParent().resolve(companion.getFileName() + ".tmp");
    }

    public Path getPath() {
        return path;
    }

    @Override
    public void close() throws IOException {
        Files.deleteIfExists(this.path);
    }
}
