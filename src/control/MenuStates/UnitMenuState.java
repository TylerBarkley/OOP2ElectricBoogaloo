package control.MenuStates;

import control.Menu;
import model.Controllables.Controllable;
import model.Controllables.ControllableCollection;

/**
 * Created by hankerins on 3/5/17.
 *
 *MAKEARMY
 * JOINARMY
 * BUILDBASE
 * STANDBY
 * POWERDOWN
 * POWERUP
 * CANCELQUEUE
 * MOVEENTER
 */
public abstract class UnitMenuState implements MenuState{



    int currentInstance = 0, currentType = ControllableCollection.COLONISTTYPE ;

    public void cycleTypeL (Menu context){
        int startCurrentType = currentType;
        if(currentType == ControllableCollection.EXPLORERTYPE)
            currentType = ControllableCollection.RANGEDUNITTYPE;
        else currentType--;
        boolean foundUnit = false;
        while(startCurrentType != currentType && !foundUnit) {
            for (int i = 0; i < 10; i++) {
                if (context.instanceExists(currentType, i)) {
                    foundUnit=true;
                    currentInstance=i;
                    break;
                }
            }
            if(!foundUnit) {
                if(currentType == ControllableCollection.EXPLORERTYPE)
                    currentType = ControllableCollection.RANGEDUNITTYPE;
                else currentType--;
            }
        }
    }
    public void cycleTypeR(Menu context){
        int startCurrentType = currentType;
        if(currentType == ControllableCollection.RANGEDUNITTYPE)
            currentType = ControllableCollection.EXPLORERTYPE;
        else currentType++;
        boolean foundUnit = false;
        while(startCurrentType != currentType && !foundUnit) {
            for (int i = 0; i < 10; i++) {
                if (context.instanceExists(currentType, i)) {
                    foundUnit=true;
                    currentInstance=i;
                    break;
                }
            }
            if(!foundUnit) {
                if(currentType == ControllableCollection.RANGEDUNITTYPE)
                    currentType = ControllableCollection.EXPLORERTYPE;
                else currentType++;
            }
        }

    }
    public void cycleInstanceL(Menu context){

    }
    public void cycleInstanceR(Menu context){

    }
    public void cycleInstructionL(Menu context){

    }
    public void cycleInstructionR(Menu context){

    }

}
