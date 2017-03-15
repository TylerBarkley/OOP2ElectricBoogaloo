package model.Map;

import model.Map.Terrain.Terrain;
import model.TileVisitor;

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
	
	public void prospect()
	{
		isProspected=true;
	}

	public boolean isProspected() {
		return isProspected;
	}

	public void accept(TileVisitor tileVisitor){
		tileVisitor.visit(this);
	}
}
