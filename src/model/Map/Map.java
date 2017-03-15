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
import model.Map.Occupancy.UnitOccupancy;
import model.Map.Occupancy.UnitOccupancyManager;

import model.Map.Resources.ResourceLevel;
import model.Map.Resources.ResourceManager;

import model.Map.Terrain.Ground;
import model.Map.Terrain.Mountain;
import model.Map.Terrain.Terrain;
import model.Map.Terrain.Water;
import model.player.Player;
import model.player.PlayerID;
import utilities.MapVisitor;
import utilities.ViewVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Map {
	private java.util.Map<Location, Tile> tiles;
	private AreaOfEffectManager aoeManager;
	private OneShotManager oneShotManager;
	private ObstacleManager obstacleManager;
	private ResourceManager resourceManager;
	private StructureOccupancyManager structureOccupancyManager;
	private UnitOccupancyManager unitOccupancyManager;

	private static Map map;

	private static boolean MoveDebug = false;
	private static boolean BFSDebug = false;

	public static Map getInstance(){

		if(map == null)
		{
			if(BFSDebug) map = new Map(69);
			else if (MoveDebug) map = new Map();


			//TODO the real one goes here
			else map = new Map(100, 100);
		}

		return map;
	}

	public static Map getInstance(String fileName){
		if(map == null)
		{
			map = new Map(fileName);
		}

		return map;
	}
	
	private Map(int width, int height) //Generates random map
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

	private Map() //Generates test map
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
		tiles.put(new Location(1, -1), new Tile(terrains[0]));
		tiles.put(new Location(0, -1), new Tile(terrains[0]));
		tiles.put(new Location(-1, 0), new Tile(terrains[0]));
		tiles.put(new Location(-1, 1), new Tile(terrains[0]));
		tiles.put(new Location(-2, 1), new Tile(terrains[0]));
		tiles.put(new Location(2, 0), new Tile(terrains[1]));
		tiles.put(new Location(2, -1), new Tile(terrains[1]));
		tiles.put(new Location(3, -1), new Tile(terrains[2]));
		tiles.put(new Location(5, 5), new Tile(terrains[1]));
		tiles.put(new Location(6, 6), new Tile(terrains[2]));

	}


	
	private Map(String fileName) //Generates test map
	{
		tiles = new HashMap<Location, Tile>();
		aoeManager = new AreaOfEffectManager();
		oneShotManager = new OneShotManager();
		obstacleManager = new ObstacleManager();
		resourceManager = new ResourceManager();
		structureOccupancyManager = new StructureOccupancyManager();
		unitOccupancyManager = new UnitOccupancyManager();

		readMap(fileName);
	}

	//TODO: delete this post-test
	private Map(int bfs) //Generates test map for BFS
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

		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){

				if(j == 5 && i >= 2){
					tiles.put(new Location(i, j), new Tile(terrains[2]));
				}
				else tiles.put(new Location(i, j), new Tile(terrains[0]));
			}
		}
	}


	private void readMap(String fileName) {
		Terrain[] terrains={Water.getWaterTerrain(),
				Ground.getGroundTerrain(), Mountain.getMountainTerrain()};
		File file=new File(fileName);

		Scanner scanner=null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		Location loc=new Location(0,0);
		while (scanner.hasNext()){
			String line=scanner.nextLine();			
			char[] chars=line.toCharArray();

			Location loc2=loc;

			for(int i=0; i<chars.length; i++)
			{
				switch(chars[i])
				{
				case 'g':
					tiles.put(loc2, new Tile(terrains[1]));
					break;
				case 'w':
					tiles.put(loc2, new Tile(terrains[0]));
					break;
				case 'm':
					tiles.put(loc2, new Tile(terrains[2]));
					break;
				default:
					tiles.put(loc2, new Tile(terrains[1]));
					break;
				}

				if(i%2==0)
				{
					loc2=loc2.getAdjacent(MapDirection.getSouthEast());
				}
				else
				{
					loc2=loc2.getAdjacent(MapDirection.getNorthEast());
				}
			}
			
			loc=loc.getAdjacent(MapDirection.getSouth());		
		}
		
		scanner.close();
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

	public static void reset() {
		map=null;
		MovementManager.reset();
		AttackManager.reset();
		ProductionManager.reset();
		resetDebug();
	}

	public void visitTile(TileVisitor target, Location location) {
		this.getTileAt(location).accept(target);
	}

	public Set<Location> getLocations(){
		return tiles.keySet();
	}

	public ResourceManager getResourceManager(){return this.resourceManager;}

	public void accept(MapVisitor visitor) {
		visitor.visit(this);
	}
	
	public static void setMoveDebug(){
		MoveDebug = true;
		}

	public static void setBFSDebug() {
		BFSDebug = true;
	}

	public static void resetDebug(){
		MoveDebug = false;
		BFSDebug = false;
	}

	public UnitOccupancy getUnitOccupancyAt(Location location) {
		return unitOccupancyManager.get(location);
	}

	public StructureOccupancy getStructureOccupancyAt(Location location) {
		return structureOccupancyManager.get(location);
	}


	public Location getNearestValid(PlayerID PID, Location RPlocation){

		MovementManager movementManager = MovementManager.getInstance();

		HashSet<Location> visited2 = new HashSet<Location>();
		ArrayDeque<Location> q2 = new ArrayDeque<Location>();

		q2.add(RPlocation);
		visited2.add(RPlocation);

		while(!q2.isEmpty()){
			Location current = q2.poll();
			//if current is the goal, return current

			ArrayList<Location> adjacents = current.getAllLocationsWithinRadius(1);
			for(Location l: adjacents){
				if(!visited2.contains(l)){
					visited2.add(l);

					if(movementManager.validateMove(PID, l)) {
						return l;
					}
				}
			}
		}

		return null;
	}

	public HashMap<Location, Location> BFS(PlayerID PID, Location RPlocation){

		MovementManager movementManager = MovementManager.getInstance();

		//HashMap<Location, MapDirection> paths = new HashMap<Location, MapDirection>();
		HashMap<Location, Location> parents = new HashMap<Location, Location>();

		HashSet<Location> visited = new HashSet<Location>();

		ArrayDeque<Location> q = new ArrayDeque<Location>();

		parents.put(RPlocation, null);
		visited.add(RPlocation);
		q.add(RPlocation);

		while(!q.isEmpty()){
			Location current = q.poll();
			//if current is the goal, return current

			ArrayList<Location> adjacents = current.getAllLocationsWithinRadius(1);
			for(Location l: adjacents){
				if(!visited.contains(l)){
					visited.add(l);

					if(movementManager.validateMove(PID, l)) {
						q.add(l);
						parents.put(l, current);
					}

				}
			}
		}
		return parents;
	}
}
