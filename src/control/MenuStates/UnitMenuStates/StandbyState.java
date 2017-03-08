package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class StandbyState extends UnitMenuState {
    static StandbyState instance = new StandbyState();
    public static StandbyState getInstance(){return instance;}
    public StandbyState(){}

    @Override
    public void select(Menu context) {

        updateUnit(context);
        //TODO: currentUnit.standby();
    }
    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = StandbyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        UnitMenuState nextState = PowerDownState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Standby";
    }
}
