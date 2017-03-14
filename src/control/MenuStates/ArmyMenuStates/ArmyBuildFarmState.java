package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import model.BuildFarmCommand;

/**
 * Created by hankerins on 3/8/17.
 */
public class ArmyBuildFarmState extends ArmyMenuState {
    private static ArmyBuildFarmState instance = new ArmyBuildFarmState();
    public static ArmyBuildFarmState getInstance(){return instance;}
    private ArmyBuildFarmState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: menu to select number of workers
        int numOfWorkers = 1;
        currentArmy.giveOrder(new BuildFarmCommand(currentArmy, numOfWorkers));
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyDefendState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyBuildFortState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Build Farm";
    }
}
