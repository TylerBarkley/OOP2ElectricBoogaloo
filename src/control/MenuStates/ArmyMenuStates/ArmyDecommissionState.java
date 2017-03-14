package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;

/**
 * Created by hankerins on 3/8/17.
 */
public class ArmyDecommissionState extends ArmyMenuState{
    private static ArmyDecommissionState instance = new ArmyDecommissionState();
    public static ArmyDecommissionState getInstance(){return instance;}
    private ArmyDecommissionState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        currentArmy.decommission();
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyDisbandState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyPowerDownState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Decommission";
    }
}
