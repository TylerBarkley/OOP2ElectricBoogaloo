package view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.ID;
import model.Location;

public class OreView extends ResourceView {

	public OreView(ID id, BufferedImage image, Location loc, int quantity) {
		super(id, image, loc, quantity, Color.BLACK);
	}

	public void draw(Graphics2D g2d, int x, int y)
	{
		FontMetrics fm = g2d.getFontMetrics(getFont());
		x-=fm.stringWidth(""+getQuantity())/2;
		x-=getImage().getWidth()/4;
		super.draw(g2d, x, y+40);
	}
	
	protected void drawText(Graphics2D g2d, int x, int y) 
	{		
		FontMetrics fm = g2d.getFontMetrics(getFont());
		x+=getImage().getWidth();
		y+=fm.getHeight()/4;
		y+=getImage().getHeight()/2;
		super.drawText(g2d, x, y);
	}
}
