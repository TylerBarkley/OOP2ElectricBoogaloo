package model.Controllables;

import model.Controllables.Structures.*;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;

import java.util.ArrayList;

/**
 * Created by hankerins on 3/5/17.
 */
public class ControllableCollection {
    private ArrayList<Colonist> colonists;
    private ArrayList<Explorer> explorers;
    private ArrayList<Melee> melees;
    private ArrayList<Ranged> rangeds;

    private ArrayList<Army> armies;

    private  ArrayList<Capital> capitals;
    private ArrayList<Farm> farms;
    private ArrayList<Fort> forts;
    private ArrayList<Mine> mines;
    private ArrayList<ObservationTower> observationTowers;
    private ArrayList<PowerPlant> powerPlants;
    private ArrayList<University> universities;

    public ControllableCollection(){
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
    }

    public ArrayList<Explorer> getExplorers() {
        return explorers;
    }

    public void setExplorers(ArrayList<Explorer> explorers) {
        this.explorers = explorers;
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
        this.observationTowers = observationTowers;
    }

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

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(ArrayList<Worker> workers) {
        this.workers = workers;
    }

    ArrayList<Worker> workers;

    public ArrayList<Colonist> getColonists() {
        return colonists;
    }

    public void setColonists(ArrayList<Colonist> colonists) {
        this.colonists = colonists;
    }

}
