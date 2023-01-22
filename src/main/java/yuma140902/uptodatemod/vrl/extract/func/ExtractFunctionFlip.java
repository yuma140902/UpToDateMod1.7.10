package yuma140902.uptodatemod.vrl.extract.func;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ExtractFunctionFlip implements IExtractFunction {
	
	@Override
	public void handle(Path src, Path dst) throws IOException {
		try (TemporaryFile temporaryFile = new TemporaryFile(dst)) {
			Path tmp = temporaryFile.getPath();
			Files.copy(src, tmp, StandardCopyOption.REPLACE_EXISTING);

			BufferedImage inBuff = ImageIO.read(tmp.toFile());
			AffineTransform flip = AffineTransform.getTranslateInstance(0.0, 0.0);
			flip.concatenate(AffineTransform.getTranslateInstance(inBuff.getWidth(), 0.0));
			flip.concatenate(AffineTransform.getScaleInstance(-1.0, 1.0));

			AffineTransformOp transformOp = new AffineTransformOp(flip, AffineTransformOp.TYPE_BICUBIC);
			BufferedImage outBuff = new BufferedImage(inBuff.getWidth(), inBuff.getHeight(), inBuff.getType());

			transformOp.filter(inBuff, outBuff);
			ImageIO.write(outBuff, "png", dst.toFile());
		}
	}
	
	
}
