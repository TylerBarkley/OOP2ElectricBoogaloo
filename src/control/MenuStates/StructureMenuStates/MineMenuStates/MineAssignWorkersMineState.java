package control.MenuStates.StructureMenuStates.MineMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/14/17.
 */
public class MineAssignWorkersMineState extends MineMenuState {
    private static MineAssignWorkersMineState instance = new MineAssignWorkersMineState();
    public static MineAssignWorkersMineState getInstance(){return instance;}
    private MineAssignWorkersMineState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.assignWorkersToMine(context.getFocus(), PopUpMenuWindow.WorkerMenu());
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = MineUnassignAllState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = MineDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Assign Workers Mine";
    }
}
