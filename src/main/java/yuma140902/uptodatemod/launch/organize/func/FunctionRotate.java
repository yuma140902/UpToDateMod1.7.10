package yuma140902.uptodatemod.launch.organize.func;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;

public class FunctionRotate implements IFunction {
	
	private int angle;
	private AffineTransform transform;
	
	/**
	 * @param angle 回転させる角度。90、180または270
	 */
	FunctionRotate(int angle) {
		this.angle = angle;
		this.transform = new AffineTransform();
	}

	@Override
	public void handle(Path src, Path dest) throws IOException {
		Path tmp = FunctionsUtil.getTmpPath(dest);
		Files.copy(src, tmp, StandardCopyOption.REPLACE_EXISTING);
		
		// see: https://teratail.com/questions/42300
		BufferedImage inBuff = ImageIO.read(tmp.toFile());
		if(this.angle == 90) {
			this.transform.setToRotation(Math.toRadians(90), inBuff.getHeight()/2, inBuff.getHeight()/2);
		}
		else if(this.angle == 180) {
			this.transform.setToRotation(Math.toRadians(180), inBuff.getWidth()/2, inBuff.getHeight()/2);
		}
		else if(this.angle == 270) {
			this.transform.setToRotation(Math.toRadians(270), inBuff.getWidth()/2, inBuff.getWidth()/2);
		}
		
		BufferedImage outBuff = null;
		if(this.angle == 90 || this.angle == 270) {
			outBuff = new BufferedImage(inBuff.getHeight(), inBuff.getWidth(), inBuff.getType());
		}
		else if(this.angle == 180) {
			outBuff = new BufferedImage(inBuff.getWidth(), inBuff.getHeight(), inBuff.getType());
		}
		
		AffineTransformOp transformOp = new AffineTransformOp(this.transform, AffineTransformOp.TYPE_BICUBIC);
		transformOp.filter(inBuff, outBuff);
		ImageIO.write(outBuff, "png", dest.toFile());
		
		Files.delete(tmp);
	}
	
}
