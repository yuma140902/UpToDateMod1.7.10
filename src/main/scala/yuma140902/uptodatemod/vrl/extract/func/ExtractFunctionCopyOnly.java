package yuma140902.uptodatemod.vrl.extract.func;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ExtractFunctionCopyOnly implements IExtractFunction {

	@Override
	public void handle(Path src, Path dst) throws IOException {
		Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
	}
	
}
