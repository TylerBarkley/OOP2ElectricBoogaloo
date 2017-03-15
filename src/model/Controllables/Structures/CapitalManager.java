package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

import java.util.ArrayList;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class CapitalManager extends WorkerManager{

    private ResourceManager resourceManager;
    private WorkerStats workerStats;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_HarvestingOre;
    private int numOfWorkers_HarvestingFood;
    private int numOfWorkers_HarvestingEnergy;
    private int numOfWorkers_Breeding;
    private int numOfWorkers_Building;
    private int numOfWorkers_ExplorerTraining;

    private Location harvestingOreLocation;
    private Location harvestingEnergyLocation;
    private Location harvestingFoodLocation;

    private ArrayList<Location> adjacencies;

    public CapitalManager(){
        workerStats = new WorkerStats();
        resourceManager = new ResourceManager();
    }

    public void assignWorkersMine(Location loc, int assignNum, Location myLoc){
        adjacencies = myLoc.getAllLocationsWithinRadius(workerStats.getWorkerRadius());
        boolean flag = false;
        for(int i = 0; i < adjacencies.size(); i++){
            if(adjacencies.get(i).equals(loc)){
                flag = true;
            }
        }
        if(flag == false){
            return;
        }
        if(loc != harvestingOreLocation && resourceManager.isWorking(loc) == true){
            return;
        }
        if(loc.equals(harvestingEnergyLocation)){
            resetEnergy(myLoc);
        }
        if(loc.equals(harvestingFoodLocation)){
            resetFood(myLoc);
        }
        if((numOfWorkers_HarvestingOre + numOfWorkers_Unassigned) < assignNum){
            numOfWorkers_Unassigned += numOfWorkers_HarvestingOre;
            assignNum = numOfWorkers_Unassigned;
            numOfWorkers_HarvestingOre = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned -= numOfWorkers_HarvestingOre;
            if(resourceManager.get(harvestingOreLocation).getWorking() == true && numOfWorkers_HarvestingOre > 0 && harvestingOreLocation == loc){
                resourceManager.setWorking(harvestingOreLocation, false);
            }
            harvestingOreLocation = loc;
            resourceManager.setWorking(harvestingOreLocation, true);
        }
        else if(assignNum < 1){
            resetOre(myLoc);
        }
        else{
            numOfWorkers_Unassigned += numOfWorkers_HarvestingOre;
            numOfWorkers_HarvestingOre = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned -= numOfWorkers_HarvestingOre;
            if(resourceManager.get(harvestingOreLocation).getWorking() == true && numOfWorkers_HarvestingOre > 0 && harvestingOreLocation == loc){
                resourceManager.setWorking(harvestingOreLocation, false);
            }
            harvestingOreLocation = loc;
            resourceManager.setWorking(harvestingOreLocation, true);
        }
    }

    public void assignWorkersEnergy(Location loc, int assignNum, Location myLoc){
        adjacencies = myLoc.getAllLocationsWithinRadius(workerStats.getWorkerRadius());
        boolean flag = false;
        for(int i = 0; i < adjacencies.size(); i++){
            if(adjacencies.get(i).equals(loc)){
                flag = true;
            }
        }
        if(flag == false){
            return;
        }
        if(loc != harvestingEnergyLocation && resourceManager.isWorking(loc) == true){
            return;
        }
        if(loc.equals(harvestingOreLocation)){
            resetOre(myLoc);
        }
        if(loc.equals(harvestingFoodLocation)){
            resetFood(myLoc);
        }
        if((numOfWorkers_HarvestingEnergy + numOfWorkers_Unassigned) < assignNum){
            numOfWorkers_Unassigned += numOfWorkers_HarvestingEnergy;
            assignNum = numOfWorkers_Unassigned;
            numOfWorkers_HarvestingEnergy = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned -= numOfWorkers_HarvestingEnergy;
            if(resourceManager.get(harvestingEnergyLocation).getWorking() == true && numOfWorkers_HarvestingEnergy > 0 && harvestingEnergyLocation == loc){
                resourceManager.setWorking(harvestingEnergyLocation, false);
            }
            harvestingEnergyLocation = loc;
            resourceManager.setWorking(harvestingEnergyLocation, true);
        }
        else if(assignNum < 1){
            resetEnergy(myLoc);
        }
        else{
            numOfWorkers_Unassigned += numOfWorkers_HarvestingEnergy;
            numOfWorkers_HarvestingEnergy = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned -= numOfWorkers_HarvestingEnergy;
            if(resourceManager.get(harvestingEnergyLocation).getWorking() == true && numOfWorkers_HarvestingEnergy > 0 && harvestingEnergyLocation == loc){
                resourceManager.setWorking(harvestingEnergyLocation, false);
            }
            harvestingEnergyLocation = loc;
            resourceManager.setWorking(harvestingEnergyLocation, true);
        }
    }

    public void assignWorkersFood(Location loc, int assignNum, Location myLoc){
        adjacencies = myLoc.getAllLocationsWithinRadius(workerStats.getWorkerRadius());
        boolean flag = false;
        for(int i = 0; i < adjacencies.size(); i++){
            if(adjacencies.get(i).equals(loc)){
                flag = true;
            }
        }
        if(flag == false){
            return;
        }
        if(loc.equals(harvestingOreLocation)){
            resetOre(myLoc);
            harvestingFoodLocation = loc;
        }
        if(loc.equals(harvestingEnergyLocation)){
            resetEnergy(myLoc);
            harvestingFoodLocation = loc;
        }
        if(loc != harvestingFoodLocation){
            if(resourceManager.isWorking(loc) == true){
                return;
            }
        }
        if((numOfWorkers_HarvestingFood + numOfWorkers_Unassigned) < assignNum){
            numOfWorkers_Unassigned += numOfWorkers_HarvestingFood;
            assignNum = numOfWorkers_Unassigned;
            numOfWorkers_HarvestingFood = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned -= numOfWorkers_HarvestingFood;
            if(resourceManager.get(harvestingFoodLocation).getWorking() == true && numOfWorkers_HarvestingFood > 0 && harvestingFoodLocation == loc){
                resourceManager.setWorking(harvestingFoodLocation, false);
            }
            harvestingFoodLocation = loc;
            resourceManager.setWorking(harvestingFoodLocation, true);
        }
        else if(assignNum < 1){
            resetFood(myLoc);
        }
        else{
            numOfWorkers_Unassigned += numOfWorkers_HarvestingFood;
            numOfWorkers_HarvestingFood = Math.min(assignNum, workerStats.getWorkerDensity());
            numOfWorkers_Unassigned -= numOfWorkers_HarvestingFood;
            if(resourceManager.get(harvestingFoodLocation).getWorking() == true && numOfWorkers_HarvestingFood > 0 && harvestingFoodLocation == loc){
                resourceManager.setWorking(harvestingFoodLocation, false);
            }
            harvestingFoodLocation = loc;
            resourceManager.setWorking(harvestingFoodLocation, true);
        }
    }

    public void resetWork(Location myLoc){
        if(harvestingFoodLocation != null){
            resourceManager.setWorking(harvestingFoodLocation, false);
        }
        if(harvestingEnergyLocation != null){
            resourceManager.setWorking(harvestingEnergyLocation, false);
        }
        if(harvestingOreLocation != null){
            resourceManager.setWorking(harvestingOreLocation, false);
        }
        harvestingFoodLocation = myLoc;
        harvestingEnergyLocation = myLoc;
        harvestingOreLocation = myLoc;
        resourceManager.setWorking(harvestingFoodLocation, false);
        resourceManager.setWorking(harvestingEnergyLocation, false);
        resourceManager.setWorking(harvestingOreLocation, false);
    }

    public int produceOre(int structureProductionRate){
        int amount = (structureProductionRate + workerStats.getOreProduction()) * numOfWorkers_HarvestingOre;
        return resourceManager.mineOre(harvestingOreLocation, amount);
    }

    public int produceEnergy(int structureProductionRate){
        int amount = (structureProductionRate + workerStats.getEnergyProduction()) * numOfWorkers_HarvestingEnergy;
        return resourceManager.mineEnergy(harvestingEnergyLocation, amount);
    }

    public int produceFood(int structureProductionRate){
        int amount = (structureProductionRate + workerStats.getFoodProduction()) * numOfWorkers_HarvestingFood;
        return resourceManager.mineFood(harvestingFoodLocation, amount);
    }

    public int breeding(){
        int newWorkers = ((workerStats.getBreeding() * numOfWorkers_Breeding)/2);
        return newWorkers;
    }

    public int building() {
        int percentageBuilt = workerStats.getBuildingRate() * 2 * numOfWorkers_Building;
        return percentageBuilt;
    }

    public int trainExplorer(){
        int percentageTrained = workerStats.getExplorerTraining() * numOfWorkers_ExplorerTraining * 10;
        return percentageTrained;
    }

    public void assignWorkersBreeding(int assignNum){
        if(assignNum > numOfWorkers_Unassigned){
            assignNum = numOfWorkers_Unassigned;
            numOfWorkers_Breeding = assignNum;
            numOfWorkers_Unassigned = 0;
        }
        else if(assignNum < 1){
            numOfWorkers_Unassigned += numOfWorkers_Breeding;
            numOfWorkers_Breeding = 0;
        }
        else{
            numOfWorkers_Breeding = assignNum;
            numOfWorkers_Unassigned -= numOfWorkers_Breeding;
        }
    }

    public void assignWorkersTrainingExplorers(int assignNum){
        if(assignNum > numOfWorkers_Unassigned){
            assignNum = numOfWorkers_Unassigned;
            numOfWorkers_ExplorerTraining = assignNum;
            numOfWorkers_Unassigned = 0;
        }
        else if(assignNum < 1){
            numOfWorkers_Unassigned += numOfWorkers_ExplorerTraining;
            numOfWorkers_ExplorerTraining = 0;
        }
        else{
            numOfWorkers_ExplorerTraining = assignNum;
            numOfWorkers_Unassigned -= numOfWorkers_ExplorerTraining;
        }
    }

    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    public void setResourceManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
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

    public int getNumOfWorkers_HarvestingOre() {
        return numOfWorkers_HarvestingOre;
    }

    public void setNumOfWorkers_HarvestingOre(int numOfWorkers_HarvestingOre) {
        this.numOfWorkers_HarvestingOre = numOfWorkers_HarvestingOre;
    }

    public int getNumOfWorkers_HarvestingFood() {
        return numOfWorkers_HarvestingFood;
    }

    public void setNumOfWorkers_HarvestingFood(int numOfWorkers_HarvestingFood) {
        this.numOfWorkers_HarvestingFood = numOfWorkers_HarvestingFood;
    }

    public int getNumOfWorkers_HarvestingEnergy() {
        return numOfWorkers_HarvestingEnergy;
    }

    public void setNumOfWorkers_HarvestingEnergy(int numOfWorkers_HarvestingEnergy) {
        this.numOfWorkers_HarvestingEnergy = numOfWorkers_HarvestingEnergy;
    }

    public int getNumOfWorkers_Breeding() {
        return numOfWorkers_Breeding;
    }

    public void setNumOfWorkers_Breeding(int numOfWorkers_Breeding) {
        this.numOfWorkers_Breeding = numOfWorkers_Breeding;
    }

    public int getNumOfWorkers_Building() {
        return numOfWorkers_Building;
    }

    public void setNumOfWorkers_Building(int numOfWorkers_Building) {
        this.numOfWorkers_Building = numOfWorkers_Building;
    }

    public int getNumOfWorkers_ExplorerTraining() {
        return numOfWorkers_ExplorerTraining;
    }

    public void setNumOfWorkers_ExplorerTraining(int numOfWorkers_ExplorerTraining) {
        this.numOfWorkers_ExplorerTraining = numOfWorkers_ExplorerTraining;
    }

    public Location getHarvestingOreLocation() {
        return harvestingOreLocation;
    }

    public void setHarvestingOreLocation(Location harvestingOreLocation) {
        this.harvestingOreLocation = harvestingOreLocation;
    }

    public Location getHarvestingEnergyLocation() {
        return harvestingEnergyLocation;
    }

    public void setHarvestingEnergyLocation(Location harvestingEnergyLocation) {
        this.harvestingEnergyLocation = harvestingEnergyLocation;
    }

    public Location getHarvestingFoodLocation() {
        return harvestingFoodLocation;
    }

    public void setHarvestingFoodLocation(Location harvestingFoodLocation) {
        this.harvestingFoodLocation = harvestingFoodLocation;
    }

    public void resetEnergy(Location myLoc){
        resourceManager.get(harvestingEnergyLocation).setWorking(false);
        harvestingEnergyLocation = myLoc;
        numOfWorkers_Unassigned += numOfWorkers_HarvestingEnergy;
        numOfWorkers_HarvestingEnergy = 0;
    }

    public void resetFood(Location myLoc){
        resourceManager.get(harvestingFoodLocation).setWorking(false);
        harvestingFoodLocation = myLoc;
        numOfWorkers_Unassigned += numOfWorkers_HarvestingFood;
        numOfWorkers_HarvestingFood = 0;
    }

    public void resetOre(Location myLoc){
        resourceManager.get(harvestingOreLocation).setWorking(false);
        harvestingOreLocation = myLoc;
        numOfWorkers_Unassigned += numOfWorkers_HarvestingOre;
        numOfWorkers_HarvestingOre= 0;
    }

    public void addUnassigned(int number){
        numOfWorkers_Unassigned += number;
    }

    public void removeUnassigned(int number){
        numOfWorkers_Unassigned -= Math.min(number, numOfWorkers_Unassigned);
    }

    @Override
    public void assignBuild(int assignment) {}

    @Override
    public void unassignAll() {
        numOfWorkers_Unassigned += numOfWorkers_Breeding + numOfWorkers_HarvestingFood + numOfWorkers_ExplorerTraining + numOfWorkers_HarvestingEnergy + numOfWorkers_HarvestingOre + numOfWorkers_Building;
        numOfWorkers_Breeding = 0;
        numOfWorkers_Building = 0;
        numOfWorkers_HarvestingOre = 0;
        numOfWorkers_HarvestingEnergy = 0;
        numOfWorkers_HarvestingFood = 0;
        numOfWorkers_ExplorerTraining = 0;
        if(harvestingFoodLocation != null){
            resourceManager.setWorking(harvestingFoodLocation, false);
        }
        if(harvestingEnergyLocation != null){
            resourceManager.setWorking(harvestingEnergyLocation, false);
        }
        if(harvestingOreLocation != null){
            resourceManager.setWorking(harvestingOreLocation, false);
        }
    }
}
