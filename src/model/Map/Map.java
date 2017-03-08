package model.Map;

import model.*;
import model.Controllables.Structures.Structure;
import model.Controllables.Units.Unit;
import model.Map.AOE.AOE;
import model.Map.AOE.AreaOfEffectManager;
import model.Map.Items.ObstacleItem;
import model.Map.Items.ObstacleManager;
import model.Map.Items.OneShotItem;
import model.Map.Items.OneShotManager;
import model.Map.Occupancy.StructureOccupancy;
import model.Map.Occupancy.StructureOccupancyManager;
import model.Map.Occupancy.UnitOccupancyManager;
import model.Map.Resources.ResourceLevel;
import model.Map.Resources.ResourceManager;
import model.Map.Terrain.Ground;
import model.Map.Terrain.Mountain;
import model.Map.Terrain.Terrain;
import model.Map.Terrain.Water;

import java.util.HashMap;
import java.util.Random;

public class Map {
	private java.util.Map<Location, Tile> tiles;
	private AreaOfEffectManager aoeManager;
	private OneShotManager oneShotManager;
	private ObstacleManager obstacleManager;
	private ResourceManager resourceManager;
	private StructureOccupancyManager structureOccupancyManager;
	private UnitOccupancyManager unitOccupancyManager;
	
	//Possibilities:
	//private UnitManager unitManager;
	//private StructureManager structManager;
	
	public Map(int width, int height) //Generates random map
	{
		tiles=new HashMap<Location, Tile>();
		aoeManager=new AreaOfEffectManager();
		oneShotManager = new OneShotManager();
		obstacleManager = new ObstacleManager();
		resourceManager = new ResourceManager();
		structureOccupancyManager = new StructureOccupancyManager();
		unitOccupancyManager = new UnitOccupancyManager();
		
		Random rn=new Random();
		Terrain[] terrains={Water.getWaterTerrain(),
				Ground.getGroundTerrain(), Mountain.getMountainTerrain()};
		
		for(int i=0; i<width; i++)
		{
			for(int j=-i/2; j<height-i/2; j++)
			{
				Location loc=new Location(i,-i-j);
				tiles.put(loc, new Tile(terrains[rn.nextInt(3)]));
				resourceManager.add(loc, new ResourceLevel(rn.nextInt(500),rn.nextInt(500),rn.nextInt(500)));
			}
		}
	}

	public Map() //Generates test map
	{
		tiles = new HashMap<Location, Tile>();
		aoeManager = new AreaOfEffectManager();
		oneShotManager = new OneShotManager();
		obstacleManager = new ObstacleManager();
		resourceManager = new ResourceManager();
		structureOccupancyManager = new StructureOccupancyManager();
		unitOccupancyManager = new UnitOccupancyManager();

		Terrain[] terrains={Water.getWaterTerrain(),
				Ground.getGroundTerrain(), Mountain.getMountainTerrain()};

		tiles.put(new Location(0, 0), new Tile(terrains[1]));
		tiles.put(new Location(0, 1), new Tile(terrains[1]));
		tiles.put(new Location(1, 0), new Tile(terrains[0]));
		tiles.put(new Location(1, 1), new Tile(terrains[0]));
		tiles.put(new Location(5, 5), new Tile(terrains[1]));
		tiles.put(new Location(6, 6), new Tile(terrains[2]));

	}
	
	public Tile getTileAt(Location loc)
	{
		return tiles.get(loc);
	}

	public AOE getAoEAt(Location loc)
	{
		return aoeManager.get(loc);
	}

	public OneShotItem getOneShotAt(Location loc)
	{
		return oneShotManager.get(loc);
	}

	public ObstacleItem getObstacleAt(Location loc)
	{
		return obstacleManager.get(loc);
	}

	public ResourceLevel getResourcesAt(Location loc)
	{
		return resourceManager.get(loc);
	}

	public StructureOccupancyManager getStructureOccupancyManager(){
		return structureOccupancyManager;
	}

	public UnitOccupancyManager getUnitOccupancyManager(){
		return unitOccupancyManager;
	}

	public OneShotManager getOneShotManager() {
		return oneShotManager;
	}

	public void addAOE(Location loc, AOE aoe)
	{
		aoeManager.add(loc, aoe);
	}
	
	public void addOneShotItem(Location loc, OneShotItem item)
	{
		oneShotManager.add(loc, item);
	}

	public void addObstacleItem(Location loc, ObstacleItem item)
	{
		obstacleManager.add(loc, item);
	}

	public void addResourceLevel(Location loc, ResourceLevel rl){
		resourceManager.add(loc, rl);
	}

	public void addUnit(Location loc, Unit target){
		unitOccupancyManager.addUnit(target, loc);
	}

	public void addStructure(Location loc, Structure target){
		structureOccupancyManager.addStructure(target, loc);
	}


}
