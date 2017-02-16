package view;

import java.awt.image.BufferedImage;

import model.Location;

public class TileView extends View
{

	public TileView(BufferedImage image, Location loc, int rotation) 
	{
		super(image, loc, rotation);
	}

	public TileView(BufferedImage image, Location loc) 
	{
		super(image, loc);
	}

}
