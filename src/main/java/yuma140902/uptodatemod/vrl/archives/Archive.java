package yuma140902.uptodatemod.vrl.archives;

import java.nio.file.Path;

public class Archive {
    private final String id;
    private final Path path;

    public Archive(String id, Path path) {
        this.id = id;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public Path getPath() {
        return path;
    }
}
