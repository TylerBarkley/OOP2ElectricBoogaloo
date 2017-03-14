package control.MenuStates.StructureMenuStates.CapitalMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/14/17.
 */
public class CapitalProduceExplorerState extends CapitalMenuState{
    private static CapitalProduceExplorerState instance = new CapitalProduceExplorerState();
    public static CapitalProduceExplorerState getInstance(){return instance;}
    private CapitalProduceExplorerState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.assignWorkersToTrainExplorers(PopUpMenuWindow.WorkerMenu());
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = CapitalProduceWorkerState.getInstance();
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
        return "Train Explorers";
    }

}
