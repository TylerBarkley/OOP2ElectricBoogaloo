package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;
import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class PowerPlant extends Structure implements Energizing {

    private PowerPlantManager powerPlantManager;
    private int builtPercentage;

    public PowerPlant(){
        builtPercentage = 0;
        powerPlantManager = new PowerPlantManager();
        setBeingBuilt(true);
    }

    @Override
    public void doWork(){
        if(getBeingBuilt() == false) {
            harvestEnergy();
        }
        else{
            build();
        }
    }

    public void assignWorkersToPowerHarvest(Location loc, int numOfWorkers_AssignToPowerPlant){
        powerPlantManager.assignWorkers(loc, numOfWorkers_AssignToPowerPlant, getLocation());
    }

    @Override
    public void unassign(){
        powerPlantManager.unassignAll();
    }

    @Override
    public void build() {
        builtPercentage += powerPlantManager.building();
        if(builtPercentage > 99){
            setBeingBuilt(false);
            unassign();
        }
    }

    public void harvestEnergy(){
        int energyMined = powerPlantManager.produceEnergy(getMyStats().getProductionRate());
        PlayerManager.getInstance().addPower(getPlayerID(), energyMined);
    }

    public void setStats(WorkerStats workerStats){
        powerPlantManager.setWorkerStats(workerStats);
    }

    public void setPowerPlantManager(PowerPlantManager powerPlantManager) {
        this.powerPlantManager = powerPlantManager;
    }

    public void addWorker(int number) {
        addNewWorkers(number);
        powerPlantManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        removeOldWorkers(number);
        powerPlantManager.removeUnassigned(number);
    }
}
