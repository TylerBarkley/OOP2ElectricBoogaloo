package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import control.MenuStates.StructureMenuState;
import control.MenuStates.StructureMenuStates.StructureAttackState;
import model.AttackCommand;
import model.Controllables.Army;

/**
 * Created by hankerins on 3/8/17.
 */
public class ArmyAttackState extends ArmyMenuState {
    private static ArmyAttackState instance = new ArmyAttackState();
    public static ArmyAttackState getInstance(){return instance;}
    private ArmyAttackState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        currentArmy.giveOrder(new AttackCommand(currentArmy, context.getFocus()));
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyCancelQueueState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyDefendState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Attack";
    }
}
