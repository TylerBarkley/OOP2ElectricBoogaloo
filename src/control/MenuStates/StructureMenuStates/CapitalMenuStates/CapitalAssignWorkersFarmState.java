package control.MenuStates.StructureMenuStates.CapitalMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.MenuStates.StructureMenuStates.ProduceMeleeState;
import control.MenuStates.StructureMenuStates.ResearchTechnologyState;
import control.PopUpMenuWindow;
import model.Controllables.Structures.StructureID;

/**
 * Created by hankerins on 3/14/17.
 */
public class CapitalAssignWorkersFarmState extends CapitalMenuState {
    private static CapitalAssignWorkersFarmState instance = new CapitalAssignWorkersFarmState();
    public static CapitalAssignWorkersFarmState getInstance(){return instance;}
    private CapitalAssignWorkersFarmState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        currentStructure.assignWorkersToFarm(context.getFocus(), PopUpMenuWindow.WorkerMenu());

    }

    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = CapitalProduceExplorerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){

        StructureMenuState nextState = CapitalAssignWorkersMineState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Assign Workers Farm";
    }


}
