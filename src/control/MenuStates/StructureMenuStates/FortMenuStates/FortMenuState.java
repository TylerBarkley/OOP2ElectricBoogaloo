package control.MenuStates.StructureMenuStates.FortMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import model.Controllables.Structures.Fort;

/**
 * Created by hankerins on 3/14/17.
 */
public abstract class FortMenuState extends StructureMenuState {
    protected Fort currentStructure;


    public void updateControllable(Menu context){
        currentStructure = (Fort) context.getControllableCollection().get(currentType, currentInstance);
    }
    public Fort getCurrentStructure(){
        return currentStructure;
    }
}
