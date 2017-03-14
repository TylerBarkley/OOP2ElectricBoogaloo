package control.MenuStates.StructureMenuStates.MineMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import model.Controllables.Structures.Mine;

/**
 * Created by hankerins on 3/14/17.
 */
public abstract class MineMenuState extends StructureMenuState {
    protected Mine currentStructure;


    public void updateControllable(Menu context){
        currentStructure = (Mine) context.getControllableCollection().get(currentType, currentInstance);
    }
    public Mine getCurrentInstance(){
        return currentStructure;
    }
}
