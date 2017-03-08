package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.MenuState;
import control.MenuStates.UnitMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class JoinArmyState extends UnitMenuState {

    static JoinArmyState instance = new JoinArmyState();
    public static JoinArmyState getInstance(){return instance;}
    public JoinArmyState(){}

    @Override
    public void select(Menu context) {

        updateUnit(context);
        //TODO: currentUnit.joinArmy();  make join army menu
    }

    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = MakeArmyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        UnitMenuState nextState = BuildCapitalState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Join Army";
    }
}
