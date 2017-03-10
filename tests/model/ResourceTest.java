package model;

import model.Map.Resources.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zrgam_000 on 3/5/2017.
 */
public class ResourceTest {

    Ore ore;
    Energy energy;
    Food food;
    ResourceLevel rl1;
    ResourceLevel rl2;
    ResourceManager rm;

    @Before
    public void TestSetup()
    {
        ore = new Ore(100);
        food = new Food(100);
        energy = new Energy(100);
        rl1 = new ResourceLevel(100, 100, 100);
        rl2 = new ResourceLevel(50, 50, 50);
        rm = new ResourceManager();
    }

    @Test
    public void oreTest(){
        assertEquals(100, ore.getAmount());

        assertEquals(20, ore.mineAmount(20));

        assertEquals(80, ore.getAmount());

        assertEquals(10, ore.addAmount(10));

        assertEquals(90, ore.getAmount());

        assertEquals(90, ore.mineAmount(100));

        assertEquals(0, ore.getAmount());
    }

    @Test
    public void energyTest(){
        assertEquals(100, energy.getAmount());

        assertEquals(20, energy.mineAmount(20));

        assertEquals(80, energy.getAmount());

        assertEquals(10, energy.addAmount(10));

        assertEquals(90, energy.getAmount());

        assertEquals(90, energy.mineAmount(100));

        assertEquals(0, energy.getAmount());
    }

    @Test
    public void foodTest(){
        assertEquals(100, food.getAmount());

        assertEquals(20, food.mineAmount(20));

        assertEquals(80, food.getAmount());

        assertEquals(10, food.addAmount(10));

        assertEquals(90, food.getAmount());

        assertEquals(90, food.mineAmount(100));

        assertEquals(0, food.getAmount());

        food.renew();

        assertEquals(10, food.getAmount());
    }

    @Test
    public void resourceLevelTest(){
        assertEquals(100, rl1.getOreLevel());

        assertEquals(100, rl1.getEnergyLevel());

        assertEquals(100, rl1.getFoodLevel());


        assertEquals(20, rl1.mineFoodLevel(20));

        assertEquals(100, rl1.getOreLevel());

        assertEquals(100, rl1.getEnergyLevel());

        assertEquals(80, rl1.getFoodLevel());


        assertEquals(20, rl1.mineEnergyLevel(20));

        assertEquals(100, rl1.getOreLevel());

        assertEquals(80, rl1.getEnergyLevel());

        assertEquals(80, rl1.getFoodLevel());


        assertEquals(20, rl1.mineOreLevel(20));

        assertEquals(80, rl1.getOreLevel());

        assertEquals(80, rl1.getEnergyLevel());

        assertEquals(80, rl1.getFoodLevel());



        assertEquals(80, rl1.mineOreLevel(100));

        assertEquals(0, rl1.getOreLevel());

        assertEquals(80, rl1.getEnergyLevel());

        assertEquals(80, rl1.getFoodLevel());


        assertEquals(80, rl1.mineEnergyLevel(100));

        assertEquals(0, rl1.getOreLevel());

        assertEquals(0, rl1.getEnergyLevel());

        assertEquals(80, rl1.getFoodLevel());


        assertEquals(80, rl1.mineFoodLevel(100));

        assertEquals(0, rl1.getOreLevel());

        assertEquals(0, rl1.getEnergyLevel());

        assertEquals(0, rl1.getFoodLevel());


        rl1.renew();

        assertEquals(0, rl1.getOreLevel());

        assertEquals(0, rl1.getEnergyLevel());

        assertEquals(10, rl1.getFoodLevel());
    }

    @Test
    public void managerTest(){
        rl1 = new ResourceLevel(100, 100, 100);
        rm.add(new Location(0, 0), rl1);

        rm.add(new Location(0, 1), rl2);

        assertNull(rm.get(new Location(5, 1)));

        assertEquals(90, rm.mineEnergy(new Location(0, 0), 90));

        assertEquals(50, rm.mineEnergy(new Location(0, 1), 90));

        assertEquals(90, rm.mineFood(new Location(0, 0), 90));

        assertEquals(50, rm.mineFood(new Location(0, 1), 90));

        assertEquals(90, rm.mineOre(new Location(0, 0), 90));

        assertEquals(50, rm.mineOre(new Location(0, 1), 90));
    }
}
