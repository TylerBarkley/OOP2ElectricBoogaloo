package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.Location;
import model.MapDirection;

public class AreaViewport extends JPanel
{
	private static final int TILE_SIZE=150;
	
	private ArrayList<View> views;
	
	private TileView[][] blankMapView;
	private int mapWidth;
	private int mapHeight;
	
	private Location focus;
	private FocusView focusView;
	
	private BufferedImage image;
	private Graphics2D g2d;

	public AreaViewport(int width, int height)
	{
		setSize(width, height);
		
		views=new ArrayList<View>();
		
		focus=new Location(0,0);
		focusView=new FocusView(focus);
		views.add(focusView);
		
		image=new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		g2d=image.createGraphics();
		
		displayView();
	}

	public void setMap(TileView[][] mapView)
	{
		blankMapView=mapView;
		mapWidth=mapView[0].length;
		mapHeight=mapView.length;
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
		
		if(blankMapView !=null)
		{
			displayMap();
		}
		
		repaint();
	}

	private Location getTopLeftCornerOfDisplay() 
	{
		int mapDisplayWidth=getMapDisplayWidth();
		int mapDisplayHeight=getMapDisplayHeight();

		int i=mapDisplayWidth/2;
		int j=mapDisplayHeight/2;
		
		Location loc=focus;
		
		while(i>0){
			loc = loc.getAdjacent(MapDirection.getNorthWest(), mapWidth, mapHeight);
			i--;
			j--;
		}
		
		while(j>0){
			loc = loc.getAdjacent(MapDirection.getNorth(), mapWidth, mapHeight);
			j--;
		}
		
		
		while(j<0){
			loc = loc.getAdjacent(MapDirection.getSouth(), mapWidth, mapHeight);
			j++;
		}
		
		return loc;
	}

	private int getMapDisplayWidth()
	{
		return getWidth()*5/TILE_SIZE/4;
	}
	
	private int getMapDisplayHeight()
	{
		return getHeight()*5/TILE_SIZE/4;
	}
	
	private void displayMap()
	{
		MapDirection ne=MapDirection.getNorthEast();
		MapDirection se=MapDirection.getSouthEast();
		MapDirection s=MapDirection.getSouth();
		
		Location topLeftCorner=getTopLeftCornerOfDisplay();
		
		Location topOfDiagonal=topLeftCorner;
		
		int pixelX=0;
		int pixelY=0;
		
		while(topOfDiagonal.getX() + TILE_SIZE < getWidth())
		{
			drawDiagonal(topOfDiagonal, pixelX, pixelY);
			pixelX+= TILE_SIZE;
			topOfDiagonal=topOfDiagonal.getAdjacent(ne).getAdjacent(se, mapWidth, mapHeight);
		}

		topOfDiagonal=topLeftCorner.getAdjacent(s, mapWidth, mapHeight);
		pixelX=0;
		pixelY=0;
		
		while(topOfDiagonal.getY() + TILE_SIZE < getHeight())
		{
			drawDiagonal(topOfDiagonal, pixelX, pixelY);
			pixelY+= TILE_SIZE;
			topOfDiagonal=topOfDiagonal.getAdjacent(s, mapWidth, mapHeight);
		}
	}

	private void drawDiagonal(Location topOfDiagonal, int pixelX, int pixelY) 
	{
		MapDirection se=MapDirection.getSouthEast();
		
		Location loc=topOfDiagonal;
		
		while(loc.getX() + TILE_SIZE < getWidth() && loc.getX() + TILE_SIZE < getWidth())
		{
			drawViewAt(loc, pixelX, pixelY);
			pixelY+= TILE_SIZE;
			loc=topOfDiagonal.getAdjacent(se, mapWidth, mapHeight);
		}
	}

	private void drawViewAt(Location loc, int pixelX, int pixelY)
	{
		drawViewAt(getTileViewAt(loc), loc, pixelX, pixelY);
		
		for(View view: getViewsAt(loc))
		{
			drawViewAt(view, loc, pixelX, pixelY);
		}
	}	
	
	@SuppressWarnings("unused")
	private void drawViewAt(ResourceView view, Location loc, int pixelX, int pixelY)
	{
		g2d.drawImage(view.getImage(), pixelX, pixelY, null);
	}
	
	@SuppressWarnings("unused")
	private void drawViewAt(UnitView view, Location loc, int pixelX, int pixelY)
	{
		BufferedImage image = view.getImage();
		int width=image.getWidth();
		int height=image.getHeight();
		
		double theta = Math.toRadians(view.getRotation() + 90);
		
		pixelX=pixelX+(TILE_SIZE-width)/2 + (int)(Math.cos(theta)*height*1.5);
		pixelY=pixelY+(TILE_SIZE-height)/2 + (int)(Math.sin(theta)*height*1.5);
		
		g2d.drawImage(image, pixelX, pixelY, null);
	}
	
	private void drawViewAt(View view, Location loc, int pixelX, int pixelY)
	{
		BufferedImage image = view.getImage();
		int width=image.getWidth();
		int height=image.getHeight();
		
		pixelX=pixelX+(TILE_SIZE-width)/2;
		pixelY=pixelY+(TILE_SIZE-height)/2;
		
		g2d.drawImage(image, pixelX, pixelY, null);
	}

	private TileView getTileViewAt(Location loc)
	{
		loc = loc.wrapAround(mapWidth, mapHeight);
		return blankMapView[loc.getY()][loc.getX()];
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
	
	public void focusOn(Location loc)
	{
		loc = loc.wrapAround(blankMapView[0].length, blankMapView.length);
		focus = loc;
		focusView.setLocation(loc);
		updateView();
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		g.drawImage(image,0,0,null);
	}
}
