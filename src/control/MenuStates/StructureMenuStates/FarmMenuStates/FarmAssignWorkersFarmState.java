package control.MenuStates.StructureMenuStates.FarmMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/14/17.
 */
public class FarmAssignWorkersFarmState extends FarmMenuState{
    private static FarmAssignWorkersFarmState instance = new FarmAssignWorkersFarmState();
    public static FarmAssignWorkersFarmState getInstance(){return instance;}
    private FarmAssignWorkersFarmState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        currentStructure.assignWorkersToFarm(context.getFocus(), PopUpMenuWindow.WorkerMenu());

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
        return "Assign Workers Farm";
    }


}
