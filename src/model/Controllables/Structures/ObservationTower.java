package model.Controllables.Structures;

import model.Controllables.Stats.WorkerStats;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class ObservationTower extends Structure {
    //TODO nothing, cause this class is useless
    // HK: ^^^LOLOL

    private ObservationTowerManager observationTowerManager;
    private int builtPercentage;

    public ObservationTower(){
        builtPercentage = 0;
        observationTowerManager = new ObservationTowerManager();
        setBeingBuilt(true);
    }

    @Override
    public void doWork() {
        if(getBeingBuilt() == false) {

        }
        else{
            build();
        }
    }

    public void unassign(){
        observationTowerManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        observationTowerManager.setNumOfWorkers_Building(0);
    }

    @Override
    public void build() {
        builtPercentage += observationTowerManager.building();
        if(builtPercentage > 99){
            setBeingBuilt(false);
            unassign();
        }
    }

    public void setStats(WorkerStats workerStats){
        observationTowerManager.setWorkerStats(workerStats);
    }

    public void setObservationTowerManager(ObservationTowerManager observationTowerManager){
        this.observationTowerManager = observationTowerManager;
    }

    public void addWorker(int number) {
        observationTowerManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        observationTowerManager.removeUnassigned(number);
    }

}
