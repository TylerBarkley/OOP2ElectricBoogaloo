package control.MenuStates.StructureMenuStates.CapitalMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/15/17.
 */
public class CapitalUnassignAllState extends CapitalMenuState {
    private static CapitalUnassignAllState instance = new CapitalUnassignAllState();
    public static CapitalUnassignAllState getInstance(){return instance;}
    private CapitalUnassignAllState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        currentStructure.unassign();

    }

    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = CapitalProduceExplorerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){

        StructureMenuState nextState = CapitalDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Unassign All Workers";
    }

}
