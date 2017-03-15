package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class ArmyPowerUpState extends ArmyMenuState {
    private static ArmyPowerUpState instance = new ArmyPowerUpState();
    public static ArmyPowerUpState getInstance(){return instance;}
    private ArmyPowerUpState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        currentArmy.powerUp();
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyPowerDownState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyDropOffWorkersState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Power Up";
    }
}
