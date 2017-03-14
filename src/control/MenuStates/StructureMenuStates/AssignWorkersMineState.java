package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Mine;
import model.Controllables.Structures.PowerPlant;
import model.Controllables.Structures.StructureID;

/**
 * Created by hankerins on 3/8/17.
 */
public class AssignWorkersMineState extends StructureMenuState {
    private static AssignWorkersMineState instance = new AssignWorkersMineState();
    public static AssignWorkersMineState getInstance(){return instance;}
    private AssignWorkersMineState(){}

    @Override
    public void select(Menu context) {
        //this is bad type casting, violates OCP. We can do better
        // Ideally each Structure has its own SubMenuState class because the lists are so different.
        updateControllable(context);
        if(currentStructure.getID().getType() == StructureID.CAPITAL_TYPE_ID){
            Capital producer = (Capital)currentStructure;
            producer.assignWorkersToMine(context.getFocus(), PopUpMenuWindow.WorkerMenu());
        }
        if(currentStructure.getID().getType() == StructureID.POWERPLANT_TYPE_ID){
            Mine producer = (Mine) currentStructure;
            producer.assignWorkersToMine(context.getFocus(), PopUpMenuWindow.WorkerMenu());
        }
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = AssignWorkersFarmState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = AssignWorkersPowerHarvestState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Assign Workers Mine";
    }
}
