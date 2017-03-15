package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import model.BuildObservationTowerCommand;
import model.BuildPowerPlantCommand;

/**
 * Created by Tyler Barkley on 3/15/2017.
 */
public class ArmyBuildPowerPlantState extends ArmyMenuState {
    private static ArmyBuildPowerPlantState instance = new ArmyBuildPowerPlantState();
    public static ArmyBuildPowerPlantState getInstance(){return instance;}
    private ArmyBuildPowerPlantState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: menu to select number of workers
        int numOfWorkers = currentArmy.getWorkers();
        currentArmy.giveOrder(new BuildPowerPlantCommand(currentArmy, numOfWorkers));
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyBuildObservationTowerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyBuildUniversityState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Build Power Plant";
    }
}
