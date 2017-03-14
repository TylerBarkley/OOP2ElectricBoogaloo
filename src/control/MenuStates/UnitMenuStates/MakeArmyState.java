package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;
import model.Controllables.RallyPoint;

/**
 * Created by hankerins on 3/5/17.
 */
public class MakeArmyState extends UnitMenuState{

    private static MakeArmyState instance = new MakeArmyState();
    public static MakeArmyState getInstance(){return instance;}
    private MakeArmyState(){}

    @Override
    public void select(Menu context) {
        
        updateControllable(context);
        new RallyPoint(currentUnit);
    }
    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = PowerUpState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        UnitMenuState nextState = JoinArmyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Make Army";
    }
}
