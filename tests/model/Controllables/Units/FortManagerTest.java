package model.Controllables.Units;
import static org.junit.Assert.*;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.Fort;
import model.Controllables.Structures.FortManager;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tyler Barkley on 3/12/2017.
 */
public class FortManagerTest {

    StructureStats structureStats;
    Fort fort;
    WorkerStats workerStats;
    FortManager fortManager;

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

        fort = new Fort();

        fortManager = new FortManager();
        fort.setFortManager(fortManager);
        fort.setMyStats(structureStats);
        fort.setStats(workerStats);
    }

    @Test
    public void FortManager_Test(){
        fort.setNumTotalOfWorkers(10);
        fort.setNumOfSoldiers(2);
        fortManager.setNumOfWorkers_Unassigned(10);
        fort.unassign();
        assertEquals(10, fortManager.getNumOfWorkers_Unassigned());
        assertEquals(0, fortManager.getNumOfWorkers_SoldierTraining());
        fort.doWork();
        assertEquals(0, fortManager.trainSoldier(fort.getNumOfSoldiers()));
        fort.assignWorkersToTrainSoldiers(20);
        assertEquals(10, fortManager.getNumOfWorkers_SoldierTraining());
        assertEquals(0, fortManager.getNumOfWorkers_Unassigned());
        assertEquals(14, fortManager.trainSoldier(fort.getNumOfSoldiers()));
        fort.setNumOfSoldiers(0);
        assertEquals(10, fortManager.trainSoldier(fort.getNumOfSoldiers()));
        fort.assignWorkersToTrainSoldiers(1);
        assertEquals(1, fortManager.getNumOfWorkers_SoldierTraining());
        assertEquals(9, fortManager.getNumOfWorkers_Unassigned());
        assertEquals(1, fortManager.trainSoldier(fort.getNumOfSoldiers()));
        fort.assignWorkersToTrainSoldiers(-20);
        assertEquals(10, fortManager.getNumOfWorkers_Unassigned());
        assertEquals(0, fortManager.getNumOfWorkers_SoldierTraining());
        fort.assignWorkersToTrainSoldiers(5);
        assertEquals(5, fortManager.getNumOfWorkers_SoldierTraining());
        assertEquals(5, fortManager.getNumOfWorkers_Unassigned());
        fort.unassign();
        assertEquals(0, fortManager.getNumOfWorkers_SoldierTraining());
        assertEquals(10, fortManager.getNumOfWorkers_Unassigned());
    }


}
