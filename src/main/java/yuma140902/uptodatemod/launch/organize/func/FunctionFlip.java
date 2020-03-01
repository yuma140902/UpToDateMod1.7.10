package yuma140902.uptodatemod.launch.organize.func;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;

public class FunctionFlip implements IFunction {
	
	FunctionFlip(){}

	@Override
	public void handle(Path src, Path dest) throws IOException {
		Path tmp = FunctionsUtil.getTmpPath(dest);
		Files.copy(src, tmp, StandardCopyOption.REPLACE_EXISTING);
		
		BufferedImage inBuff = ImageIO.read(tmp.toFile());
		AffineTransform flip = AffineTransform.getTranslateInstance(0.0, 0.0);
		flip.concatenate(AffineTransform.getTranslateInstance(inBuff.getWidth(), 0.0));
		flip.concatenate(AffineTransform.getScaleInstance(-1.0, 1.0));
		
		AffineTransformOp transformOp = new AffineTransformOp(flip, AffineTransformOp.TYPE_BICUBIC);
		BufferedImage outBuff = new BufferedImage(inBuff.getWidth(), inBuff.getHeight(), inBuff.getType());
		
		transformOp.filter(inBuff, outBuff);
		ImageIO.write(outBuff, "png", dest.toFile());
		
		Files.delete(tmp);
	}
	
	
}
