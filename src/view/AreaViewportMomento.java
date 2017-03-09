package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import model.ID;
import model.Location;

public class AreaViewportMomento {
	private HashMap<ID, View> views;

	private HashMap<Location, TileView> mapView;
	private HashMap<Location, CompositeView> resources;

	private Location focus;
	private FocusView focusView;
	private ID focusID;
	
	private BufferedImage image;
	private Graphics2D g2d;

	private boolean resourceViewVisible;

	public AreaViewportMomento(HashMap<ID, View> views, HashMap<Location, TileView> mapView,
			HashMap<Location, CompositeView> resources, Location focus, FocusView focusView, ID focusID,
			BufferedImage image, Graphics2D g2d, boolean resourceViewVisible) 
	{
		this.views = views;
		this.mapView = mapView;
		this.resources = resources;
		this.focus = focus;
		this.focusView = focusView;
		this.focusID = focusID;
		this.image = image;
		this.g2d = g2d;
		this.resourceViewVisible = resourceViewVisible;
	}
	
	public HashMap<ID, View> getViews() {
		return views;
	}

	public HashMap<Location, TileView> getMapView() {
		return mapView;
	}

	public HashMap<Location, CompositeView> getResources() {
		return resources;
	}

	public Location getFocus() {
		return focus;
	}

	public FocusView getFocusView() {
		return focusView;
	}

	public ID getFocusID() {
		return focusID;
	}

	public BufferedImage getImage() {
		return image;
	}

	public Graphics2D getG2d() {
		return g2d;
	}

	public boolean isResourceViewVisible() {
		return resourceViewVisible;
	}
}
