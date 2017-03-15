package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.UnitID;

public class ProspectState extends UnitMenuState {
    private static ProspectState instance = new ProspectState();
    public static ProspectState getInstance(){return instance;}
    private ProspectState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        if(currentUnit.getID().getType() == UnitID.EXPLORER_TYPE_ID){
           Explorer explorer = (Explorer)currentUnit;
           explorer.setProspecting();
        }
    }

    public void cycleInstructionL(Menu context){
        UnitMenuState nextState = PowerUpState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context) {
        UnitMenuState nextState = MakeArmyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);

    }
    public String toString(){
        return "Prospect";
    }
}
