package model.player;

import java.util.ArrayList;

import model.Controllables.Army;
import model.Controllables.ControllableCollection;
import model.Controllables.RallyPoint;
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
import model.observers.PlayerObserver;
import model.observers.UnitObserver;

public class Player {
	private PlayerID id;
	
	private UnitManager unitManager;
	private StructureManager structureManager;

	private PlayerStats PStats;

	private ArrayList<Army> armies;
	private ArrayList<RallyPoint> rallyPoints;
	private int workers;
	
	private Food nutrients;
	private Energy power;
	private Ore metal;
	private int technology;
	
	private ArrayList<PlayerObserver> observers;
	
	public Player()
	{
		id = new PlayerID();

		PStats = new PlayerStats();

		unitManager=new UnitManager(id);
		structureManager=new StructureManager(id);

		armies=new ArrayList<Army>();
		workers=0;
		
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

			notifyObservers(unit);
		}
		
		return b;
	}

	public boolean addUnit(Explorer unit) {
		boolean b=unitManager.add(unit);
		if(b)
		{
			unit.setMyStats(PStats.getExplorerStats());

			notifyObservers(unit);
		}
		
		return b;
	}

	public boolean addUnit(Melee unit) {
		boolean b=unitManager.add(unit);
		if(b)
		{
			unit.setMyStats(PStats.getMeleeStats());

			notifyObservers(unit);
		}
		
		return b;
	}

	public boolean addUnit(Ranged unit) {
		boolean b=unitManager.add(unit);
		if(b)
		{
			unit.setMyStats(PStats.getRangedStats());

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

			notifyObservers(structure);
		}
		
		return b;
	}
	
	public boolean addArmy(Army army) {
		boolean b=armies.add(army);
		if(b)
		{
			notifyObservers(army);
		}
		
		return b;
	}

	public boolean addRallyPoint(RallyPoint rp) {
		boolean b=rallyPoints.add(rp);
		if(b)
		{
			notifyObservers();
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

	public void distributePower(Structure structure,int percentage){
		int amountOfPowerGiven=(int)((double) (percentage)/100*power.getAmount());
		structureManager.getSpecificStructure(structure).incrementEnergyResourceLevel(amountOfPowerGiven);
	}

	public void distributeMetal(Structure structure,int percentage){
		int amountOfMetalGiven=(int)((double) (percentage)/100*metal.getAmount());
		structureManager.getSpecificStructure(structure).incrementMetalResourceLevel(amountOfMetalGiven);
	}

	public void distributeNutrients(Structure structure,int percentage){
		int amountOfNutrientsGiven=(int)((double) (percentage)/100*nutrients.getAmount());
		structureManager.getSpecificStructure(structure).incrementNutrientResourceLevel(amountOfNutrientsGiven);

	}

	public void distributePower(Army army,int percentage){
		int amountOfPowerGiven=(int)((double) (percentage)/100*power.getAmount());
		for(int i=0;i<armies.size();i++){
			if(armies.get(i)==army){
				armies.get(i).incrementEnergyResourceLevel(amountOfPowerGiven);
			}
		}
	}
	public void distributeMetal(Army army,int percentage){
		int amountOfMetalGiven=(int)((double) (percentage)/100*metal.getAmount());
		for(int i=0;i<armies.size();i++){
			if(armies.get(i)==army){
				armies.get(i).incrementMetalResourceLevel(amountOfMetalGiven);
			}

		}
	}
	public void distributeNutrients(Army army,int percentage){
		int amountOfNutrientsGiven=(int)((double) (percentage)/100*nutrients.getAmount());
		for(int i=0;i<armies.size();i++){
			if(armies.get(i)==army){
				armies.get(i).incrementNutrientResourceLevel(amountOfNutrientsGiven);
			}
		}
	}
	public void storeMetal(int percentage){
		power.setAmount((int)((double) (power.getAmount())*(percentage)/100));
	}
	public void storeNutrients(int percentage){
		power.setAmount((int)((double) (nutrients.getAmount())*(percentage)/100));
	}
	public void storePower(int percentage){
		power.setAmount((int)((double) (power.getAmount())*(percentage)/100));
	}

	public void chargeResources()
	{
		unitManager.chargeResources(nutrients);
		structureManager.chargeResources(metal, power);
	}
	
	public ControllableCollection getControllableCollection()
	{
		return new ControllableCollection(unitManager.getColonists(), unitManager.getExplorers(), 
				unitManager.getSoldiers(), unitManager.getRangedSoldiers(), armies, structureManager.getCapitals(), structureManager.getFarms(),
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

	public void notifyObserver(PlayerObserver observer, Worker object)
	{
		observer.update(this, object);
	}
	
	public void notifyObservers(Worker object)
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


	public int getWorkers(){
		return this.workers;
	}

	public int getTechnology() {
		return technology;
	}

	public void setTechnology(int technology) {
		this.technology = technology;
	}
}
