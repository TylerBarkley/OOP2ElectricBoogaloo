package utilities;

import model.Controllables.Units.Unit;
import model.Controllables.Units.UnitID;
import model.Map.Map;
import model.Map.Tile;
import model.Map.Occupancy.StructureOccupancy;
import model.Map.Occupancy.StructureOccupancyManager;
import model.Map.Occupancy.UnitOccupancy;
import model.Map.Occupancy.UnitOccupancyManager;
import model.Map.Resources.ResourceLevel;
import model.Map.Terrain.Ground;
import model.Map.Terrain.Mountain;
import model.Map.Terrain.Terrain;
import model.Map.Terrain.Water;
import model.player.PlayerID;
import view.AreaViewport;
import view.CompositeView;
import view.TileView;
import view.View;
import view.ViewFactory;

import java.awt.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.ID;
import model.Location;
import model.Controllables.Worker;
import model.Controllables.Structures.Structure;
import model.Controllables.Structures.StructureID;

public class ViewVisitor implements UnitVisitor,MapVisitor,WorkerVisitor,
StructureVisitor, AreaViewportVisitor {

	private PlayerID playerID;
	private ArrayList<Location> visibleLocations;
	private HashMap<Location, Terrain> terrains;
	private HashMap<Location, ResourceLevel> resources;
	private ArrayList<Unit> units;
	private ArrayList<Structure> structures;

	public ViewVisitor(PlayerID playerID){
		this.playerID=playerID;
		visibleLocations=new ArrayList<Location>();
		terrains=new HashMap<Location, Terrain>();
		resources=new HashMap<Location, ResourceLevel>();
		units=new ArrayList<Unit>();
		structures=new ArrayList<Structure>();
	}

	@Override
	public void visit(Unit unit) {
		if(unit.getID().getPlayerID().equals(playerID)){
			Location loc=unit.getLocation();
			int sight = unit.getMyStats().getInfluenceRadius();

			visibleLocations.addAll(loc.getAllLocationsWithinRadius(sight));

			units.add(unit);
		}
	}

	@Override
	public void visit(Worker unit) {
		// TODO Do we need this? If there is time, we can.
	}

	@Override
	public void visit(Map map) {
		for(Location loc: visibleLocations)
		{
			Tile tile = map.getTileAt(loc);
			if(tile!=null)
			{
				terrains.put(loc, tile.getTerrain());
				boolean prospected = tile.isProspected();
				if(prospected){
					resources.put(loc,map.getResourcesAt(loc));
				}
			}

			UnitOccupancyManager uOccMan=map.getUnitOccupancyManager();
			UnitOccupancy uOcc=uOccMan.get(loc);
			if(uOcc!=null)
			{
				units.addAll(uOcc.getOccupants());
			}

			StructureOccupancyManager sOccMan=map.getStructureOccupancyManager();
			StructureOccupancy sOcc=sOccMan.get(loc);
			if(sOcc!=null)
			{
				structures.add(sOcc.getOccupant());
			}
		}
	}

	@Override
	public void visit(Structure structure) {
		if(structure.getID().getPlayerID().equals(playerID)){
			Location loc=structure.getLocation();
			int sight = structure.getMyStats().getInfluenceRadius();

			visibleLocations.addAll(loc.getAllLocationsWithinRadius(sight));

			structures.add(structure);
		}
	}

	public void visit(AreaViewport viewport)
	{
		ViewFactory factory = ViewFactory.getFactory();


		addTerrainsToView(viewport,factory);
		addResourcesToView(viewport,factory);
		addStructuresToView(viewport, factory);
		addUnitsToView(viewport, factory);
	}

	private void addTerrainsToView(AreaViewport viewport, ViewFactory factory) {
		for(Entry<Location, Terrain> entry: terrains.entrySet())
		{
			String type="";

			if(entry.getValue().equals(Water.getWaterTerrain()))
			{
				type="Water";
			}
			else if(entry.getValue().equals(Mountain.getMountainTerrain()))
			{
				type="Mountain";
			}
			else if(entry.getValue().equals(Ground.getGroundTerrain()))
			{
				type="Ground";
			}

			TileView view = (TileView)factory.getView(new ID(), type, entry.getKey());
			viewport.updateMapView(entry.getKey(), view);
		}
	}

	private void addResourcesToView(AreaViewport viewport, ViewFactory factory) {
		for(Entry<Location, ResourceLevel> entry: resources.entrySet())
		{
			int oreQuantity=entry.getValue().getOreLevel();
			int energyQuantity=entry.getValue().getEnergyLevel();
			int foodQuantity=entry.getValue().getFoodLevel();

			CompositeView view = factory.getCopositeResourceView(new ID(), 
					entry.getKey(), oreQuantity, energyQuantity, foodQuantity);

			viewport.updateResourceView(entry.getKey(), view);
		}
	}

	private void addStructuresToView(AreaViewport viewport, ViewFactory factory) {
		for(Structure structure: structures)
		{
			String type="";
			StructureID id= structure.getID(); 

			switch(id.getType()){
			case StructureID.CAPITAL_TYPE_ID:
				type="Capital";
				break;
			case StructureID.FARM_TYPE_ID:
				type="Farm";
				break;
			case StructureID.FORT_TYPE_ID:
				type="Fort";
				break;
			case StructureID.MINE_TYPE_ID:
				type="Mine";
				break;
			case StructureID.OBSERVATIONTOWER_TYPE_ID:
				type="Tower";
				break;
			case StructureID.POWERPLANT_TYPE_ID:
				type="Factory";
				break;
			case StructureID.UNIVERSITY_TYPE_ID:
				type="University";
				break;
			}

			Location loc=structure.getLocation();
			int dir=structure.getMapDirection().getAngle();
			boolean isOpponent=!id.getPlayerID().equals(playerID);

			View view = factory.getView(id, type, loc, dir, isOpponent);
			viewport.addView(view);
		}
	}

	private void addUnitsToView(AreaViewport viewport, ViewFactory factory) {
		for(Unit unit: units)
		{
			String type="";
			UnitID id= unit.getID(); 

			switch(id.getType()){
			case UnitID.COLONIST_TYPE_ID:
				type="Colonist";
				break;
			case UnitID.EXPLORER_TYPE_ID:
				type="Explorer";
				break;
			case UnitID.MELEE_TYPE_ID:
				type="Melee";
				break;
			case UnitID.RANGED_TYPE_ID:
				type="Ranged";
				break;
			}

			Location loc=unit.getLocation();
			int dir=unit.getMapDirection().getAngle();
			boolean isOpponent=!id.getPlayerID().equals(playerID);

			View view = factory.getView(id, type, loc, dir, isOpponent);
			viewport.addView(view);
		}
	}
}
