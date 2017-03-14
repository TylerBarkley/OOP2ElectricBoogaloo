package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;
import model.Controllables.Structures.Fort;
import model.Controllables.Structures.StructureID;

/**
 * Created by hankerins on 3/14/17.
 */
public class ProduceMeleeState extends StructureMenuState {
    private static ProduceMeleeState instance = new ProduceMeleeState();
    public static ProduceMeleeState getInstance(){return instance;}
    private ProduceMeleeState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        if(currentStructure.getID().getType() == StructureID.FORT_TYPE_ID){
            Fort producer = (Fort) currentStructure;
            producer.assignWorkersToTrainMeleeSoldiers(PopUpMenuWindow.WorkerMenu());
        }


    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = ProduceExplorerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = ProduceRangedState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Produce Melee";
    }
}
