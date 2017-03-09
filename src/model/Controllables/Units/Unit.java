package model.Controllables.Units;
import model.Controllables.Controllable;
import model.Controllables.Stats.UnitStats;

import model.observers.DeathObservable;
import model.Location;
import model.MapDirection;
import model.player.PlayerID;

public abstract class Unit extends DeathObservable implements Controllable //implements OverviewVisitable, TurnObserver //implements OverviewVisitable, TurnObserver
{
	private int currentHealth;
	private int maxActionPoints;
	private int currentActionPoints;

	private UnitStats myStats;
	private UnitID id;
	private Location location;
	private MapDirection md;

	protected Unit(){
		myStats = new UnitStats();
		maxActionPoints = myStats.getMovement();
		currentActionPoints = maxActionPoints;
	}

	protected Unit(Location loc){
		myStats = new UnitStats();
		maxActionPoints = myStats.getMovement();
		currentActionPoints = maxActionPoints;

		location = loc;
		md = MapDirection.getNorth();
	}

	//public abstract void accept(Visitor visitor);

	public void killMe() {
		notifyObservers(id);
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
	}

	public void healMe(int intensity){
		currentHealth += intensity;
		if(currentHealth > myStats.getHealth()){
			currentHealth = myStats.getHealth();
		}
	}

	public int getCurrentHealth(){
		return this.currentHealth;
	}

	public void setCurrentHealth(int currentHealth){
		this.currentHealth = currentHealth;
	}


	public void makeArmy(){
		//TODO just copy Iteration 1 code for this
	}

	public void setMyStats(UnitStats myStats) {
		this.myStats = myStats;
	}

	public void setID(UnitID id)
	{
		this.id=id;
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
	}

	public void setMapDirection(MapDirection md){
		this.md = md;
	}

	public void malnourish() {
		// TODO This is called if there aren't enough resources for upkeep
	}

	public int getUpkeep() {
		return myStats.getUpkeep();
	}

	public abstract boolean canEscort();

	public void setActionPoints(int AP) {
		this.maxActionPoints = AP;
		this.currentActionPoints = AP;
	}

	public void resetAP() {
		maxActionPoints = myStats.getMovement();
		currentActionPoints = maxActionPoints;
	}
}

