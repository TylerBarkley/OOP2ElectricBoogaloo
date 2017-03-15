package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import control.PopUpMenuWindow;

/**
 * Created by hankerins on 3/14/17.
 */
public class ArmyRemoveUnitState extends ArmyMenuState{
    private static ArmyRemoveUnitState instance = new ArmyRemoveUnitState();
    public static ArmyRemoveUnitState getInstance(){return instance;}
    private ArmyRemoveUnitState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        currentArmy.removeUnitFromBattleGroup(PopUpMenuWindow.UnitMenu(currentArmy.getBattleGroup()));
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyCancelQueueState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyAttackState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Remove Unit";
    }
}
