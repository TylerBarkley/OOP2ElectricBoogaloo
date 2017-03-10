package model.player;

import java.util.ArrayList;

import model.Controllables.Army;
import model.Controllables.ControllableCollection;
import model.Controllables.Worker;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Farm;
import model.Controllables.Structures.Fort;
import model.Controllables.Structures.Mine;
import model.Controllables.Structures.ObservationTower;
import model.Controllables.Structures.PowerPlant;
import model.Controllables.Structures.Structure;
import model.Controllables.Structures.StructureManager;
import model.Controllables.Structures.University;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;
import model.Controllables.Units.UnitManager;
import model.Map.Resources.Energy;
import model.Map.Resources.Food;
import model.Map.Resources.Ore;

public class Player {
	private PlayerID id;
	
	private UnitManager unitManager;
	private StructureManager structureManager;

	private ArrayList<Army> armies;
	private ArrayList<Worker> workers;
	
	private Food nutrients;
	private Energy power;
	private Ore metal;
	
	public Player()
	{
		id = new PlayerID();
		
		unitManager=new UnitManager(id);
		structureManager=new StructureManager(id);

		armies=new ArrayList<Army>();
		workers=new ArrayList<Worker>();
		
		nutrients=new Food(0);
		power=new Energy(0);
		metal=new Ore(0);

		PlayerManager.getInstance().addPlayer(this);
	}

	public PlayerID getId() {
		return id;
	}

	public ArrayList<Unit> getUnits() {
		return unitManager.getUnits();
	}

	public ArrayList<Structure> getStructures() {
		return structureManager.getStructures();
	}

	public ArrayList<Army> getArmies() {
		return armies;
	}

	public ArrayList<Worker> getWorkers() {
		return workers;
	}

	public Food getFood(){return nutrients;}

	public Ore getOre(){ return metal;}

	public Energy getEnergy(){ return power;}

	public boolean addUnit(Colonist unit) {
		return unitManager.add(unit);
	}

	public boolean addUnit(Explorer unit) {
		return unitManager.add(unit);
	}

	public boolean addUnit(Melee unit) {
		return unitManager.add(unit);
	}

	public boolean addUnit(Ranged unit) {
		return unitManager.add(unit);
	}
	
	public boolean addStructure(Capital structure) {
		return structureManager.add(structure);
	}

	public boolean addStructure(Farm structure) {
		return structureManager.add(structure);
	}
	
	public boolean addStructure(Fort structure) {
		return structureManager.add(structure);
	}
	
	public boolean addStructure(Mine structure) {
		return structureManager.add(structure);
	}
	
	public boolean addStructure(ObservationTower structure) {
		return structureManager.add(structure);
	}
	
	public boolean addStructure(PowerPlant structure) {
		return structureManager.add(structure);
	}
	
	public boolean addStructure(University structure) {
		return structureManager.add(structure);
	}
	
	public boolean addArmy(Army army) {
		return armies.add(army);
	}

	public boolean addWorker(Worker worker) 
	{
		if(workers.size() >= 100)
		{
			return false;
		}
		return workers.add(worker);
	}
	
	public void addNutrients(int food){
		nutrients.addAmount(food);
	}
	
	public void addMetal(int ore){
		metal.addAmount(ore);
	}
	
	public void addPower(int energy){
		power.addAmount(energy);
	}
	
	public void chargeResources()
	{
		unitManager.chargeResources(nutrients);
		//structureManager.chargeResources(metal, power);
	}
	
	public ControllableCollection getControllableCollection()
	{
		return new ControllableCollection(unitManager.getColonists(), unitManager.getExplorers(), 
				unitManager.getSoldiers(), unitManager.getRangedSoldiers(), armies, structureManager.getCapitals(), structureManager.getFarms(),
				structureManager.getForts(), structureManager.getMines(), structureManager.getTowers(),
				structureManager.getPlants(), structureManager.getUniversities(), workers);
	}
}
