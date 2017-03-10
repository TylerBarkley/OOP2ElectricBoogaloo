package utilities;

import model.Controllables.Units.Unit;
import model.Map.Map;
import model.Map.Tile;
import model.Map.Resources.ResourceLevel;
import model.Map.Terrain.Terrain;
import model.player.PlayerID;

import java.util.ArrayList;
import java.util.HashMap;

import model.ID;
import model.Location;
import model.Controllables.Worker;
import model.Controllables.Structures.Structure;

public class ViewVisitor implements UnitVisitor,MapVisitor,WorkerVisitor,StructureVisitor,TileVisitor {

	private PlayerID playerID;
	private ArrayList<Location> visibleLocations;
	private HashMap<Location, Terrain> terrains;
	private HashMap<Location, ResourceLevel> resources;
	
	public ViewVisitor(PlayerID playerID){
		this.playerID=playerID;
		visibleLocations=new ArrayList<Location>();
		terrains=new HashMap<Location, Terrain>();
		resources=new HashMap<Location, ResourceLevel>();
	}
	
	@Override
	public void visit(Unit unit) {
		ID id=unit.getID();
	}

	@Override
	public void visit(Worker unit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Map map) {
		for(Location loc: visibleLocations)
		{
			Tile tile = map.getTileAt(loc);
			terrains.put(loc, tile.getTerrain());
			
			boolean prospected = tile.isProspected();
			if(prospected){
				resources.put(loc,map.getResourcesAt(loc));
			}
			
			map.getUnitOccupancyManager().get(loc).getOccupants();
		}
	}

	@Override
	public void visit(Structure structure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Tile tile) {
		// TODO Auto-generated method stub
		
	}

}
