package model.Controllables.Units;
import static org.junit.Assert.*;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.WorkerStats;

import model.Controllables.Structures.ObservationTower;
import model.Controllables.Structures.ObservationTowerManager;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by Tyler Barkley on 3/12/2017.
 */
public class ObservationTowerManagerTest {

    ObservationTower observationTower;
    ObservationTowerManager observationTowerManager;
    WorkerStats workerStats;
    StructureStats structureStats;

    @Before
    public void setUp(){
        workerStats = new WorkerStats();
        structureStats = new StructureStats();

        workerStats.setBreeding(1);
        workerStats.setOreProduction(1);
        workerStats.setTechnologyProduction(1);
        workerStats.setSoldierTraining(1);
        workerStats.setBuildingRate(1);
        workerStats.setExplorerTraining(1);
        workerStats.setFoodProduction(1);
        workerStats.setEnergyProduction(1);
        workerStats.setWorkerRadius(0);
        workerStats.setWorkerDensity(5);

        structureStats.setArmor(1);
        structureStats.setDefensiveDamage(1);
        structureStats.setHealth(1);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(1);
        structureStats.setProductionRate(1);
        structureStats.setUpkeep(1);

        observationTower = new ObservationTower();

        observationTowerManager = new ObservationTowerManager();
        observationTower.setObservationTowerManager(observationTowerManager);
        observationTower.setMyStats(structureStats);
        observationTower.setStats(workerStats);
    }

    @Test
    public void ObservationTower_Test(){
        observationTower.setNumTotalOfWorkers(1);
        observationTowerManager.setNumOfWorkers_Building(1);
        observationTowerManager.setNumOfWorkers_Unassigned(0);
        assertEquals(1, observationTowerManager.getNumOfWorkers_Building());
        assertEquals(0, observationTowerManager.getNumOfWorkers_Unassigned());
        observationTower.unassign();
        assertEquals(0, observationTowerManager.getNumOfWorkers_Building());
        assertEquals(1, observationTowerManager.getNumOfWorkers_Unassigned());
    }

}
