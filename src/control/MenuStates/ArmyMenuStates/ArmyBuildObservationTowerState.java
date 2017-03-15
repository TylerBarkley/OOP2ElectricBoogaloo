package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import model.BuildMineCommand;
import model.BuildObservationTowerCommand;

/**
 * Created by Tyler Barkley on 3/15/2017.
 */
public class ArmyBuildObservationTowerState extends ArmyMenuState {
    private static ArmyBuildObservationTowerState instance = new ArmyBuildObservationTowerState();
    public static ArmyBuildObservationTowerState getInstance(){return instance;}
    private ArmyBuildObservationTowerState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: menu to select number of workers
        int numOfWorkers = currentArmy.getWorkers();
        currentArmy.giveOrder(new BuildObservationTowerCommand(currentArmy, numOfWorkers));
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyBuildMineState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyBuildPowerPlantState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Build Observation Tower";
    }
}
