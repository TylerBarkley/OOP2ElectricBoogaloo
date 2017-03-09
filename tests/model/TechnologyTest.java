package model;

import static org.junit.Assert.*;

import model.Map.Items.HealOneShot;
import model.Map.Items.OneShotItem;
import model.Map.Items.OneShotManager;
import org.junit.Before;
import org.junit.Test;
import model.Controllables.Units.*;
import model.Technology;
import model.Stats.*;


/**
 * Created by zrgam_000 on 3/5/2017.
 */
public class TechnologyTest {

    WorkerStats workerStats;
    ArrayList<UnitStats> unitStats;
    ArrayList<StructureStats> structureStats;
    Technology technology;


    @Before
    public void testSetup(){
       workerStats=new WorkerStats();
       unitStats=new ArrayList<UnitStats>();
       structureStats=new ArrayList<StructureStats>();
       unitStats.add(new UnitStats());
       unitStats.add(new UnitStats());
       unitsStats.add(new UnitStats());
       structureStats.add(new StructureStats());
       structureStats.add(new StructureStats());
       structureStats.add(new StructureStats());
       technology=new Technology(unitStats,workerStats,workerStats);
    }

    @Test
    public void editUnitStatsTest(){
       technology.editUnitStats(0,"health");
        technology.editUnitStats(0,"armor");
        technology.editUnitStats(0,"upkeep");
        technology.editUnitStats(0,"offensiveDamage");
        technology.editUnitStats(0,"defensiveDamage");
        technology.editUnitStats(0,"influenceRadius");
        technology.editUnitStats(0,"movement");
        technology.editUnitStats(1,"health");
        technology.editUnitStats(1,"armor");
        technology.editUnitStats(1,"upkeep");
        technology.editUnitStats(1,"offensiveDamage");
        technology.editUnitStats(1,"defensiveDamage");
        technology.editUnitStats(1,"influenceRadius");
        technology.editUnitStats(1,"movement");
        assertEquals(1,technology.getUnitAdvancements(0,"health"));
        assertEquals(1,technology.getUnitAdvancements(0,"armor"));
        assertEquals(1,technology.getUnitAdvancements(0,"upkeep"));
        assertEquals(1,technology.getUnitAdvancements(0,"offensiveDamage"));
        assertEquals(1,technology.getUnitAdvancements(0,"defensiveDamage"));
        assertEquals(1,technology.getUnitAdvancements(0,"influenceRadius"));
        assertEquals(1,technology.getUnitAdvancements(0,"movement"));
        assertEquals(1,technology.getUnitAdvancements(1,"health"));
        assertEquals(1,technology.getUnitAdvancements(1,"armor"));
        assertEquals(1,technology.getUnitAdvancements(1,"upkeep"));
        assertEquals(1,technology.getUnitAdvancements(1,"offensiveDamage"));
        assertEquals(1,technology.getUnitAdvancements(1,"defensiveDamage"));
        assertEquals(1,technology.getUnitAdvancements(1,"influenceRadius"));
        assertEquals(1,technology.getUnitAdvancements(1,"movement"));
        assertEquals(0,technology.getUnitAdvancements(2,"health"));
        assertEquals(0,technology.getUnitAdvancements(2,"armor"));
        assertEquals(0,technology.getUnitAdvancements(2,"upkeep"));
        assertEquals(0,technology.getUnitAdvancements(2,"offensiveDamage"));
        assertEquals(0,technology.getUnitAdvancements(2,"defensiveDamage"));
        assertEquals(0,technology.getUnitAdvancements(2,"influenceRadius"));
        assertEquals(0,technology.getUnitAdvancements(2,"movement"));
        assertEquals(1,unitStats[0].getHealth());
        assertEquals(1,unitStats[0].getArmor());
        assertEquals(-1,unitStats[0].getUpKeep());
        assertEquals(1,unitStats[0].getOffensiveDamage());
        assertEquals(1,unitStats[0].getDefensiveDamage());
        assertEquals(1,unitStats[0].getMovement());
    }

