package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class BuildCapitalState extends UnitMenuState {

    private static BuildCapitalState instance = new BuildCapitalState();
    public static BuildCapitalState getInstance(){return instance;}
    private BuildCapitalState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: currentUnit.buildCapital();
    }
    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = BuildCapitalState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        UnitMenuState nextState = StandbyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Build Capital";
    }
}
