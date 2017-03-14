package control.MenuStates.RallyPointMenuStates;

import control.Menu;
import control.MenuStates.RallyPointMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class RPSetState extends RallyPointMenuState{
    private static RPSetState instance = new RPSetState();
    public static RPSetState getInstance(){return instance;}
    private RPSetState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        currentRallyPoint.setLocation(context.getFocus());
        
    }
    public void cycleInstructionL(Menu context){
        RallyPointMenuState nextState = RPSetState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        RallyPointMenuState nextState = RPSetState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Set";
    }
}
