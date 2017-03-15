package view;

import java.awt.Graphics2D;
import java.util.ArrayList;

import model.ID;
import model.Location;

public class CompositeView extends View 
{
	private ArrayList<View> views;
	
	public CompositeView(ID id, Location loc) 
	{
		super(new ID());
		setLocation(loc);
		this.views=new ArrayList<View>();
	}

	public void draw(Graphics2D g2d, int x, int y) 
	{
		for(View view: views)
		{
			view.draw(g2d, x, y);
		}
	}

	public void add(View view) 
	{
		views.add(view);
	}
}
