package control.MenuStates.StructureMenuStates.FarmMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

/**
 * Created by Tyler Barkley on 3/15/2017.
 */
public class FarmUnassignAllState extends FarmMenuState {
    private static FarmUnassignAllState instance = new FarmUnassignAllState();
    public static FarmUnassignAllState getInstance(){return instance;}
    private FarmUnassignAllState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.unassign();
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = FarmDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = FarmAssignWorkersFarmState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Unassign All Workers";
    }
}
