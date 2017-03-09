package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.MenuStates.UnitMenuState;
import control.MenuStates.UnitMenuStates.StandbyState;

/**
 * Created by hankerins on 3/8/17.
 */
public class StructureAttackState extends StructureMenuState {
    private static StructureAttackState instance = new StructureAttackState();
    public static StructureAttackState getInstance(){return instance;}
    private StructureAttackState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: currentStructure.attack();
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = StructureAttackState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = StructureAttackState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Attack";
    }
}
