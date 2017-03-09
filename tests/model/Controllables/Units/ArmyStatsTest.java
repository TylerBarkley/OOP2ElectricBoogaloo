package model.Controllables.Units;

import model.Controllables.Stats.ArmyStats;
import model.Controllables.Stats.UnitStats;

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
public class ArmyStatsTest {

    private ArmyStats armyStats;
    private UnitStats unitStats1;
    private UnitStats unitStats2;
    private UnitStats unitStats3;
    private UnitStats unitStats4;

    @Before
    public void setUp(){

        unitStats1 = new UnitStats();
        unitStats2 = new UnitStats();
        unitStats3 = new UnitStats();
        unitStats4 = new UnitStats();

        armyStats = new ArmyStats();

        unitStats1.setArmor(2);
        unitStats1.setDefensiveDamage(2);
        unitStats1.setHealth(2);
        unitStats1.setOffensiveDamage(2);
        unitStats1.setInfluenceRadius(2);
        unitStats1.setUpkeep(2);
        unitStats1.setMovement(10);

        unitStats2.setArmor(4);
        unitStats2.setDefensiveDamage(4);
        unitStats2.setHealth(4);
        unitStats2.setOffensiveDamage(4);
        unitStats2.setInfluenceRadius(4);
        unitStats2.setUpkeep(4);
        unitStats2.setMovement(5);

        unitStats3.setArmor(8);
        unitStats3.setDefensiveDamage(8);
        unitStats3.setHealth(8);
        unitStats3.setOffensiveDamage(8);
        unitStats3.setInfluenceRadius(8);
        unitStats3.setUpkeep(8);
        unitStats3.setMovement(5);

        unitStats4.setArmor(16);
        unitStats4.setDefensiveDamage(16);
        unitStats4.setHealth(16);
        unitStats4.setOffensiveDamage(16);
        unitStats4.setInfluenceRadius(16);
        unitStats4.setUpkeep(16);
        unitStats4.setMovement(10);
    }

    @Test
    public void add_UnitStats_Test(){
        armyStats.addStats(unitStats1);
        assertEquals(2, armyStats.getArmor());
        assertEquals(2, armyStats.getDefensiveDamage());
        assertEquals(2, armyStats.getOffensiveDamage());
        assertEquals(2, armyStats.getUpkeep());
        assertEquals(10, armyStats.getMovement());

        armyStats.addStats(unitStats2);
        assertEquals(4, armyStats.getArmor());
        assertEquals(4, armyStats.getDefensiveDamage());
        assertEquals(4, armyStats.getOffensiveDamage());
        assertEquals(6, armyStats.getUpkeep());
        assertEquals(5, armyStats.getMovement());

        armyStats.addStats(unitStats3);
        assertEquals(6, armyStats.getArmor());
        assertEquals(6, armyStats.getDefensiveDamage());
        assertEquals(6, armyStats.getOffensiveDamage());
        assertEquals(14, armyStats.getUpkeep());
        assertEquals(5, armyStats.getMovement());

        armyStats.addStats(unitStats4);
        assertEquals(10, armyStats.getArmor());
        assertEquals(10, armyStats.getDefensiveDamage());
        assertEquals(10, armyStats.getOffensiveDamage());
        assertEquals(30, armyStats.getUpkeep());
        assertEquals(5, armyStats.getMovement());
    }

    @Test
    public void remove_UnitStats_Test(){
        armyStats.addStats(unitStats1);
        armyStats.addStats(unitStats2);
        armyStats.addStats(unitStats3);
        armyStats.addStats(unitStats4);

        armyStats.removeStats(unitStats4);
        assertEquals(6, armyStats.getArmor());
        assertEquals(6, armyStats.getDefensiveDamage());
        assertEquals(6, armyStats.getOffensiveDamage());
        assertEquals(14, armyStats.getUpkeep());
        assertEquals(5, armyStats.getMovement());

        armyStats.removeStats(unitStats3);
        assertEquals(4, armyStats.getArmor());
        assertEquals(4, armyStats.getDefensiveDamage());
        assertEquals(4, armyStats.getOffensiveDamage());
        assertEquals(6, armyStats.getUpkeep());
        assertEquals(5, armyStats.getMovement());

        armyStats.removeStats(unitStats2);
        assertEquals(2, armyStats.getArmor());
        assertEquals(2, armyStats.getDefensiveDamage());
        assertEquals(2, armyStats.getOffensiveDamage());
        assertEquals(2, armyStats.getUpkeep());
        assertEquals(10, armyStats.getMovement());

        armyStats.removeStats(unitStats1);
        assertEquals(0, armyStats.getArmor());
        assertEquals(0, armyStats.getDefensiveDamage());
        assertEquals(0, armyStats.getOffensiveDamage());
        assertEquals(0, armyStats.getUpkeep());
        assertEquals(0, armyStats.getMovement());

        armyStats.addStats(unitStats1);
        armyStats.addStats(unitStats2);
        armyStats.addStats(unitStats3);
        armyStats.addStats(unitStats4);

        armyStats.removeStats(unitStats1);
        assertEquals(9, armyStats.getArmor());
        assertEquals(9, armyStats.getDefensiveDamage());
        assertEquals(9, armyStats.getOffensiveDamage());
        assertEquals(28, armyStats.getUpkeep());
        assertEquals(5, armyStats.getMovement());
    }

}
