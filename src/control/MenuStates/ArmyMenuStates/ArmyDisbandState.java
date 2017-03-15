package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class ArmyDisbandState extends ArmyMenuState {
    private static ArmyDisbandState instance = new ArmyDisbandState();
    public static ArmyDisbandState getInstance(){return instance;}
    private ArmyDisbandState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        currentArmy.disband();
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyBuildUniversityState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Disband";
    }
}
