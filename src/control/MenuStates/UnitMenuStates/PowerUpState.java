package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class PowerUpState extends UnitMenuState {
    static PowerUpState instance = new PowerUpState();
    public static PowerUpState getInstance(){return instance;}
    public PowerUpState(){}

    @Override
    public void select(Menu context) {

        updateUnit(context);
        //TODO: currentUnit.powerUp();
    }
    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = PowerDownState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        UnitMenuState nextState = MakeArmyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Power Up";
    }
}
