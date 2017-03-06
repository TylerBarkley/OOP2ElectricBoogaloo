package control.MenuStates;

import control.Menu;
import model.Controllables.ControllableCollection;

/**
 * Created by hankerins on 3/5/17.
 */
public abstract class StructureMenuState implements MenuState {

    int currentInstance = 0, currentType = ControllableCollection.CAPITALTYPE ;

    public void cycleTypeL (Menu context){
        int startCurrentType = currentType;
        if(currentType == ControllableCollection.CAPITALTYPE)
            currentType = ControllableCollection.UNIVERSITYTYPE;
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
                if(currentType == ControllableCollection.CAPITALTYPE)
                    currentType = ControllableCollection.UNIVERSITYTYPE;
                else currentType--;
            }
        }
    }
    public void cycleTypeR(Menu context){
        int startCurrentType = currentType;
        if(currentType == ControllableCollection.UNIVERSITYTYPE)
            currentType = ControllableCollection.CAPITALTYPE;
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
                if(currentType == ControllableCollection.UNIVERSITYTYPE)
                    currentType = ControllableCollection.CAPITALTYPE;
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
    public void reset(Menu context){
        currentType = 1;
        cycleTypeL(context);
        currentInstance = 1;
        cycleInstanceL(context);
    }
}
