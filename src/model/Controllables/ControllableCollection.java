package model.Controllables;

import control.Menu;
import model.Controllables.Structures.*;
import model.Controllables.Units.*;


import java.util.ArrayList;

/**
 * Created by hankerins on 3/5/17.
 */
public class ControllableCollection {

    public final static int EXPLORERTYPE     	= 0;
    public final static int COLONISTTYPE    	= 1;
    public final static int MELEEUNITTYPE    	= 2;
    public final static int RANGEDUNITTYPE    	= 3;

    public final static int CAPITALTYPE     	= 4;
    public final static int FARMTYPE        	= 5;
    public final static int FORTTYPE    	    = 6;
    public final static int MINETYPE        	= 7;
    public final static int OBSERVATIONTOWERTYPE= 8;
    public final static int POWERPLANTTYPE    	= 9;
    public final static int UNIVERSITYTYPE    	= 10;

    public final static int ARMYTYPE    	    = 11;
    public final static int BATTLEGROUPTYPE    	= 12;
    public final static int REINFORCEMENTSTYPE 	= 13;

    public final static int RPTYPE = 14;

    private ArrayList<Colonist> colonists;
    private ArrayList<Explorer> explorers;
    private ArrayList<Melee> melees;
    private ArrayList<Ranged> rangeds;

    private ArrayList<Army> armies;
    private ArrayList<RallyPoint> rallyPoints;

    private ArrayList<Capital> capitals;
    private ArrayList<Farm> farms;
    private ArrayList<Fort> forts;
    private ArrayList<Mine> mines;
    private ArrayList<ObservationTower> observationTowers;
    private ArrayList<PowerPlant> powerPlants;
    private ArrayList<University> universities;

    private int workers;
    


    
    public ControllableCollection(ArrayList<Colonist> colonists, ArrayList<Explorer> explorers, ArrayList<Melee> melees,
			ArrayList<Ranged> rangeds, ArrayList<Army> armies, ArrayList<RallyPoint> rallyPoints, ArrayList<Capital> capitals, ArrayList<Farm> farms,
			ArrayList<Fort> forts, ArrayList<Mine> mines, ArrayList<ObservationTower> observationTowers,
			ArrayList<PowerPlant> powerPlants, ArrayList<University> universities, int workers) {
		this.colonists = colonists;
		this.explorers = explorers;
		this.melees = melees;
		this.rangeds = rangeds;
		this.armies = armies;
        this.rallyPoints = rallyPoints;
		this.capitals = capitals;
		this.farms = farms;
		this.forts = forts;
		this.mines = mines;
		this.observationTowers = observationTowers;
		this.powerPlants = powerPlants;
		this.universities = universities;
		this.workers = workers;

	}

	
    
    /*public ControllableCollection(){
        colonists = new ArrayList<Colonist>(10);
        explorers = new ArrayList<Explorer>(10);
        melees = new ArrayList<Melee>(10);
        rangeds = new ArrayList<Ranged>(10);

        armies = new ArrayList<Army>(10);

        capitals = new ArrayList<Capital>(10);
        farms = new ArrayList<Farm>(10);
        forts = new ArrayList<Fort>(10);
        mines = new ArrayList<Mine>(10);
        observationTowers = new ArrayList<ObservationTower>(10);
        powerPlants = new ArrayList<PowerPlant>(10);
        universities = new ArrayList<University>(10);

        //Initialize each ArrayList to size 10

        for(int i = 0; i < 10; i++){
            colonists.add(null);
            explorers.add(null);
            melees.add(null);
            rangeds.add(null);

            armies.add(null);

            capitals.add(null);
            farms.add(null);
            forts.add(null);
            mines.add(null);
            observationTowers.add(null);
            powerPlants.add(null);
            universities.add(null);
        }
    }*/

    //controllableExists is used to check if any controllables exist
    //for a given mode, determines which mode to switch to when
    //cycling through modes in the menu
    public boolean controllableExists(int modeType){
        if(modeType == Menu.UNITMODE) {

            for (int i = 0; i < 10 ; i++) {
                if(colonists.get(i) != null)
                    return true;
                if(explorers.get(i) != null)
                    return true;
                if(melees.get(i) != null)
                    return true;
                if(rangeds.get(i) != null)
                    return true;
            }
        } else if(modeType==Menu.STRUCTUREMODE){
            //new code for variable sized lists.
            //assumes the list is empty (size == 0) if none of a particular structure exists
            if(capitals.size() > 0) return true;
            if(farms.size() > 0) return true;
            if(forts.size() > 0) return true;
            if(mines.size() > 0) return true;
            if(observationTowers.size() > 0) return true;
            if(powerPlants.size() > 0) return true;
            if(universities.size() > 0) return true;

        } else if(modeType==Menu.RALLYPOINTMODE){
            //System.out.println("CHECKING RALLYPOINT");
            for (int i = 0; i < rallyPoints.size(); i++) {
                if (rallyPoints.get(i) != null){
                    return true;
                }
            }
        } else if(modeType==Menu.ARMYMODE){
            //System.out.println("CHECKING ARMYMODE");
            for (int i = 0; i < armies.size(); i++) {
                if (armies.get(i) != null){
                    return true;
                }
            }
        }
        return false;
    }


