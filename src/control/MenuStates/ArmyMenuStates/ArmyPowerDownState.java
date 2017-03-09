package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class ArmyPowerDownState extends ArmyMenuState {
    private static ArmyPowerDownState instance = new ArmyPowerDownState();
    public static ArmyPowerDownState getInstance(){return instance;}
    private ArmyPowerDownState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: currentArmy.powerDown();
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyPowerUpState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Power Down";
    }
}
