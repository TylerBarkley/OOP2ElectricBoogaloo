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

        unitStats1 = new UnitStats();
        unitStats2 = new UnitStats();
        unitStats3 = new UnitStats();
        unitStats4 = new UnitStats();

        unitStats1.setArmor(5);
        unitStats1.setDefensiveDamage(2);
        unitStats1.setHealth(100);
        unitStats1.setOffensiveDamage(10);
        unitStats1.setInfluenceRadius(2);
        unitStats1.setUpkeep(2);
        unitStats1.setMovement(1);

        unitStats2.setArmor(5);
        unitStats2.setDefensiveDamage(4);
        unitStats2.setHealth(100);
        unitStats2.setOffensiveDamage(10);
        unitStats2.setInfluenceRadius(4);
        unitStats2.setUpkeep(4);
        unitStats2.setMovement(2);

        unitStats3.setArmor(5);
        unitStats3.setDefensiveDamage(8);
        unitStats3.setHealth(100);
        unitStats3.setOffensiveDamage(10);
        unitStats3.setInfluenceRadius(8);
        unitStats3.setUpkeep(8);
        unitStats3.setMovement(1);

        unitStats4.setArmor(5);
        unitStats4.setDefensiveDamage(16);
        unitStats4.setHealth(100);
        unitStats4.setOffensiveDamage(10);
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

        StructureStats structureStats1 = new StructureStats();
        StructureStats structureStats2 = new StructureStats();

        c1 = new Capital();
        f2 = new Fort();

        structureStats1.setArmor(5);
        structureStats1.setDefensiveDamage(16);
        structureStats1.setHealth(100);
        structureStats1.setOffensiveDamage(10);
        structureStats1.setInfluenceRadius(16);
        structureStats1.setUpkeep(16);

        structureStats2.setArmor(5);
        structureStats2.setDefensiveDamage(16);
        structureStats2.setHealth(100);
        structureStats2.setOffensiveDamage(10);
        structureStats2.setInfluenceRadius(16);
        structureStats2.setUpkeep(16);

        c1.setMyStats(structureStats1);
        f2.setMyStats(structureStats2);

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

        assertEquals(10, army.getAttackDamage());

        army.addUnitToBattleGroup(ranged2);

        assertEquals(15, army.getAttackDamage());

        AttackManager.getInstance().attack(army, new Location(1, 0));

        assertEquals(90, enemyRanged1.getCurrentHealth());
        assertEquals(100, enemyRanged2.getCurrentHealth());

        army.attack(new Location(1, 0));

        assertEquals(80, enemyRanged1.getCurrentHealth());
        assertEquals(100, enemyRanged2.getCurrentHealth());

        MovementManager.getInstance().makeMove(enemyRanged2, new Location(1, 0));

        AttackManager.getInstance().attack(army, new Location(1, 0));

        assertEquals(70, enemyRanged1.getCurrentHealth());
        assertEquals(90, enemyRanged2.getCurrentHealth());

        AttackManager.getInstance().attack(f2, new Location(1, 0));

        assertEquals(65, enemyRanged1.getCurrentHealth());
        assertEquals(85, enemyRanged2.getCurrentHealth());
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

        assertEquals(10, army2.getAttackDamage());

        army = new Army(ranged1);

        assertEquals(10, army.getAttackDamage());

        army.addUnitToBattleGroup(ranged2);

        assertEquals(15, army.getAttackDamage());

        AttackManager.getInstance().attack(army, new Location(5, 5));

        assertEquals(90, f2.getCurrentHealth());

        army2.attack(new Location(5,5));

        assertEquals(85, f2.getCurrentHealth());

    }
}
