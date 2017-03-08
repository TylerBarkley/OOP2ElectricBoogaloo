package control.MenuStates;

import control.Menu;
import model.Controllables.Army;
import model.Controllables.ControllableCollection;
import model.Controllables.Units.Unit;

/**
 * Created by hankerins on 3/5/17.
 * TODO: don't know how we are handling battle group/reinforcements,
 * TODO: currently all 3 Types reference the whole army
 *
 * Attack
 * Defend
 * Wait
 * Disband
 * Decommission
 * Powerdown
 * Powerup
 * CancelQueue
 */
public abstract class ArmyMenuState implements MenuState{
    protected int currentInstance = 0, currentType = ControllableCollection.ARMYTYPE ;
    protected Army currentArmy;

    public void cycleTypeL (Menu context){
        int startCurrentType = currentType;
        if(currentType == ControllableCollection.ARMYTYPE)
            currentType = ControllableCollection.REINFORCEMENTSTYPE;
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
                if(currentType == ControllableCollection.ARMYTYPE)
                    currentType = ControllableCollection.REINFORCEMENTSTYPE;
                else currentType--;
            }
        }
        updateControllable(context);
    }
    public void cycleTypeR(Menu context){
        int startCurrentType = currentType;
        if(currentType == ControllableCollection.REINFORCEMENTSTYPE)
            currentType = ControllableCollection.ARMYTYPE;
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
                if(currentType == ControllableCollection.REINFORCEMENTSTYPE)
                    currentType = ControllableCollection.ARMYTYPE;
                else currentType++;
            }
        }
        updateControllable(context);
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
        updateControllable(context);
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
        updateControllable(context);
    }
    public void reset(Menu context){
        currentType = ControllableCollection.BATTLEGROUPTYPE;
        cycleTypeL(context);
        currentInstance = 1;
        cycleInstanceL(context);
        updateControllable(context);
    }
    public void updateControllable(Menu context){
        currentArmy = (Army)context.getControllableCollection().get(currentType, currentInstance);
    }
    public int getCurrentInstance() {return currentInstance;}
    public void setCurrentInstance(int currentInstance) {this.currentInstance = currentInstance;}

    public int getCurrentType() {return currentType;}
    public void setCurrentType(int currentType) {this.currentType = currentType;}


    public String typeToString(){
        switch (currentType){
            case ControllableCollection.ARMYTYPE: return "Army";
            case ControllableCollection.BATTLEGROUPTYPE: return "Battle Group";
            case ControllableCollection.REINFORCEMENTSTYPE: return "Reinforcements";
        }
        return null;
    }
}
