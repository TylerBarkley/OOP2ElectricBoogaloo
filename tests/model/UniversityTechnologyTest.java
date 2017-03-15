package model;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.University;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by Tyler Barkley on 3/14/2017.
 */
public class UniversityTechnologyTest {

    University university;
    ResearchCommand researchCommand;
    Technology technology;
    Player p1;
    PlayerManager playerManager;
    WorkerStats workerStats;

    @Before
    public void TestSetup()
    {
        workerStats = new WorkerStats();
        p1 = new Player();
        playerManager = PlayerManager.getInstance();
        playerManager.addPlayer(p1);

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

        university=new University();
        technology=new Technology(new ArrayList<UnitStats>(),new ArrayList<StructureStats>(),workerStats);
        researchCommand=new WorkerResearchCommand(technology,0,technology.ExplorerTraining);
    }
    @Test
    public void UniversityResearchTest(){
        university.addWorker(5);
        university.assignWorkersToHarvestTechnology(5);
        university.assignResearch(researchCommand);
        university.harvestScience();
        university.harvestScience();
        university.harvestScience();
        assertEquals(2, workerStats.getExplorerTraining());


    }
}
