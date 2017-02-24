package view;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class CompositeView extends View 
{
	private ArrayList<View> views;

	public CompositeView(ArrayList<View> views)
	{
		this.views=views;
	}
	
	public CompositeView() 
	{
		this(new ArrayList<View>());
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
