package model.Controllables.Units;
import static org.junit.Assert.*;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.Fort;
import model.Controllables.Structures.FortManager;
import model.Location;
import model.Map.Map;
import model.Map.Occupancy.StructureOccupancy;
import model.Map.Occupancy.StructureOccupancyManager;
import model.Map.Occupancy.UnitOccupancy;
import model.Map.Occupancy.UnitOccupancyManager;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tyler Barkley on 3/12/2017.
 */
public class FortManagerTest {

    Fort fort;
    WorkerStats workerStats;
    FortManager fortManager;
    Location loc1;
    Location loc2;
    Melee melee;

    Player p1;
    Player p2;

    PlayerManager playerManager;

    StructureOccupancyManager som;

    UnitOccupancyManager uom;
    UnitOccupancy meleeOcc;
    StructureOccupancy fortOcc;

    @Before
    public void setUp(){
        workerStats = new WorkerStats();
        p1 = new Player();
        p2 = new Player();

        playerManager = PlayerManager.getInstance();

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

        fort = new Fort();
        playerManager.addStructure(p1.getId(), fort);
        loc1 = new Location(0,0);
        loc2 = new Location(0, 1);
        fortOcc = new StructureOccupancy();
        meleeOcc = new UnitOccupancy();
        som = new StructureOccupancyManager();
        uom = new UnitOccupancyManager();

        fortManager = new FortManager();
        fort.setFortManager(fortManager);
        fort.setStats(workerStats);
        fort.setLocation(loc1);
        melee = new Melee();
        playerManager.addUnit(p2.getId(), melee);
        melee.setLocation(loc2);
    }

    @Test
    public void FortManager_Test(){
        Map.reset();
        Map.setMoveDebug();
        Map.getInstance().addStructure(loc1, fort);
        Map.getInstance().addUnit(loc2, melee);
        fort.setBeingBuilt(false);
        fort.setNumTotalOfWorkers(10);
        fort.setNumOfSoldiers(2);
        fortManager.setNumOfWorkers_Unassigned(10);
        fort.unassign();
        assertEquals(10, fortManager.getNumOfWorkers_Unassigned());
        assertEquals(0, fortManager.getNumOfWorkers_SoldierTraining());
        melee.setCurrentHealth(100);
        fort.doWork();
        assertEquals(10, melee.getCurrentHealth());
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
