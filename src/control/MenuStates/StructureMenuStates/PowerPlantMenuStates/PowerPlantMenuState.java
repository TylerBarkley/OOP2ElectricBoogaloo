package control.MenuStates.StructureMenuStates.PowerPlantMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import model.Controllables.Structures.PowerPlant;

/**
 * Created by hankerins on 3/14/17.
 */
public abstract class PowerPlantMenuState extends StructureMenuState {
    protected PowerPlant currentStructure;


    public void updateControllable(Menu context){
        currentStructure = (PowerPlant) context.getControllableCollection().get(currentType, currentInstance);
    }
    public PowerPlant getCurrentInstance(){
        return currentStructure;
    }
}
