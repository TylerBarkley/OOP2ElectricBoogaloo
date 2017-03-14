package model.Controllables.Units;
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
import static org.junit.Assert.*;

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
    public void FortManager_Test() {
        Map.reset();
        Map.setMoveDebug();
        Map.getInstance().addStructure(loc1, fort);
        Map.getInstance().addUnit(loc2, melee);
        fort.setNumTotalOfWorkers(10);
        fortManager.setNumOfWorkers_Unassigned(10);
        fort.unassign();
        assertEquals(10, fortManager.getNumOfWorkers_Unassigned());
        assertEquals(0, fortManager.getNumOfWorkers_MeleeTraining());
        melee.setCurrentHealth(100);
        assertEquals(0, fort.getBuiltPercentage());
        fort.doWork();
        assertEquals(20, fort.getBuiltPercentage());
        fort.doWork();
        assertEquals(40, fort.getBuiltPercentage());
        fort.doWork();
        assertEquals(60, fort.getBuiltPercentage());
        fort.assignWorkersToTrainMeleeSoldiers(5);
        fort.doWork();
        assertEquals(0, fortManager.getNumOfWorkers_MeleeTraining());
        assertEquals(80, fort.getBuiltPercentage());
        fort.doWork();
        assertEquals(false, fort.getBeingBuilt());
        fort.doWork();
        assertEquals(95, melee.getCurrentHealth());
        fort.assignWorkersToTrainMeleeSoldiers(10);
        fort.doWork();
        assertEquals(10, fort.getMeleeBuildPercentage());
        fort.doWork();
        fort.doWork();
        fort.doWork();
        fort.doWork();
        fort.doWork();
        fort.doWork();
        fort.doWork();
        fort.doWork();
        fort.unassign();
        fortManager.assignWorkersMelee(9);
        fort.doWork();
        assertEquals(99, fort.getMeleeBuildPercentage());
        fort.doWork();
        assertEquals(8, fort.getMeleeBuildPercentage());
        assertEquals(1, PlayerManager.getInstance().getUnits(p1.getId()).size());
        fort.unassign();
        fort.setNumTotalOfWorkers(40);
        fortManager.setNumOfWorkers_Unassigned(40);
        fort.assignWorkersToTrainRangedSoldiers(50);
        assertEquals(40, fortManager.getNumOfWorkers_RangedTraining());
        fort.doWork();
        fort.doWork();
        assertEquals(80, fort.getRangedBuildPercentage());
        fort.doWork();
        assertEquals(2, PlayerManager.getInstance().getUnits(p1.getId()).size());
        System.out.println(PlayerManager.getInstance().getUnits(p1.getId()).toString());
        assertEquals(20, fort.getRangedBuildPercentage());
    }


}
