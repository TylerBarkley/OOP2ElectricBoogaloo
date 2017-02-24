package view;

import java.awt.image.BufferedImage;

import model.Location;

public class TileView extends ImageView
{
	public static final int TILE_SIZE=200;
	
	public TileView(BufferedImage image, Location loc, int rotation) 
	{
		super(image, loc, rotation);
		if(image.getHeight() != TILE_SIZE || image.getWidth() != TILE_SIZE)
		{
			System.err.println("Image is not the correct size.\n" + new Throwable().getStackTrace());
		}
	}

	public TileView(BufferedImage image, Location loc) 
	{
		super(image, loc, 60*(int)(Math.random()*6));
	}

}
