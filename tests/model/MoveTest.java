package model;

import static org.junit.Assert.*;


import model.Controllables.Stats.UnitStats;
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

    UnitStats unitStats1;
    UnitStats unitStats2;
    UnitStats unitStats3;
    UnitStats unitStats4;

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

        enemyRanged1 = new Ranged();
        enemyRanged2 = new Ranged();

        unitStats1 = new UnitStats();
        unitStats2 = new UnitStats();
        unitStats3 = new UnitStats();
        unitStats4 = new UnitStats();

        unitStats1.setArmor(5);
        unitStats1.setDefensiveDamage(2);
        unitStats1.setHealth(100);
        unitStats1.setOffensiveDamage(2);
        unitStats1.setInfluenceRadius(2);
        unitStats1.setUpkeep(2);
        unitStats1.setMovement(1);

        unitStats2.setArmor(5);
        unitStats2.setDefensiveDamage(4);
        unitStats2.setHealth(100);
        unitStats2.setOffensiveDamage(4);
        unitStats2.setInfluenceRadius(4);
        unitStats2.setUpkeep(4);
        unitStats2.setMovement(2);

        unitStats3.setArmor(5);
        unitStats3.setDefensiveDamage(8);
        unitStats3.setHealth(100);
        unitStats3.setOffensiveDamage(8);
        unitStats3.setInfluenceRadius(8);
        unitStats3.setUpkeep(8);
        unitStats3.setMovement(1);

        unitStats4.setArmor(5);
        unitStats4.setDefensiveDamage(16);
        unitStats4.setHealth(100);
        unitStats4.setOffensiveDamage(16);
        unitStats4.setInfluenceRadius(16);
        unitStats4.setUpkeep(16);
        unitStats4.setMovement(2);

        ranged1.setMyStats(unitStats1);
        ranged2.setMyStats(unitStats2);
        enemyRanged1.setMyStats(unitStats3);
        enemyRanged2.setMyStats(unitStats4);

        PlayerManager.getInstance().addUnit(p1.getId(), ranged1);
        PlayerManager.getInstance().addUnit(p1.getId(), ranged2);
        PlayerManager.getInstance().addUnit(p2.getId(), enemyRanged1);
        PlayerManager.getInstance().addUnit(p2.getId(), enemyRanged2);

        c1 = new Capital();
        c2 = new Capital();

        PlayerManager.getInstance().addStructure(p1.getId(), c1);
        PlayerManager.getInstance().addStructure(p1.getId(), c2);


    }

    @Test
    public void ValidOccupantTest(){

        Map.getInstance().resetMap();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);

        assertFalse( MovementManager.getInstance().validateMove(ranged1, new Location(1, 0)));
        assertFalse( MovementManager.getInstance().validateMove(ranged1, new Location(1, 1)));

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(0, 1)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(0, 0)));
        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(0, 1)));

        assertTrue(MovementManager.getInstance().validateMove(enemyRanged1, new Location(1, 1)));
    }

    @Test
    public void ValidTerrainTest(){

        Map.getInstance().resetMap();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(ranged1, new Location(6, 6)));

        assertTrue(MovementManager.getInstance().validateMove(enemyRanged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(6, 6)));
    }

    @Test
    public void ValidStructureTest(){

        Map.getInstance().resetMap();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);

        Map.getInstance().addStructure(new Location(5,5), c1);

        Map.getInstance().addStructure(new Location(6, 6), c2);

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(ranged1, new Location(6, 6)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(6, 6)));
    }

    @Test
    public void ValidObstacleTest(){

        Map.getInstance().resetMap();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);

        Map.getInstance().addObstacleItem(new Location(5, 5), new RealObstacle());

        assertFalse(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(ranged1, new Location(6, 6)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(5, 5)));

        assertFalse(MovementManager.getInstance().validateMove(enemyRanged1, new Location(6, 6)));
    }

    @Test
    public void ValidAfterMoveTest(){

        Map.getInstance().resetMap();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);

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

        Map.getInstance().resetMap();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);

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

        Map.getInstance().resetMap();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);

        Map.getInstance().addOneShotItem(new Location(5, 5), new HealOneShot());

        assertEquals(100, ranged1.getCurrentHealth());

        ranged1.damageMe(20);

        assertEquals(85, ranged1.getCurrentHealth());

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        MovementManager.getInstance().makeMove(ranged1, new Location(5, 5));

        assertEquals(95, ranged1.getCurrentHealth());

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(0, 0)));

        MovementManager.getInstance().makeMove(ranged1, new Location(0, 0));

        assertTrue(MovementManager.getInstance().validateMove(ranged1, new Location(5, 5)));

        MovementManager.getInstance().makeMove(ranged1, new Location(5, 5));

        assertEquals(95, ranged1.getCurrentHealth());
    }

    @Test
    public void APMoveTest(){

        Map.getInstance().resetMap();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);

        assertEquals(ranged1.getActionPoints(), 1);
        assertEquals(ranged2.getActionPoints(), 2);

        //Moving On Water
        MovementManager.getInstance().makeMove(ranged1, new Location(1,0));

        assertEquals(ranged1.getActionPoints(), -1);
        assertEquals(ranged2.getActionPoints(), 2);

        ranged1.refreshAP();
        ranged2.refreshAP();

        assertEquals(ranged1.getActionPoints(), 0);
        assertEquals(ranged2.getActionPoints(), 2);


        //Movement on Ground
        MovementManager.getInstance().makeMove(ranged2, new Location(0,0));

        assertEquals(ranged1.getActionPoints(), 0);
        assertEquals(ranged2.getActionPoints(), 1);

        ranged1.refreshAP();
        ranged2.refreshAP();

        assertEquals(ranged1.getActionPoints(), 1);
        assertEquals(ranged2.getActionPoints(), 2);
    }
}
