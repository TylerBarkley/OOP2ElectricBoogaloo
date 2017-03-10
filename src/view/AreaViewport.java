package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JPanel;

import model.ID;
import model.Location;
import model.MapDirection;
import utilities.AreaViewportVisitor;
import utilities.ViewVisitor;

public class AreaViewport extends JPanel
{
	private static final int xOffset=(int)(TileView.TILE_SIZE*Math.cos(Math.PI/3));
	private static final int yOffset=(int)(TileView.TILE_SIZE*Math.sin(Math.PI/3));

	private HashMap<ID, View> views;

	private HashMap<Location, TileView> mapView;
	private HashMap<Location, CompositeView> resources;

	private Location focus;
	private FocusView focusView;
	private ID focusID;
	
	private BufferedImage image;
	private Graphics2D g2d;

	private boolean resourceViewVisible;
	
	public AreaViewport(int width, int height)
	{
		setSize(width, height);

		initializeFields();
		
		displayView();
	}

	private void initializeFields() {
		views=new HashMap<ID, View>();

		resources=new HashMap<Location, CompositeView>();

		focus=new Location(0,0);
		focusID=new ID();
		focusView=new FocusView(focusID, focus);

		views.put(focusID, focusView);
		
		image=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		g2d=image.createGraphics();

		resourceViewVisible=false;
	}

	public void setBlankMap(Set<Location> mapLocations)
	{
		mapView = new HashMap<Location, TileView>();

		ViewFactory factory=ViewFactory.getFactory();
		for(Location loc: mapLocations)
		{
			if(mapView.get(loc)==null)
			{
				mapView.put(loc, (TileView)factory.getView(new ID(), "UnknownTerrain", loc, 0));
			}
		}

		updateView();
	}

	public void updateMapView(Location loc, TileView view)
	{
		mapView.put(loc, view);
		updateView();
	}

	public void updateMapView(HashMap<Location, TileView> views)
	{
		mapView.putAll(views);
		updateView();
	}

	public void updateResourceView(Location loc, CompositeView view)
	{
		resources.put(loc, view);
		updateView();
	}

	public void updateResourceView(HashMap<Location, CompositeView> views)
	{
		resources.putAll(views);
		updateView();
	}

	public void toggleResourceView()
	{
		resourceViewVisible=!resourceViewVisible;
		updateView();
	}
	
	public void enableResourceView()
	{
		resourceViewVisible=true;
		updateView();
	}

	public void disableResourceView()
	{
		resourceViewVisible=false;
		updateView();
	}
	
	public void focusOn(Location loc)
	{
		focus = loc;
		focusView.setLocation(loc);
		updateView();
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(image,0,0,null);
	}
	
	public void setViews(HashMap<ID, View> views)
	{
		this.views=views;
		
		views.put(focusID, focusView);
		updateView();
	}

	public void addView(View view)
	{
		views.put(view.getID(), view);
		updateView();
	}

	public void displayView()
	{
		setBackground(Color.BLACK);
		updateView();
	}

	public void updateView()
	{
		int width=getWidth();
		int height=getHeight();

		image=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2d=image.createGraphics();
		g2d.clearRect(0, 0, width, height);

		if(mapView !=null)
		{
			displayMap();
		}

		repaint();
	}

	public AreaViewportMomento saveToMomento()
	{
		AreaViewportMomento momento = new AreaViewportMomento(views, mapView, resources, focus, focusView, focusID, image, g2d, resourceViewVisible);
		initializeFields();
		return momento;
	}
	
	public void restoreFromMomento(AreaViewportMomento momento)
	{
		views=momento.getViews();

		resources=momento.getResources();

		focus=momento.getFocus();
		focusID=momento.getFocusID();
		focusView=momento.getFocusView();
		
		image=momento.getImage();
		g2d=momento.getG2d();

		resourceViewVisible=momento.isResourceViewVisible();
	}
	
