package yuma140902.uptodatemod.vrl.extract.func;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ExtractFunctionRotate implements IExtractFunction {
	
	private int angle;
	private AffineTransform transform;
	
	/**
	 * @param angle 回転させる角度。90、180または270
	 */
	public ExtractFunctionRotate(int angle) {
		this.angle = angle;
		this.transform = new AffineTransform();
	}

	@Override
	public void handle(Path src, Path dst) throws IOException {
		try(TemporaryFile temporaryFile = new TemporaryFile(dst)) {
			Path tmp = temporaryFile.getPath();
			Files.copy(src, tmp, StandardCopyOption.REPLACE_EXISTING);

			// see: https://teratail.com/questions/42300
			BufferedImage inBuff = ImageIO.read(tmp.toFile());
			if (this.angle == 90) {
				this.transform.setToRotation(Math.toRadians(90), inBuff.getHeight() / 2.0, inBuff.getHeight() / 2.0);
			} else if (this.angle == 180) {
				this.transform.setToRotation(Math.toRadians(180), inBuff.getWidth() / 2.0, inBuff.getHeight() / 2.0);
			} else if (this.angle == 270) {
				this.transform.setToRotation(Math.toRadians(270), inBuff.getWidth() / 2.0, inBuff.getWidth() / 2.0);
			}

			BufferedImage outBuff = null;
			if (this.angle == 90 || this.angle == 270) {
				outBuff = new BufferedImage(inBuff.getHeight(), inBuff.getWidth(), inBuff.getType());
			} else if (this.angle == 180) {
				outBuff = new BufferedImage(inBuff.getWidth(), inBuff.getHeight(), inBuff.getType());
			}

			AffineTransformOp transformOp = new AffineTransformOp(this.transform, AffineTransformOp.TYPE_BICUBIC);
			transformOp.filter(inBuff, outBuff);
			ImageIO.write(outBuff, "png", dst.toFile());
		}
	}
	
}
