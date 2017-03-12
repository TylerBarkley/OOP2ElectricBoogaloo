package model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import model.Controllables.Units.*;
import model.Technology;
import model.Controllables.Stats.*;


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
       unitStats.add(new UnitStats());
       structureStats.add(new StructureStats());
       structureStats.add(new StructureStats());
       structureStats.add(new StructureStats());
       technology=new Technology(unitStats,structureStats,workerStats);
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
<<<<<<< HEAD
        assertEquals(technology,technology.getUnitAdvancements(0,"health"));
        assertEquals(1,technology.getUnitAdvancements(0,"armor"));
        assertEquals(-1,technology.getUnitAdvancements(0,"upkeep"));
        assertEquals(1,technology.getUnitAdvancements(0,"offensiveDamage"));
        assertEquals(1,technology.getUnitAdvancements(0,"defensiveDamage"));
        assertEquals(1,technology.getUnitAdvancements(0,"influenceRadius"));
        assertEquals(1,technology.getUnitAdvancements(0,"movement"));
        assertEquals(1,technology.getUnitAdvancements(1,"health"));
        assertEquals(1,technology.getUnitAdvancements(1,"armor"));
        assertEquals(-1,technology.getUnitAdvancements(1,"upkeep"));
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
        assertEquals(1,unitStats.get(0).getHealth());
        assertEquals(1,unitStats.get(0).getArmor());
        assertEquals(-1,unitStats.get(0).getUpkeep());
        assertEquals(1,unitStats.get(0).getOffensiveDamage());
        assertEquals(1,unitStats.get(0).getDefensiveDamage());
        assertEquals(1,unitStats.get(0).getMovement());
=======
        assertEquals(technology.getUnitStatsIncrements(0,"health"),technology.getCurrentUnitAdvancements(0,"health"));
        assertEquals(technology.getUnitStatsIncrements(0,"armor"),technology.getCurrentUnitAdvancements(0,"armor"));
        assertEquals(technology.getUnitStatsIncrements(0,"offensiveDamage"),technology.getCurrentUnitAdvancements(0,"offensiveDamage"));
        assertEquals(technology.getUnitStatsIncrements(0,"defensiveDamage"),technology.getCurrentUnitAdvancements(0,"defensiveDamage"));
        assertEquals(technology.getUnitStatsIncrements(0,"movement"),technology.getCurrentUnitAdvancements(0,"movement"));
        assertEquals(technology.getUnitStatsIncrements(0,"upkeep"),technology.getCurrentUnitAdvancements(0,"upkeep"));
        assertEquals(technology.getUnitStatsIncrements(1,"health"),technology.getCurrentUnitAdvancements(0,"health"));
        assertEquals(technology.getUnitStatsIncrements(1,"armor"),technology.getCurrentUnitAdvancements(0,"armor"));
        assertEquals(technology.getUnitStatsIncrements(1,"offensiveDamage"),technology.getCurrentUnitAdvancements(0,"offensiveDamage"));
        assertEquals(technology.getUnitStatsIncrements(1,"defensiveDamage"),technology.getCurrentUnitAdvancements(0,"defensiveDamage"));
        assertEquals(technology.getUnitStatsIncrements(1,"movement"),technology.getCurrentUnitAdvancements(0,"movement"));
        assertEquals(technology.getUnitStatsIncrements(1,"upkeep"),technology.getCurrentUnitAdvancements(0,"upkeep"));
        assertEquals(technology.getUnitStatsIncrements(1,"health"),unitStats.get(0).getHealth());
        assertEquals(technology.getUnitStatsIncrements(0,"armor"),unitStats.get(0).getArmor());
        assertEquals(technology.getUnitStatsIncrements(0,"upkeep"),unitStats.get(0).getUpkeep());
        assertEquals(technology.getUnitStatsIncrements(0,"offensiveDamage"),unitStats.get(0).getOffensiveDamage());
        assertEquals(technology.getUnitStatsIncrements(0,"defensiveDamage"),unitStats.get(0).getDefensiveDamage());
        assertEquals(technology.getUnitStatsIncrements(0,"movement"),unitStats.get(0).getMovement());
