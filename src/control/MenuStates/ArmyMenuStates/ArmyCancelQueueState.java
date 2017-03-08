package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class ArmyCancelQueueState extends ArmyMenuState {
    private static ArmyCancelQueueState instance = new ArmyCancelQueueState();
    public static ArmyCancelQueueState getInstance(){return instance;}
    private ArmyCancelQueueState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: currentArmy.attack();
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyPowerUpState.getInstance();
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
        return "Cancel Queue";
    }
}
