package control.MenuStates.StructureMenuStates.CapitalMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import model.Controllables.Structures.Capital;

public abstract class CapitalMenuState extends StructureMenuState {
    protected Capital currentStructure;


    public void updateControllable(Menu context){
        currentStructure = (Capital) context.getControllableCollection().get(currentType, currentInstance);
    }
    public Capital getCurrentInstance(){
            return currentStructure;
    }
}
