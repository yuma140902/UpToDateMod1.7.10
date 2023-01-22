package yuma140902.uptodatemod.vrl.extract.func;

import java.io.IOException;
import java.nio.file.Path;

public interface IExtractFunction {
    void handle(Path src, Path dst) throws IOException;
}
