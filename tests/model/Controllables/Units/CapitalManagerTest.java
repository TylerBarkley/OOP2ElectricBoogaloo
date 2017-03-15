package model.Controllables.Units;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.CapitalManager;
import model.Location;
import model.Map.Map;
import model.Map.Resources.ResourceLevel;
import model.Map.Resources.ResourceManager;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tyler Barkley on 3/12/2017.
 */
public class CapitalManagerTest {

    StructureStats structureStats;
    Capital capital;
    Player p1;
    WorkerStats workerStats;
    CapitalManager capitalManager;
    ResourceManager resourceManager;
    ResourceLevel r1;
    ResourceLevel r2;
    ResourceLevel r3;

    Location loc1;
    Location loc2;
    Location loc3;

    Map map;

    @Before
    public void setUp(){
        Map.reset();
        Map.setMoveDebug();
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


        p1 = new Player();

        PlayerManager.getInstance().addPlayer(p1);
        capitalManager = new CapitalManager();
        capital = new Capital();
        capital.setCapitalManager(capitalManager);
        capital.setLocation(new Location(0, 0));
        capital.setStats(workerStats);

        PlayerManager.getInstance().addStructure(p1.getId(), capital);

        r1 = new ResourceLevel(100, 100, 100);
        r2 = new ResourceLevel(50, 50, 50);
        r3 = new ResourceLevel(60, 60, 60);
        r1.setWorking(false);
        r2.setWorking(false);
        r3.setWorking(false);

        map = Map.getInstance();
        resourceManager = map.getResourceManager();

        capitalManager.setResourceManager(resourceManager);

        resourceManager.add(new Location(0,0), r1);
        resourceManager.add(new Location(0, 1), r2);
        resourceManager.add(new Location(1, 0), r3);

        loc1 = new Location(0, 0);
        loc2 = new Location(0, 1);
        loc3 = new Location(1, 0);

    }

    @After
    public void teardown(){
        Map.reset();
    }

