package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import model.BuildMineCommand;

/**
 * Created by hankerins on 3/15/17.
 */
public class ArmyBuildMineState extends ArmyMenuState {
    private static ArmyBuildMineState instance = new ArmyBuildMineState();
    public static ArmyBuildMineState getInstance(){return instance;}
    private ArmyBuildMineState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: menu to select number of workers
        int numOfWorkers = currentArmy.getWorkers();
        currentArmy.giveOrder(new BuildMineCommand(currentArmy, numOfWorkers));
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyBuildFortState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyBuildObservationTowerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Build Mine";
    }
}
