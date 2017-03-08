package view;

import model.ID;
import model.Location;

public abstract class Decal extends ImageView
{
	protected Decal(ID id, Location loc) 
	{
		super(id, loc);
	}
}