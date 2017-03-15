package control.MenuStates.StructureMenuStates.FortMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/14/17.
 */
public class ProduceMeleeState extends FortMenuState {
    private static ProduceMeleeState instance = new ProduceMeleeState();
    public static ProduceMeleeState getInstance(){return instance;}
    private ProduceMeleeState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.assignWorkersToTrainMeleeSoldiers(PopUpMenuWindow.WorkerMenu());
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = FortUnassignAllState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = ProduceRangedState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Produce Melee";
    }
}
