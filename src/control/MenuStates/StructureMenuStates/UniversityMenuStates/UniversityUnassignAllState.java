package control.MenuStates.StructureMenuStates.UniversityMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

/**
 * Created by Tyler Barkley on 3/15/2017.
 */
public class UniversityUnassignAllState extends UniversityMenuState {
    private static UniversityUnassignAllState instance = new UniversityUnassignAllState();
    public static UniversityUnassignAllState getInstance(){return instance;}
    private UniversityUnassignAllState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        currentStructure.unassign();
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = UniversityDecommissionState.getInstance();
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
        return "Unassign All Workers";
    }
}