    @Test
    public void CapitalManager_Food_Test(){
        Map.reset();
        Map.setMoveDebug();
        capital.setLocation(loc1);
        capital.setNumTotalOfWorkers(10);
        capitalManager.setNumOfWorkers_Unassigned(10);
        capital.unassign();
        capitalManager.setHarvestingFoodLocation(loc1);
        assertEquals(10, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingFood());
        assertEquals(100, r1.getFoodLevel());
        capital.assignWorkersToFarm(loc1, 6);
        assertEquals(5, capitalManager.getNumOfWorkers_HarvestingFood());
        assertEquals(5, capitalManager.getNumOfWorkers_Unassigned());
        capital.doWork();
        assertEquals(30, p1.getFood().getAmount());
        capital.unassign();
        capital.assignWorkersToFarm(loc2, 5);
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingFood());
        capitalManager.getWorkerStats().setWorkerRadius(1);
        capital.assignWorkersToFarm(loc2, 5);
        assertEquals(5, capitalManager.getNumOfWorkers_HarvestingFood());
        assertEquals(false, resourceManager.isWorking(loc1));
        assertEquals(true, resourceManager.isWorking(loc2));
        capital.doWork();
        assertEquals(60, p1.getFood().getAmount());
        capital.unassign();
        assertEquals(false, resourceManager.isWorking(loc2));
    }

    @Test
    public void CapitalManager_Metal_Test(){
        Map.reset();
        Map.setMoveDebug();
        capital.setLocation(loc1);
        capital.setNumTotalOfWorkers(10);
        capitalManager.setNumOfWorkers_Unassigned(10);
        capital.unassign();
        capitalManager.setHarvestingOreLocation(loc1);
        assertEquals(10, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingOre());
        assertEquals(100, r1.getFoodLevel());
        capital.assignWorkersToMine(loc1, 6);
        assertEquals(5, capitalManager.getNumOfWorkers_HarvestingOre());
        assertEquals(5, capitalManager.getNumOfWorkers_Unassigned());
        capital.doWork();
        assertEquals(30, p1.getOre().getAmount());
        capital.unassign();
        capital.assignWorkersToMine(loc2, 5);
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingOre());
        capitalManager.getWorkerStats().setWorkerRadius(1);
        capital.assignWorkersToMine(loc2, 5);
        assertEquals(5, capitalManager.getNumOfWorkers_HarvestingOre());
        assertEquals(false, resourceManager.isWorking(loc1));
        assertEquals(true, resourceManager.isWorking(loc2));
        capital.doWork();
        assertEquals(60, p1.getOre().getAmount());
        capital.unassign();
        assertEquals(false, resourceManager.isWorking(loc2));
    }

    @Test
    public void CapitalManager_Energy_Test(){
        Map.reset();
        Map.setMoveDebug();
        capital.setLocation(loc1);
        capital.setNumTotalOfWorkers(10);
        capitalManager.setNumOfWorkers_Unassigned(10);
        capital.unassign();
        capitalManager.setHarvestingEnergyLocation(loc1);
        assertEquals(10, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingEnergy());
        assertEquals(100, r1.getEnergyLevel());
        capital.assignWorkersToPowerHarvest(loc1, 6);
        assertEquals(5, capitalManager.getNumOfWorkers_HarvestingEnergy());
        assertEquals(5, capitalManager.getNumOfWorkers_Unassigned());
        capital.doWork();
        assertEquals(30, p1.getEnergy().getAmount());
        capital.unassign();
        capital.assignWorkersToPowerHarvest(loc2, 5);
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingEnergy());
        capitalManager.getWorkerStats().setWorkerRadius(1);
        capital.assignWorkersToPowerHarvest(loc2, 5);
        assertEquals(5, capitalManager.getNumOfWorkers_HarvestingEnergy());
        assertEquals(false, resourceManager.isWorking(loc1));
        assertEquals(true, resourceManager.isWorking(loc2));
        capital.doWork();
        assertEquals(60, p1.getEnergy().getAmount());
        capital.unassign();
        assertEquals(false, resourceManager.isWorking(loc2));
    }

    @Test
    public void CapitalManager_ExplorerTrainingTest(){
        Map.reset();
        Map.setMoveDebug();
        capital.setLocation(loc1);
        capital.setNumTotalOfWorkers(10);
        capitalManager.setNumOfWorkers_Unassigned(10);
        capital.unassign();
        capital.assignWorkersToTrainExplorers(5);
        assertEquals(5, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(5, capitalManager.getNumOfWorkers_ExplorerTraining());
        assertEquals(50, capitalManager.trainExplorer());
        capital.unassign();
        assertEquals(0, capitalManager.trainExplorer());
        capital.assignWorkersToTrainExplorers(50);
        assertEquals(10, capitalManager.getNumOfWorkers_ExplorerTraining());
        assertEquals(0, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(0, PlayerManager.getInstance().getUnits(p1.getId()).size());
        capital.doWork();
        assertEquals(1, PlayerManager.getInstance().getUnits(p1.getId()).size());
    }

    @Test
    public void CapitalManager_BreedingTest() {
        Map.reset();
        Map.setMoveDebug();
        capital.setLocation(loc1);
        capital.setNumTotalOfWorkers(10);
        capitalManager.setNumOfWorkers_Unassigned(10);
        capital.unassign();
        capital.assignWorkersToBreed(4);
        assertEquals(6, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(4, capitalManager.getNumOfWorkers_Breeding());
        assertEquals(2, capitalManager.breeding());
        capital.unassign();
        assertEquals(0, capitalManager.breeding());
        capital.assignWorkersToBreed(50);
        assertEquals(10, capitalManager.getNumOfWorkers_Breeding());
        assertEquals(0, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(5, capitalManager.breeding());
    }

    @Test
    public void CapitalManager_ComprehensiveHarvesting_Test(){
        Map.reset();
        Map.setMoveDebug();
        capital.setLocation(loc1);
        capital.addWorker(10);
        capital.unassign();
        capital.assignWorkersToFarm(loc2, 5);
        assertEquals(false, resourceManager.get(loc2).getWorking());
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingFood());
        assertEquals(10, capitalManager.getNumOfWorkers_Unassigned());
        capital.assignWorkersToFarm(loc1, 5);
        assertEquals(5, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(5, capitalManager.getNumOfWorkers_HarvestingFood());
        assertEquals(true, resourceManager.get(loc1).getWorking());
        capital.assignWorkersToMine(loc1, 4);
        assertEquals(4, capitalManager.getNumOfWorkers_HarvestingOre());
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingFood());
        assertEquals(6, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(true, resourceManager.get(loc1).getWorking());
        capitalManager.getWorkerStats().setWorkerRadius(1);
        capital.assignWorkersToFarm(loc2,3);
        assertEquals(3, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(3, capitalManager.getNumOfWorkers_HarvestingFood());
        assertEquals(4, capitalManager.getNumOfWorkers_HarvestingOre());
        assertEquals(true, resourceManager.get(loc1).getWorking());
        assertEquals(true, resourceManager.get(loc2).getWorking());
        assertEquals(false, resourceManager.get(loc3).getWorking());
        capital.assignWorkersToPowerHarvest(loc3, 2);
        assertEquals(1, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(2, capitalManager.getNumOfWorkers_HarvestingEnergy());
        assertEquals(4, capitalManager.getNumOfWorkers_HarvestingOre());
        assertEquals(3, capitalManager.getNumOfWorkers_HarvestingFood());
        assertEquals(true, resourceManager.get(loc1).getWorking());
        assertEquals(true, resourceManager.get(loc2).getWorking());
        assertEquals(true, resourceManager.get(loc3).getWorking());
        capital.unassign();
        assertEquals(10, capitalManager.getNumOfWorkers_Unassigned());
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingEnergy());
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingOre());
        assertEquals(0, capitalManager.getNumOfWorkers_HarvestingFood());
        assertEquals(false, resourceManager.get(loc1).getWorking());
        assertEquals(false, resourceManager.get(loc2).getWorking());
        assertEquals(false, resourceManager.get(loc3).getWorking());
    }

}
