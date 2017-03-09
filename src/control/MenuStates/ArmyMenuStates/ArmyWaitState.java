package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class ArmyWaitState extends ArmyMenuState {
    private static ArmyWaitState instance = new ArmyWaitState();
    public static ArmyWaitState getInstance(){return instance;}
    private ArmyWaitState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: currentArmy.wait();
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyDefendState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyDisbandState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Wait";
    }
}
