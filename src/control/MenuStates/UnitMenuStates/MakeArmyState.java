package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;
import model.Controllables.Units.Unit;

/**
 * Created by hankerins on 3/5/17.
 */
public class MakeArmyState extends UnitMenuState{
    @Override
    public void select(Menu context) {

        //Unit u = (Unit)context.getControllableCollection().get(currentType, currentInstance);
        //u.makeArmy();
        updateUnit(context);
        currentUnit.makeArmy();
    }
}
