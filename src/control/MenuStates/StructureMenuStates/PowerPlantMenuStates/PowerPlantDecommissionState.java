package control.MenuStates.StructureMenuStates.PowerPlantMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.MenuStates.StructureMenuStates.ObservationTowerMenuStates.OTDecommissionState;

/**
 * Created by hankerins on 3/14/17.
 */
public class PowerPlantDecommissionState extends PowerPlantMenuState {
    private static PowerPlantDecommissionState instance = new PowerPlantDecommissionState();
    public static PowerPlantDecommissionState getInstance(){return instance;}
    private PowerPlantDecommissionState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        //TODO: decommission
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = PPAssignWorkersPowerHarvestState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = PPAssignWorkersPowerHarvestState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Decommission";
    }
}
