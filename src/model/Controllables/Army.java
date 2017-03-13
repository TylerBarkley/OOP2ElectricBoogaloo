package model.Controllables;


import model.*;
import model.Controllables.Stats.ArmyStats;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;
import model.observers.ArmyObserver;
import model.observers.UnitObserver;
import model.player.Player;
import model.player.PlayerID;
import model.player.PlayerManager;
import utilities.ArmyVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hankerins on 3/5/17.
 */
public class Army implements Controllable, Attacker//, DeathObserver
{
    //TODO LMAO DECAL

    private Location myLocation;

    private MovementManager movementManager;

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
    private RallyPoint myRP;

    private ArmyID id;
    
    public Army(Unit unit, RallyPoint rallyPoint){
		observers=new ArrayList<ArmyObserver>();

        movementManager = MovementManager.getInstance();

        this.myRP = rallyPoint;

        this.myLocation = unit.getLocation();
        this.myCommands = new CommandQueue();
        this.armyStats = new ArmyStats();
        this.battleGroup = new ArrayList<Unit>();

        this.canMove = true;

        this.addUnitToBattleGroup(unit);

		isDisbanded=false;
		
		PlayerManager.getInstance().addArmy(rallyPoint.getPlayerID(), this);
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
            if(!movementManager.validateMove(unit, md)){

                //TODO MOVING THE RALLYPOINT TO THE ARMY'S CURRENT LOCATION
                myRP.moveRallyPoint(this.myLocation);
                myCommands.clear();

                return;
            }
        }

        for(Unit unit : battleGroup){
            movementManager.makeMove(unit, md);
        }

        this.myLocation = myLocation.getAdjacent(md);
        
        notifyObservers();
    }

    public void doTurn(){

        for(Unit unit : battleGroup){
            canMove = unit.canMove();
        }

        while(canMove && !myCommands.isEmpty()){
            myCommands.carryOut();

            for(Unit unit : battleGroup){
                canMove = unit.canMove();
            }
        }
    }

    public void giveOrder(Command command){
        this.myCommands.add(command);
    }

    public void startTurn(){
        this.canMove = true;

        Queue<Unit> ringOutYourDead = new LinkedList<Unit>();

        Iterator<Unit> unitItr = battleGroup.iterator();

        while(unitItr.hasNext()) {
            Unit unit = unitItr.next();
            if(!unit.isAlive()){
                ringOutYourDead.add(unit);
            }
        }

        while (!ringOutYourDead.isEmpty()){
            this.removeUnitFromBattleGroup(ringOutYourDead.poll());
        }

    }

    public void disband(){
    	isDisbanded=true;
        for(Unit unit : battleGroup){
            unit.resetAP();
        }
        battleGroup.clear();

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
        for(int i=0;i<battleGroup.size();i++) {
            if (battleGroup.get(i) != null) {
                if (battleGroup.get(i).getUpkeep() > energyResourceLevel) {
                    battleGroup.get(i).incrementEnergyResourceLevel(battleGroup.get(i).getUpkeep());
                    energyResourceLevel -= battleGroup.get(i).getUpkeep();
                }
                if (battleGroup.get(i).getUpkeep() > metalResourceLevel) {
                    battleGroup.get(i).incrementMetalResourceLevel(battleGroup.get(i).getUpkeep());
                    metalResourceLevel -= battleGroup.get(i).getUpkeep();
                }
                if (battleGroup.get(i).getUpkeep() > nutrientResourceLevel) {
                    battleGroup.get(i).incrementEnergyResourceLevel(battleGroup.get(i).getUpkeep());
                    nutrientResourceLevel -= battleGroup.get(i).getUpkeep();
                }
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

    public void clearQueue() {
        this.myCommands.clear();
    }

	public boolean isDisbanded() {
		return isDisbanded;
	}

    public PlayerID getPlayerID() {
        return id.getPlayerID();
    }

    public void removeWorkers(int workers) {
        if(this.workers - workers < 0){
            this.workers = 0;
            return;
        }

        this.workers -= workers;
    }

    public void addWorkers(int workers){
        this.workers += workers;
    }

	@Override
	public ControllableID getID() {
		return id;
	}
	
	public void setID(ArmyID id){
		this.id=id;
	}
}
