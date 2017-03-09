package view;

import java.awt.Graphics2D;

import model.ID;
import model.Location;

public abstract class View {
	private Location location;
	private ID id;
	
	public View(ID id){
		this.id=id;
	}
	
	public abstract void draw(Graphics2D g2d, int x, int y);
	
	public Location getLocation(){
		return location;
	}
	
	public ID getID(){
		return id;
	}
	
	protected void setLocation(Location location) {
		this.location = location;
	}
}
