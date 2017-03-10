package model.Map.Terrain;

import model.TerrainVisitor;

public class Water extends Terrain
{
	private static Water terrain;
	
	private Water(){}
	
	public static Water getWaterTerrain(){
		if(terrain==null)
		{
			terrain=new Water();
		}
		
		return terrain;
	}

	@Override
	public void visitTerrain(TerrainVisitor tv) {
		tv.visitWaterTerrain();
	}
}
