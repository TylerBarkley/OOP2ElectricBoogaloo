package control.MenuStates.ArmyMenuStates;

import control.Menu;
import control.MenuStates.ArmyMenuState;
import control.PopUpMenuWindow;
import model.Controllables.Structures.Structure;
import model.DropOffCommand;
import model.PickUpCommand;
import model.player.PlayerManager;

/**
 * Created by hankerins on 3/15/17.
 */
public class ArmyPickUpWorkersState extends ArmyMenuState {
    private static ArmyPickUpWorkersState instance = new ArmyPickUpWorkersState();
    public static ArmyPickUpWorkersState getInstance(){return instance;}
    private ArmyPickUpWorkersState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);


        try{
            Structure target = PopUpMenuWindow.StructureMenu(PlayerManager.getInstance().getStructures(context.getPlayerId()));
            int numWorkers = PopUpMenuWindow.WorkerMenu();
            currentArmy.getRallyPoint().moveRallyPoint(target.getLocation());
            currentArmy.giveOrder(new PickUpCommand(currentArmy, target, numWorkers));
        }catch (Exception e){}

    }
    public void cycleInstructionL(Menu context){
        ArmyMenuState nextState = ArmyPowerUpState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        ArmyMenuState nextState = ArmyDropOffWorkersState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Pick Up Workers";
    }
}
