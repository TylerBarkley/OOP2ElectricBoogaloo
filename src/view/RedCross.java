package view;

import java.io.IOException;

import javax.imageio.ImageIO;

import model.ID;
import model.Location;

public class RedCross extends Decal 
{
	public RedCross(ID id, Location loc)  
	{
		super(id, loc);
		
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
