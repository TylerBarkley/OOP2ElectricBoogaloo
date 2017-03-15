package control.MenuStates.StructureMenuStates.FortMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

/**
 * Created by Tyler Barkley on 3/15/2017.
 */
public class FortUnassignAllState extends FortMenuState{
    private static FortUnassignAllState instance = new FortUnassignAllState();
    public static FortUnassignAllState getInstance(){return instance;}
    private FortUnassignAllState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.unassign();
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = FortDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = ProduceMeleeState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Unassign All Workers";
    }
}
