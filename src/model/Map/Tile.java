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
		System.out.println("Visiting location");
		tileVisitor.visit(this);
	}
}
