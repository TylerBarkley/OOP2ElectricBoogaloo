package model;

import java.util.ArrayList;
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

	
	public ArrayList<Location> getLocationsAtRadius(int radius)
	{
		ArrayList<Location> locs=new ArrayList<Location>();
		MapDirection[] mds = {MapDirection.getSouthEast(), MapDirection.getSouth(), 
				MapDirection.getSouthWest(), MapDirection.getNorthWest(), 
				MapDirection.getNorth(), MapDirection.getNorthEast()};
		
		Location loc=this;
		
		for(int i=0; i<radius; i++)
		{
			loc = loc.getAdjacent(MapDirection.getNorth());
		}
		
		for(int i=0; i<mds.length; i++)
		{
			for(int j=0; j<radius; j++)
			{
				locs.add(loc);
				loc = loc.getAdjacent(mds[i]);
			}
		}
		
		return locs;
	}
	
	public ArrayList<Location> getAllLocationsWithinRadius(int radius)
	{
		ArrayList<Location> locs=new ArrayList<Location>();
		
		for(int i=0; i<radius; i++)
		{
			locs.addAll(getLocationsAtRadius(radius));
		}
		
		return locs;
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
    
    public String toString()
    {
        return "("+x+", "+y+")";
    }

    public MapDirection getDirectionTo(Location location) {
		return new MapDirection(location.getX() - this.x, location.getY() - this.y);
    }
}
