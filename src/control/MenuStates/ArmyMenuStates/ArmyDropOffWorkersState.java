package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import control.PopUpMenuWindow;
import model.Controllables.Army;
import model.Controllables.Structures.Structure;
import model.DropOffCommand;
import model.player.PlayerManager;

/**
 * Created by hankerins on 3/15/17.
 */
public class ArmyDropOffWorkersState extends ArmyMenuState {
    private static ArmyDropOffWorkersState instance = new ArmyDropOffWorkersState();
    public static ArmyDropOffWorkersState getInstance(){return instance;}
    private ArmyDropOffWorkersState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);


        try{
            Structure target = PopUpMenuWindow.StructureMenu(PlayerManager.getInstance().getStructures(context.getPlayerId()));
            int numWorkers = PopUpMenuWindow.WorkerMenu();
            currentArmy.getRallyPoint().moveRallyPoint(target.getLocation());
           currentArmy.giveOrder(new DropOffCommand(currentArmy, target, numWorkers));
        }catch (Exception e){}

    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyPickUpWorkersState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyCancelQueueState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Drop Off Workers";
    }
}
