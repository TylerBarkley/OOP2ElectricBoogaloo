package view;

import java.awt.Graphics2D;

import model.Location;

public abstract class View {
	private Location location;
	
	public abstract void draw(Graphics2D g2d, int x, int y);
	
	public Location getLocation(){
		return location;
	}
	
	protected void setLocation(Location location) {
		this.location = location;
	}
}
