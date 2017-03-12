package model.Controllables.Units;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.CapitalManager;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tyler Barkley on 3/12/2017.
 */
public class CapitalManagerTest {

    StructureStats structureStats;
    Capital capital;
    WorkerStats workerStats;
    CapitalManager capitalManager;

    @Before
    public void setUp(){
        workerStats = new WorkerStats();
        structureStats = new StructureStats();

        workerStats.setBreeding(1);
        workerStats.setOreProduction(1);
        workerStats.setTechnologyProduction(2);
        workerStats.setSoldierTraining(1);
        workerStats.setBuildingRate(1);
        workerStats.setExplorerTraining(1);
        workerStats.setFoodProduction(1);
        workerStats.setEnergyProduction(1);
        workerStats.setWorkerRadius(0);
        workerStats.setWorkerDensity(5);

        structureStats.setArmor(1);
        structureStats.setDefensiveDamage(1);
        structureStats.setHealth(1);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(1);
        structureStats.setProductionRate(1);
        structureStats.setUpkeep(1);

        capital = new Capital();

        capital.setMyStats(structureStats);
        capital.setStats(workerStats);



    }

}
