package model.Controllables;

import model.*;
import model.Controllables.Units.Unit;
import model.Map.Map;
import model.observers.EndTurnObserver;
import model.observers.StartTurnObserver;
import model.observers.UnitObserver;
import model.player.PlayerID;
import model.player.PlayerManager;

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
public class RallyPoint implements Controllable, UnitObserver, StartTurnObserver, EndTurnObserver {
    private Location location;

    Map map;

    private PlayerID playerID;

    private MovementManager movementManager;

    private Army myArmy;
    private ArrayList<Unit> reinforcements;
    private Queue<Unit> waitingForArmy;

    private HashMap<Location, Location> path;

    private ArrayList<RPObserver> observers;
    
    private boolean isActive;

	private RPID id;
    
    public RallyPoint(Unit myUnit){

        this.map = Map.getInstance();

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
        this.map = Map.getInstance();

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

        if(!movementManager.validateMove(this.getPlayerID(), location)){
            location = map.getNearestValid(this.getPlayerID(), location);
        }

        this.location = location;
        this.getPath();
        this.orderArmyMove();
    }

    private void getPath() {
        path = map.BFS(this.getPlayerID(), this.location);
    }

    public void doTurn(){

        ArrayList<Unit> tempUnits = new ArrayList<Unit>();
        for(Unit unit : reinforcements){
            tempUnits.add(unit);
        }

        for(Unit unit : tempUnits){
            while(unit.canMove()){

                if(this.path.get(unit.getLocation()) == null){
                    reinforcements.remove(unit);
                    waitingForArmy.add(unit);
                    break;
                }

                if(!movementManager.validateMove(unit, this.path.get(unit.getLocation()))){
                    this.getPath();
                    continue;
                }

                movementManager.makeMove(unit, this.path.get(unit.getLocation()));
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

        //myArmy.clearQueue();

        Location moveLocation = this.path.get(myArmy.getLocation());

        while(moveLocation != null){
            myArmy.giveOrder(new MoveCommand(myArmy, moveLocation));

            moveLocation = this.path.get(moveLocation);
        }
        
        notifyObservers();
    }

    public void reinforce(Unit unit){
       if(path.get(unit.getLocation()) == null){
            waitingForArmy.add(unit);
        }
        else {
            reinforcements.add(unit);
        }
        unit.addObserver(this);
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

	public void setID(RPID id) {
		this.id=id;
	}
	
	public RPID getID() {
		return id;
	}

    public PlayerID getPlayerID() {
        return id.getPlayerID();
    }

    @Override
    public void update(Unit unit) {
        if(!unit.isAlive()){
            if(!reinforcements.remove(unit)){
                waitingForArmy.remove(unit);
            }
        }
    }

    @Override
    public void endUpdate(TurnManager turn) {
        this.doTurn();
    }

    @Override
    public void startUpdate(TurnManager turn) {
        this.startTurn();
    }
}
