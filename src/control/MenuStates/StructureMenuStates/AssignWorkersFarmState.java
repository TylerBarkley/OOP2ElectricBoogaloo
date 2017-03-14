package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Farm;
import model.Controllables.Structures.StructureID;

/**
 * Created by hankerins on 3/14/17.
 */
public class AssignWorkersFarmState extends StructureMenuState {
    private static AssignWorkersFarmState instance = new AssignWorkersFarmState();
    public static AssignWorkersFarmState getInstance(){return instance;}
    private AssignWorkersFarmState(){}

    @Override
    public void select(Menu context) {
        //this is bad type casting, violates OCP. We can do better
        // Ideally each Structure has its own SubMenuState class because the lists are so different.
        updateControllable(context);
        if(currentStructure.getID().getType() == StructureID.CAPITAL_TYPE_ID){
            Capital producer = (Capital)currentStructure;
            producer.assignWorkersToFarm(context.getFocus(), PopUpMenuWindow.WorkerMenu());
        }
        if(currentStructure.getID().getType() == StructureID.POWERPLANT_TYPE_ID){
            Farm producer = (Farm) currentStructure;
            producer.assignWorkersToFarm(context.getFocus(), PopUpMenuWindow.WorkerMenu());
        }
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = ResearchTechnologyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState;
        if(currentStructure.getID().getType() == StructureID.CAPITAL_TYPE_ID ||
                currentStructure.getID().getType() == StructureID.MINE_TYPE_ID ){
            nextState = AssignWorkersMineState.getInstance();
        }
        else if (currentStructure.getID().getType() == StructureID.POWERPLANT_TYPE_ID){
            nextState = AssignWorkersPowerHarvestState.getInstance();
        }
        else if(currentStructure.getID().getType() == StructureID.FORT_TYPE_ID){
            nextState = ProduceMeleeState.getInstance();
        }
        else nextState = ResearchTechnologyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Assign Workers Farm";
    }
}
