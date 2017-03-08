package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.ID;
import model.Location;
import model.StructureID;

public class StructureView extends ImageView 
{

	public StructureView(ID id, BufferedImage image, Location loc, int rotation) 
	{
		super(id, image, loc, rotation);
	}

	public StructureView(StructureID id, BufferedImage image, Location loc) 
	{
		super(id, image, loc);
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
