package model;

import java.util.Objects;

public class Location 
{
	private int x;
	private int y;

	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Location getAdjacent(MapDirection md)
	{
		int newX = x + md.getDx();
		int newY = y + md.getDy();

		return new Location(newX, newY);
	}

	public Location getAdjacent(MapDirection md, int width, int height)
	{
		return getAdjacent(md).wrapAround(width, height);
	}
	
	public Location wrapAround(int width, int height)
	{
		Location loc=new Location(x,y);
		while(!loc.isValid(width, height)){
			loc.x = (loc.x + width) % width;
			loc.y = (loc.y + height) % height;
		}
		
		return loc;
	}
	
	public boolean isValid(int width, int height)
	{
		return (x >= 0) && (x < width) 
				&& (y >= 0) && (y < height);
	}
	
	public boolean equals(Object o)
	{
		if(!(o instanceof Location))
		{
			return false;
		}
		
		Location target = (Location)o;
		return x == target.x && y == target.y;
	}
	
	public int hashCode(){
		return Objects.hash(x, y);
	}
}
