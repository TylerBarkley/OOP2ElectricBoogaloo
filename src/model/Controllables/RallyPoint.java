package model.Controllables;

import model.Controllables.Units.Unit;
import model.Location;
import model.Map.Map;
import model.MapDirection;
import model.MoveCommand;
import model.MovementManager;
import model.player.PlayerID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by zrgam_000 on 3/9/2017.
 */
public class RallyPoint {
    private Location location;

    private PlayerID playerID;

    private Army myArmy;
    private ArrayList<Unit> reinforcements;
    private Queue<Unit> waitingForArmy;

    private HashMap<Location, Location> path;

    public RallyPoint(Unit myUnit){
        this.playerID = myUnit.getPid();
        this.reinforcements = new ArrayList<Unit>();
        this.waitingForArmy = new LinkedList<Unit>();
        this.myArmy = new Army(myUnit);
        this.getPath();
    }

    public RallyPoint(Unit myUnit, Location location){
        this.playerID = myUnit.getPid();
        this.reinforcements = new ArrayList<Unit>();
        this.waitingForArmy = new LinkedList<Unit>();
        this.myArmy = null;

        reinforcements.add(myUnit);
    }

    public void moveRallyPoint(Location location){
        this.location = location;
        this.getPath();
        this.orderArmyMove();
    }

    public void getPath(){
        path = Map.getInstance().BFS(this.playerID, this.location);
    }

    public void doTurn(){
        boolean goodMove;

        for(Unit unit : reinforcements){
            while(unit.getActionPoints() >= 0){

                goodMove = MovementManager.getInstance().validateMove(unit, path.get(unit.getLocation()));

                if(!goodMove){
                    this.getPath();
                    continue;
                }

                MovementManager.getInstance().makeMove(unit, path.get(unit.getLocation()));

                if(path.get(unit.getLocation()) == null){
                    waitingForArmy.add(unit);
                    reinforcements.remove(unit);
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

        Location moveLocation = this.path.get(myArmy.getLocation());

        while(moveLocation != null){
            myArmy.giveOrder(new MoveCommand(myArmy, this.path.get(myArmy.getLocation())));
        }
    }

    public void detletThis() {
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
}
