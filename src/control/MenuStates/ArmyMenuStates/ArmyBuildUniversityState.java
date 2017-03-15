package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import model.BuildFortCommand;
import model.BuildUniversityCommand;

/**
 * Created by Tyler Barkley on 3/15/2017.
 */
public class ArmyBuildUniversityState extends ArmyMenuState {

    private static ArmyBuildUniversityState instance = new ArmyBuildUniversityState();
    public static ArmyBuildUniversityState getInstance(){return instance;}
    private ArmyBuildUniversityState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: menu to select number of workers
        int numOfWorkers = currentArmy.getWorkers();
        currentArmy.giveOrder(new BuildUniversityCommand(currentArmy, numOfWorkers));
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyBuildFarmState.getInstance();
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
        return "Build University";
    }
}
