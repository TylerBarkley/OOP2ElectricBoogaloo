package model.Controllables;

import model.*;
import model.Controllables.Units.Unit;
import model.Map.Map;
import model.observers.EndTurnObserver;
import model.observers.StartTurnObserver;
import model.observers.UnitObserver;
import model.player.PlayerID;
import model.player.PlayerManager;

import java.util.*;

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

    public RallyPoint(Unit myUnit){

        this.map = Map.getInstance();

        this.movementManager = MovementManager.getInstance();

        this.location = myUnit.getLocation();
        this.playerID = myUnit.getPid();
        this.reinforcements = new ArrayList<Unit>();
        this.waitingForArmy = new LinkedList<Unit>();
        this.myArmy = new Army(myUnit, this);
        this.getPath();
    }

    public RallyPoint(Unit myUnit, Location location){
        this.map = Map.getInstance();

        this.movementManager = MovementManager.getInstance();

        this.location = location;
        this.playerID = myUnit.getPid();
        this.reinforcements = new ArrayList<Unit>();
        this.waitingForArmy = new LinkedList<Unit>();
        this.myArmy = null;

        reinforcements.add(myUnit);
        this.getPath();
    }

    public void moveRallyPoint(Location location){

        if(!movementManager.validateMove(this.getPlayerID(), location)){
            location = map.getNearestValid(this.getPlayerID(), location);
        }

        this.location = location;
        this.getPath();
        this.orderArmyMove();
    }

    private void getPath(){
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
    }

    public void reinforce(Unit unit){
        if(path.get(unit.getLocation()) == null){
            waitingForArmy.add(unit);
        }
        else {
            reinforcements.add(unit);
        }
        unit.addObserver(this);
    }

    public void deletThis() {
        reinforcements.clear();
        waitingForArmy.clear();
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public PlayerID getPlayerID() {
        return playerID;
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
