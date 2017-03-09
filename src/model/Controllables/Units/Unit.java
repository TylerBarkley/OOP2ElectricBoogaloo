package model.Controllables.Units;
import model.Controllables.Controllable;
import model.Controllables.Stats.UnitStats;
import model.observers.Observable;
import model.Location;
import model.MapDirection;
import model.player.PlayerID;

public abstract class Unit extends Observable<Unit> implements Controllable //implements OverviewVisitable, TurnObserver
{
	private int currentHealth;
	private UnitStats myStats;
	private UnitID id;
	private Location location;
	private MapDirection md;
	private boolean isAlive;

	protected Unit(){
		myStats = new UnitStats();
	}

	protected Unit(Location loc){
		myStats = new UnitStats();
		location = loc;
		md = MapDirection.getNorth();
		isAlive=true;
	}

	//public abstract void accept(Visitor visitor);

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
	}

	public int getUpkeep() {
		return myStats.getUpkeep();
	}

	public boolean isAlive(){
		return isAlive;
	}
}

