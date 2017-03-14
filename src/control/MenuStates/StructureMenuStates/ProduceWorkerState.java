package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.StructureID;

import javax.swing.*;

/**
 * Created by hankerins on 3/14/17.
 */
public class ProduceWorkerState extends StructureMenuState{
    private static ProduceWorkerState instance = new ProduceWorkerState();
    public static ProduceWorkerState getInstance(){return instance;}
    private ProduceWorkerState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        if(currentStructure.getID().getType() == StructureID.CAPITAL_TYPE_ID){
            Capital producer = (Capital)currentStructure;
            producer.assignWorkersToBreed(PopUpMenuWindow.WorkerMenu());
        }


    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = AssignWorkersPowerHarvestState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = ProduceExplorerState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Produce Workers";
    }
}
