package model;

import model.Controllables.Army;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Map.Map;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by zrgam_000 on 3/9/2017.
 */
public class ArmyTest {

    Player p1;

    Melee melee;
    Ranged ranged;
    Colonist colonist;
    Explorer explorer;

    UnitStats unitStats1;
    UnitStats unitStats2;
    UnitStats unitStats3;
    UnitStats unitStats4;

    Army army;

    @Before
    public void TestSetup(){

        p1 = new Player();

        PlayerManager.getInstance().addPlayer(p1);

        melee = new Melee();
        ranged = new Ranged();
        colonist = new Colonist();
        explorer = new Explorer();

        unitStats1 = new UnitStats();
        unitStats2 = new UnitStats();
        unitStats3 = new UnitStats();
        unitStats4 = new UnitStats();

        unitStats1.setArmor(2);
        unitStats1.setDefensiveDamage(2);
        unitStats1.setHealth(2);
        unitStats1.setOffensiveDamage(2);
        unitStats1.setInfluenceRadius(2);
        unitStats1.setUpkeep(2);
        unitStats1.setMovement(1);

        unitStats2.setArmor(4);
        unitStats2.setDefensiveDamage(4);
        unitStats2.setHealth(4);
        unitStats2.setOffensiveDamage(4);
        unitStats2.setInfluenceRadius(4);
        unitStats2.setUpkeep(4);
        unitStats2.setMovement(1);

        unitStats3.setArmor(8);
        unitStats3.setDefensiveDamage(8);
        unitStats3.setHealth(8);
        unitStats3.setOffensiveDamage(8);
        unitStats3.setInfluenceRadius(8);
        unitStats3.setUpkeep(8);
        unitStats3.setMovement(1);

        unitStats4.setArmor(16);
        unitStats4.setDefensiveDamage(16);
        unitStats4.setHealth(16);
        unitStats4.setOffensiveDamage(16);
        unitStats4.setInfluenceRadius(16);
        unitStats4.setUpkeep(16);
        unitStats4.setMovement(2);

        melee.setMyStats(unitStats1);
        ranged.setMyStats(unitStats2);
        colonist.setMyStats(unitStats3);
        explorer.setMyStats(unitStats4);

        PlayerManager.getInstance().addUnit(p1.getId(), colonist);
        PlayerManager.getInstance().addUnit(p1.getId(), explorer);
        PlayerManager.getInstance().addUnit(p1.getId(), melee);
        PlayerManager.getInstance().addUnit(p1.getId(), ranged);




    }

    @Test
    public void JoinArmyTest(){
        army = new Army(explorer);

        assertEquals(explorer.getLocation(), army.getMyLocation());
        assertEquals(1, army.getBattleGroup().size());

        army.addUnitToBattleGroup(melee);
        assertEquals(2, army.getBattleGroup().size());

        army.removeUnitFromBattleGroup(explorer);
        assertEquals(1, army.getBattleGroup().size());

    }

    @Test
    public void EscortTest(){
        army = new Army(explorer);

        assertFalse(army.canEscort());

        army.addUnitToBattleGroup(melee);

        assertTrue(army.canEscort());

        army.removeUnitFromBattleGroup(explorer);

        assertTrue(army.canEscort());

        army.addUnitToBattleGroup(colonist);

        assertTrue(army.canEscort());

        army.removeUnitFromBattleGroup(melee);

        assertFalse(army.canEscort());

        army.addUnitToBattleGroup(ranged);

        assertTrue(army.canEscort());
    }

    @Test
    public void MoveSpeedTest(){
        army = new Army(explorer);

        assertEquals(explorer.getMaxActionPoints(), 2);
        assertEquals(army.getArmyStats().getMovement(), 2);

        army.addUnitToBattleGroup(melee);

        assertEquals(explorer.getMaxActionPoints(), 1);
        assertEquals(melee.getMaxActionPoints(), 1);
        assertEquals(army.getArmyStats().getMovement(), 1);

        army.removeUnitFromBattleGroup(explorer);

        assertEquals(explorer.getMaxActionPoints(), 2);
        assertEquals(melee.getMaxActionPoints(), 1);
        assertEquals(army.getArmyStats().getMovement(), 1);

        army.addUnitToBattleGroup(explorer);
        army.removeUnitFromBattleGroup(melee);

        assertEquals(explorer.getMaxActionPoints(), 2);
        assertEquals(melee.getMaxActionPoints(), 1);
        assertEquals(army.getArmyStats().getMovement(), 2);
    }

    @Test
    public void MoveTest(){

        Map.getInstance().addUnit(new Location(0,0), melee);
        Map.getInstance().addUnit(new Location(0,0), ranged);
        Map.getInstance().addUnit(new Location(0,0), colonist);
        Map.getInstance().addUnit(new Location(0,0), explorer);

        army = new Army(explorer);
        army.addUnitToBattleGroup(melee);

        assertEquals(new Location(0, 0), explorer.getLocation());
        assertEquals(new Location(0, 0), melee.getLocation());
        assertEquals(new Location(0, 0), army.getMyLocation());
        assertEquals(new Location(0, 0), colonist.getLocation());
        assertEquals(new Location(0, 0), ranged.getLocation());

        army.moveBattleGroup(MapDirection.getSouth());

        assertEquals(new Location(0, 1), explorer.getLocation());
        assertEquals(new Location(0, 1), melee.getLocation());
        assertEquals(new Location(0, 1), army.getMyLocation());
        assertEquals(new Location(0, 0), colonist.getLocation());
        assertEquals(new Location(0, 0), ranged.getLocation());

        army.removeUnitFromBattleGroup(melee);
        army.moveBattleGroup(MapDirection.getNorthEast());

        assertEquals(new Location(1, 0), explorer.getLocation());
        assertEquals(new Location(0, 1), melee.getLocation());
        assertEquals(new Location(1, 0), army.getMyLocation());
        assertEquals(new Location(0, 0), colonist.getLocation());
        assertEquals(new Location(0, 0), ranged.getLocation());
    }

    /*
    @Test
    public void QueuedMoveTest(){

    }
    */
}
