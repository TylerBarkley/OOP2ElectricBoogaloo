package control.MenuStates.RallyPointMenuStates;

import control.Menu;
import control.MenuStates.RallyPointMenuState;
import control.MenuStates.StructureMenuState;
import control.MenuStates.StructureMenuStates.StructureAttackState;

/**
 * Created by hankerins on 3/8/17.
 */
public class RPBuildState extends RallyPointMenuState{
    private static RPBuildState instance = new RPBuildState();
    public static RPBuildState getInstance(){return instance;}
    private RPBuildState(){}

    @Override
    public void select(Menu context) {

        updateControllable(context);
        //TODO: currentArmy.build(); MAKE A BUILD STRUCTURE MENU FOR WORKERS
    }
    public void cycleInstructionL(Menu context){
        RallyPointMenuState nextState = RPBuildState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public void cycleInstructionR(Menu context){
        RallyPointMenuState nextState = RPBuildState.getInstance();
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }
    public String toString(){
        return "Build";
    }
}
