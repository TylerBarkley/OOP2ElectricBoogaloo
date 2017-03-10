package model.Controllables;


import model.Controllables.Stats.ArmyStats;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;
import model.observers.ArmyObserver;
import model.observers.UnitObserver;
import utilities.ArmyVisitor;
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

    private CommandQueue myCommands;
    private ArmyStats armyStats;
    private ArrayList<Unit> battleGroup;

	private ArrayList<ArmyObserver> observers;

    public Army()
    {
		observers=new ArrayList<ArmyObserver>();
	}

	public void addObserver(ArmyObserver observer)
	{
		observers.add(observer);
		notifyObserver(observer);
	}
	
	public void removeObserver(ArmyObserver observer)
	{
		observers.remove(observer);
	}
	
	public void notifyObservers()
	{
		for(ArmyObserver ob: observers)
		{
			ob.update(this);
		}
	}
    
	public void notifyObserver(ArmyObserver observer)
	{
		observer.update(this);
	}
	
    public void addUnitToBattleGroup(Unit unit){
        battleGroup.add(unit);
        armyStats.addStats(unit.getMyStats());
        unit.setActionPoints(armyStats.getMovement());
        updateEscort();
    }

    public void removeUnitFromBattleGroup(Unit unit){
        battleGroup.remove(unit);
        armyStats.removeStats(unit.getMyStats());
        unit.resetAP();
        updateEscort();
    }

    public void updateEscort(){
        canEscort = false;

        for(Unit unit : battleGroup){
            if(unit.canEscort()){ canEscort = true; }
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
    }

    public void doTurn(){
        while(canMove && !myCommands.isEmpty()){
            myCommands.carryOut();
        }
    }


    public Location getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
    }

    public boolean isCanEscort() {
        return canEscort;
    }

    public ArmyStats getArmyStats() {
        return armyStats;
    }
    
    public void accept(ArmyVisitor visitor){
    	visitor.visit(this);
    }
}
