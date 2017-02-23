package view;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.Location;

public class EnergyView extends ResourceView{

	public EnergyView(BufferedImage image, Location loc, int quantity) {
		super(image, loc, quantity, Color.YELLOW);
	}

	public void draw(Graphics2D g2d, int x, int y)
	{
		super.draw(g2d, x+40, y);
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
