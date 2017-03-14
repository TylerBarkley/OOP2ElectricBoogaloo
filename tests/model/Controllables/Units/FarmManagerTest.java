package model.Controllables.Units;
import model.Controllables.Structures.Farm;
import model.Controllables.Structures.FarmManager;
import model.Location;
import model.Map.Map;
import model.Map.Resources.ResourceLevel;
import model.Map.Resources.ResourceManager;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.WorkerStats;

/**
 * Created by Tyler Barkley on 3/12/2017.
 */
public class FarmManagerTest {

    Player p1;


    StructureStats structureStats;
    Farm farm;
    WorkerStats workerStats;
    FarmManager farmManager;
    ResourceManager resourceManager;
    ResourceLevel r1;
    ResourceLevel r2;

    Location loc1;
    Location loc2;

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
        farmManager = new FarmManager();
        farm = new Farm();
        farm.setFarmManager(farmManager);
        farm.setLocation(new Location(0, 0));
        farm.setStats(workerStats);

        PlayerManager.getInstance().addStructure(p1.getId(), farm);

        r1 = new ResourceLevel(100, 100, 100);
        r2 = new ResourceLevel(50, 50, 50);
        r1.setWorking(false);
        r2.setWorking(false);

        map = Map.getInstance();
        resourceManager = map.getResourceManager();

        farmManager.setResourceManager(resourceManager);

        resourceManager.add(new Location(0,0), r1);
        resourceManager.add(new Location(0, 1), r2);

        loc1 = new Location(0, 0);
        loc2 = new Location(0, 1);

    }

    @After
    public void teardown(){
        Map.reset();
    }

    @Test
    public void FarmManager_Test(){
        Map.reset();
        Map.setMoveDebug();
        farm.setLocation(loc1);
        farm.setNumTotalOfWorkers(10);
        farmManager.setNumOfWorkers_Unassigned(10);
        farm.unassign();
        farmManager.setHarvestingLocation(loc1);
        assertEquals(10, farmManager.getNumOfWorkers_Unassigned());
        assertEquals(0, farmManager.getNumOfWorkers_Harvesting());
        assertEquals(100, r1.getFoodLevel());
        farm.assignWorkersToFarm(loc1, 6);
        assertEquals(5, farmManager.getNumOfWorkers_Harvesting());
        assertEquals(5, farmManager.getNumOfWorkers_Unassigned());
        farm.setBeingBuilt(false);
        farm.doWork();
        assertEquals(30, p1.getFood().getAmount());
        farm.unassign();
        farm.assignWorkersToFarm(loc2, 5);
        assertEquals(0, farmManager.getNumOfWorkers_Harvesting());
        farmManager.getWorkerStats().setWorkerRadius(1);
        farm.assignWorkersToFarm(loc2, 5);
        assertEquals(5, farmManager.getNumOfWorkers_Harvesting());
        assertEquals(false, resourceManager.isWorking(loc1));
        assertEquals(true, resourceManager.isWorking(loc2));
        farm.doWork();
        assertEquals(60, p1.getFood().getAmount());
        farm.unassign();
        assertEquals(false, resourceManager.isWorking(loc2));

    }
}
