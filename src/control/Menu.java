package control;

import control.MenuStates.ArmyMenuStates.ArmyAttackState;

import java.util.ArrayList;

import control.MenuStates.MenuState;
import control.MenuStates.RallyPointMenuStates.RPSetState;
import control.MenuStates.StructureMenuStates.CapitalMenuStates.CapitalAssignWorkersMineState;
import control.MenuStates.StructureMenuStates.FarmMenuStates.FarmAssignWorkersFarmState;
import control.MenuStates.UnitMenuStates.BuildCapitalState;
import control.MenuStates.UnitMenuStates.MakeArmyState;
import model.Location;
import model.MapDirection;
import model.TurnManager;
import model.Controllables.Army;
import model.Controllables.Controllable;
import model.Controllables.ControllableCollection;
import model.Controllables.RallyPoint;
import model.Controllables.Structures.Structure;
import model.Controllables.Units.Unit;
import model.observers.MenuObserver;
import model.observers.PlayerObserver;
import model.observers.StartTurnObserver;
import model.player.Player;
import model.player.PlayerID;
import model.player.PlayerManager;
import utilities.MenuVisitor;

/**
 * Created by hankerins on 3/5/17.
 *
 *
 *
 *
 */
public class Menu implements PlayerObserver, StartTurnObserver{

	public final static int RALLYPOINTMODE      = 0;
	public final static int ARMYMODE       	    = 1;
	public final static int UNITMODE       	    = 2;
	public final static int STRUCTUREMODE       = 3;


	private MenuState menuState;
	private ControllableCollection controllableCollection;

	private int currentMode = UNITMODE;
	private ArrayList<MenuObserver> observers;
	private PlayerID id;
	private Location focus;
	
	public Menu(PlayerID id){
		this.id=id;
		controllableCollection=PlayerManager.getInstance().getControllableCollection(id);
		observers=new ArrayList<MenuObserver>();
		//Menu starts on Colonist - Build Capital.
		//Call updateControllable once to assign the currentUnit variable to the colonist
		setMenuState(BuildCapitalState.getInstance());
		menuState.updateControllable(this);
		
		setFocus(new Location(0,0));
	}

	public void addObserver(MenuObserver observer)
	{
		observers.add(observer);
		notifyObserver(observer);
	}

	public void removeObserver(MenuObserver observer)
	{
		observers.remove(observer);
	}

	public void notifyObservers()
	{
		for(MenuObserver ob: observers)
		{
			ob.update(this);
		}
	}

	public void notifyObserver(MenuObserver observer)
	{
		observer.update(this);
	}

	//State Design Pattern
	public void select(){

		menuState.select(this);
		updateControllableCollection();
		//reset();
	}

	//check if a given instance exists in the ControllableCollection
	public boolean instanceExists(int x, int y){
		try{
		if (controllableCollection.get(x, y) != null)
			return true;

		else 
			return false;
		}
		catch(IndexOutOfBoundsException e)
		{
			return false;
		}
	}

	public void cycleModeL(){
		int startedMode = currentMode;
		do{
			currentMode--;
			if(currentMode < RALLYPOINTMODE) {
				currentMode = STRUCTUREMODE;
			}
		} while(!controllableCollection.controllableExists(currentMode) && currentMode != startedMode);
		//change state here to first instruction of each mode, call reset()
		if(currentMode != startedMode){
			switch (currentMode){
			case RALLYPOINTMODE:
				setMenuState(RPSetState.getInstance());
				break;
			case ARMYMODE:
				setMenuState(ArmyAttackState.getInstance());
				break;
			case UNITMODE:
				setMenuState(MakeArmyState.getInstance());
				break;
			case STRUCTUREMODE:
				//does this need to be changed?
				setMenuState(CapitalAssignWorkersMineState.getInstance());
				break;
			}
			menuState.reset(this);
		}
		
		notifyObservers();
	}
	public void cycleModeR(){
		int startedMode = currentMode;
		do{
			currentMode++;
			if(currentMode > STRUCTUREMODE) {
				currentMode = RALLYPOINTMODE;
			}
		} while(!controllableCollection.controllableExists(currentMode) && currentMode != startedMode);
		//change state here to first instruction of each mode, call reset()
		if(currentMode != startedMode){
			switch (currentMode){
			case RALLYPOINTMODE:
				setMenuState(RPSetState.getInstance());
				break;
			case ARMYMODE:
				setMenuState(ArmyAttackState.getInstance());
				break;
			case UNITMODE:
				setMenuState(MakeArmyState.getInstance());
				break;
			case STRUCTUREMODE:
				setMenuState(CapitalAssignWorkersMineState.getInstance());
				break;
			}
			menuState.reset(this);
		}
		
		notifyObservers();
	}

