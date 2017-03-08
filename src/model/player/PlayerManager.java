package model.player;

import java.util.ArrayList;
import java.util.HashMap;

import model.Controllables.Army;
import model.Controllables.Worker;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Farm;
import model.Controllables.Structures.Fort;
import model.Controllables.Structures.Mine;
import model.Controllables.Structures.ObservationTower;
import model.Controllables.Structures.PowerPlant;
import model.Controllables.Structures.Structure;
import model.Controllables.Structures.University;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;

public class PlayerManager {
	private static PlayerManager pm;
	
	private HashMap<PlayerID, Player> players;
	
	private PlayerManager()
	{
		players=new HashMap<PlayerID, Player>();
	}
	
	public static PlayerManager getInstance()
	{
		if(pm == null)
		{
			pm = new PlayerManager();
		}
		
		return pm;
	}
	
	public ArrayList<Unit> getUnits(PlayerID id) {
		return players.get(id).getUnits();
	}

	public ArrayList<Structure> getStructures(PlayerID id) {
		return players.get(id).getStructures();
	}

	public ArrayList<Army> getArmies(PlayerID id) {
		return players.get(id).getArmies();
	}

	public ArrayList<Worker> getWorkers(PlayerID id) {
		return players.get(id).getWorkers();
	}
	
	public boolean addUnit(PlayerID id, Colonist unit) {
		return players.get(id).addUnit(unit);
	}

	public boolean addUnit(PlayerID id, Explorer unit) {
		return players.get(id).addUnit(unit);
	}

	public boolean addUnit(PlayerID id, Melee unit) {
		return players.get(id).addUnit(unit);
	}

	public boolean addUnit(PlayerID id, Ranged unit) {
		return players.get(id).addUnit(unit);
	}
	
	public boolean addStructure(PlayerID id, Capital structure) {
		return players.get(id).addStructure(structure);
	}

	public boolean addStructure(PlayerID id, Farm structure) {
		return players.get(id).addStructure(structure);
	}
	
	public boolean addStructure(PlayerID id, Fort structure) {
		return players.get(id).addStructure(structure);
	}
	
	public boolean addStructure(PlayerID id, Mine structure) {
		return players.get(id).addStructure(structure);
	}
	
	public boolean addStructure(PlayerID id, ObservationTower structure) {
		return players.get(id).addStructure(structure);
	}
	
	public boolean addStructure(PlayerID id, PowerPlant structure) {
		return players.get(id).addStructure(structure);
	}
	
	public boolean addStructure(PlayerID id, University structure) {
		return players.get(id).addStructure(structure);
	}
}
