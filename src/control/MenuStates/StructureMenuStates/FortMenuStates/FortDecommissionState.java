package control.MenuStates.StructureMenuStates.FortMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

public class FortDecommissionState extends FortMenuState{
    private static FortDecommissionState instance = new FortDecommissionState();
    public static FortDecommissionState getInstance(){return instance;}
    private FortDecommissionState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.killMe();
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = ProduceRangedState.getInstance();
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
        return "Decommission";
    }
}
