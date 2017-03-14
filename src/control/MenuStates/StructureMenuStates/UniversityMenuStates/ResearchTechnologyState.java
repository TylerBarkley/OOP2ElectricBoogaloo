package control.MenuStates.StructureMenuStates.UniversityMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.MenuStates.StructureMenuStates.CapitalMenuStates.CapitalAssignWorkersFarmState;
import control.MenuStates.StructureMenuStates.FortMenuStates.ProduceRangedState;
import model.Controllables.Structures.StructureID;

/**
 * Created by hankerins on 3/14/17.
 */
public class ResearchTechnologyState extends UniversityMenuState {
    private static ResearchTechnologyState instance = new ResearchTechnologyState();
    public static ResearchTechnologyState getInstance(){return instance;}
    private ResearchTechnologyState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
            //TODO: research
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = ResearchTechnologyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = ResearchTechnologyState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Research Technology";
    }
}
