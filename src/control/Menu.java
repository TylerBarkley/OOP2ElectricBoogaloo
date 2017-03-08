package control;

import control.MenuStates.MenuState;
import model.Controllables.ControllableCollection;

/**
 * Created by hankerins on 3/5/17.
 *
 * Q4D: are States allowed to access a context's field like a context itself?
 * Q4D: review good vs. bad casting
 */
public class Menu {

    public final static int RALLYPOINTMODE      = 0;
    public final static int ARMYMODE       	    = 1;
    public final static int UNITMODE       	    = 2;
    public final static int STRUCTUREMODE       = 3;




    MenuState menuState;
    ControllableCollection controllableCollection;

    int currentMode = UNITMODE;
    //int currentType = COLONISTTYPE, currentInstance = 0;

    Menu(ControllableCollection controllableCollection){
        this.controllableCollection = controllableCollection;
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
        //change state here, call reset()
    }
    void cycleModeR(){
        int startedMode = currentMode;
        do{
            currentMode++;
            if(currentMode > STRUCTUREMODE) {
                currentMode = RALLYPOINTMODE;
            }
        } while(!controllableCollection.controllableExists(currentMode) && currentMode != startedMode);
        //change state here, call reset()
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
    void setMenuState(MenuState menuState){this.menuState = menuState;}

    public ControllableCollection getControllableCollection() {return controllableCollection;}

    public int getCurrentMode() {return currentMode;}

    //public int getCurrentType() {return currentType;}

    //public int getCurrentInstance() {return currentInstance;}


}
