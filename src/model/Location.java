package model;

public class Location 
{
	private int x;
	private int y;

	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Location getAdjacent(MapDirection md)
	{
		int newX = x + md.getDx();
		int newY = y + md.getDy();

		return new Location(newX, newY);
	}

	public boolean equals(Location target)
	{
		return x == target.x && y == target.y;
	}

	public boolean isValid(int maxX, int maxY)
	{
		return (x >= 0) && (x < maxX) 
				&& (y >= 0) && (y < maxY);
	}
}
