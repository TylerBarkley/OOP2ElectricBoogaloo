package view;

import java.io.IOException;

import javax.imageio.ImageIO;

import model.Location;

public class RedCross extends Decal 
{
	public RedCross(Location loc)  
	{
		super(loc);
		
		try 
		{
			setImage(ImageIO.read(getClass().getResource("/RedCross.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
