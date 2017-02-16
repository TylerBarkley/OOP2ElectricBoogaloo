package view;

import java.awt.image.BufferedImage;

import model.Location;

public class StructureView extends View 
{

	public StructureView(BufferedImage image, Location loc, int rotation) 
	{
		super(image, loc, rotation);
	}

	public StructureView(BufferedImage image, Location loc) 
	{
		super(image, loc);
	}

}
