package model.Controllables;

import model.Controllables.Units.Unit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import model.Location;
import model.observers.RPObserver;
import model.MoveCommand;
import model.MovementManager;
import model.player.PlayerID;
import model.player.PlayerManager;
import utilities.RPVisitor;


/**
 * Created by zrgam_000 on 3/9/2017.
 */
public class RallyPoint implements Controllable {
    private Location location;

    private MovementManager movementManager;

    private Army myArmy;
    private ArrayList<Unit> reinforcements;
    private Queue<Unit> waitingForArmy;

    private HashMap<Location, Location> path;

    private ArrayList<RPObserver> observers;
    
    private boolean isActive;

	private RPID id;
    
    public RallyPoint(Unit myUnit){
    	observers = new ArrayList<RPObserver>();
        this.movementManager = MovementManager.getInstance();

        this.location = myUnit.getLocation();
        this.reinforcements = new ArrayList<Unit>();
        this.waitingForArmy = new LinkedList<Unit>();
        this.myArmy = new Army(myUnit, this);
        
        isActive=true;
        
        PlayerManager.getInstance().addRallyPoint(myUnit.getPid(), this);
        
        this.getPath();
    }

    public RallyPoint(Unit myUnit, Location location){
    	observers = new ArrayList<RPObserver>();
        this.movementManager = MovementManager.getInstance();

        this.location = location;
        this.reinforcements = new ArrayList<Unit>();
        this.waitingForArmy = new LinkedList<Unit>();
        this.myArmy = null;

        reinforcements.add(myUnit);
        
        isActive=true;
        
        PlayerManager.getInstance().addRallyPoint(myUnit.getPid(), this);
        
        this.getPath();
    }

	public void addObserver(RPObserver observer)
	{
		observers.add(observer);
		notifyObserver(observer);
	}

	public void removeObserver(RPObserver observer)
	{
		observers.remove(observer);
	}

	public void notifyObservers()
	{
		for(RPObserver ob: observers)
		{
			ob.update(this);
		}
	}

	public void notifyObserver(RPObserver observer)
	{
		observer.update(this);
	}
    
    public void moveRallyPoint(Location location){
        this.location = location;
        this.getPath();
        this.orderArmyMove();
    }

    private void getPath(){
        path = BFS();
    }

    private Location getNearestValid(){
        Location RPlocation = this.location;
        PlayerID PID = id.getPlayerID();

        HashSet<Location> visited2 = new HashSet<Location>();
        ArrayDeque<Location> q2 = new ArrayDeque<Location>();

        q2.add(RPlocation);
        visited2.add(RPlocation);

        while(!q2.isEmpty()){
            Location current = q2.poll();
            //if current is the goal, return current

            ArrayList<Location> adjacents = current.getAllLocationsWithinRadius(1);
            for(Location l: adjacents){
                if(!visited2.contains(l)){
                    visited2.add(l);

                    if(movementManager.validateMove(PID, l)) {
                        return l;
                    }
                }
            }
        }

        return null;
    }

    public HashMap<Location, Location> BFS(){

        PlayerID PID = id.getPlayerID();
        Location RPlocation = this.location;

        //HashMap<Location, MapDirection> paths = new HashMap<Location, MapDirection>();
        HashMap<Location, Location> parents = new HashMap<Location, Location>();

        HashSet<Location> visited = new HashSet<Location>();

        ArrayDeque<Location> q = new ArrayDeque<Location>();

        if(!movementManager.validateMove(PID, RPlocation)){
            RPlocation = getNearestValid();
            this.location = RPlocation;
        }

        parents.put(RPlocation, null);
        visited.add(RPlocation);
        q.add(RPlocation);

        while(!q.isEmpty()){
            Location current = q.poll();
            //if current is the goal, return current

            ArrayList<Location> adjacents = current.getAllLocationsWithinRadius(1);
            for(Location l: adjacents){
                if(!visited.contains(l)){
                    visited.add(l);

                        if(movementManager.validateMove(PID, l)) {
                            q.add(l);
                            parents.put(l, current);
                        }

                }
            }
        }
        
        notifyObservers();
        
        return parents;
    }

    public void doTurn(){
        boolean goodMove;

        Iterator<Unit> unitItr = reinforcements.iterator();


        while(unitItr.hasNext()){
            Unit unit = unitItr.next();

            while(unit.canMove()){

                if(path.get(unit.getLocation()) == null){
                    unitItr.remove();
                    waitingForArmy.add(unit);
                    break;
                }

                goodMove = movementManager.validateMove(unit, path.get(unit.getLocation()));

                if(!goodMove){
                    this.getPath();
                    continue;
                }

                movementManager.makeMove(unit, path.get(unit.getLocation()));

                if(path.get(unit.getLocation()) == null){
                    unitItr.remove();
                    waitingForArmy.add(unit);
                    break;
                }
            }
        }
        
        notifyObservers();
    }

    public void startTurn(){
        if(waitingForArmy.isEmpty()) return;

        if(myArmy == null){
            myArmy = new Army(waitingForArmy.poll(), this);
        }

        if(myArmy.getLocation().equals(this.getLocation())){
            while(!waitingForArmy.isEmpty()){
                myArmy.addUnitToBattleGroup(waitingForArmy.poll());
            }
        }
    }

    public void orderArmyMove(){
        if(myArmy == null) return;

        myArmy.clearQueue();

        Location moveLocation = this.path.get(myArmy.getLocation());

        while(moveLocation != null){
            myArmy.giveOrder(new MoveCommand(myArmy, moveLocation));

            moveLocation = this.path.get(moveLocation);
        }
        
        notifyObservers();
    }

    public void reinforce(Unit unit){
        reinforcements.add(unit);
        notifyObservers();
    }

    public void deletThis() {
        reinforcements.clear();
        waitingForArmy.clear();
        isActive=false;
        
        notifyObservers();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        notifyObservers();
    }

    public int getReinforcementSize(){
        return reinforcements.size();
    }

    public int getWaitingSize(){
        return waitingForArmy.size();
    }

    public Army getArmy() {
        return myArmy;
    }

	public boolean isActive() {
		return isActive;
	}
	
	public void accept(RPVisitor visitor){
		visitor.visit(this);
	}

	public void setRPID(RPID id) {
		this.id=id;
	}
	
	public RPID getID() {
		return id;
	}
}
