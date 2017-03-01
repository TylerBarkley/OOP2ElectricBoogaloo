package model;

import java.util.HashMap;
import java.util.Random;

public class Map {
	private java.util.Map<Location, Tile> tiles;
	private AreaOfEffectManager aoeManager;
	private ItemManager itemManager;
	
	//Possibilities:
	//private UnitManager unitManager;
	//private StructureManager structManager;
	
	public Map(int width, int height) //Generates random map
	{
		tiles=new HashMap<Location, Tile>();
		aoeManager=new AreaOfEffectManager();
		itemManager=new ItemManager();
		
		Random rn=new Random();
		Terrain[] terrains={Water.getWaterTerrain(), 
				Ground.getGroundTerrain(), Mountain.getMountainTerrain()};
		
		for(int i=0; i<width; i++)
		{
			for(int j=-i/2; j<height-i/2; j++)
			{
				Location loc=new Location(i,-i-j);
				tiles.put(loc, new Tile(terrains[rn.nextInt(3)]));
			}
		}
	}
	
	//TODO Additional constructors
	
	public Tile getTileAt(Location loc)
	{
		return tiles.get(loc);
	}
	
	public void addAOE(Location loc, AOE aoe)
	{
		aoeManager.add(loc, aoe);
	}
	
	public void addItem(Location loc, Item item)
	{
		itemManager.add(loc, item);
	}
}
