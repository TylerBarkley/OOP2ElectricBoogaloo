package control.MenuStates.StructureMenuStates.CapitalMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/14/17.
 */
public class CapitalProduceWorkerState extends CapitalMenuState{
    private static CapitalProduceWorkerState instance = new CapitalProduceWorkerState();
    public static CapitalProduceWorkerState getInstance(){return instance;}
    private CapitalProduceWorkerState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
            currentStructure.assignWorkersToBreed(PopUpMenuWindow.WorkerMenu());
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = CapitalAssignWorkersPowerHarvestState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = CapitalProduceExplorerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Breed Workers";
    }
}