>>>>>>> 37041f9935eb5e92aff5b15afb7de57eba6fcbb8
    }

    @Test
    public void editStructureStatsTest(){
        technology.editStructureStats(0, "health");
        technology.editStructureStats(0, "armor");
        technology.editStructureStats(0, "upkeep");
        technology.editStructureStats(0, "offensiveDamage");
        technology.editStructureStats(0, "defensiveDamage");
        technology.editStructureStats(0, "influenceRadius");
        technology.editStructureStats(0, "productionRate");
        technology.editStructureStats(1, "health");
        technology.editStructureStats(1, "armor");
        technology.editStructureStats(1, "upkeep");
        technology.editStructureStats(1, "offensiveDamage");
        technology.editStructureStats(1, "defensiveDamage");
        technology.editStructureStats(1, "influenceRadius");
        technology.editStructureStats(1, "productionRate");
        assertEquals(technology.getStructureStatsIncrements(0, "health"), technology.getCurrentStructureAdvancements(0, "health"));
        assertEquals(technology.getStructureStatsIncrements(0, "armor"), technology.getCurrentStructureAdvancements(0, "armor"));
        assertEquals(technology.getStructureStatsIncrements(0, "offensiveDamage"), technology.getCurrentStructureAdvancements(0, "offensiveDamage"));
        assertEquals(technology.getStructureStatsIncrements(0, "defensiveDamage"), technology.getCurrentStructureAdvancements(0, "defensiveDamage"));
        assertEquals(technology.getStructureStatsIncrements(0, "productionRate"), technology.getCurrentStructureAdvancements(0, "productionRate"));
        assertEquals(technology.getStructureStatsIncrements(0, "upkeep"), technology.getCurrentStructureAdvancements(0, "upkeep"));
        assertEquals(technology.getStructureStatsIncrements(1, "health"), technology.getCurrentStructureAdvancements(0, "health"));
        assertEquals(technology.getStructureStatsIncrements(1, "armor"), technology.getCurrentStructureAdvancements(0, "armor"));
        assertEquals(technology.getStructureStatsIncrements(1, "offensiveDamage"), technology.getCurrentStructureAdvancements(0, "offensiveDamage"));
        assertEquals(technology.getStructureStatsIncrements(1, "defensiveDamage"), technology.getCurrentStructureAdvancements(0, "defensiveDamage"));
        assertEquals(technology.getStructureStatsIncrements(1, "productionRate"), technology.getCurrentStructureAdvancements(0, "productionRate"));
        assertEquals(technology.getStructureStatsIncrements(1, "upkeep"), technology.getCurrentStructureAdvancements(0, "upkeep"));
        assertEquals(technology.getStructureStatsIncrements(1, "health"), structureStats.get(0).getHealth());
        assertEquals(technology.getStructureStatsIncrements(0, "armor"), structureStats.get(0).getArmor());
        assertEquals(technology.getStructureStatsIncrements(0, "upkeep"), structureStats.get(0).getUpkeep());
        assertEquals(technology.getStructureStatsIncrements(0, "offensiveDamage"), structureStats.get(0).getOffensiveDamage());
        assertEquals(technology.getStructureStatsIncrements(0, "defensiveDamage"), structureStats.get(0).getDefensiveDamage());
        assertEquals(technology.getStructureStatsIncrements(0, "productionRate"), structureStats.get(0).getProductionRate());

    }
    /*@Test

    public void editWorkerStatsTest(){
        technology.editWorkerStats("foodProduction");
        technology.editWorkerStats("oreProduction");
        technology.editWorkerStats("energyProduction");
        technology.editWorkerStats("technologyProduction");
        technology.editWorkerStats("soldierTraining");
        technology.editWorkerStats("breeding");
        technology.editWorkerStats("explorerTraining");
        technology.editWorkerStats("buildingRate");
        assertEquals(1,workerStats.getFoodProduction());
        assertEquals(1,workerStats.getOreProduction());
        assertEquals(1,workerStats.getEnergyProduction());
        assertEquals(1,workerStats.getTechnologyProduction());
        assertEquals(1,workerStats.getSoldierTraining());
        assertEquals(1,workerStats.getBreeding());
        assertEquals(1,workerStats.getExplorerTraining());
        assertEquals(1,workerStats.getBuildingRate());
        assertEquals(1,technology.getWorkerAdvancements("foodProduction"));
        assertEquals(1,technology.getWorkerAdvancements("oreProduction"));
        assertEquals(1,technology.getWorkerAdvancements("energyProduction"));
        assertEquals(1,technology.getWorkerAdvancements("technologyProduction"));
        assertEquals(1,technology.getWorkerAdvancements("soldierTraining"));
        assertEquals(1,technology.getWorkerAdvancements("breeding"));
        assertEquals(1,technology.getWorkerAdvancements("explorerTraining"));
        assertEquals(1,technology.getWorkerAdvancements("buildingRate"));
    }*/
}
