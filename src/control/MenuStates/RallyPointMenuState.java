package control.MenuStates;

import control.Menu;
import model.Controllables.ControllableCollection;

/**
 * Created by hankerins on 3/5/17.
 */
public abstract class RallyPointMenuState implements MenuState {
    int currentInstance = 0, currentType = 0;

    public void cycleTypeL (Menu context){

    }
    public void cycleTypeR(Menu context){
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
        currentInstance = 1;
        cycleInstanceL(context);

    }
}
