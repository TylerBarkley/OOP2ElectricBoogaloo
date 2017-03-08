package model.Map.Terrain;

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
}
