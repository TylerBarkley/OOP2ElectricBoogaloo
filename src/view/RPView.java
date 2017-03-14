package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.ID;
import model.Location;

public class RPView extends ImageView{

	public RPView(ID id, BufferedImage image, Location loc) {
		super(id, image, loc);
	}

	public void draw(Graphics2D g2d, int x, int y)
	{
		int width=getImage().getWidth();
		int height=getImage().getHeight();
		
		x+=width;
		y+=(TileView.TILE_SIZE-height)/2;
		
		g2d.drawImage(getImage(), x, y, null);
	}
}
