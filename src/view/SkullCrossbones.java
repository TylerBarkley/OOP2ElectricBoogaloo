package view;

import java.io.IOException;

import javax.imageio.ImageIO;

import model.Location;

public class SkullCrossbones extends Decal 
{
	public SkullCrossbones(Location loc)  
	{
		super(loc);
		
		try 
		{
			setImage(ImageIO.read(getClass().getResource("/SkullCrossBones.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
