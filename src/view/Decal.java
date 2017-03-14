package view;

import java.awt.Graphics2D;

import model.ID;
import model.Location;

public abstract class Decal extends ImageView
{
	protected Decal(ID id, Location loc) 
	{
		super(id, loc);
	}
	
	public void draw(Graphics2D g2d, int x, int y)
	{
		int width=getImage().getWidth();
		int height=getImage().getHeight();
		
		x+=TileView.TILE_SIZE-2*width;
		y+=(TileView.TILE_SIZE-height)/2;
		
		g2d.drawImage(getImage(), x, y, null);
	}
}