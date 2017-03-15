package control.MenuStates.StructureMenuStates.FarmMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import model.Controllables.Structures.Farm;

public abstract class FarmMenuState extends StructureMenuState {
    protected Farm currentStructure;


    public void updateControllable(Menu context){
        currentStructure = (Farm) context.getControllableCollection().get(currentType, currentInstance);
    }
    public Farm getCurrentInstance(){
        return currentStructure;
    }
}
