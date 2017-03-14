package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
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
            //TODO get number of workers to breed
            Object[] possibilities = new Object[100];
            for(int i = 0; i < 100; i++){
                possibilities[i] = ("" + i+1) ;
            }
            String result = (String)JOptionPane.showInputDialog(null, "Choose how many workers: ",
                    "Worker Menu", JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);
            int count = Integer.parseInt(result);
            producer.assignWorkersToBreed(count);
        }


    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = AssignWorkersMineState.getInstance();
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
        return "Assign Workers Farm";
    }
}
