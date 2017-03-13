package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import model.Controllables.Units.Unit;

/**
 * Created by hankerins on 3/8/17.
 */
public class ArmyDefendState extends ArmyMenuState {
    private static ArmyDefendState instance = new ArmyDefendState();
    public static ArmyDefendState getInstance(){return instance;}
    private ArmyDefendState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        for(Unit u: currentArmy.getBattleGroup()){
            //TODO: submenu to pick a direction
            //TODO: make this the appropriate direction: u.setMapDirection(MapDirection.getNorth());
        }
        //TODO: currentArmy.defend(); -- probably won't get implemented
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyAttackState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyBuildFarmState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Defend";
    }
}
