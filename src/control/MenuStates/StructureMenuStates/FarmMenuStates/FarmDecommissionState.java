package control.MenuStates.StructureMenuStates.FarmMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.MenuStates.StructureMenuStates.ObservationTowerMenuStates.OTDecommissionState;

/**
 * Created by hankerins on 3/14/17.
 */
public class FarmDecommissionState extends FarmMenuState {
    private static FarmDecommissionState instance = new FarmDecommissionState();
    public static FarmDecommissionState getInstance(){return instance;}
    private FarmDecommissionState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        //TODO: decommission
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = FarmAssignWorkersFarmState.getInstance();
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
        return "Decommission";
    }
}