package control.MenuStates.StructureMenuStates.MineMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.MenuStates.StructureMenuStates.ObservationTowerMenuStates.OTDecommissionState;

/**
 * Created by hankerins on 3/14/17.
 */
public class MineDecommissionState extends MineMenuState {
    private static MineDecommissionState instance = new MineDecommissionState();
    public static MineDecommissionState getInstance(){return instance;}
    private MineDecommissionState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        //TODO: decommission
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = MineAssignWorkersMineState.getInstance();
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
        return "Decommission";
    }
}
