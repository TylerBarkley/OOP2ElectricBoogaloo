package control.MenuStates.StructureMenuStates.CapitalMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Structure;

/**
 * Created by hankerins on 3/14/17.
 */
public abstract class CapitalMenuState extends StructureMenuState {
    protected Capital currentStructure;


    public void updateControllable(Menu context){
        currentStructure = (Capital) context.getControllableCollection().get(currentType, currentInstance);
    }
    public Capital getCurrentInstance(){
            return currentStructure;
    }
}
