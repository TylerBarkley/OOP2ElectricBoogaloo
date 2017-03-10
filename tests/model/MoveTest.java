package model;

import static org.junit.Assert.*;

import org.junit.After;

import model.Controllables.Structures.Capital;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;
import model.Map.AOE.AOEDamage;
import model.Map.Items.HealOneShot;
import model.Map.Items.RealObstacle;
import model.Map.Map;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zrgam_000 on 3/8/2017.
 */
public class MoveTest {

    Player p1;
    Player p2;

    Ranged ranged1;
    Ranged ranged2;
    Ranged enemyRanged1;
    Ranged enemyRanged2;

    Capital c1;
    Capital c2;

    @Before
    public void TestSetup(){

        p1 = new Player();
        p2 = new Player();

        PlayerManager.getInstance().addPlayer(p1);
        PlayerManager.getInstance().addPlayer(p2);

        ranged1 = new Ranged();
        ranged2 = new Ranged();

        PlayerManager.getInstance().addUnit(p1.getId(), ranged1);
        PlayerManager.getInstance().addUnit(p1.getId(), ranged2);

        enemyRanged1 = new Ranged();
        enemyRanged2 = new Ranged();

        PlayerManager.getInstance().addUnit(p2.getId(), enemyRanged1);
        PlayerManager.getInstance().addUnit(p2.getId(), enemyRanged2);

        c1 = new Capital();
        c2 = new Capital();

        PlayerManager.getInstance().addStructure(p1.getId(), c1);
        PlayerManager.getInstance().addStructure(p1.getId(), c2);

        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);


    }

    @After
    public void tearDown(){
    	MovementManager.reset();
    	Map.reset();
    }
    
    @Test
    public void ValidOccupantTest(){
        assertFalse( MovementManager.getInstance().validateMove(ranged1, new Location(1, 0)));
        assertFalse( MovementManager.getInstance().validateMove(ranged1, new Location(1, 1)));

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(0, 1)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(0, 0)));
        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(0, 1)));

        assertTrue(MovementManager.getInstance().validateMove(enemyRanged1, new Location(1, 1)));
    }

    @Test
    public void ValidTerrainTest(){
        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(ranged1, new Location(6, 6)));

        assertTrue(MovementManager.getInstance().validateMove(enemyRanged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(6, 6)));
    }

    @Test
    public void ValidStructureTest(){

        Map.getInstance().addStructure(new Location(5,5), c1);

        Map.getInstance().addStructure(new Location(6, 6), c2);

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(ranged1, new Location(6, 6)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(6, 6)));
    }

    @Test
    public void ValidObstacleTest(){

        Map.getInstance().addObstacleItem(new Location(5, 5), new RealObstacle());

        assertFalse(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(ranged1, new Location(6, 6)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(6, 6)));
    }

    @Test
    public void ValidAfterMoveTest(){
        MovementManager.getInstance().makeMove(ranged1, new Location(5, 5));

        assertTrue(MovementManager.getInstance().getUnitOccupancyManager() == Map.getInstance().getUnitOccupancyManager());

        assertTrue(MovementManager.getInstance().validateMove(enemyRanged1, new Location(0, 0)));

        MovementManager.getInstance().makeMove(enemyRanged1, new Location(0, 0));

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(1, 0)));

        MovementManager.getInstance().makeMove(ranged1, new Location(1, 0));

        assertTrue(MovementManager.getInstance().validateMove(enemyRanged1, new Location(5, 5)));
    }

    @Test
    public void AOETest(){
        Map.getInstance().addAOE(new Location(5, 5), new AOEDamage(10));

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        MovementManager.getInstance().makeMove(ranged1, new Location(5, 5));

        assertEquals(95, ranged1.getCurrentHealth());

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(0, 0)));

        MovementManager.getInstance().makeMove(ranged1, new Location(0, 0));

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        MovementManager.getInstance().makeMove(ranged1, new Location(5, 5));

        assertEquals(90, ranged1.getCurrentHealth());
    }

    @Test
    public void ItemTest(){
        Map.getInstance().addOneShotItem(new Location(5, 5), new HealOneShot());

        ranged1.damageMe(20);

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        MovementManager.getInstance().makeMove(ranged1, new Location(5, 5));

        assertEquals(95, ranged1.getCurrentHealth());

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(0, 0)));

        MovementManager.getInstance().makeMove(ranged1, new Location(0, 0));

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        MovementManager.getInstance().makeMove(ranged1, new Location(5, 5));

        assertEquals(95, ranged1.getCurrentHealth());
    }
}
