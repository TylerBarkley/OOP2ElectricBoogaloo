package model;

import model.Controllables.Army;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.Capital;
import model.Controllables.Units.Melee;
import model.player.Player;

public class ResourceManagementTest {

   WorkerStats workerStats;
   Player player;
   Melee melee;
   Army army;
   Capital capital;



    /*@Before
    public void testSetup(){
       player=new Player();
       melee =new Melee();
       capital=new Capital();

       player.addUnit(melee);
       army=new RallyPoint(melee).getArmy();

       player.addStructure(capital);
       player.addArmy(army);
       player.addMetal(10);
       player.addPower(10);
       player.addNutrients(10);
    }

    @Test
    public void resourceDistributionTest(){
        player.distributePower(capital,50);
        player.storePower(50);
        player.distributeNutrients(capital,20);
        player.storeNutrients(80);
        player.distributeMetal(capital, 10);
        player.storeMetal(90);
        assertEquals(5,capital.getEnergyResourceLevel());
        assertEquals(2,capital.getNutrientResourceLevel());
        assertEquals(1,capital.getMetalResourceLevel());
        assertEquals(5,player.getPower().getAmount());
        assertEquals(8,player.getNutrients().getAmount());
         assertEquals(9,player.getMetal().getAmount());
    }
   @Test
    public void resourceConsumptionTest(){
        player.distributePower(capital,50);
        player.distributeNutrients(capital,20);
        player.distributeMetal(capital, 10);
        player.distributePower(army,30);
        player.distributeNutrients(army,20);
        player.distributeMetal(army, 10);
        player.storePower(20);
        player.storeNutrients(60);
        player.storeMetal(80);
        army.distribute();
        capital.distribute();
        assertEquals(melee.getUpkeep(),melee.getEnergyResourceLevel());
        assertEquals(melee.getUpkeep(),melee.getMetalResourceLevel());
        assertEquals(melee.getUpkeep(),melee.getNutrientResourceLevel());
    }*/
}
