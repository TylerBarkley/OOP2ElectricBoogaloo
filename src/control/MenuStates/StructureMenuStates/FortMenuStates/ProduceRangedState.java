package control.MenuStates.StructureMenuStates.FortMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/14/17.
 */
public class ProduceRangedState extends FortMenuState {
    private static ProduceRangedState instance = new ProduceRangedState();
    public static ProduceRangedState getInstance(){return instance;}
    private ProduceRangedState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
            currentStructure.assignWorkersToTrainRangedSoldiers(PopUpMenuWindow.WorkerMenu());
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = ProduceMeleeState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = ProduceMeleeState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Produce Ranged";
    }
}
