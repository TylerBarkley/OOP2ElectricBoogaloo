package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Fort;
import model.Controllables.Structures.StructureID;

/**
 * Created by hankerins on 3/14/17.
 */
public class ProduceRangedState extends StructureMenuState {
    private static ProduceRangedState instance = new ProduceRangedState();
    public static ProduceRangedState getInstance(){return instance;}
    private ProduceRangedState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        if(currentStructure.getID().getType() == StructureID.FORT_TYPE_ID){
            Fort producer = (Fort) currentStructure;
            producer.assignWorkersToTrainRangedSoldiers(PopUpMenuWindow.WorkerMenu());
        }


    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = ProduceMeleeState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = AssignWorkersMineState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Produce Ranged";
    }
}
