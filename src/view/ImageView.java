package view;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import model.ID;
import model.Location;

public class ImageView extends View
{
	private BufferedImage image;
	private int rotation;

	public ImageView(ID id, BufferedImage image, Location loc, int rotation)
	{
		super(id);
		this.image=image;
		this.setLocation(loc);
		this.rotation=rotation;
	}

	public ImageView(ID id, BufferedImage image, Location loc)
	{
		this(id, image, loc, 0);
	}

	protected ImageView(ID id, Location loc)
	{
		this(id, null, loc, 0);
	}

	protected void setImage(BufferedImage image) 
	{
		this.image=image;
	}

	protected BufferedImage getImage()
	{
		AffineTransform tx = new AffineTransform();

		tx.translate(image.getHeight() / 2,image.getWidth() / 2);

		double theta= Math.toRadians(rotation);

		tx.rotate(theta);

		tx.translate(-image.getWidth() / 2,-image.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		BufferedImage newImage =new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
		op.filter(image, newImage);

		return newImage;
	}

	public void draw(Graphics2D g2d, int x, int y)
	{
		g2d.drawImage(getImage(), x, y, null);
	}
	
	public int getRotation() 
	{
		return rotation;
	}

	protected void setRotation(int rotation) 
	{
		this.rotation = rotation;
	}
}
