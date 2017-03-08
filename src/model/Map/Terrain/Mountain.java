package model.Map.Terrain;

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
}
