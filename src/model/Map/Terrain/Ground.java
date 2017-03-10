package model.Map.Terrain;

import model.TerrainVisitor;

public class Ground extends Terrain
{
	private static Ground terrain;
	
	private Ground(){}
	
	public static Ground getGroundTerrain(){
		if(terrain==null)
		{
			terrain=new Ground();
		}
		
		return terrain;
	}

	@Override
	public void visitTerrain(TerrainVisitor tv) {
		tv.visitGroundTerrain();
	}
}
