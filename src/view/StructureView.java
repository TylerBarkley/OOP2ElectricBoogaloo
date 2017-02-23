package view;

import java.awt.Graphics2D;
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

	public void draw(Graphics2D g2d, int x, int y)
	{
		int width=getImage().getWidth();
		int height=getImage().getHeight();
		
		x+=(TileView.TILE_SIZE-width)/2;
		y+=(TileView.TILE_SIZE-height)/2;
		
		g2d.drawImage(getImage(), x, y, null);
	}
}