    //GETTERS AND SETTERS

    //One-size fits all get() method for returning any controllable in the collection
    //x is the constant corresponding to the ArrayList you wish to access,
    //y is the index in the array.  This method avoids chained getters in other classes
    public Controllable get(int x, int y){
        if(x==EXPLORERTYPE)
            return explorers.get(y);
        if(x==COLONISTTYPE)
            return colonists.get(y);
        if(x==MELEEUNITTYPE)
            return melees.get(y);
        if(x==RANGEDUNITTYPE)
            return rangeds.get(y);
        if(x==CAPITALTYPE)
            return capitals.get(y);
        if(x==FARMTYPE)
            return farms.get(y);
        if(x==FORTTYPE)
            return forts.get(y);
        if(x==MINETYPE)
            return mines.get(y);
        if(x==OBSERVATIONTOWERTYPE)
            return observationTowers.get(y);
        if(x==POWERPLANTTYPE)
            return powerPlants.get(y);
        if(x==UNIVERSITYTYPE)
            return universities.get(y);
        if(x==ARMYTYPE)
            return armies.get(y);

        //TODO: implement battle group + reinforcements structure
        if(x==BATTLEGROUPTYPE)
            return armies.get(y);
        if(x==REINFORCEMENTSTYPE)
            return armies.get(y);
        if(x==RPTYPE)
            return rallyPoints.get(y);


        else return null;
    }

    public ArrayList<Explorer> getExplorers() {
        return explorers;
    }
    public void setExplorers(ArrayList<Explorer> explorers) {
        this.explorers = explorers;
    }
    public ArrayList<Colonist> getColonists() {
        return colonists;
    }
    public void setColonists(ArrayList<Colonist> colonists) {
        this.colonists = colonists;
    }
    public ArrayList<Melee> getMelees() {
        return melees;
    }
    public void setMelees(ArrayList<Melee> melees) {
        this.melees = melees;
    }
    public ArrayList<Ranged> getRangeds() {
        return rangeds;
    }
    public void setRangeds(ArrayList<Ranged> rangeds) {
        this.rangeds = rangeds;
    }
    public ArrayList<Army> getArmies() {
        return armies;
    }
    public void setArmies(ArrayList<Army> armies) {
        this.armies = armies;
    }
    public ArrayList<RallyPoint> getRallyPoints() {
        return rallyPoints;
    }
    public void setRallyPoints(ArrayList<RallyPoint> rallyPoints) {
        this.rallyPoints = rallyPoints;
    }
    public ArrayList<Capital> getCapitals() {
        return capitals;
    }
    public void setCapitals(ArrayList<Capital> capitals) {
        this.capitals = capitals;
    }
    public ArrayList<Farm> getFarms() {
        return farms;
    }
    public void setFarms(ArrayList<Farm> farms) {
        this.farms = farms;
    }
    public ArrayList<Fort> getForts() {
        return forts;
    }
    public void setForts(ArrayList<Fort> forts) {
        this.forts = forts;
    }
    public ArrayList<Mine> getMines() {
        return mines;
    }
    public void setMines(ArrayList<Mine> mines) {
        this.mines = mines;
    }
    public ArrayList<ObservationTower> getObservationTowers() {
        return observationTowers;
    }
    public void setObservationTowers(ArrayList<ObservationTower> observationTowers) {
        this.observationTowers = observationTowers; }
    public ArrayList<PowerPlant> getPowerPlants() {
        return powerPlants;
    }
    public void setPowerPlants(ArrayList<PowerPlant> powerPlants) {
        this.powerPlants = powerPlants;
    }
    public ArrayList<University> getUniversities() {
        return universities;
    }
    public void setUniversities(ArrayList<University> universities) {
        this.universities = universities;
    }
    public int getWorkers() {
        return workers;
    }
    public void setWorkers(int workers) {
        this.workers = workers;
    }




}
