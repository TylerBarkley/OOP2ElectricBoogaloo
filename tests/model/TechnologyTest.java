package model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import model.Controllables.Units.*;
import model.Technology;
import model.Controllables.Stats.*;

public class TechnologyTest {

   /*WorkerStats workerStats;
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
@Test

    public void editWorkerStatsTest(){
        int foodProduction=workerStats.getFoodProduction();
        int oreProduction=workerStats.getOreProduction();
        int energyProduction=workerStats.getEnergyProduction();
        int technologyProduction=workerStats.getTechnologyProduction();
        int soldierTraining=workerStats.getSoldierTraining();
        int breeding=workerStats.getBreeding();
        int explorerTraining=workerStats.getExplorerTraining();
        int buildingRate=workerStats.getBuildingRate();
        int workerRadius=workerStats.getWorkerRadius();
        int workerDensity=workerStats.getWorkerDensity();
        technology.editWorkerStats("foodProduction");
        technology.editWorkerStats("oreProduction");
        technology.editWorkerStats("energyProduction");
        technology.editWorkerStats("technologyProduction");
        technology.editWorkerStats("soldierTraining");
        technology.editWorkerStats("breeding");
        technology.editWorkerStats("explorerTraining");
        technology.editWorkerStats("buildingRate");
        technology.editWorkerStats("workerDensity");
        technology.editWorkerStats("workerRadius");
        assertEquals(technology.getWorkerStatsIncrements("foodProduction")+foodProduction,workerStats.getFoodProduction());
        assertEquals(technology.getWorkerStatsIncrements("oreProduction")+oreProduction,workerStats.getOreProduction());
        assertEquals(technology.getWorkerStatsIncrements("energyProduction")+energyProduction,workerStats.getEnergyProduction());
        assertEquals(technology.getWorkerStatsIncrements("technologyProduction")+technologyProduction,workerStats.getTechnologyProduction());
        assertEquals(technology.getWorkerStatsIncrements("soldierTraining")+soldierTraining,workerStats.getSoldierTraining());
        assertEquals(technology.getWorkerStatsIncrements("breeding")+breeding,workerStats.getBreeding());
        assertEquals(technology.getWorkerStatsIncrements("explorerTraining")+explorerTraining,workerStats.getExplorerTraining());
        assertEquals(technology.getWorkerStatsIncrements("buildingRate")+buildingRate,workerStats.getBuildingRate());
        assertEquals(technology.getWorkerStatsIncrements("workerDensity")+workerDensity,workerStats.getWorkerDensity());
        assertEquals(technology.getWorkerStatsIncrements("workerRadius")+workerRadius,workerStats.getWorkerRadius());
    }*/
}
