package model.player;

import java.util.ArrayList;
import java.util.HashMap;

import model.Controllables.Army;
import model.Controllables.ControllableCollection;
import model.Controllables.RallyPoint;
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
import model.Map.Resources.Energy;
import model.Map.Resources.Food;
import model.Map.Resources.Ore;
import model.Technology;

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

	public void addPlayer(Player player){
		this.players.put(player.getId(), player);
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

	public Integer getWorkers(PlayerID id) {
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

	public boolean addWorker(PlayerID id, int workers){
		return players.get(id).addWorker(workers);
	}

	public boolean addArmy(PlayerID id, Army army) {
		return players.get(id).addArmy(army);
	}

	public boolean addRallyPoint(PlayerID id, RallyPoint rp) {
		return players.get(id).addRallyPoint(rp);
	}

	public void addNutrients(PlayerID id, int food){
		players.get(id).addNutrients(food);
	}
	
	public void addMetal(PlayerID id, int ore){
		players.get(id).addMetal(ore);
	}
	
	public void addPower(PlayerID id, int energy){
		players.get(id).addPower(energy);
	}

	public void addTechnology(PlayerID id, int technology){
		players.get(id).addTechnology(technology);
	}

	public Food getNutrients(PlayerID id) {
		return players.get(id).getNutrients();
	}
	
	public Ore getMetal(PlayerID id) {
		return players.get(id).getMetal();
	}
	
	public Energy getPower(PlayerID id) {
		return players.get(id).getPower();
	}
	
	public void distributePower(PlayerID id,Structure structure,int amount){players.get(id).distributePower(structure,amount);}

	public void distributeMetal(PlayerID id, Structure structure,int amount){players.get(id).distributeMetal(structure,amount);}

	public void distributeNutrients(PlayerID id, Structure structure,int amount){players.get(id).distributeNutrients(structure,amount);}

	public void distributeNutrients(PlayerID id, Army army,int amount){players.get(id).distributeNutrients(army,amount);}

	public void distributeNutrients(PlayerID id,Unit unit,int amount){players.get(id).distributeNutrients(unit,amount);}

	public Technology getTechnology(Player id){return players.get(id).getTech();}

	//public void storePower(PlayerID id,int amount){ players.get(id).storePower(amount);}
	//public void storeMetal(PlayerID id,int amount){ players.get(id).storeMetal(amount);}
	//public void storeNutrients(PlayerID id,int amount){ players.get(id).storeNutrients(amount);}
	
	public ControllableCollection getControllableCollection(PlayerID id){
		return players.get(id).getControllableCollection();
	}

	public ArrayList<RallyPoint> getRallyPoints(PlayerID id) {
		return players.get(id).getRallyPoints();
	}

	public static void reset() {
		pm=null;
	}
}
