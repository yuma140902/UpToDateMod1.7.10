package yuma140902.uptodatemod.launch.organize.func;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FunctionOnlyCopy implements IFunction {

	FunctionOnlyCopy(){}
	
	@Override
	public void handle(Path src, Path dest) throws IOException {
		Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
	}
	
}
