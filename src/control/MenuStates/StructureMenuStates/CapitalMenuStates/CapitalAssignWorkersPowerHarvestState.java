package control.MenuStates.StructureMenuStates.CapitalMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/14/17.
 */
public class CapitalAssignWorkersPowerHarvestState extends CapitalMenuState {
    private static CapitalAssignWorkersPowerHarvestState instance = new CapitalAssignWorkersPowerHarvestState();
    public static CapitalAssignWorkersPowerHarvestState getInstance(){return instance;}
    private CapitalAssignWorkersPowerHarvestState(){}

    @Override
    public void select(Menu context) {
    //this is bad type casting, violates OCP. We can do better.
        // Ideally each Structure has its own SubMenuState class because the lists are so different.
        updateControllable(context);
            currentStructure.assignWorkersToPowerHarvest(context.getFocus(), PopUpMenuWindow.WorkerMenu());
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = CapitalAssignWorkersMineState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = CapitalProduceWorkerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Assign Workers Harvest Power";
    }
}
