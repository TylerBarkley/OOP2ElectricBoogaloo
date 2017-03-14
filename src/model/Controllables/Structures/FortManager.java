package model.Controllables.Structures;

import model.AttackManager;
import model.Controllables.Stats.WorkerStats;
import model.Location;

import java.util.ArrayList;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class FortManager extends WorkerManager{

    private WorkerStats workerStats;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_Building;
    private int numOfWorkers_SoldierTraining;
    private ArrayList<Location> attackLocations;
    private AttackManager attackManager;


    public FortManager(){
        //attackManager = AttackManager.getInstance();
        workerStats = new WorkerStats();
    }

    public int building() {
        numOfWorkers_Building += numOfWorkers_Unassigned + numOfWorkers_SoldierTraining;
        int percentageBuilt = workerStats.getBuildingRate() * 2 * numOfWorkers_Building;
        return percentageBuilt;
    }

    public void attack(Location loc, int radius, Fort fort){
       for(int i = 0; i < radius; i++){
            attackLocations = loc.getLocationsAtRadius(i+1);
            for(int j = 0; j < attackLocations.size(); j++){
                AttackManager.getInstance().attack(fort, attackLocations.get(j));
            }
        }
    }

    public int trainSoldier(int numOfSoldiers){
        if(numOfWorkers_SoldierTraining < 1){
            return 0;
        }

        int percentageTrained = workerStats.getSoldierTraining() * numOfWorkers_SoldierTraining + (2 * numOfSoldiers * workerStats.getSoldierTraining());
        return percentageTrained;
    }

    public void assignWorkers(int assignNum){
        if((numOfWorkers_Unassigned + numOfWorkers_SoldierTraining) < (assignNum + 1)){
            numOfWorkers_SoldierTraining = numOfWorkers_Unassigned + numOfWorkers_SoldierTraining;
            numOfWorkers_Unassigned = 0;
        }
        else if(assignNum < 1){
            numOfWorkers_Unassigned = numOfWorkers_Unassigned + numOfWorkers_SoldierTraining;
            numOfWorkers_SoldierTraining = 0;
        }
        else{
            numOfWorkers_Unassigned = numOfWorkers_Unassigned + numOfWorkers_SoldierTraining;
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - assignNum;
            numOfWorkers_SoldierTraining = assignNum;
        }
    }

    public WorkerStats getWorkerStats() {
        return workerStats;
    }

    public void setWorkerStats(WorkerStats workerStats) {
        this.workerStats = workerStats;
    }

    public int getNumOfWorkers_Unassigned() {
        return numOfWorkers_Unassigned;
    }

    public void setNumOfWorkers_Unassigned(int numOfWorkers_Unassigned) {
        this.numOfWorkers_Unassigned = numOfWorkers_Unassigned;
    }

    public int getNumOfWorkers_Building() {
        return numOfWorkers_Building;
    }

    public void setNumOfWorkers_Building(int numOfWorkers_Building) {
        this.numOfWorkers_Building = numOfWorkers_Building;
    }

    public int getNumOfWorkers_SoldierTraining() {
        return numOfWorkers_SoldierTraining;
    }

    public void setNumOfWorkers_SoldierTraining(int numOfWorkers_SoldierTraining) {
        this.numOfWorkers_SoldierTraining = numOfWorkers_SoldierTraining;
    }

    public void addUnassigned(int number){
        numOfWorkers_Unassigned += number;
    }

    public void removeUnassigned(int number){
        numOfWorkers_Unassigned -= Math.min(number, numOfWorkers_Unassigned);
    }

    @Override
    public void assignBuild(int assignment) {
        numOfWorkers_Building += Math.min(numOfWorkers_Unassigned, assignment);
    }

    public ArrayList<Location> getAttackLocations() {
        return attackLocations;
    }

    public void setAttackLocations(ArrayList<Location> attackLocations) {
        this.attackLocations = attackLocations;
    }

    public AttackManager getAttackManager() {
        return attackManager;
    }

    public void setAttackManager(AttackManager attackManager) {
        this.attackManager = attackManager;
    }
}
