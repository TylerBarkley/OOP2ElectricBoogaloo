package control.MenuStates;

import control.Menu;
import model.Controllables.Army;
import model.Controllables.ControllableCollection;
import model.Controllables.Structures.Structure;

/**
 * Created by hankerins on 3/5/17.
 */
public abstract class StructureMenuState implements MenuState {

    protected int currentInstance = 0, currentType = ControllableCollection.CAPITALTYPE ;
    //protected Structure currentStructure;

    //TODO: implement this and make the cycleType() methods call it at the end
    public void setDefaultInstruction(Menu context){

    }

    //can probably eliminate for() loops here because if an instance exists, it will always be in index 0
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
        updateControllable(context);
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
        updateControllable(context);
    }

    //TODO: set lastInstance to the appropriate value for cycleInstance() methods
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
        currentType = ControllableCollection.FARMTYPE;
        cycleTypeL(context);
        currentInstance = 1;
        cycleInstanceL(context);
        updateControllable(context);
    }
    //abstract void updateControllable(Menu context);
        //currentStructure = (Structure) context.getControllableCollection().get(currentType, currentInstance);

    public int getCurrentInstanceNumber() {return currentInstance;}
    public void setCurrentInstance(int currentInstance) {this.currentInstance = currentInstance;}

    public int getCurrentType() {return currentType;}
    public void setCurrentType(int currentType) {this.currentType = currentType;}


    public String typeToString(){
        switch (currentType){
            case ControllableCollection.CAPITALTYPE: return "Capital";
            case ControllableCollection.FARMTYPE: return "Farm";
            case ControllableCollection.FORTTYPE: return "Fort";
            case ControllableCollection.MINETYPE: return "Mine";
            case ControllableCollection.OBSERVATIONTOWERTYPE: return "Observation Tower";
            case ControllableCollection.POWERPLANTTYPE: return "Powerplant";
            case ControllableCollection.UNIVERSITYTYPE: return "University";
        }
        return null;
    }



}
