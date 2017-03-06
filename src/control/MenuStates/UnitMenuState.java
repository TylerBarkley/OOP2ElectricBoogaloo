package control.MenuStates;

import control.Menu;
import model.Controllables.Controllable;
import model.Controllables.ControllableCollection;
import model.Controllables.Units.Unit;

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



    protected int currentInstance = 0, currentType = ControllableCollection.COLONISTTYPE;
    protected Unit currentUnit;

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
        int lastInstance = 9;
        int startIndex=currentInstance;
        if(currentInstance == 0)
            currentInstance = lastInstance;
        else currentInstance--;
        while(currentInstance != startIndex){
            if(context.instanceExists(currentType, currentInstance)){
                break;
            }
            currentInstance--;
            if(currentInstance < 0){
                currentInstance = lastInstance;
            }
        }
    }
    public void cycleInstanceR(Menu context){
        int lastInstance = 9;
        int startIndex=currentInstance;
        if(currentInstance == lastInstance)
            currentInstance = 0;
        else currentInstance++;
        while(currentInstance != startIndex){
            if(context.instanceExists(currentType, currentInstance)){
                break;
            }
            currentInstance++;
            if(currentInstance > lastInstance){
                currentInstance = 0;
            }
        }

    }
    public void cycleInstructionL(Menu context){

    }
    public void cycleInstructionR(Menu context){

    }
    public void reset(Menu context){
        currentType = 1;
        cycleTypeL(context);
        currentInstance = 1;
        cycleInstanceL(context);
    }

    public void updateUnit(Menu context){
        currentUnit = (Unit)context.getControllableCollection().get(currentType, currentInstance);
    }

}
