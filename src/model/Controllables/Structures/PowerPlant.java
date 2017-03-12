package model.Controllables.Structures;

import model.Location;
import model.player.PlayerManager;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class PowerPlant extends Structure implements Energizing {

    private PowerPlantManager powerPlantManager;

    public PowerPlant(){
        powerPlantManager = new PowerPlantManager();
    }

    @Override
    public void doWork(){
        if(powerPlantManager.getNumOfWorkers_HarvestingEnergy() > 0){
            harvestEnergy();
        }
    }

    public void assignWorkersToPowerPlant(Location loc, int numOfWorkers_AssignToPowerPlant){
        if(powerPlantManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToPowerPlant){
            numOfWorkers_AssignToPowerPlant = powerPlantManager.getNumOfWorkers_Unassigned();
            powerPlantManager.setNumOfWorkers_Unassigned(0);
            powerPlantManager.setNumOfWorkers_HarvestingEnergy(numOfWorkers_AssignToPowerPlant);
        }
        else{
            powerPlantManager.setNumOfWorkers_HarvestingEnergy(numOfWorkers_AssignToPowerPlant);
            powerPlantManager.assignWorkers(numOfWorkers_AssignToPowerPlant);
        }
        powerPlantManager.setHarvestingEnergyLocation(loc);
    }

    @Override
    public void unassign(){
        powerPlantManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        powerPlantManager.setNumOfWorkers_HarvestingEnergy(0);
        powerPlantManager.setNumOfWorkers_Building(0);
    }

    public void harvestEnergy(){
        int energyMined = powerPlantManager.produceEnergy(getMyStats().getProductionRate());
        PlayerManager.getInstance().addPower(getPid(), energyMined);
    }

    public void setPowerPlantManager(PowerPlantManager powerPlantManager) {
        this.powerPlantManager = powerPlantManager;
    }
}
