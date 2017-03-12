package model.Controllables.Units;
import static org.junit.Assert.*;

import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.*;
import model.Location;
import model.Map.Occupancy.StructureOccupancy;
import model.Map.Resources.*;
import model.player.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tyler Barkley on 3/9/2017.
 */
public class WorkerManagerTest {
/*
    WorkerManager workerManager;
    WorkerManager workerManager2;
    WorkerStats workerStats;

    ResourceLevel rl;

    Player p1;

    Ore ore;
    Energy energy;
    Food food;
    ResourceLevel rl1;
    ResourceLevel rl2;
    ResourceLevel r13;
    ResourceManager rm;

    StructureStats structureStats;

    Capital capital;
    Mine mine;
    Farm farm;
    PowerPlant powerPlant;
    University university;

    StructureOccupancy capitalOcc;
    StructureOccupancy mineOcc;

    Location loc1;
    Location loc2;

    @Before
    public void setUp(){
        workerManager = new WorkerManager();
        workerManager2 = new WorkerManager();
        workerStats = new WorkerStats();
        structureStats = new StructureStats();

        workerStats.setBreeding(1);
        workerStats.setOreProduction(1);
        workerStats.setTechnologyProduction(2);
        workerStats.setSoldierTraining(1);
        workerStats.setBuildingRate(1);
        workerStats.setExplorerTraining(1);
        workerStats.setFoodProduction(1);
        workerStats.setEnergyProduction(5);

        structureStats.setArmor(1);
        structureStats.setDefensiveDamage(1);
        structureStats.setHealth(1);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(1);
        structureStats.setProductionRate(1);
        structureStats.setUpkeep(1);
        structureStats.setWorkerRadius(1);

        workerManager.setWS(workerStats);
        workerManager2.setWS(workerStats);

        ore = new Ore(100);
        food = new Food(100);
        energy = new Energy(100);
        rl1 = new ResourceLevel(100, 100, 100);
        rl2 = new ResourceLevel(50, 50, 50);
        r13 = new ResourceLevel(60, 0, 0);
        rm = new ResourceManager();

        rm.add(new Location(0, 0), rl1);
        rm.add(new Location(0, 1), rl2);
        rm.add(new Location(1, 2), r13);

        workerManager.setRM(rm);
        workerManager2.setRM(rm);

        p1 = new Player();

        capital = new Capital();
        mine = new Mine();
        farm = new Farm();
        powerPlant = new PowerPlant();

        capital.setMyStats(structureStats);
        mine.setMyStats(structureStats);
        farm.setMyStats(structureStats);
        powerPlant.setMyStats(structureStats);

        p1.addStructure(capital);
        p1.addStructure(mine);
        p1.addStructure(farm);
        p1.addStructure(powerPlant);

        capitalOcc = new StructureOccupancy();
        mineOcc = new StructureOccupancy();


        Location loc1 = new Location(0, 0);
        Location loc2 = new Location(0, 1);
    }

    @Test
    public void mine_Ore_Test(){
        loc1 = new Location(0, 0);
        capital.setLocation(loc1);
        workerManager.setNumOfWorkers_Unassigned(50);
        capital.setWorkerManager(workerManager);
        capital.setNumOfWorkers(50);
        assertEquals(50, capital.getNumOfWorkers());
        capital.assignWorkersToMine(loc1, 25);
        assertEquals(25, workerManager.getNumOfWorkers_HarvestingOre());
        assertEquals(25, workerManager.getNumOfWorkers_Unassigned());
        assertEquals(100, rl1.getOreLevel());
        assertEquals(100, rl1.getEnergyLevel());
        capital.doWork();
        assertEquals(50, rl1.getOreLevel());
        assertEquals(100, rl1.getEnergyLevel());
        capital.doWork();
        assertEquals(0, rl1.getOreLevel());
        capital.doWork();
        assertEquals(0, rl1.getOreLevel());
        capital.assignWorkersToFarm(loc1, 10);
        capital.assignWorkersToPowerPlant(loc1, 10);
        capital.doWork();
        assertEquals(40, rl1.getEnergyLevel());
        assertEquals(80, rl1.getFoodLevel());
        assertEquals(0, rl1.getOreLevel());
        capital.doWork();
        assertEquals(0, rl1.getEnergyLevel());
        assertEquals(60, rl1.getFoodLevel());
        assertEquals(5, workerManager.getNumOfWorkers_Unassigned());
        assertEquals(10, workerManager.getNumOfWorkers_HarvestingEnergy());
        assertEquals(10, workerManager.getNumOfWorkers_HarvestingFood());
        assertEquals(5, workerManager.getNumOfWorkers_Unassigned());
        capital.unassign();
        assertEquals(0, workerManager.getNumOfWorkers_HarvestingOre());
        assertEquals(0, workerManager.getNumOfWorkers_HarvestingFood());
        assertEquals(0, workerManager.getNumOfWorkers_HarvestingEnergy());
        assertEquals(50, workerManager.getNumOfWorkers_Unassigned());
        assertEquals(40, p1.getFood().getAmount());

        loc2 = new Location(0, 1);
        mine.setLocation(loc2);
        workerManager2.setNumOfWorkers_Unassigned(25);
        mine.setWorkerManager(workerManager2);
        mine.setNumTotalOfWorkers(25);
        assertEquals(0, workerManager2.getNumOfWorkers_HarvestingOre());
        assertEquals(25, workerManager2.getNumOfWorkers_Unassigned());
        mine.unassign();
        assertEquals(0, workerManager2.getNumOfWorkers_HarvestingOre());
        assertEquals(25, workerManager2.getNumOfWorkers_Unassigned());
        mine.assignWorkersToMine(loc2, 10);
        assertEquals(100, p1.getOre().getAmount());
        assertEquals(50, rl2.getOreLevel());
        mine.doWork();
        assertEquals(120, p1.getOre().getAmount());
        assertEquals(30, rl2.getOreLevel());
        assertEquals(50, rl2.getFoodLevel());
        mine.doWork();
        mine.doWork();
        assertEquals(150, p1.getOre().getAmount());
        assertEquals(0, rl2.getOreLevel());
        assertEquals(50, rl2.getEnergyLevel());
        assertEquals(15, workerManager2.getNumOfWorkers_Unassigned());
        assertEquals(10, workerManager2.getNumOfWorkers_HarvestingOre());
        mine.unassign();
        assertEquals(0, workerManager2.getNumOfWorkers_HarvestingOre());
        assertEquals(25, workerManager2.getNumOfWorkers_Unassigned());

    }

    @Test
    public void Harvest_Food_Test(){

        loc2 = new Location(0, 1);
        farm.setLocation(loc2);
        workerManager2.setNumOfWorkers_Unassigned(25);
        farm.setWorkerManager(workerManager2);
        farm.setNumTotalOfWorkers(25);
        assertEquals(0, workerManager2.getNumOfWorkers_HarvestingFood());
        assertEquals(25, workerManager2.getNumOfWorkers_Unassigned());
        farm.unassign();
        assertEquals(0, workerManager2.getNumOfWorkers_HarvestingFood());
        assertEquals(25, workerManager2.getNumOfWorkers_Unassigned());
        farm.assignWorkersToFarm(loc2, 10);
        assertEquals(0, p1.getFood().getAmount());
        assertEquals(50, rl2.getFoodLevel());
        farm.doWork();
        assertEquals(20, p1.getFood().getAmount());
        assertEquals(30, rl2.getFoodLevel());
        assertEquals(50, rl2.getOreLevel());
        farm.doWork();
        farm.doWork();
        assertEquals(50, p1.getFood().getAmount());
        assertEquals(0, rl2.getFoodLevel());
        assertEquals(50, rl2.getEnergyLevel());
        assertEquals(15, workerManager2.getNumOfWorkers_Unassigned());
        assertEquals(10, workerManager2.getNumOfWorkers_HarvestingFood());
        farm.unassign();
        assertEquals(0, workerManager2.getNumOfWorkers_HarvestingFood());
        assertEquals(25, workerManager2.getNumOfWorkers_Unassigned());

    }

    @Test
    public void Harvest_Power_Test(){

        loc2 = new Location(0, 1);
        powerPlant.setLocation(loc2);
        workerManager2.setNumOfWorkers_Unassigned(25);
        powerPlant.setWorkerManager(workerManager2);
        powerPlant.setNumTotalOfWorkers(25);
        assertEquals(0, workerManager2.getNumOfWorkers_HarvestingEnergy());
        assertEquals(25, workerManager2.getNumOfWorkers_Unassigned());
        powerPlant.unassign();
        assertEquals(0, workerManager2.getNumOfWorkers_HarvestingEnergy());
        assertEquals(25, workerManager2.getNumOfWorkers_Unassigned());
        powerPlant.assignWorkersToPowerPlant(loc2, 10);
        assertEquals(0, p1.getEnergy().getAmount());
        assertEquals(50, rl2.getEnergyLevel());
        powerPlant.doWork();
        assertEquals(50, p1.getEnergy().getAmount());
        assertEquals(50, rl2.getFoodLevel());
        assertEquals(50, rl2.getOreLevel());
        powerPlant.doWork();
        powerPlant.doWork();
        assertEquals(50, p1.getEnergy().getAmount());
        assertEquals(50, rl2.getFoodLevel());
        assertEquals(0, rl2.getEnergyLevel());
        assertEquals(15, workerManager2.getNumOfWorkers_Unassigned());
        assertEquals(10, workerManager2.getNumOfWorkers_HarvestingEnergy());
        powerPlant.unassign();
        assertEquals(0, workerManager2.getNumOfWorkers_HarvestingEnergy());
        assertEquals(25, workerManager2.getNumOfWorkers_Unassigned());

    }
*/

}
