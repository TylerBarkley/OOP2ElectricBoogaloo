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
        if(getBeingBuilt() == true) {
            if (powerPlantManager.getNumOfWorkers_Harvesting() > 0) {
                harvestEnergy();
            }
        }
        else{
            build();
        }
    }

    public void assignWorkersToPowerPlant(Location loc, int numOfWorkers_AssignToPowerPlant){
        powerPlantManager.assignWorkers(loc, numOfWorkers_AssignToPowerPlant, getLocation());
    }

    @Override
    public void unassign(){
        powerPlantManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        powerPlantManager.setNumOfWorkers_Harvesting(0);
        powerPlantManager.setNumOfWorkers_Building(0);
        powerPlantManager.resetWork(getLocation());
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
        PlayerManager.getInstance().addPower(getPid(), energyMined);
    }

    public void setStats(WorkerStats workerStats){
        powerPlantManager.setWorkerStats(workerStats);
    }

    public void setPowerPlantManager(PowerPlantManager powerPlantManager) {
        this.powerPlantManager = powerPlantManager;
    }

    public void addWorker(int number) {
        powerPlantManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        powerPlantManager.removeUnassigned(number);
    }
}
