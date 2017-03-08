package model.player;

import java.util.ArrayList;

import model.Controllables.Army;
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

public class Player {
	private PlayerID id;
	
	private UnitManager unitManager;

	private ArrayList<Army> armies;
	private ArrayList<Worker> workers;

	private StructureManager structureManager;
	
	public Player()
	{
		id = new PlayerID();
		
		unitManager=new UnitManager(id);
		structureManager=new StructureManager(id);

		armies=new ArrayList<Army>();
		workers=new ArrayList<Worker>();
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

	public boolean addWorker(Worker worker) {
		return workers.add(worker);
	}
}
