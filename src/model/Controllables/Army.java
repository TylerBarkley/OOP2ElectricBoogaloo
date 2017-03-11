package model.Controllables;


import model.*;
import model.Controllables.Stats.ArmyStats;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;
import model.observers.ArmyObserver;
import model.observers.UnitObserver;
import utilities.ArmyVisitor;

import java.util.ArrayList;

/**
 * Created by hankerins on 3/5/17.
 */
public class Army implements Controllable, Attacker//, DeathObserver
{
    //TODO LMAO DECAL

    private RallyPoint myRP;

    private Location myLocation;

    private boolean canMove;
    private boolean canEscort;

    private int energyResourceLevel;
    private int metalResourceLevel;
    private int nutrientResourceLevel;

    private int workers;

    private CommandQueue myCommands;
    private ArmyStats armyStats;
    private ArrayList<Unit> battleGroup;

	private ArrayList<ArmyObserver> observers;

	private boolean isDisbanded;
	
    public Army(Unit unit){
		observers=new ArrayList<ArmyObserver>();
		
        this.myLocation = unit.getLocation();
        this.myCommands = new CommandQueue();
        this.armyStats = new ArmyStats();
        this.battleGroup = new ArrayList<Unit>();

        this.canMove = true;

        this.addUnitToBattleGroup(unit);

        this.myRP = new RallyPoint(this);
		
		isDisbanded=false;
    }

    public Army()
    {
		//TODO REMOVE AND FIX
        //Currently for testing
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

        updateAP();
        updateEscort();
        //No Mike
        
        notifyObservers();
    }

    public void removeUnitFromBattleGroup(Unit unit){
        battleGroup.remove(unit);
        armyStats.removeStats(unit.getMyStats());

        unit.resetAP();

        updateAP();
        updateEscort();
        
        notifyObservers();
    }

    public void updateEscort(){
        canEscort = false;

        for(Unit unit : battleGroup){
            if(unit.canEscort()){ 
            	canEscort = true;
            	break;
           	} 
        }
        
        notifyObservers();
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
        
        notifyObservers();
    }

    public void doTurn(){

        for(Unit unit : battleGroup){
            if(unit.getActionPoints() <= 0){
                canMove = false;
            }
        }

        while(canMove && !myCommands.isEmpty()){
            myCommands.carryOut();

            for(Unit unit : battleGroup){
                if(unit.getActionPoints() <= 0){
                    canMove = false;
                }
            }
        }
    }

    public void giveOrder(Command command){
        this.myCommands.add(command);
    }

    public void startTurn(){
        this.canMove = true;
    }

    public void disband(){
    	isDisbanded=true;
        for(Unit unit : battleGroup){
            unit.resetAP();
        }
        battleGroup.clear();

        myRP.detletThis();
        
        notifyObservers();
    }


    public Location getLocation() {
        return myLocation;
    }

    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
        
        notifyObservers();
    }

    public boolean canEscort() {
        return canEscort;
    }

    public ArmyStats getArmyStats() {
        return armyStats;
    }

    public ArrayList<Unit> getBattleGroup() {
        return battleGroup;
    }
    
    public void accept(ArmyVisitor visitor){
    	visitor.visit(this);
    }

    public boolean canMove() {
        return canMove;
    }

    public CommandQueue getCommandQueue() {
        return myCommands;
    }

    public void distribute(){
        for(int i=0;i<battleGroup.size();i++){
            if(battleGroup.get(i).getUpkeep()>energyResourceLevel){
                battleGroup.get(i).incrementEnergyResourceLevel(battleGroup.get(i).getUpkeep());
                energyResourceLevel-=battleGroup.get(i).getUpkeep();
            }
            if(battleGroup.get(i).getUpkeep()>metalResourceLevel){
                battleGroup.get(i).incrementMetalResourceLevel(battleGroup.get(i).getUpkeep());
                metalResourceLevel-=battleGroup.get(i).getUpkeep();
            }
            if(battleGroup.get(i).getUpkeep()>nutrientResourceLevel){
                battleGroup.get(i).incrementEnergyResourceLevel(battleGroup.get(i).getUpkeep());
                nutrientResourceLevel-=battleGroup.get(i).getUpkeep();
            }
        }
    }

    public int getEnergyResourceLevel() {
        return energyResourceLevel;
    }

    public void setEnergyResourceLevel(int energyResourceLevel) {
        this.energyResourceLevel = energyResourceLevel;
    }

    public int getMetalResourceLevel() {
        return metalResourceLevel;
    }

    public void setMetalResourceLevel(int metalResourceLevel) {
        this.metalResourceLevel = metalResourceLevel;
    }

    public int getNutrientResourceLevel() {
        return nutrientResourceLevel;
    }

    public void setNutrientResourceLevel(int nutrientResourceLevel) {
        this.nutrientResourceLevel = nutrientResourceLevel;
    }
    public void incrementNutrientResourceLevel(int increment){
        nutrientResourceLevel+=increment;
    }
    public void incrementEnergyResourceLevel(int increment){
        energyResourceLevel+=increment;
    }
    public void incrementMetalResourceLevel(int increment){
        metalResourceLevel+=increment;
    }

    @Override
    public int getAttackDamage() {
        return armyStats.getOffensiveDamage();
    }

    public void attack(Location loc){
        AttackManager.getInstance().attack(this, loc);
    }
}
