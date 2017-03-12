package model.Controllables.Structures;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class ObservationTower extends Structure {
    //TODO nothing, cause this class is useless
    // HK: ^^^LOLOL

    private ObservationTowerManager observationTowerManager;

    public ObservationTower(){
        observationTowerManager = new ObservationTowerManager();
    }

    public void unassign(){
        observationTowerManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        observationTowerManager.setNumOfWorkers_Building(0);
    }

}
