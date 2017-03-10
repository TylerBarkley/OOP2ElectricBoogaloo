package model.Map;

import model.Map.Terrain.Terrain;
import utilities.TileVisitor;

public class Tile {
	private Terrain terrain;
	private boolean isProspected;
	
	public Tile(Terrain terrain) {
		this.terrain=terrain;
	}
	
	public Terrain getTerrain()
	{
		return terrain;
	}
	
	public void accept(TileVisitor visitor)
	{
		visitor.visit(this);
	}
	
	public void prospect()
	{
		isProspected=true;
	}

	public boolean isProspected() {
		return isProspected;
	}
}
