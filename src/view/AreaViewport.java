package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JPanel;

import model.Location;
import model.MapDirection;

public class AreaViewport extends JPanel
{
	private static final int xOffset=(int)(TileView.TILE_SIZE*Math.cos(Math.PI/3));
	private static final int yOffset=(int)(TileView.TILE_SIZE*Math.sin(Math.PI/3));

	private ArrayList<View> views;

	private HashMap<Location, TileView> mapView;
	private HashMap<Location, CompositeView> resources;
	private int mapWidth;
	private int mapHeight;

	private Location focus;
	private FocusView focusView;

	private BufferedImage image;
	private Graphics2D g2d;

	private boolean resourceViewVisible;
	
	public AreaViewport(int width, int height)
	{
		setSize(width, height);

		views=new ArrayList<View>();

		resources=new HashMap<Location, CompositeView>();

		focus=new Location(0,0);
		focusView=new FocusView(focus);
		views.add(focusView);

		image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		g2d=image.createGraphics();

		resourceViewVisible=false;
		
		displayView();
	}

	public void setBlankMap(Set<Location> mapLocations, int width, int height)
	{
		mapView = new HashMap<Location, TileView>();

		ViewFactory factory=ViewFactory.getFactory();
		for(Location loc: mapLocations)
		{
			if(mapView.get(loc)==null)
			{
				mapView.put(loc, (TileView)factory.getView("UnknownTerrain", loc, 0));
			}
		}

		mapWidth=width;
		mapHeight=height;
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
		focus = loc.wrapAround(mapWidth, mapHeight);
		focusView.setLocation(loc);
		updateView();
	}

	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(image,0,0,null);
	}
	
	public void setViews(ArrayList<View> views)
	{
		this.views=views;
		this.views.add(focusView);
		updateView();
	}

	public void addView(View view)
	{
		views.add(view);
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

	private Location getTopLeftCornerOfDisplay() 
	{
		int mapDisplayWidth=getMapDisplayWidth();
		int mapDisplayHeight=getMapDisplayHeight();

		int i=(mapDisplayWidth-1)/2;
		int j=mapDisplayHeight/2;

		Location loc=focus;

		while(i>0){
			loc = loc.getAdjacent(MapDirection.getNorthWest(), mapWidth, mapHeight);
			i--;
			j--;
		}

		while(j>0){
			loc = loc.getAdjacent(MapDirection.getNorth(), mapWidth, mapHeight);
			j-=2;
		}


		while(j<0){
			loc = loc.getAdjacent(MapDirection.getSouth(), mapWidth, mapHeight);
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
			topOfDiagonal=topOfDiagonal.getAdjacent(ne).getAdjacent(se, mapWidth, mapHeight);
		}

		topOfDiagonal=topLeftCorner.getAdjacent(s, mapWidth, mapHeight);
		pixelX=0;
		pixelY=yOffset;

		while(pixelY + TileView.TILE_SIZE < getHeight())
		{
			drawDiagonal(topOfDiagonal, pixelX, pixelY);
			pixelY+= yOffset;
			topOfDiagonal=topOfDiagonal.getAdjacent(s, mapWidth, mapHeight);
		}

	}

	private void drawDiagonal(Location topOfDiagonal, int pixelX, int pixelY) 
	{
		MapDirection se=MapDirection.getSouthEast();

		Location loc=topOfDiagonal;

		while(pixelX + TileView.TILE_SIZE < getWidth() && pixelY + TileView.TILE_SIZE < getHeight())
		{
			drawViewAt(loc, pixelX, pixelY);
			pixelX+= TileView.TILE_SIZE-xOffset/2;
			pixelY+= yOffset/2;
			loc=loc.getAdjacent(se, mapWidth, mapHeight);
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
		loc = loc.wrapAround(mapWidth, mapHeight);

		for(View view: views)
		{
			if(view.getLocation().wrapAround(mapWidth, mapHeight).equals(loc))
			{
				desiredViews.add(view);
			}
		}

		return desiredViews;
	}
}
