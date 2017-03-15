package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import model.BuildFortCommand;

/**
 * Created by hankerins on 3/13/17.
 */
public class ArmyBuildFortState extends ArmyMenuState {
    private static ArmyBuildFortState instance = new ArmyBuildFortState();
    public static ArmyBuildFortState getInstance(){return instance;}
    private ArmyBuildFortState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: menu to select number of workers
        int numOfWorkers = 1;
        currentArmy.giveOrder(new BuildFortCommand(currentArmy, numOfWorkers));
    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyBuildUniversityState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyBuildMineState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Build Fort";
    }
}
