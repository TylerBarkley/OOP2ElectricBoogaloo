package view;

import java.io.IOException;

import javax.imageio.ImageIO;

import model.ID;
import model.Location;

public class FocusView extends ImageView {
	public FocusView(ID id, Location loc)  
	{
		super(id, loc);
		
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
