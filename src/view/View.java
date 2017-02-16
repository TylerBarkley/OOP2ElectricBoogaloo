package view;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import model.Location;
import model.MapDirection;

public class View 
{
	private BufferedImage image;
	private Location location;
	private int rotation;

	public View(BufferedImage image, Location loc, int rotation)
	{
		this.image=image;
		this.setLocation(loc);
		this.rotation=rotation;
	}

	public View(BufferedImage image, Location loc)
	{
		this(image, loc, 0);
	}

	protected View(Location loc)
	{
		this(null, loc, 0);
	}

	protected void setImage(BufferedImage image) 
	{
		this.image=image;
	}

	public BufferedImage getImage()
	{
		AffineTransform tx = new AffineTransform();

		tx.translate(image.getHeight() / 2,image.getWidth() / 2);

		double theta= rotation * Math.PI / 180;

		tx.rotate(theta);

		tx.translate(-image.getWidth() / 2,-image.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		BufferedImage newImage =new BufferedImage(image.getHeight(), image.getWidth(), image.getType());
		op.filter(image, newImage);

		return image;
	}

	public int getRotation() 
	{
		return rotation;
	}

	protected void setRotation(int rotation) 
	{
		this.rotation = rotation;
	}

	public Location getLocation() {
		return location;
	}

	protected void setLocation(Location location) {
		this.location = location;
	}
}
