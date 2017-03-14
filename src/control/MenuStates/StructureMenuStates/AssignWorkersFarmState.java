package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

/**
 * Created by hankerins on 3/14/17.
 */
public class AssignWorkersFarmState extends StructureMenuState {
    private static AssignWorkersFarmState instance = new AssignWorkersFarmState();
    public static AssignWorkersFarmState getInstance(){return instance;}
    private AssignWorkersFarmState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: currentStructure.attack();
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = AssignWorkersMineState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = AssignWorkersMineState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Assign Workers Farm";
    }
}
