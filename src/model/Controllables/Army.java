package model.Controllables;


import model.Controllables.Stats.ArmyStats;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;
import model.Location;
import model.MapDirection;
import model.MovementManager;

import java.util.ArrayList;

/**
 * Created by hankerins on 3/5/17.
 */
public class Army implements Controllable//, DeathObserver
{
    //TODO LMAO DECAL

    private RallyPoint myRP;

    private Location myLocation;

    private boolean canMove;
    private boolean canEscort;

    private int workers;

    private CommandQueue myCommands;
    private ArmyStats armyStats;
    private ArrayList<Unit> battleGroup;

    public Army(Unit unit){
        this.myLocation = unit.getLocation();
        this.myCommands = new CommandQueue();
        this.armyStats = new ArmyStats();
        this.battleGroup = new ArrayList<Unit>();

        this.addUnitToBattleGroup(unit);

        this.myRP = new RallyPoint(this);
    }

    public Army() {
        //TODO REMOVE AND FIX
        //Currently for testing
    }

    public void addUnitToBattleGroup(Unit unit){
        battleGroup.add(unit);
        armyStats.addStats(unit.getMyStats());

        updateAP();
        updateEscort();
        //No Mike
    }

    public void removeUnitFromBattleGroup(Unit unit){
        battleGroup.remove(unit);
        armyStats.removeStats(unit.getMyStats());

        unit.resetAP();

        updateAP();
        updateEscort();
    }

    public void updateEscort(){
        canEscort = false;

        for(Unit unit : battleGroup){
            if(unit.canEscort()){ canEscort = true; }
        }
    }

    public void updateAP(){
        for(Unit unit : battleGroup){
            unit.setActionPoints(armyStats.getMovement());
        }
    }


    public void moveBattleGroup(MapDirection md){
        for(Unit unit : battleGroup){
            if(!MovementManager.getInstance().validateMove(unit, md)){

                //TODO MOVING THE RALLYPOINT TO THE ARMY'S CURRENT LOCATION
                //myRP.moveRallyPoint(this.myLocation);
                myCommands.clear();

                return;
            }
        }

        for(Unit unit : battleGroup){
            MovementManager.getInstance().makeMove(unit, md);
        }

        this.myLocation = myLocation.getAdjacent(md);
    }

    public void doTurn(){
        while(canMove && !myCommands.isEmpty()){
            myCommands.carryOut();
        }
    }

    public void disband(){
        for(Unit unit : battleGroup){
            unit.resetAP();
        }
        battleGroup.clear();

        myRP.detletThis();

        //TODO REMOVE FROM PLAYER
    }


    public Location getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
    }

    public boolean canEscort() {
        return canEscort;
    }

    public ArmyStats getArmyStats() {
        return armyStats;
    }

    public ArrayList<Unit> getBattleGroup(){
        return battleGroup;
    }
}
