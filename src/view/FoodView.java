package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.ID;
import model.Location;

public class FoodView extends ResourceView{

	public FoodView(ID id, BufferedImage image, Location loc, int quantity) {
		super(id, image, loc, quantity, Color.RED.brighter());
	}
	
	public void draw(Graphics2D g2d, int x, int y)
	{
		super.draw(g2d, x-40, y);
	}

	protected void drawText(Graphics2D g2d, int x, int y) 
	{		
		FontMetrics fm = g2d.getFontMetrics(getFont());
		x-=fm.stringWidth(""+getQuantity());
		y+=fm.getHeight()/4;
		y+=getImage().getHeight()/2;
		super.drawText(g2d, x, y);
	}
}
