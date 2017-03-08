package model;

import static org.junit.Assert.*;


import model.Controllables.Structures.Capital;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;
import model.Map.AOE.AOEDamage;
import model.Map.Items.HealOneShot;
import model.Map.Items.RealObstacle;
import model.Map.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zrgam_000 on 3/8/2017.
 */
public class MoveTest {
    Map map;
    MovementManager mm;
    Unit ranged1;
    Unit ranged2;
    Unit enemyRanged1;
    Unit enemyRanged2;

    @Before
    public void TestSetup(){
        map = new Map();
        mm = new MovementManager(map);
        ranged1 = new Ranged();
        ranged2 = new Ranged();


        enemyRanged1 = new Ranged();
        enemyRanged1.damageMe(15);
        enemyRanged2 = new Ranged();
        enemyRanged2.damageMe(15);

        map.addUnit(new Location(0,0), ranged1);
        map.addUnit(new Location(0,1), ranged2);
        map.addUnit(new Location(1,0), enemyRanged1);
        map.addUnit(new Location(1,1), enemyRanged2);


    }

    @Test
    public void ValidOccupantTest(){
        assertFalse(mm.validateMove(ranged1, new Location(1, 0)));
        assertFalse(mm.validateMove(ranged1, new Location(1, 1)));

        assertTrue(mm.validateMove(ranged1, new Location(0, 1)));

        assertFalse(mm.validateMove(enemyRanged1, new Location(0, 0)));
        assertFalse(mm.validateMove(enemyRanged1, new Location(0, 1)));

        assertTrue(mm.validateMove(enemyRanged1, new Location(1, 1)));
    }

    @Test
    public void ValidTerrainTest(){
        assertTrue(mm.validateMove(ranged1, new Location(5, 5)));

        assertFalse(mm.validateMove(ranged1, new Location(6, 6)));

        assertTrue(mm.validateMove(enemyRanged1, new Location(5, 5)));

        assertFalse(mm.validateMove(enemyRanged1, new Location(6, 6)));
    }

    @Test
    public void ValidStructureTest(){

        map.addStructure(new Location(5,5), new Capital());

        map.addStructure(new Location(6, 6), new Capital());

        assertTrue(mm.validateMove(ranged1, new Location(5, 5)));

        assertFalse(mm.validateMove(ranged1, new Location(6, 6)));

        assertFalse(mm.validateMove(enemyRanged1, new Location(5, 5)));

        assertFalse(mm.validateMove(enemyRanged1, new Location(6, 6)));
    }

    @Test
    public void ValidObstacleTest(){

        map.addObstacleItem(new Location(5, 5), new RealObstacle());

        assertFalse(mm.validateMove(ranged1, new Location(5, 5)));

        assertFalse(mm.validateMove(ranged1, new Location(6, 6)));

        assertFalse(mm.validateMove(enemyRanged1, new Location(5, 5)));

        assertFalse(mm.validateMove(enemyRanged1, new Location(6, 6)));
    }

    @Test
    public void ValidAfterMoveTest(){
        mm.makeMove(ranged1, new Location(5, 5));

        assertTrue(mm.validateMove(enemyRanged1, new Location(0, 0)));

        mm.makeMove(enemyRanged1, new Location(0, 0));

        assertTrue(mm.validateMove(ranged1, new Location(1, 0)));

        mm.makeMove(ranged1, new Location(1, 0));

        assertTrue(mm.validateMove(enemyRanged1, new Location(5, 5)));
    }

    @Test
    public void AOETest(){
        map.addAOE(new Location(5, 5), new AOEDamage(10));

        assertTrue(mm.validateMove(ranged1, new Location(5, 5)));

        mm.makeMove(ranged1, new Location(5, 5));

        assertEquals(95, ranged1.getCurrentHealth());

        assertTrue(mm.validateMove(ranged1, new Location(0, 0)));

        mm.makeMove(ranged1, new Location(0, 0));

        assertTrue(mm.validateMove(ranged1, new Location(5, 5)));

        mm.makeMove(ranged1, new Location(5, 5));

        assertEquals(90, ranged1.getCurrentHealth());
    }

    @Test
    public void ItemTest(){
        map.addOneShotItem(new Location(5, 5), new HealOneShot());

        ranged1.damageMe(20);

        assertTrue(mm.validateMove(ranged1, new Location(5, 5)));

        mm.makeMove(ranged1, new Location(5, 5));

        assertEquals(95, ranged1.getCurrentHealth());

        assertTrue(mm.validateMove(ranged1, new Location(0, 0)));

        mm.makeMove(ranged1, new Location(0, 0));

        assertTrue(mm.validateMove(ranged1, new Location(5, 5)));

        mm.makeMove(ranged1, new Location(5, 5));

        assertEquals(95, ranged1.getCurrentHealth());
    }
}
