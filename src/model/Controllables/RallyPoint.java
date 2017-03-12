package model.Controllables;

import model.Controllables.Units.Unit;
import model.Location;
import model.Map.Map;
import model.MapDirection;
import model.MoveCommand;
import model.MovementManager;
import model.player.PlayerID;

import java.util.*;

/**
 * Created by zrgam_000 on 3/9/2017.
 */
public class RallyPoint implements Controllable {
    private Location location;

    private PlayerID playerID;

    private MovementManager movementManager;

    private Army myArmy;
    private ArrayList<Unit> reinforcements;
    private Queue<Unit> waitingForArmy;

    private HashMap<Location, Location> path;

    public RallyPoint(Unit myUnit){
        this.movementManager = MovementManager.getInstance();

        this.location = myUnit.getLocation();
        this.playerID = myUnit.getPid();
        this.reinforcements = new ArrayList<Unit>();
        this.waitingForArmy = new LinkedList<Unit>();
        this.myArmy = new Army(myUnit);
        this.getPath();
    }

    public RallyPoint(Unit myUnit, Location location){
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
        this.location = location;
        this.getPath();
        this.orderArmyMove();
    }

    private void getPath(){
        path = BFS();
    }

    private Location getNearestValid(){
        Location RPlocation = this.location;
        PlayerID PID = this.playerID;

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

        PlayerID PID = this.playerID;
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
        return parents;
    }

    public void doTurn(){
        boolean goodMove;

        Iterator<Unit> unitItr = reinforcements.iterator();


        while(unitItr.hasNext()){
            Unit unit = unitItr.next();

            while(unit.getActionPoints() >= 0){

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
    }

    public void startTurn(){
        if(waitingForArmy.isEmpty()) return;

        if(myArmy == null){
            myArmy = new Army(waitingForArmy.poll());
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
    }

    public void reinforce(Unit unit){
        reinforcements.add(unit);
    }

    public void deletThis() {
        reinforcements.clear();
        waitingForArmy.clear();
        //TODO REMOVE FROM PLAYER
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
}
