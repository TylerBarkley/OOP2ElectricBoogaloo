package control.MenuStates;

import control.Menu;
import control.MenuStates.StructureMenuStates.CapitalMenuStates.CapitalAssignWorkersFarmState;
import control.MenuStates.StructureMenuStates.CapitalMenuStates.CapitalProduceExplorerState;
import control.MenuStates.StructureMenuStates.FarmMenuStates.FarmAssignWorkersFarmState;
import control.MenuStates.StructureMenuStates.FortMenuStates.ProduceMeleeState;
import control.MenuStates.StructureMenuStates.MineMenuStates.MineAssignWorkersMineState;
import control.MenuStates.StructureMenuStates.ObservationTowerMenuStates.OTDecommissionState;
import control.MenuStates.StructureMenuStates.PowerPlantMenuStates.PPAssignWorkersPowerHarvestState;
import control.MenuStates.StructureMenuStates.UniversityMenuStates.ResearchTechnologyState;
import model.Controllables.Army;
import model.Controllables.ControllableCollection;
import model.Controllables.Structures.Structure;

/**
 * Created by hankerins on 3/5/17.
 */
public abstract class StructureMenuState implements MenuState {

    protected int currentInstance = 0, currentType = ControllableCollection.CAPITALTYPE ;
    //protected Structure currentStructure;

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
        setDefaultState(context);
        //updateControllable(context);
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
        setDefaultState(context);
        //updateControllable(context);
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
        //updateControllable(context);
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

    public void setDefaultState(Menu context){
        //this is called after the instance/type ints are set, before the current Instance
        //object is updated, to avoid null pointers (hopefully)
        StructureMenuState nextState = this;
        switch (currentType){
            case ControllableCollection.CAPITALTYPE:
                nextState = CapitalAssignWorkersFarmState.getInstance(); break;
            case ControllableCollection.FARMTYPE:
                nextState = FarmAssignWorkersFarmState.getInstance(); break;
            case ControllableCollection.FORTTYPE:
                nextState = ProduceMeleeState.getInstance(); break;
            case ControllableCollection.MINETYPE:
                nextState = MineAssignWorkersMineState.getInstance(); break;
            case ControllableCollection.OBSERVATIONTOWERTYPE:
                nextState = OTDecommissionState.getInstance(); break;
            case ControllableCollection.POWERPLANTTYPE:
                nextState = PPAssignWorkersPowerHarvestState.getInstance(); break;
            case ControllableCollection.UNIVERSITYTYPE:
                nextState = ResearchTechnologyState.getInstance(); break;
        }
        nextState.setCurrentInstance(currentInstance);
        nextState.setCurrentType(currentType);
        nextState.updateControllable(context);
        context.setMenuState(nextState);
    }


}
