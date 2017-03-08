package control.MenuStates.UnitMenuStates;

import control.Menu;
import control.MenuStates.UnitMenuState;
import model.Controllables.Units.Unit;

/**
 * Created by hankerins on 3/5/17.
 */
public class MakeArmyState extends UnitMenuState{

    public MakeArmyState(){
    }
    @Override
    public void select(Menu context) {
        
        updateUnit(context);
        currentUnit.makeArmy();
    }
}
