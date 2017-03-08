package view;

import java.io.IOException;

import javax.imageio.ImageIO;

import model.ID;
import model.Location;

public class SkullCrossbones extends Decal 
{
	public SkullCrossbones(ID id, Location loc)  
	{
		super(id, loc);
		
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
