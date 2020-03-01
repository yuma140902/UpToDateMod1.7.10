package yuma140902.uptodatemod.launch.organize.func;

import java.nio.file.Path;

public class FunctionsUtil {
	public static Path getTmpPath(Path path) {
		return path.getParent().resolve(path.getFileName() + ".tmp");
	}
}
