package control.MenuStates.StructureMenuStates.PowerPlantMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/14/17.
 */
public class PPAssignWorkersPowerHarvestState extends PowerPlantMenuState{
    private static PPAssignWorkersPowerHarvestState instance = new PPAssignWorkersPowerHarvestState();
    public static PPAssignWorkersPowerHarvestState getInstance(){return instance;}
    private PPAssignWorkersPowerHarvestState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.assignWorkersToPowerHarvest(context.getFocus(), PopUpMenuWindow.WorkerMenu());
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = PowerPlantDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = PowerPlantDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Assign Workers Harvest Power";
    }
}
