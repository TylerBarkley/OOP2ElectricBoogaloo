package model.Controllables.Units;
import static org.junit.Assert.*;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.University;
import model.Controllables.Structures.UniversityManager;
import org.junit.Before;
import org.junit.Test;

public class UniversityManagerTest {


    StructureStats structureStats;
    University university;
    WorkerStats workerStats;
    UniversityManager universityManager;

    @Before
    public void setUp(){
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

        structureStats.setArmor(1);
        structureStats.setDefensiveDamage(1);
        structureStats.setHealth(1);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(1);
        structureStats.setProductionRate(1);
        structureStats.setUpkeep(1);

        university = new University();

        universityManager = new UniversityManager();
        university.setUniversityManager(universityManager);
        university.setMyStats(structureStats);
        university.setStats(workerStats);

    }

    @Test
    public void University_Test(){
        university.unassign();
        assertEquals(0, university.getNumTotalOfWorkers());
        university.addWorker(10);
        assertEquals(10, university.getNumTotalOfWorkers());
        university.setBeingBuilt(false);
        assertEquals(10, university.getNumTotalOfWorkers());
        assertEquals(10, universityManager.getNumOfWorkers_Unassigned());
        university.assignWorkersToHarvestTechnology(5);
        assertEquals(5, universityManager.getNumOfWorkers_HarvestingTechnology());
        assertEquals(5, universityManager.getNumOfWorkers_Unassigned());
        university.doWork();
        assertEquals(10, universityManager.produceTechnology(university.getMyStats().getProductionRate()));
        university.assignWorkersToHarvestTechnology(50);
        assertEquals(10, universityManager.getNumOfWorkers_HarvestingTechnology());
        university.assignWorkersToHarvestTechnology(6);
        assertEquals(6, universityManager.getNumOfWorkers_HarvestingTechnology());
        assertEquals(4, universityManager.getNumOfWorkers_Unassigned());
        assertEquals(0, universityManager.getNumOfWorkers_Building());
        assertEquals(12, universityManager.produceTechnology(university.getMyStats().getProductionRate()));
        university.assignWorkersToHarvestTechnology(-5);
        assertEquals(0, universityManager.getNumOfWorkers_HarvestingTechnology());
        assertEquals(10, universityManager.getNumOfWorkers_Unassigned());
        university.assignWorkersToHarvestTechnology(2);
        assertEquals(2, universityManager.getNumOfWorkers_HarvestingTechnology());
        university.unassign();
        assertEquals(10, universityManager.getNumOfWorkers_Unassigned());
        assertEquals(0, universityManager.getNumOfWorkers_Building());
        assertEquals(0, universityManager.getNumOfWorkers_HarvestingTechnology());
    }
}
