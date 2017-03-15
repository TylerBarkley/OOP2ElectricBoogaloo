package control.MenuStates.StructureMenuStates.UniversityMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;

public class UniversityDecommissionState extends UniversityMenuState {
    private static UniversityDecommissionState instance = new UniversityDecommissionState();
    public static UniversityDecommissionState getInstance(){return instance;}
    private UniversityDecommissionState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        //TODO: decommission
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
        return "Decommission";
    }
}
