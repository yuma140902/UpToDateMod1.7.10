package yuma140902.uptodatemod.launch.organize.func;

import java.io.IOException;
import java.nio.file.Path;

public interface IFunction {
	void handle(Path src, Path dest) throws IOException;
}
