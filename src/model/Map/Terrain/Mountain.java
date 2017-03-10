package model.Map.Terrain;

import model.TerrainVisitor;

public class Mountain extends Terrain
{
	private static Mountain terrain;
	
	private Mountain(){}
	
	public static Mountain getMountainTerrain(){
		if(terrain==null)
		{
			terrain=new Mountain();
		}
		
		return terrain;
	}

	@Override
	public void visitTerrain(TerrainVisitor tv) {
		tv.visitMountainTerrain();
	}
}
