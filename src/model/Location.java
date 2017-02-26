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

	public Location getAdjacent(MapDirection md, int width, int height)
	{
		return getAdjacent(md).wrapAround(width, height);
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
	
	public ArrayList<Location> getLocationsAtRadius(int radius, int width, int height)
	{
		ArrayList<Location> locs=new ArrayList<Location>();
		MapDirection[] mds = {MapDirection.getSouthEast(), MapDirection.getSouth(), 
				MapDirection.getSouthWest(), MapDirection.getNorthWest(), 
				MapDirection.getNorth(), MapDirection.getNorthEast()};
		
		Location loc=this;
		
		for(int i=0; i<radius; i++)
		{
			loc = loc.getAdjacent(MapDirection.getNorth(), width, height);
		}
		
		for(int i=0; i<mds.length; i++)
		{
			for(int j=0; j<radius; j++)
			{
				locs.add(loc);
				loc = loc.getAdjacent(mds[i], width, height);
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
	
	public ArrayList<Location> getAllLocationsWithinRadius(int radius, int width, int height)
	{
		ArrayList<Location> locs=new ArrayList<Location>();
		
		for(int i=0; i<radius; i++)
		{
			locs.addAll(getLocationsAtRadius(radius, width, height));
		}
		
		return locs;
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
    
    public String toString()
    {
        return "("+x+", "+y+")";
    }
}
