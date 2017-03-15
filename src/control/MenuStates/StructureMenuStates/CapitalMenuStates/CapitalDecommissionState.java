package control.MenuStates.StructureMenuStates.CapitalMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

public class CapitalDecommissionState extends CapitalMenuState {
    private static CapitalDecommissionState instance = new CapitalDecommissionState();
    public static CapitalDecommissionState getInstance(){return instance;}
    private CapitalDecommissionState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        //TODO: decommission
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = CapitalProduceExplorerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = CapitalAssignWorkersFarmState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Decommission";
    }
}
