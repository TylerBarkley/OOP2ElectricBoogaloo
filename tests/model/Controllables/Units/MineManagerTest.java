package model.Controllables.Units;
import model.Controllables.Structures.Mine;
import model.Controllables.Structures.MineManager;
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

public class MineManagerTest {
    Player p1;


    StructureStats structureStats;
    Mine mine;
    WorkerStats workerStats;
    MineManager mineManager;
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
        mineManager = new MineManager();
        mine = new Mine();
        mine.setMineManager(mineManager);
        mine.setLocation(new Location(0, 0));
        mine.setStats(workerStats);

        PlayerManager.getInstance().addStructure(p1.getId(), mine);

        r1 = new ResourceLevel(100, 100, 100);
        r2 = new ResourceLevel(50, 50, 50);
        r1.setWorking(false);
        r2.setWorking(false);

        map = Map.getInstance();
        resourceManager = map.getResourceManager();

        mineManager.setResourceManager(resourceManager);

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
    public void MineManager_Test(){
        Map.reset();
        Map.setMoveDebug();
        mine.setLocation(loc1);
        mine.setBeingBuilt(false);
        mine.setNumTotalOfWorkers(10);
        mineManager.setNumOfWorkers_Unassigned(10);
        mine.unassign();
        mineManager.setHarvestingLocation(loc1);
        assertEquals(10, mineManager.getNumOfWorkers_Unassigned());
        assertEquals(0, mineManager.getNumOfWorkers_Harvesting());
        assertEquals(100, r1.getFoodLevel());
        mine.assignWorkersToMine(loc1, 6);
        assertEquals(5, mineManager.getNumOfWorkers_Harvesting());
        assertEquals(5, mineManager.getNumOfWorkers_Unassigned());
        mine.doWork();
        assertEquals(30, p1.getOre().getAmount());
        mine.unassign();
        mine.assignWorkersToMine(loc2, 5);
        assertEquals(0, mineManager.getNumOfWorkers_Harvesting());
        mineManager.getWorkerStats().setWorkerRadius(1);
        mine.assignWorkersToMine(loc2, 5);
        assertEquals(5, mineManager.getNumOfWorkers_Harvesting());
        assertEquals(false, resourceManager.isWorking(loc1));
        assertEquals(true, resourceManager.isWorking(loc2));
        mine.doWork();
        assertEquals(60, p1.getOre().getAmount());
        mine.unassign();
        assertEquals(false, resourceManager.isWorking(loc2));

    }

}
