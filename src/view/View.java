package view;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import model.MapDirection;

public class View {
	private BufferedImage image;
	
	public View(BufferedImage image){
		this.image=image;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public BufferedImage getImage(MapDirection direction){
		AffineTransform tx = new AffineTransform();

		tx.translate(image.getHeight() / 2,image.getWidth() / 2);
		
		double theta= direction.getTheta();
		
		tx.rotate(theta);
		
		tx.translate(-image.getWidth() / 2,-image.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		BufferedImage newImage =new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
		op.filter(image, newImage);
		
		return image;
	}
}
