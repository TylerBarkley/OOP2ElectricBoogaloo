package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class AssignWorkersMineState extends StructureMenuState {
    private static AssignWorkersMineState instance = new AssignWorkersMineState();
    public static AssignWorkersMineState getInstance(){return instance;}
    private AssignWorkersMineState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);

        //TODO: currentStructure.attack();
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = AssignWorkersFarmState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = ProduceWorkerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Assign Workers Mine";
    }
}
