package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;
import control.PopUpMenuWindow;
import model.Controllables.RallyPoint;
import model.Controllables.Units.UnitID;

public class JoinArmyState extends UnitMenuState {

    private static JoinArmyState instance = new JoinArmyState();
    public static JoinArmyState getInstance(){return instance;}
    private JoinArmyState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        try{
            RallyPoint rp = PopUpMenuWindow.RallyPointMenu(context.getControllableCollection().getRallyPoints());
            rp.reinforce(currentUnit);
        }
        catch (Exception e){}

    }

    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = MakeArmyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context) {
        updateControllable(context);
        if(currentUnit.getID().getType() == UnitID.COLONIST_TYPE_ID){
            UnitMenuState nextState = BuildCapitalState.getInstance();
            nextState.setCurrentInstance(currentInstance);
            nextState.setCurrentType(currentType);
            nextState.updateControllable(context);
            context.setMenuState(nextState);
        } else {
            UnitMenuState nextState = StandbyState.getInstance();
            nextState.setCurrentInstance(currentInstance);
            nextState.setCurrentType(currentType);
            nextState.updateControllable(context);
            context.setMenuState(nextState);
        }
    }
    public String toString(){
        return "Reinforce Rally Point";
    }
}
