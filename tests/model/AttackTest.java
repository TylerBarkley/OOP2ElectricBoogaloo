package model;

import model.Controllables.Army;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Fort;
import model.Controllables.Units.Ranged;
import model.Map.Map;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zrgam_000 on 3/10/2017.
 */
public class AttackTest {
    Player p1;
    Player p2;

    Army army;

    Ranged ranged1;
    Ranged ranged2;
    Ranged enemyRanged1;
    Ranged enemyRanged2;

    UnitStats unitStats1;
    UnitStats unitStats2;
    UnitStats unitStats3;
    UnitStats unitStats4;

    Capital c1;
    Fort f2;

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

        PlayerManager.getInstance().addUnit(p1.getId(), ranged1);
        PlayerManager.getInstance().addUnit(p1.getId(), ranged2);
        PlayerManager.getInstance().addUnit(p2.getId(), enemyRanged1);
        PlayerManager.getInstance().addUnit(p2.getId(), enemyRanged2);

        c1 = new Capital();
        f2 = new Fort();

        c1.setCurrentHealth(100);
        f2.setCurrentHealth(100);

        PlayerManager.getInstance().addStructure(p1.getId(), c1);
        PlayerManager.getInstance().addStructure(p1.getId(), f2);


    }


    @After
    public void tearDown(){
        Map.reset();
    }

    @Test
    public void AttackUnitTest(){
        Map.reset();
        Map.setMoveDebug();

        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,0), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);

        Map.getInstance().addStructure(new Location(5,5), f2);

        army = new Army(ranged1);

        assertEquals(15, army.getAttackDamage());

        army.addUnitToBattleGroup(ranged2);

        assertEquals(30, army.getAttackDamage());

        AttackManager.getInstance().attack(army, new Location(1, 0));

        assertEquals(75, enemyRanged1.getCurrentHealth());
        assertEquals(100, enemyRanged2.getCurrentHealth());

        army.attack(new Location(1, 0));

        assertEquals(50, enemyRanged1.getCurrentHealth());
        assertEquals(100, enemyRanged2.getCurrentHealth());

        MovementManager.getInstance().makeMove(enemyRanged2, new Location(1, 0));

        AttackManager.getInstance().attack(army, new Location(1, 0));

        assertEquals(25, enemyRanged1.getCurrentHealth());
        assertEquals(75, enemyRanged2.getCurrentHealth());

        AttackManager.getInstance().attack(f2, new Location(1, 0));

        assertEquals(20, enemyRanged1.getCurrentHealth());
        assertEquals(70, enemyRanged2.getCurrentHealth());
    }

    @Test
    public void AttackStructureTest(){
        Map.reset();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,0), ranged2);
        Map.getInstance().addUnit(new Location(1,0), enemyRanged1);
        Map.getInstance().addUnit(new Location(1,1), enemyRanged2);

        Map.getInstance().addStructure(new Location(5,5), f2);


        Army army2 = new Army(enemyRanged1);

        assertEquals(15, army2.getAttackDamage());

        army = new Army(ranged1);

        assertEquals(15, army.getAttackDamage());

        army.addUnitToBattleGroup(ranged2);

        assertEquals(30, army.getAttackDamage());

        AttackManager.getInstance().attack(army, new Location(5, 5));

        assertEquals(75, f2.getCurrentHealth());

        army2.attack(new Location(5,5));

        assertEquals(65, f2.getCurrentHealth());

    }
}
