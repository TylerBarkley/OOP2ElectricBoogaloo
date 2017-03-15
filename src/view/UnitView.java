package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.ID;
import model.Location;

public class UnitView extends ImageView {

	public UnitView(ID id, BufferedImage image, Location loc, int rotation) {
		super(id, image, loc, rotation);
	}

	public UnitView(ID id, BufferedImage image, Location loc) {
		super(id, image, loc);
	}

	public void draw(Graphics2D g2d, int x, int y)
	{
		int width=getImage().getWidth();
		int height=getImage().getHeight();
		
		double theta = Math.toRadians(getRotation() - 90);
		
		x+=(TileView.TILE_SIZE-width)/2 + (int)(Math.cos(theta)*(TileView.TILE_SIZE/2-width));
		y+=(TileView.TILE_SIZE-height)/2 + (int)(Math.sin(theta)*(TileView.TILE_SIZE/2-width));
		
		g2d.drawImage(getImage(), x, y, null);
	}
}
