package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.UnitID;

/**
 * Created by hankerins on 3/8/17.
 */
public class StandbyState extends UnitMenuState {
    private static StandbyState instance = new StandbyState();
    public static StandbyState getInstance(){return instance;}
    private StandbyState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: what is standby?
    }
    public void cycleInstructionL(Menu context){
        updateControllable(context);
        if(currentUnit.getID().getType() == UnitID.COLONIST_TYPE_ID){
            UnitMenuState nextState = BuildCapitalState.getInstance();
            nextState.setCurrentInstance(currentInstance);
            nextState.setCurrentType(currentType);
            nextState.updateControllable(context);
            context.setMenuState(nextState);
        }
        else{
            UnitMenuState nextState = JoinArmyState.getInstance();
            nextState.setCurrentInstance(currentInstance);
            nextState.setCurrentType(currentType);
            nextState.updateControllable(context);
            context.setMenuState(nextState);
        }

    }
    public void cycleInstructionR(Menu context){
        UnitMenuState nextState = PowerDownState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Standby";
    }
}
