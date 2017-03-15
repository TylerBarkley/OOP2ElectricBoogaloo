package model.Controllables;


import model.*;
import model.Controllables.Stats.ArmyStats;
import model.Controllables.Units.Unit;
import model.observers.ArmyObserver;
import model.observers.EndTurnObserver;
import model.observers.StartTurnObserver;
import model.observers.UnitObserver;
import model.player.PlayerID;
import model.player.PlayerManager;
import utilities.ArmyVisitor;

import java.util.ArrayList;

/**
 * Created by hankerins on 3/5/17.
 */
public class Army implements Controllable, Attacker, UnitObserver, EndTurnObserver, StartTurnObserver
{
    //TODO LMAO DECAL


    private Location myLocation;

    private MovementManager movementManager;

    private boolean canMove;
    private boolean canEscort;

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

		isDisbanded=false;
		
		PlayerManager.getInstance().addArmy(unit.getPlayerID(), this);
        
        this.addUnitToBattleGroup(unit);
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

        unit.addObserver(this);

        updateAP();
        updateEscort();
        //No Mike
        
        notifyObservers();
    }

    public void removeUnitFromBattleGroup(Unit unit){
        battleGroup.remove(unit);
        armyStats.removeStats(unit.getMyStats());

        unit.resetAP();

        if(battleGroup.isEmpty()) {
            this.disband();
            return;
        }

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
            unit.setMaxActionPoints(armyStats.getMovement());
        }
    }


    public void moveBattleGroup(MapDirection md){
        for(Unit unit : battleGroup){
            if(!movementManager.validateMove(unit, md)){

                myRP.moveRallyPoint(this.myLocation);
                myCommands.clear();

                return;
            }
        }

        ArrayList<Unit> tempGroup = new ArrayList<Unit>();

        for (Unit unit : battleGroup){
            tempGroup.add(unit);
        }

        for(Unit unit : tempGroup){
            movementManager.makeMove(unit, md);
        }

        this.myLocation = myLocation.getAdjacent(md);
        
        notifyObservers();
    }

    public void doTurn(){

        for(Unit unit : battleGroup){
            if(!unit.canMove()) {
                canMove = false;
            }
        }

        while(canMove && !myCommands.isEmpty()){
            myCommands.carryOut();

            for(Unit unit : battleGroup){
                if(!unit.canMove()) {
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
        //Queue<Unit> ringOutYourDead = new LinkedList<Unit>();

        /*
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
        */

    }

    public void disband(){
    	isDisbanded=true;
        for(Unit unit : battleGroup){
            unit.resetAP();
        }
        battleGroup.clear();

        myRP.deletThis();

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

    public void distribute() {
        for (int i = 0; i < battleGroup.size(); i++) {
            if (battleGroup.get(i) != null) {
                if (battleGroup.get(i).getUpkeep() < nutrientResourceLevel && battleGroup.get(i).getNutrientResourceLevel()<=battleGroup.get(i).getUpkeep()) {
                    battleGroup.get(i).incrementNutrientResourceLevel(battleGroup.get(i).getUpkeep());
                    nutrientResourceLevel -= battleGroup.get(i).getUpkeep();
                }
            }
        }
    }

    public int getNutrientResourceLevel() {
        return nutrientResourceLevel;
    }

    public void incrementNutrientResourceLevel(int increment){
        nutrientResourceLevel+=increment;
        distribute();
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
    public void update(Unit unit) {
        if(!unit.isAlive()){
            this.removeUnitFromBattleGroup(unit);
        }
        this.updateAP();
    }

    @Override
    public void endUpdate(TurnManager turn) {

        if(turn.getCurrentPlayerID() != this.getPlayerID()){
            return;
        }

        this.doTurn();
    }

    @Override
    public void startUpdate(TurnManager turn) {

        if(turn.getCurrentPlayerID() != this.getPlayerID()){
            return;
        }

        this.startTurn();
    }

	@Override
	public ArmyID getID() {
		return id;
	}
	
	public void setID(ArmyID id){
		this.id=id;
	}

	public void decommission(){
        ArrayList<Unit> battleGroupCopy = new ArrayList<Unit>();
	    for(Unit u: battleGroup){
	        battleGroupCopy.add(u);
        }
        for(Unit u: battleGroupCopy){
            u.killMe();
        }
    }

    public void powerUp(){
        for(Unit u: battleGroup){
            u.powerUp();
        }
    }
    public void powerDown(){
        for(Unit u: battleGroup){
            u.powerDown();
        }
    }
    public void powerActive(){
        for(Unit u: battleGroup){
            u.powerActive();
        }
    }

    public void setCanMove(boolean b){
        canMove = b;
    }

    public int getAttackRange(){
        return armyStats.getMovement();
    }
}
