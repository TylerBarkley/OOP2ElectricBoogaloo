package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class JoinArmyState extends UnitMenuState {

    private static JoinArmyState instance = new JoinArmyState();
    public static JoinArmyState getInstance(){return instance;}
    private JoinArmyState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: currentUnit.joinArmy();  make join army menu
    }

    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = MakeArmyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        UnitMenuState nextState = BuildCapitalState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Join Army";
    }
}