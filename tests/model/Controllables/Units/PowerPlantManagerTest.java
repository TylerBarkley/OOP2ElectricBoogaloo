package model.Controllables.Units;
import model.Controllables.Structures.Farm;
import model.Controllables.Structures.FarmManager;
import model.Controllables.Structures.PowerPlant;
import model.Controllables.Structures.PowerPlantManager;
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
public class PowerPlantManagerTest {

    Player p1;


    StructureStats structureStats;
    PowerPlant powerPlant;
    WorkerStats workerStats;
    PowerPlantManager powerPlantManager;
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
        powerPlantManager = new PowerPlantManager();
        powerPlant = new PowerPlant();
        powerPlant.setPowerPlantManager(powerPlantManager);
        powerPlant.setLocation(new Location(0, 0));
        powerPlant.setStats(workerStats);

        PlayerManager.getInstance().addStructure(p1.getId(), powerPlant);

        r1 = new ResourceLevel(100, 100, 100);
        r2 = new ResourceLevel(50, 50, 50);
        r1.setWorking(false);
        r2.setWorking(false);

        map = Map.getInstance();
        resourceManager = map.getResourceManager();

        powerPlantManager.setResourceManager(resourceManager);

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
    public void PowerPlantManager_Test(){
        Map.reset();
        Map.setMoveDebug();
        powerPlant.setLocation(loc1);
        powerPlant.setNumTotalOfWorkers(10);
        powerPlantManager.setNumOfWorkers_Unassigned(10);
        powerPlant.unassign();
        powerPlantManager.setHarvestingLocation(loc1);
        assertEquals(10, powerPlantManager.getNumOfWorkers_Unassigned());
        assertEquals(0, powerPlantManager.getNumOfWorkers_Harvesting());
        assertEquals(100, r1.getEnergyLevel());
        powerPlant.assignWorkersToPowerPlant(loc1, 6);
        assertEquals(5, powerPlantManager.getNumOfWorkers_Harvesting());
        assertEquals(5, powerPlantManager.getNumOfWorkers_Unassigned());
        powerPlant.doWork();
        assertEquals(30, p1.getEnergy().getAmount());
        powerPlant.unassign();
        powerPlant.assignWorkersToPowerPlant(loc2, 5);
        assertEquals(0, powerPlantManager.getNumOfWorkers_Harvesting());
        powerPlantManager.getWorkerStats().setWorkerRadius(1);
        powerPlant.assignWorkersToPowerPlant(loc2, 5);
        assertEquals(5, powerPlantManager.getNumOfWorkers_Harvesting());
        assertEquals(false, resourceManager.isWorking(loc1));
        assertEquals(true, resourceManager.isWorking(loc2));
        powerPlant.doWork();
        assertEquals(60, p1.getEnergy().getAmount());
        powerPlant.unassign();
        assertEquals(false, resourceManager.isWorking(loc2));

    }
}
