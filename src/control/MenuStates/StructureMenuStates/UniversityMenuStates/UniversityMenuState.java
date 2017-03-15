package control.MenuStates.StructureMenuStates.UniversityMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import model.Controllables.Structures.University;

/**
 * Created by hankerins on 3/14/17.
 */
public abstract class UniversityMenuState extends StructureMenuState {
    protected University currentStructure;


    public void updateControllable(Menu context){
        currentStructure = (University) context.getControllableCollection().get(currentType, currentInstance);
    }
    public University getCurrentInstance(){
        return currentStructure;
    }
}
