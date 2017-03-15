package model.Controllables.Units;
import model.*;
import model.Controllables.Controllable;
import model.Controllables.Stats.UnitStats;
import model.observers.EndTurnObserver;
import model.observers.StartTurnObserver;
import model.observers.UnitObserver;

import java.util.ArrayList;

import model.player.PlayerID;
import utilities.UnitVisitor;

public abstract class Unit implements Controllable, TerrainVisitor, TileVisitor, StartTurnObserver, EndTurnObserver //implements OverviewVisitable, TurnObserver
{
	private int currentHealth;
	private int maxActionPoints;
	private int currentActionPoints;
	private int nutrientResourceLevel;
	private double powerState;

	private static final double POWERUP  = 1;
	private static final double POWERDOWN  = .25;
	private static final double POWERACTIVE  = 1.25;

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
		powerState = POWERUP;
		
		nutrientResourceLevel = 3 * this.getUpkeep();
		System.out.println("Unit Initial Upkeep: " + this.getUpkeep());
	}

	protected Unit(Location loc){
		myStats = new UnitStats();
		maxActionPoints = myStats.getMovement();
		currentActionPoints = maxActionPoints;

		location = loc;
		md = MapDirection.getNorth();
		isAlive=true;
		observers=new ArrayList<UnitObserver>();
		powerState = POWERUP;
	}

	public void addObserver(UnitObserver observer)
	{
		observers.add(observer);
		//notifyObserver(observer);
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

		System.out.println("KILLING");
	}

	public void damageMe(int intensity) {
		if(intensity - myStats.getArmor() < 0){
			return;
		}
		currentHealth -= (intensity - myStats.getArmor());
		if(currentHealth <= 0){
			this.killMe();
			return;
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



	public void setMyStats(UnitStats myStats) {
		this.myStats = myStats;

		this.currentHealth = myStats.getHealth();
		maxActionPoints = myStats.getMovement();
		currentActionPoints = maxActionPoints;

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

	public PlayerID getPlayerID(){
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
		if(nutrientResourceLevel<0){
			damageMe(15);
			nutrientResourceLevel = 0;
		}

	}
	public void distribute(){
		nutrientResourceLevel-=getUpkeep();
	}

	public int getUpkeep() {
		return (int)(myStats.getUpkeep() * powerState);
	}

	public abstract boolean canEscort();

	public int getMaxActionPoints(){
		return maxActionPoints;
	}

	public int getActionPoints(){
		return currentActionPoints;
	}

	public void setActionPoints(int AP) {
		this.currentActionPoints = AP;
	}

	public void setMaxActionPoints(int AP){
		this.maxActionPoints = AP;
		if(currentActionPoints > AP){
			currentActionPoints = AP;
		}
	}

	public void resetAP() {
		this.setMaxActionPoints(this.getMovement());

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

	public boolean canMove(){ return this.currentActionPoints > 0 && isAlive; }

	public int getNutrientResourceLevel() {
		return nutrientResourceLevel;
	}

	public void setNutrientResourceLevel(int nutrientResourceLevel) {
		this.nutrientResourceLevel = nutrientResourceLevel;
		notifyObservers();
	}
	public void incrementNutrientResourceLevel(int increment){
		nutrientResourceLevel+=increment;
		notifyObservers();
	}
	@Override
	public void endUpdate(TurnManager turn) {
		if(turn.getCurrentPlayerID() != this.getPlayerID()){
			return;
		}
	}

	@Override
	public void startUpdate(TurnManager turn) {

		if(turn.getCurrentPlayerID() != this.getPlayerID()){
			return;
		}

		distribute();
		malnourish();

		this.refreshAP();
	}

	public void powerUp(){powerState = POWERUP;}
	public void powerDown(){powerState = POWERDOWN;}
	public void powerActive(){powerState = POWERACTIVE;}

	public abstract int getMovement();

	public void cheatResources() {
		nutrientResourceLevel=Integer.MAX_VALUE;
	}

}

