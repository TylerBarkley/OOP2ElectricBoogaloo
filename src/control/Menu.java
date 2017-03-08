package control;

import control.MenuStates.ArmyMenuStates.ArmyAttackState;
import control.MenuStates.MenuState;
import control.MenuStates.RallyPointMenuStates.RPBuildState;
import control.MenuStates.StructureMenuStates.StructureAttackState;
import control.MenuStates.UnitMenuStates.BuildCapitalState;
import control.MenuStates.UnitMenuStates.MakeArmyState;
import model.Controllables.ControllableCollection;

/**
 * Created by hankerins on 3/5/17.
 *
 * Q4D: are States allowed to access a context's field like a context itself?
 * Q4D: review good vs. bad casting
 * TODO: write a reset() method to be called whenever turn changes or something dies
 */
public class Menu {

    public final static int RALLYPOINTMODE      = 0;
    public final static int ARMYMODE       	    = 1;
    public final static int UNITMODE       	    = 2;
    public final static int STRUCTUREMODE       = 3;


    MenuState menuState;
    ControllableCollection controllableCollection;

    int currentMode = UNITMODE;

    Menu(ControllableCollection controllableCollection){
        this.controllableCollection = controllableCollection;
        //Menu starts on Colonist - Build Capital.
        //Call updateControllable once to assign the currentUnit variable to the colonist
        setMenuState(BuildCapitalState.getInstance());
        menuState.updateControllable(this);
    }

    //State Design Pattern
    public void select(){
        menuState.select(this);
    }

    //check if a given instance exists in the ControllableCollection
    public boolean instanceExists(int x, int y){
        if (controllableCollection.get(x, y) != null)
            return true;
        else return false;
    }

    void cycleModeL(){
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
                    setMenuState(RPBuildState.getInstance());
                    break;
                case ARMYMODE:
                    setMenuState(ArmyAttackState.getInstance());
                    break;
                case UNITMODE:
                    setMenuState(MakeArmyState.getInstance());
                    break;
                case STRUCTUREMODE:
                    setMenuState(StructureAttackState.getInstance());
                    break;
            }
            menuState.reset(this);
        }
    }
    void cycleModeR(){
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
                    setMenuState(RPBuildState.getInstance());
                    break;
                case ARMYMODE:
                    setMenuState(ArmyAttackState.getInstance());
                    break;
                case UNITMODE:
                    setMenuState(MakeArmyState.getInstance());
                    break;
                case STRUCTUREMODE:
                    setMenuState(StructureAttackState.getInstance());
                    break;
            }
            menuState.reset(this);
        }
    }

    void cycleTypeL(){
        menuState.cycleTypeL(this);
    }
    void cycleTypeR(){
        menuState.cycleTypeR(this);

    }
    void cycleInstanceL(){
        menuState.cycleInstanceL(this);
    }
    void cycleInstanceR(){
        menuState.cycleInstanceR(this);
    }
    void cycleInstructionL(){
        menuState.cycleInstructionL(this);
    }
    void cycleInstructionR(){
        menuState.cycleInstructionR(this);

    }

    public MenuState getMenuState() {return menuState;}
    public void setMenuState(MenuState menuState){this.menuState = menuState;}

    public ControllableCollection getControllableCollection() {return controllableCollection;}

    public int getCurrentMode() {return currentMode;}

    //public int getCurrentType() {return currentType;}

    //public int getCurrentInstance() {return currentInstance;}

    String modeToString(){
        switch (currentMode){
            case RALLYPOINTMODE: return "Rally Point";
            case ARMYMODE: return "Army";
            case UNITMODE: return "Unit";
            case STRUCTUREMODE: return "Structure";
        }
        return null;
    }

    void printState(){
        System.out.println("Mode: " + modeToString());
        System.out.println("Type: " + menuState.typeToString());
        System.out.println("Instance: " + menuState.getCurrentInstance());
        System.out.println("Instruction: " + menuState.toString());
    }


}
