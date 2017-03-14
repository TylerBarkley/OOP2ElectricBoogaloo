package control.MenuStates.StructureMenuStates.ObservationTowerMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import control.MenuStates.StructureMenuStates.UniversityMenuStates.ResearchTechnologyState;

/**
 * Created by hankerins on 3/14/17.
 */
public class OTDecommissionState extends ObservationTowerMenuState {
    private static OTDecommissionState instance = new OTDecommissionState();
    public static OTDecommissionState getInstance(){return instance;}
    private OTDecommissionState(){}

    @Override
    public void select(Menu context) {
        updateControllable(context);
        //TODO: decommission
    }
    public void cycleInstructionL(Menu context){
        StructureMenuState nextState = OTDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        StructureMenuState nextState = OTDecommissionState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Decommission";
    }
}
