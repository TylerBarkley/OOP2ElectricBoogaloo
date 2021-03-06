package model.player;

import java.util.ArrayList;

import model.Controllables.Army;
import model.Controllables.ArmyID;
import model.Controllables.ControllableCollection;
import model.Controllables.RPID;
import model.Controllables.RallyPoint;
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
import model.Technology;
import model.TurnManager;
import model.observers.ArmyObserver;
import model.observers.PlayerObserver;
import model.observers.RPObserver;

public class Player implements ArmyObserver, RPObserver{
	private PlayerID id;

	private UnitManager unitManager;
	private StructureManager structureManager;

	private PlayerStats PStats;

	private ArrayList<Army> armies;
	private ArrayList<RallyPoint> rallyPoints;
	private Technology tech;
	private int workers;

	private Food nutrients;
	private Energy power;
	private Ore metal;
	private int technology;

	TurnManager turnManager;

	private ArrayList<PlayerObserver> observers;

	public Player()
	{
		id = new PlayerID();

		PStats = new PlayerStats();

		turnManager = TurnManager.getInstance();

		unitManager=new UnitManager(id);
		structureManager=new StructureManager(id);

		armies=new ArrayList<Army>();
		rallyPoints=new ArrayList<RallyPoint>();
		workers=0;
		tech=new Technology(PStats.myUnitStats,PStats.myStructureStats,PStats.workerStats);
		nutrients=new Food(0);
		power=new Energy(0);
		metal=new Ore(0);

		observers=new ArrayList<PlayerObserver>();

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

	public Food getFood(){return nutrients;}

	public Ore getOre(){ return metal;}

	public Energy getEnergy(){ return power;}

	public boolean addUnit(Colonist unit) {
		boolean b=unitManager.add(unit);
		if(b)
		{
			unit.setMyStats(PStats.getColonistStats());

			turnManager.addEndObserver(unit);
			turnManager.addStartObserver(unit);

			notifyObservers(unit);
		}

		return b;
	}

	public boolean addUnit(Explorer unit) {
		boolean b=unitManager.add(unit);
		if(b)
		{
			unit.setMyStats(PStats.getExplorerStats());

			turnManager.addEndObserver(unit);
			turnManager.addStartObserver(unit);

			notifyObservers(unit);
		}

		return b;
	}

	public boolean addUnit(Melee unit) {
		boolean b=unitManager.add(unit);
		if(b)
		{
			unit.setMyStats(PStats.getMeleeStats());

			turnManager.addEndObserver(unit);
			turnManager.addStartObserver(unit);

			notifyObservers(unit);
		}

		return b;
	}

	public boolean addUnit(Ranged unit) {
		boolean b=unitManager.add(unit);
		if(b)
		{
			unit.setMyStats(PStats.getRangedStats());

			turnManager.addEndObserver(unit);
			turnManager.addStartObserver(unit);

			notifyObservers(unit);
		}

		return b;
	}

	public boolean addStructure(Capital structure) {
		boolean b=structureManager.add(structure);
		if(b)
		{
			structure.setMyStats(PStats.getCapitalStats());
			structure.setStats(PStats.getWorkerStats());

			turnManager.addEndObserver(structure);

			notifyObservers(structure);
		}

		return b;
	}

	public boolean addStructure(Farm structure) {
		boolean b=structureManager.add(structure);
		if(b)
		{
			structure.setMyStats(PStats.getFarmStats());
			structure.setStats(PStats.getWorkerStats());

			turnManager.addEndObserver(structure);

			notifyObservers(structure);
		}

		return b;
	}

	public boolean addStructure(Fort structure) {
		boolean b=structureManager.add(structure);
		if(b)
		{
			structure.setMyStats(PStats.getFortStats());
			structure.setStats(PStats.getWorkerStats());

			turnManager.addEndObserver(structure);

			notifyObservers(structure);
		}

		return b;
	}

	public boolean addStructure(Mine structure) {
		boolean b=structureManager.add(structure);
		if(b)
		{
			structure.setMyStats(PStats.getMineStats());
			structure.setStats(PStats.getWorkerStats());

			turnManager.addEndObserver(structure);

			notifyObservers(structure);
		}

		return b;
	}

	public boolean addStructure(ObservationTower structure) {
		boolean b=structureManager.add(structure);
		if(b)
		{
			structure.setMyStats(PStats.getObservationTowerStats());
			structure.setStats(PStats.getWorkerStats());

			turnManager.addEndObserver(structure);

			notifyObservers(structure);
		}

		return b;
	}

	public boolean addStructure(PowerPlant structure) {
		boolean b=structureManager.add(structure);
		if(b)
		{
			structure.setMyStats(PStats.getPowerPlantStats());
			structure.setStats(PStats.getWorkerStats());

			turnManager.addEndObserver(structure);

			notifyObservers(structure);
		}

		return b;
	}

	public boolean addStructure(University structure) {
		boolean b=structureManager.add(structure);
		if(b)
		{
			structure.setMyStats(PStats.getUniversityStats());
			structure.setStats(PStats.getWorkerStats());

			turnManager.addEndObserver(structure);

			notifyObservers(structure);
		}

		return b;
	}

	public boolean addArmy(Army army) {
		boolean b=armies.add(army);
		if(b)
		{
			army.setID(new ArmyID(this.id,armies.size()-1));
			army.addObserver(this);

			turnManager.addEndObserver(army);
			turnManager.addStartObserver(army);

			notifyObservers(army);
		}

		return b;
	}

	public boolean addRallyPoint(RallyPoint rp) {
		boolean b=rallyPoints.add(rp);
		if(b)
		{
			rp.setID(new RPID(this.id,rallyPoints.size()-1));
			rp.addObserver(this);

			turnManager.addEndObserver(rp);
			turnManager.addStartObserver(rp);

			notifyObservers(rp);
		}

		return b;
	}

	public boolean addWorker(int newWorkers)
	{
		if(this.workers + newWorkers > 100)
		{
			workers = 100;
			return false;
		}
		this.workers += newWorkers;
		return true;
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


	public void addTechnology(int technology) {
		this.technology += technology;
	}

	public void distributePower(Structure structure,int amountOfPowerGiven){

		if(amountOfPowerGiven<=power.getAmount()) {
			structure.incrementEnergyResourceLevel(amountOfPowerGiven);
			power.setAmount(power.getAmount() - amountOfPowerGiven);
		} else {
			structure.incrementEnergyResourceLevel(power.getAmount());
			power.setAmount(0);
		}
		notifyObservers();
	}

	public void distributeMetal(Structure structure,int amountOfMetalGiven){
		if(amountOfMetalGiven<=metal.getAmount()) {
			structure.incrementMetalResourceLevel(amountOfMetalGiven);
			metal.setAmount(metal.getAmount()-amountOfMetalGiven);
		} else {
			structure.incrementMetalResourceLevel(metal.getAmount());
			metal.setAmount(0);
		}
		notifyObservers();
	}

	public void distributeNutrients(Structure structure,int amountOfNutrientsGiven){
		if(amountOfNutrientsGiven<=nutrients.getAmount()) {
			structure.incrementNutrientResourceLevel(amountOfNutrientsGiven);
			nutrients.setAmount(nutrients.getAmount()-amountOfNutrientsGiven);
		} else {
			structure.incrementNutrientResourceLevel(nutrients.getAmount());
			nutrients.setAmount(0);
		}

		notifyObservers();
	}

	public void distributeNutrients(Unit unit,int amountOfNutrientsGiven){

		if(amountOfNutrientsGiven<=nutrients.getAmount()) {
			unit.incrementNutrientResourceLevel(amountOfNutrientsGiven);
			nutrients.setAmount(nutrients.getAmount()-amountOfNutrientsGiven);
		} else {
			unit.incrementNutrientResourceLevel(nutrients.getAmount());
			nutrients.setAmount(0);
		}
		notifyObservers();
	}
	public void distributeNutrients(Army army,int amountOfNutrientsGiven){
		if(amountOfNutrientsGiven<=nutrients.getAmount()) {
			army.incrementNutrientResourceLevel(amountOfNutrientsGiven);
			nutrients.setAmount(nutrients.getAmount()-amountOfNutrientsGiven);
		} else {
			army.incrementNutrientResourceLevel(nutrients.getAmount());
			nutrients.setAmount(0);
		}
		notifyObservers();
	}

	public void chargeResources()
	{
		unitManager.chargeResources(nutrients);
		structureManager.chargeResources(metal, power);
	}

	public ControllableCollection getControllableCollection()
	{
		return new ControllableCollection(unitManager.getColonists(), unitManager.getExplorers(), 
				unitManager.getSoldiers(), unitManager.getRangedSoldiers(), armies, rallyPoints, structureManager.getCapitals(), structureManager.getFarms(),
				structureManager.getForts(), structureManager.getMines(), structureManager.getTowers(),
				structureManager.getPlants(), structureManager.getUniversities(), workers);
	}

	public Food getNutrients() {
		return nutrients;
	}

	public Energy getPower() {
		return power;
	}

	public Ore getMetal() {
		return metal;
	}

	public void addObserver(PlayerObserver observer)
	{
		observers.add(observer);
		notifyObserver(observer);
	}

	public void removeObserver(PlayerObserver observer)
	{
		observers.remove(observer);
	}

	public void notifyObserver(PlayerObserver observer)
	{
		observer.update(this);
	}

	public void notifyObservers()
	{
		for(PlayerObserver ob: observers)
		{
			notifyObserver(ob);
		}
	}

	public void notifyObserver(PlayerObserver observer, Unit object)
	{
		observer.update(this, object);
	}

	public void notifyObservers(Unit unit)
	{
		for(PlayerObserver ob: observers)
		{
			notifyObserver(ob, unit);
		}
	}

	public void notifyObserver(PlayerObserver observer, Structure object)
	{
		observer.update(this, object);
	}

	public void notifyObservers(Structure object)
	{
		for(PlayerObserver ob: observers)
		{
			notifyObserver(ob, object);
		}
	}

	public void notifyObserver(PlayerObserver observer, Army object)
	{
		observer.update(this, object);
	}

	public void notifyObservers(Army object)
	{
		for(PlayerObserver ob: observers)
		{
			notifyObserver(ob, object);
		}
	}

	public void notifyObserver(PlayerObserver observer, RallyPoint object)
	{
		observer.update(this, object);
	}

	public void notifyObservers(RallyPoint object)
	{
		for(PlayerObserver ob: observers)
		{
			notifyObserver(ob, object);
		}
	}

	@Override
	public void update(Army army) {
		if(army.isDisbanded())
		{
			armies.remove(army);
			//army.removeObserver(this);

			for(int i=army.getID().getInstanceNumber(); i<armies.size(); i++)
			{
				armies.get(i).getID().setInstanceNumber(i);
			}

			notifyObservers(army);
		}
	}

	@Override
	public void update(RallyPoint rp) {
		if(!rp.isActive())
		{
			rallyPoints.remove(rp);
			//rp.removeObserver(this);

			for(int i=rp.getID().getInstanceNumber(); i<rallyPoints.size(); i++)
			{
				rallyPoints.get(i).getID().setInstanceNumber(i);
			}

			notifyObservers(rp);
		}
	}

	public int getWorkers(){
		return this.workers;
	}

	public int getTechnology() {
		return technology;
	}

	public void setTechnology(int technology) {
		this.technology = technology;
	}

	public ArrayList<RallyPoint> getRallyPoints() {
		return rallyPoints;
	}

	public Technology getTech() {
		return tech;
	}

	public void setTech(Technology tech) {
		this.tech = tech;
	}

	public void cheatResources() {
		ArrayList<Army> armies=getArmies();
		
		for(Army army: armies){
			army.cheatResources();
		}
		
		ArrayList<Unit> units=getUnits();
		
		for(Unit unit: units){
			unit.cheatResources();
		}
		
		ArrayList<Structure> structures=getStructures();
		
		for(Structure structure: structures){
			structure.cheatResources();
		}
	}
}
