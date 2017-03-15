package control.MenuStates.StructureMenuStates.MineMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

/**
 * Created by Tyler Barkley on 3/15/2017.
 */
public class MineUnassignAllState extends MineMenuState{

    private static MineUnassignAllState instance = new MineUnassignAllState();
    public static MineUnassignAllState getInstance(){return instance;}
    private MineUnassignAllState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.unassign();
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = MineDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = MineAssignWorkersMineState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Unassign All Workers";
    }
}
