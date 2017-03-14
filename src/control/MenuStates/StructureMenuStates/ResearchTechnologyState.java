package control.MenuStates.StructureMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.PopUpMenuWindow;
import model.Controllables.Structures.Fort;
import model.Controllables.Structures.StructureID;

/**
 * Created by hankerins on 3/14/17.
 */
public class ResearchTechnologyState extends StructureMenuState {
    private static ResearchTechnologyState instance = new ResearchTechnologyState();
    public static ResearchTechnologyState getInstance(){return instance;}
    private ResearchTechnologyState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        if(currentStructure.getID().getType() == StructureID.UNIVERSITY_TYPE_ID){
            //TODO: research
        }
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = ProduceRangedState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = AssignWorkersFarmState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Research Technology";
    }
}
