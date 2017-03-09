package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class PowerDownState extends UnitMenuState {
    private static PowerDownState instance = new PowerDownState();
    public static PowerDownState getInstance(){return instance;}
    private PowerDownState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: currentUnit.powerDown();
    }
    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = StandbyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        UnitMenuState nextState = PowerUpState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Power Down";
    }
}
