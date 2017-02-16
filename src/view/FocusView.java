package view;

import java.io.IOException;

import javax.imageio.ImageIO;

import model.Location;

public class FocusView extends View {
	public FocusView(Location loc)  
	{
		super(loc);
		
		try 
		{
			setImage(ImageIO.read(getClass().getResource("/Focus.png")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setLocation(Location loc){
		super.setLocation(loc);
	}
}
