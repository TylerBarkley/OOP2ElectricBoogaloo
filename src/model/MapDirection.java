package model;

public class MapDirection 
{
	private static MapDirection N;
	private static MapDirection S;
	private static MapDirection NE;
	private static MapDirection SE;
	private static MapDirection NW;
	private static MapDirection SW;

	private int dx;
	private int dy;

	private MapDirection(int dx, int dy) 
	{
		this.dx = dx;
		this.dy = dy;
	}

	public int getDx() 
	{
		return dx;
	}

	public int getDy() 
	{
		return dy;
	}

	public double getTheta()
	{
		double theta=Math.PI/3;
		
		switch(getDx()){
		case -1:
			switch(getDy()){
			case 0:
				theta*=1;
				break;
			case 1:
				theta*=2;
				break;
			}
			break;
		case 0:
			switch(getDy()){
			case -1:
				theta*=0;
				break;
			case 1:
				theta*=3;
				break;
			}
			break;
		case 1:
			switch(getDy()){
			case -1:
				theta*=5;
				break;
			case 0:
				theta*=4;
				break;
			}
			break;
		}

		
		return theta;
	}

	public static MapDirection getNorth()
	{
		if(N==null)
		{
			N = new MapDirection(0, -1);
		}
		return N;
	}

	public static MapDirection getSouth()
	{
		if(S==null)
		{
			S = new MapDirection(0, 1);
		}
		return S;
	}

	public static MapDirection getNorthEast()
	{
		if(NE==null)
		{
			NE = new MapDirection(1, -1);
		}
		return NE;
	}

	public static MapDirection getSouthEast()
	{
		if(SE==null)
		{
			SE = new MapDirection(1, 0);
		}
		return SE;
	}

	public static MapDirection getNorthWest()
	{
		if(NW==null)
		{
			NW = new MapDirection(-1, 0);
		}
		return NW;
	}

	public static MapDirection getSouthWest()
	{
		if(SW==null)
		{
			SW = new MapDirection(-1, 1);
		}
		return SW;
	}
}
