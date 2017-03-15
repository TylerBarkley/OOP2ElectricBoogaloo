package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;
import model.Controllables.RallyPoint;
import model.Controllables.Units.UnitID;

/**
 * Created by hankerins on 3/5/17.
 */
public class MakeArmyState extends UnitMenuState{

    private static MakeArmyState instance = new MakeArmyState();
    public static MakeArmyState getInstance(){return instance;}
    private MakeArmyState(){}

    @Override
    public void select(Menu context) {
        //TODO: delete print statement


        updateControllable(context);
        System.out.println("you tried to make an army with: " + currentUnit.toString() );
        new RallyPoint(currentUnit);
    }
    public void cycleInstructionL(Menu context){
        if(currentUnit.getID().getType() == UnitID.EXPLORER_TYPE_ID){
            UnitMenuState nextState = ProspectState.getInstance();
            nextState.setCurrentInstance(currentInstance);
            nextState.setCurrentType(currentType);
            nextState.updateControllable(context);
            context.setMenuState(nextState);}
        else{
            UnitMenuState nextState = PowerUpState.getInstance();
            nextState.setCurrentInstance(currentInstance);
            nextState.setCurrentType(currentType);
            nextState.updateControllable(context);
            context.setMenuState(nextState);
        }
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