    public int getCurrentMode() {return currentMode;}
    
	//Call reset() at beginning of turn or when a Controllable ceases to exist
	public void reset(){
		cycleModeL();
		cycleModeR();
		
		cycleTypeL();
		cycleTypeR();
		
		cycleInstanceL();
		cycleInstanceR();

		if(controllableCollection.controllableExists(currentMode)){
			cycleInstructionL();
			cycleInstructionR();
		}
		notifyObservers();
	}
    
    public String typeToString() {
    	return menuState.typeToString();
    }

	public void cycleTypeL()
	{
		menuState.cycleTypeL(this);
		notifyObservers();
	}

	public void cycleTypeR()
	{
		menuState.cycleTypeR(this);
		notifyObservers();
	}

	public void cycleInstanceL()
	{
		menuState.cycleInstanceL(this);
		notifyObservers();
	}

	public void cycleInstanceR()
	{
		menuState.cycleInstanceR(this);
		notifyObservers();
	}

	public void cycleInstructionL()
	{	menuState.updateControllable(this);
		menuState.cycleInstructionL(this);
		notifyObservers();
	}

	public void cycleInstructionR()
	{	menuState.updateControllable(this);
		menuState.cycleInstructionR(this);
		notifyObservers();
	}

	public MenuState getMenuState() 
	{
		return menuState;
	}

	public void setMenuState(MenuState menuState)
	{
		this.menuState = menuState;
		notifyObservers();
	}

	public ControllableCollection getControllableCollection() 
	{
		return controllableCollection;
	}

	public int getCurrentType() {
		return menuState.getCurrentType();
	}

	public int getCurrentInstanceNumber() {
		return menuState.getCurrentInstanceNumber();
	}

	public Controllable getCurrentInstance() {
		return menuState.getCurrentInstance();
	}
	
	public String getCurrentInstruction() { 
		return menuState.toString();
	}

	public String modeToString(){
		switch (currentMode){
		case RALLYPOINTMODE: return "Rally Point";
		case ARMYMODE: return "Army";
		case UNITMODE: return "Unit";
		case STRUCTUREMODE: return "Structure";
		}
		return null;
	}

	public void printState(){
		System.out.println("Mode: " + modeToString());
		System.out.println("Type: " + menuState.typeToString());
		System.out.println("Instance: " + menuState.getCurrentInstanceNumber());
		System.out.println("Instruction: " + menuState.toString());
	}

	public void accept(MenuVisitor visitor)
	{
		visitor.visit(this);
	}
	
	public void updateControllableCollection(){
		controllableCollection=PlayerManager.getInstance().getControllableCollection(id);
		reset();

		notifyObservers();
	}

	public Location getFocus() {
		return focus;
	}

	public void setFocus(Location focus) {
		this.focus = focus;
		notifyObservers();
	}
	
	public void setFocus(MapDirection direction) {
		this.focus = focus.getAdjacent(direction);
		notifyObservers();
	}

	@Override
	public void startUpdate(TurnManager turn) {
		id=turn.getCurrentPlayerID();
		updateControllableCollection();
	}

	@Override
	public void update(Player player) {
		updateControllableCollection();
	}

	@Override
	public void update(Player player, Unit unit) {
		update(player);
	}

	@Override
	public void update(Player player, Structure structure) {
		update(player);
	}

	@Override
	public void update(Player player, Army army) {
		update(player);
	}

	@Override
	public void update(Player player, RallyPoint rp) {
		update(player);
	}
}
