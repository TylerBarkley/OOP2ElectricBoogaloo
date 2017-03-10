package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.Map.Resources.ResourceManager;

/**
 * Created by Tyler Barkley on 3/8/2017.
 */
public class WorkerManager {
    private ResourceManager RM;
    private WorkerStats WS;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_HarvestingOre;
    private int numOfWorkers_HarvestingFood;
    private int numOfWorkers_HarvestingEnergy;
    private int numOfWorkers_HarvestingTechnology;
    private int numOfWorkers_Breeding;
    private int numOfWorkers_Building;
    private int numOfWorkers_ExplorerTraining;
    private int numOfWorkers_SoldierTraining;

    private Location harvestingOreLocation;
    private Location harvestingEnergyLocation;
    private Location harvestingFoodLocation;

    public WorkerManager(){
        WS = new WorkerStats();
        RM = new ResourceManager();

    }

    public int produceOre(int structureProductionRate){
        int amount = (structureProductionRate + WS.getOreProduction()) * numOfWorkers_HarvestingOre;
        return RM.mineOre(harvestingOreLocation, amount);
    }

    public int produceTechnology(int structureProductionRate){
        int amount = (structureProductionRate + WS.getTechnologyProduction()) * numOfWorkers_HarvestingTechnology;
        return amount;
    }

    public int produceEnergy(int structureProductionRate){
        int amount = (structureProductionRate + WS.getEnergyProduction()) * numOfWorkers_HarvestingEnergy;
        return RM.mineEnergy(harvestingEnergyLocation, amount);
    }

    public int produceFood(int structureProductionRate){
        int amount = (structureProductionRate + WS.getFoodProduction()) * numOfWorkers_HarvestingFood;
        return RM.mineFood(harvestingFoodLocation, amount);
    }

    public int breeding(){
        int newWorkers = ((WS.getBreeding() * numOfWorkers_Breeding)/2);
        return newWorkers;
    }

    public int building() {
        int percentageBuilt = WS.getBuildingRate() * 2 * numOfWorkers_Building;
        return percentageBuilt;
    }

    public int produceOre(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getOreProduction()) * numOfWorkers;
        return RM.mineOre(loc, amount);
    }

    public int produceTechnology(int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getTechnologyProduction()) * numOfWorkers;
        return amount;
    }

    public int produceEnergy(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getEnergyProduction()) * numOfWorkers;
        return RM.mineEnergy(loc, amount);
    }

    public int produceFood(Location loc, int structureProductionRate, int numOfWorkers){
        int amount = (structureProductionRate + WS.getFoodProduction()) * numOfWorkers;
        return RM.mineFood(loc, amount);
    }

    public int breeding(int numOfWorkers){
        int newWorkers = ((WS.getBreeding() * numOfWorkers)/2);
        return newWorkers;
    }

    public int building(int numOfWorker){
        int percentageBuilt = WS.getBuildingRate() * 2 * numOfWorker;
        return percentageBuilt;
    }

    public int trainSoldier(int numOfSoldiers){
        if(numOfWorkers_SoldierTraining < 1){
            return 0;
        }

        int percentageTrained = WS.getSoldierTraining() * numOfWorkers_SoldierTraining + (2 * numOfSoldiers * WS.getSoldierTraining());
        return percentageTrained;
    }

    public int trainExplorer(){
        int percentageTrained = WS.getExplorerTraining() * numOfWorkers_ExplorerTraining * 10;
        return percentageTrained;
    }

    public int trainExplorer(int numOfWorker){
        int percentageTrained = WS.getExplorerTraining() * numOfWorker * 10;
        return percentageTrained;
    }

    public void assignWorkers(int assignNum){
        numOfWorkers_Unassigned = numOfWorkers_Unassigned - assignNum;
    }

    public ResourceManager getRM() {
        return RM;
    }

    public void setRM(ResourceManager rm){
        this.RM = rm;
    }

    public WorkerStats getWS() {
        return WS;
    }

    public void setWS(WorkerStats WS) {
        this.WS = WS;
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

    public int getNumOfWorkers_HarvestingTechnology() {
        return numOfWorkers_HarvestingTechnology;
    }

    public void setNumOfWorkers_HarvestingTechnology(int numOfWorkers_HarvestingTechnology) {
        this.numOfWorkers_HarvestingTechnology = numOfWorkers_HarvestingTechnology;
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

    public int getNumOfWorkers_SoldierTraining() {
        return numOfWorkers_SoldierTraining;
    }

    public void setNumOfWorkers_SoldierTraining(int numOfWorkers_SoldierTraining) {
        this.numOfWorkers_SoldierTraining = numOfWorkers_SoldierTraining;
    }

    public Location getHarvestingFoodLocation() {
        return harvestingFoodLocation;
    }

    public void setHarvestingFoodLocation(Location harvestingFoodLocation) {
        this.harvestingFoodLocation = harvestingFoodLocation;
    }

    public Location getHarvestingEnergyLocation() {
        return harvestingEnergyLocation;
    }

    public void setHarvestingEnergyLocation(Location harvestingEnergyLocation) {
        this.harvestingEnergyLocation = harvestingEnergyLocation;
    }

    public Location getHarvestingOreLocation() {
        return harvestingOreLocation;
    }

    public void setHarvestingOreLocation(Location harvestingOreLocation) {
        this.harvestingOreLocation = harvestingOreLocation;
    }
}