    @Test
    public void editStructureStatsTest(){

        technology.editStructureStats(0,"health");
        technology.editStructureStats(0,"armor");
        technology.editStructureStats(0,"upkeep");
        technology.editStructureStats(0,"offensiveDamage");
        technology.editStructureStats(0,"defensiveDamage");
        technology.editStructureStats(0,"influenceRadius");
        technology.editStructureStats(0,"productionRate");
        technology.editStructureStats(1,"health");
        technology.editStructureStats(1,"armor");
        technology.editStructureStats(1,"upkeep");
        technology.editStructureStats(1,"offensiveDamage");
        technology.editStructureStats(1,"defensiveDamage");
        technology.editStructureStats(1,"influenceRadius");
        technology.editStructureStats(1,"productionRate");
        assertEquals(1,technology.getStructureAdvancements(0,"health"));
        assertEquals(1,technology.getStructureAdvancements(0,"armor"));
        assertEquals(1,technology.getStructureAdvancements(0,"upkeep"));
        assertEquals(1,technology.getStructureAdvancements(0,"offensiveDamage"));
        assertEquals(1,technology.getStructureAdvancements(0,"defensiveDamage"));
        assertEquals(1,technology.getStructureAdvancements(0,"influenceRadius"));
        assertEquals(1,technology.getStructureAdvancements(0,"productionRate"));
        assertEquals(1,technology.getStructureAdvancements(1,"health"));
        assertEquals(1,technology.getStructureAdvancements(1,"armor"));
        assertEquals(1,technology.getStructureAdvancements(1,"upkeep"));
        assertEquals(1,technology.getStructureAdvancements(1,"offensiveDamage"));
        assertEquals(1,technology.getStructureAdvancements(1,"defensiveDamage"));
        assertEquals(1,technology.getStructureAdvancements(1,"influenceRadius"));
        assertEquals(1,technology.getStructureAdvancements(1,"productionRate"));
        assertEquals(0,technology.getStructureAdvancements(2,"health"));
        assertEquals(0,technology.getStructureAdvancements(2,"armor"));
        assertEquals(0,technology.getStructureAdvancements(2,"upkeep"));
        assertEquals(0,technology.getStructureAdvancements(2,"offensiveDamage"));
        assertEquals(0,technology.getStructureAdvancements(2,"defensiveDamage"));
        assertEquals(0,technology.getStructureAdvancements(2,"influenceRadius"));
        assertEquals(0,technology.getStructureAdvancements(2,"productionRate"));
        assertEquals(1,structureStats[0].getHealth());
        assertEquals(1,structureStats[0].getArmor());
        assertEquals(-1,structureStats[0].getUpKeep());
        assertEquals(1,structureStats[0].getOffensiveDamage());
        assertEquals(1,structureStats[0].getDefensiveDamage());
        assertEquals(1,structureStats[0].getProductionRate());
    }
    @Test
    public void editWorkerStatsTest(){
        technology.editWorkerStats("foodProduction");
        technology.editWorkerStats("oreProduction");
        technology.editWorkerStats("energyProduction");
        technology.editWorkerStats("technologyProduction");
        technology.editWorkerStats("soldierTraining");
        technology.editWorkerStats("breeding");
        technology.editWorkerStats("explorerTesting");
        technology.editWorkerStats("buildingRate");
        assertEquals(1,technology.getWorkerStats("foodProduction"));
        assertEquals(1,technology.getWorkerStats("oreProduction"));
        assertEquals(1,technology.getWorkerStats("energyProduction"));
        assertEquals(1,technology.getWorkerStats("technologyProduction"));
        assertEquals(1,technology.getWorkerStats("soldierTraining"));
        assertEquals(1,technology.getWorkerStats("breeding"));
        assertEquals(1,technology.getWorkerStats("explorerTesting"));
        assertEquals(1,technology.getWorkerStats("buildingRate"));
        assertEquals(1,technology.getWorkerAdvancements("foodProduction"));
        assertEquals(1,technology.getWorkerAdvancements("oreProduction"));
        assertEquals(1,technology.getWorkerAdvancements("energyProduction"));
        assertEquals(1,technology.getWorkerAdvancements("technologyProduction"));
        assertEquals(1,technology.getWorkerAdvancements("soldierTraining"));
        assertEquals(1,technology.getWorkerAdvancements("breeding"));
        assertEquals(1,technology.getWorkerAdvancements("explorerTesting"));
        assertEquals(1,technology.getWorkerAdvancements("buildingRate"));
    }

}
