package model.Map.Terrain;

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
}
