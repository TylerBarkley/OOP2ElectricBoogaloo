package control.MenuStates.StructureMenuStates.CapitalMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/8/17.
 */
public class CapitalAssignWorkersMineState extends CapitalMenuState {
    private static CapitalAssignWorkersMineState instance = new CapitalAssignWorkersMineState();
    public static CapitalAssignWorkersMineState getInstance(){return instance;}
    private CapitalAssignWorkersMineState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
            currentStructure.assignWorkersToMine(context.getFocus(), PopUpMenuWindow.WorkerMenu());
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = CapitalAssignWorkersFarmState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = CapitalAssignWorkersPowerHarvestState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Assign Workers Mine";
    }
}
