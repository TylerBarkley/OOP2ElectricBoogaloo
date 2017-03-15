package control.MenuStates.StructureMenuStates.ObservationTowerMenuStates;

import control.Menu;
import control.MenuStates.StructureMenuState;
import model.Controllables.Structures.ObservationTower;

/**
 * Created by hankerins on 3/14/17.
 */
public abstract class ObservationTowerMenuState extends StructureMenuState {
    protected ObservationTower currentStructure;


    public void updateControllable(Menu context){
        currentStructure = (ObservationTower) context.getControllableCollection().get(currentType, currentInstance);
    }
    public ObservationTower getCurrentInstance(){
        return currentStructure;
    }
}
