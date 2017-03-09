package model.Controllables.Units;
import static org.junit.Assert.*;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.WorkerManager;
import model.Location;
import model.Map.Resources.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tyler Barkley on 3/9/2017.
 */
public class WorkerManagerTest {

    private WorkerManager workerManager;
    private WorkerStats workerStats;

    Ore ore;
    Energy energy;
    Food food;
    ResourceLevel rl1;
    ResourceLevel rl2;
    ResourceManager rm;

    @Before
    public void setUp(){
        workerManager = new WorkerManager();
        workerStats = new WorkerStats();

        workerStats.setBreeding(1);
        workerStats.setOreProduction(1);
        workerStats.setTechnologyProduction(2);
        workerStats.setSoldierTraining(1);
        workerStats.setBuildingRate(1);
        workerStats.setExplorerTraining(1);
        workerStats.setFoodProduction(1);
        workerStats.setEnergyProduction(5);

        workerManager.setWS(workerStats);

        ore = new Ore(100);
        food = new Food(100);
        energy = new Energy(100);
        rl1 = new ResourceLevel(100, 100, 100);
        rl2 = new ResourceLevel(50, 50, 50);
        rm = new ResourceManager();

        rm.add(new Location(0, 0), rl1);
        rm.add(new Location(0, 1), rl2);

        workerManager.setRM(rm);

        Location loc = new Location(0, 0);
    }

    @Test
    public void mine_Ore_Test(){
        assertEquals(22, workerManager.produceOre(new Location(0,0), 10, 2));
        assertEquals(78, workerManager.produceOre(new Location(0,0), 50, 10));
        assertEquals(0, workerManager.produceOre(new Location(0,0), 1, 1));
        assertEquals(50, workerManager.produceOre(new Location(0,1), 10, 5));
    }

    @Test
    public void mine_Food_Test(){
        assertEquals(33, workerManager.produceFood(new Location(0, 0), 10, 3));
        assertEquals(50, workerManager.produceFood(new Location(0,1), 20, 3));
    }

    @Test
    public void mine_Energy_Test(){
        assertEquals(30, workerManager.produceEnergy(new Location(0, 0), 10, 2));
        assertEquals(50, workerManager.produceEnergy(new Location(0, 1), 10, 5));
    }

    @Test
    public void mine_Technology_Test(){
        assertEquals(10, workerManager.produceTechnology(3, 2));
        assertEquals(1000, workerManager.produceTechnology(8, 100));
    }

    @Test
    public void breeding_Test(){
        assertEquals(10, workerManager.breeding(20));
        assertEquals(0, workerManager.breeding(0));
    }

    @Test
    public void building_Test(){
        assertEquals(2, workerManager.building(1));
        assertEquals(100, workerManager.building(50));
    }

    @Test
    public void training_Soldier_Test(){
        assertEquals(5, workerManager.trainSoldier(3, 1));
        assertEquals(60, workerManager.trainSoldier(50, 5));
        assertEquals(100, workerManager.trainSoldier(100, 0));
        assertEquals(20, workerManager.trainSoldier(0, 10));
    }

    @Test
    public void training_Explorer_Test(){
        assertEquals(10, workerManager.trainExplorer(1));
        assertEquals(100, workerManager.trainExplorer(10));
        assertEquals(1000, workerManager.trainExplorer(100));
        assertEquals(50, workerManager.trainExplorer(5));
    }


}
