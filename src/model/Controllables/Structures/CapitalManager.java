package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

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

    public CapitalManager(){
        workerStats = new WorkerStats();
        resourceManager = new ResourceManager();
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

    public void assignWorkers(int assignNum){
        numOfWorkers_Unassigned = numOfWorkers_Unassigned - assignNum;
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
}
