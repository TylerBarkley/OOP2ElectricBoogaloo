package control.MenuStates.StructureMenuStates.PowerPlantMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

/**
 * Created by Tyler Barkley on 3/15/2017.
 */
public class PowerPlantUnassignAllState extends PowerPlantMenuState {
    private static PowerPlantUnassignAllState instance = new PowerPlantUnassignAllState();
    public static PowerPlantUnassignAllState getInstance(){return instance;}
    private PowerPlantUnassignAllState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.unassign();
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = PowerPlantDecommissionState.getInstance();
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
        return "Unassign All Workers";
    }
}
