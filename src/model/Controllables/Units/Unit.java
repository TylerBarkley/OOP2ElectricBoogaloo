package model.Controllables.Units;
import model.Controllables.Controllable;
import model.Controllables.Stats.UnitStats;
import model.TerrainVisitor;
import model.observers.UnitObserver;

import java.util.ArrayList;

import model.Location;
import model.MapDirection;
import model.player.PlayerID;
import utilities.UnitVisitor;

public abstract class Unit implements Controllable, TerrainVisitor //implements OverviewVisitable, TurnObserver
{
	private int currentHealth;
	private int maxActionPoints;
	private int currentActionPoints;
	private int energyResourceLevel;
	private int metalResourceLevel;
	private int nutrientResourceLevel;

	private UnitStats myStats;
	private UnitID id;
	private Location location;
	private MapDirection md;
	private boolean isAlive;
	private ArrayList<UnitObserver> observers;

	protected Unit(){
		myStats = new UnitStats();
		observers=new ArrayList<UnitObserver>();
		maxActionPoints = myStats.getMovement();
		currentActionPoints = maxActionPoints;
		md = MapDirection.getNorth();
		isAlive=true;
	}

	protected Unit(Location loc){
		myStats = new UnitStats();
		maxActionPoints = myStats.getMovement();
		currentActionPoints = maxActionPoints;

		location = loc;
		md = MapDirection.getNorth();
		isAlive=true;
		observers=new ArrayList<UnitObserver>();
	}

	public void addObserver(UnitObserver observer)
	{
		observers.add(observer);
		notifyObserver(observer);
	}

	public void removeObserver(UnitObserver observer)
	{
		observers.remove(observer);
	}

	public void notifyObservers()
	{
		for(UnitObserver ob: observers)
		{
			ob.update(this);
		}
	}

	public void notifyObserver(UnitObserver observer)
	{
		observer.update(this);
	}

	public abstract void accept(UnitVisitor visitor);

	public void killMe() {
		isAlive=false;
		notifyObservers();
		//TODO KILLING SELF
		//REMOVING SELF FROM PLAYER REGISTRY AND OCCUPANCY MANAGER
		//POSSIBLY USING PLAYER MANAGER
		//Possibly a death observer/visitor

		//FOR TESTING PURPOSES
		System.out.println("KILLING");
	}

	public void damageMe(int intensity) {
		currentHealth -= (intensity - myStats.getArmor());
		if(currentHealth <= 0){
			this.killMe();
		}

		notifyObservers();
	}

	public void healMe(int intensity){
		currentHealth += intensity;
		if(currentHealth > myStats.getHealth()){
			currentHealth = myStats.getHealth();
		}

		notifyObservers();
	}

	public int getCurrentHealth(){
		return this.currentHealth;
	}

	public void setCurrentHealth(int currentHealth){
		this.currentHealth = currentHealth;

		notifyObservers();
	}


	public void makeArmy(){
		//TODO just copy Iteration 1 code for this
	}

	public void setMyStats(UnitStats myStats) {
		this.myStats = myStats;

		this.setActionPoints(myStats.getMovement());

		notifyObservers();
	}

	public void setID(UnitID id)
	{
		this.id=id;

		notifyObservers();
	}

	public UnitID getID()
	{
		return id;
	}

	public UnitStats getMyStats(){
		return this.myStats;
	}

	public PlayerID getPid(){
		return id.getPlayerID();
	}

	public Location getLocation() {
		return location;
	}

	public MapDirection getMapDirection(){
		return md;
	}

	public void setLocation(Location loc){
		this.location = loc;

		notifyObservers();
	}

	public void setMapDirection(MapDirection md){
		this.md = md;

		notifyObservers();
	}

	public void malnourish() {
		// TODO This is called if there aren't enough resources for upkeep
		if(energyResourceLevel<0){
			damageMe(1);
		}
		if(metalResourceLevel<0){
			damageMe(1);
		}
		if(nutrientResourceLevel<0){
			damageMe(1);
		}

	}

	public int getUpkeep() {
		return myStats.getUpkeep();
	}

	public abstract boolean canEscort();

	public int getMaxActionPoints(){
		return maxActionPoints;
	}

	public int getActionPoints(){
		return currentActionPoints;
	}

	public void setActionPoints(int AP) {
		this.maxActionPoints = AP;
		this.currentActionPoints = AP;
	}

	public void resetAP() {
		maxActionPoints = myStats.getMovement();
		currentActionPoints = maxActionPoints;
	}

	public void reduceAP(int amount){
		this.currentActionPoints -= amount;
	}

	public void refreshAP(){
		this.currentActionPoints += maxActionPoints;

		if(currentActionPoints > maxActionPoints){
			currentActionPoints = maxActionPoints;
		}
	}

	public boolean isAlive(){
		return isAlive;
	}

	public int getEnergyResourceLevel() {
		return energyResourceLevel;
	}

	public void setEnergyResourceLevel(int energyResourceLevel) {
		this.energyResourceLevel = energyResourceLevel;
	}

	public int getMetalResourceLevel() {
		return metalResourceLevel;
	}

	public void setMetalResourceLevel(int metalResourceLevel) {
		this.metalResourceLevel = metalResourceLevel;
	}

	public int getNutrientResourceLevel() {
		return nutrientResourceLevel;
	}

	public void setNutrientResourceLevel(int nutrientResourceLevel) {
		this.nutrientResourceLevel = nutrientResourceLevel;
	}
	public void incrementNutrientResourceLevel(int increment){
		nutrientResourceLevel+=increment;
	}
	public void incrementEnergyResourceLevel(int increment){
		energyResourceLevel+=increment;
	}
	public void incrementMetalResourceLevel(int increment){
		metalResourceLevel+=increment;
	}
}