	private Location getTopLeftCornerOfDisplay() 
	{
		int mapDisplayWidth=getMapDisplayWidth();
		int mapDisplayHeight=getMapDisplayHeight();

		int i=(mapDisplayWidth-1)/2;
		int j=mapDisplayHeight/2;

		Location loc=focus;
		Location temp=loc;
		while(i>0){
			temp = loc.getAdjacent(MapDirection.getNorthWest());
			loc=temp;
			i--;
			j--;
		}

		while(j>0){
			temp = loc.getAdjacent(MapDirection.getNorth());
			loc=temp;
			j-=2;
		}


		while(j<0){
			temp = loc.getAdjacent(MapDirection.getSouth());
			loc=temp;
			j+=2;
		}

		return loc;
	}

	private int getMapDisplayWidth()
	{
		double offset=TileView.TILE_SIZE*Math.cos(Math.PI/3)/2;
		return (int)((getWidth()-offset)/(TileView.TILE_SIZE-offset));
	}

	private int getMapDisplayHeight()
	{
		double offset=TileView.TILE_SIZE*Math.sin(Math.PI/3)/2;
		return (int)((getHeight()-offset)/offset);
	}

	private void displayMap()
	{
		MapDirection ne=MapDirection.getNorthEast();
		MapDirection se=MapDirection.getSouthEast();
		MapDirection s=MapDirection.getSouth();

		Location topLeftCorner=getTopLeftCornerOfDisplay();

		Location topOfDiagonal=topLeftCorner;

		int xOffset=(int)(TileView.TILE_SIZE*Math.cos(Math.PI/3));
		int yOffset=(int)(TileView.TILE_SIZE*Math.sin(Math.PI/3));

		int pixelX=0;
		int pixelY=0;

		while(pixelX + TileView.TILE_SIZE < getWidth())
		{
			drawDiagonal(topOfDiagonal, pixelX, pixelY);
			pixelX+= 2*(TileView.TILE_SIZE)-xOffset;
			topOfDiagonal=topOfDiagonal.getAdjacent(ne).getAdjacent(se);
		}

		topOfDiagonal=topLeftCorner.getAdjacent(s);
		pixelX=0;
		pixelY=yOffset;

		while(pixelY + TileView.TILE_SIZE < getHeight())
		{
			drawDiagonal(topOfDiagonal, pixelX, pixelY);
			pixelY+= yOffset;
			topOfDiagonal=topOfDiagonal.getAdjacent(s);
		}

	}

	private void drawDiagonal(Location topOfDiagonal, int pixelX, int pixelY) 
	{
		MapDirection se=MapDirection.getSouthEast();

		Location loc=topOfDiagonal;

		while(pixelX + TileView.TILE_SIZE < getWidth() && pixelY + TileView.TILE_SIZE < getHeight())
		{
			if(mapView.get(loc) != null)
			{
				drawViewAt(loc, pixelX, pixelY);
			}

			pixelX+= TileView.TILE_SIZE-xOffset/2;
			pixelY+= yOffset/2;
			loc=loc.getAdjacent(se);
		}
	}

	private void drawViewAt(Location loc, int pixelX, int pixelY)
	{
		mapView.get(loc).draw(g2d, pixelX, pixelY);

		for(View view: getViewsAt(loc))
		{
			view.draw(g2d, pixelX, pixelY);
		}
		
		CompositeView resourcesView=resources.get(loc);
		if(resourceViewVisible && resourcesView != null)
		{
			resourcesView.draw(g2d, pixelX, pixelY);
		}
	}	

	private ArrayList<View> getViewsAt(Location loc)
	{
		ArrayList<View> desiredViews=new ArrayList<View>();

		for(View view: views.values())
		{
			if(view.getLocation().equals(loc))
			{
				desiredViews.add(view);
			}
		}

		return desiredViews;
	}

	public void accept(AreaViewportVisitor visitor) {
		visitor.visit(this);
	}
}
