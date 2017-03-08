package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class PowerDownState extends UnitMenuState {
    static PowerDownState instance = new PowerDownState();
    public static PowerDownState getInstance(){return instance;}
    public PowerDownState(){}

    @Override
    public void select(Menu context) {

        updateUnit(context);
        //TODO: currentUnit.powerDown();
    }
    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = StandbyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        UnitMenuState nextState = PowerUpState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Power Down";
    }
}
