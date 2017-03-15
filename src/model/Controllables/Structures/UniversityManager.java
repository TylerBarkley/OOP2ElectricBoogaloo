package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class UniversityManager extends WorkerManager{

    private WorkerStats workerStats;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_HarvestingTechnology;
    private int numOfWorkers_Building;
    //private technology

    public UniversityManager(){
        workerStats = new WorkerStats();
    }

    public int produceTechnology(int structureProductionRate){
        int amount = (structureProductionRate + workerStats.getTechnologyProduction()) * numOfWorkers_HarvestingTechnology;
        return amount;
    }

    public int building() {
        numOfWorkers_Building += numOfWorkers_Unassigned + numOfWorkers_HarvestingTechnology;
        numOfWorkers_HarvestingTechnology = Math.max(0,0);
        numOfWorkers_Unassigned = 0;
        int percentageBuilt = workerStats.getBuildingRate() * 2 * numOfWorkers_Building;
        return percentageBuilt;
    }

    public void assignWorkers(int assignNum){
        if((numOfWorkers_HarvestingTechnology + numOfWorkers_Unassigned) < (assignNum + 1)){
            numOfWorkers_HarvestingTechnology = (numOfWorkers_HarvestingTechnology + numOfWorkers_Unassigned);
            numOfWorkers_Unassigned = 0;
        }
        else if(assignNum < 1){
            numOfWorkers_Unassigned = numOfWorkers_HarvestingTechnology + numOfWorkers_Unassigned;
            numOfWorkers_HarvestingTechnology = 0;
        }
        else{
            numOfWorkers_Unassigned = numOfWorkers_HarvestingTechnology + numOfWorkers_Unassigned;
            numOfWorkers_HarvestingTechnology = assignNum;
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - assignNum;
        }

    }

    public void addUnassignedWorkers(int newWorkers){
        numOfWorkers_Unassigned = numOfWorkers_Unassigned + newWorkers;
    }

    public int getNumOfWorkers_Unassigned() {
        return numOfWorkers_Unassigned;
    }

    public void setNumOfWorkers_Unassigned(int numOfWorkers_Unassigned) {
        this.numOfWorkers_Unassigned = numOfWorkers_Unassigned;
    }

    public int getNumOfWorkers_HarvestingTechnology() {
        return numOfWorkers_HarvestingTechnology;
    }

    public void setNumOfWorkers_HarvestingTechnology(int numOfWorkers_HarvestingTechnology) {
        this.numOfWorkers_HarvestingTechnology = numOfWorkers_HarvestingTechnology;
    }

    public void setWorkerStats(WorkerStats workerStats) {
        this.workerStats = workerStats;
    }

    public int getNumOfWorkers_Building() {
        return numOfWorkers_Building;
    }

    public void setNumOfWorkers_Building(int numOfWorkers_Building) {
        this.numOfWorkers_Building = numOfWorkers_Building;
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

    @Override
    public void unassignAll() {
        numOfWorkers_Unassigned += numOfWorkers_HarvestingTechnology + numOfWorkers_Building;
        numOfWorkers_HarvestingTechnology = 0;
        numOfWorkers_Building = 0;
    }
}
