package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import model.Location;

public abstract class ResourceView extends View{
	private int quantity;
	private Color textColor;
	
	private Font font;
	
	
	public ResourceView(BufferedImage image, Location loc, int quantity, Color textColor) 
	{
		super(image, loc);
		this.quantity=quantity;
		font = new Font("Times New Roman", Font.BOLD, 20);
		this.textColor=textColor;
	}

	public void draw(Graphics2D g2d, int x, int y)
	{
		int width=getImage().getWidth();
		int height=getImage().getHeight();
		
		x+=(TileView.TILE_SIZE-width)/2;
		y+=(TileView.TILE_SIZE-height)/2;
		
		g2d.drawImage(getImage(), x, y, null);
		
		drawText(g2d, x, y);
	}

	protected void drawText(Graphics2D g2d, int x, int y)
	{
		g2d.setColor(textColor);
		g2d.setFont(getFont());
		g2d.drawString(""+getQuantity(), x, y);
	}
	
	protected int getQuantity() {
		return quantity;
	}

	protected Font getFont() {
		return font;
	}

	protected Color getTextColor() {
		return textColor;
	}
	
	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	protected void setFont(Font font) {
		this.font = font;
	}

	protected void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
}
