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

        PlayerManager.getInstance().addUnit(p1.getId(), colonist);
        PlayerManager.getInstance().addUnit(p1.getId(), explorer);
        PlayerManager.getInstance().addUnit(p1.getId(), melee);
        PlayerManager.getInstance().addUnit(p1.getId(), ranged);

    }

    @Test
    public void JoinArmyTest(){
        army = new Army(explorer);

        assertEquals(explorer.getLocation(), army.getLocation());
        assertEquals(1, army.getBattleGroup().size());

        army.addUnitToBattleGroup(melee);
        assertEquals(2, army.getBattleGroup().size());

        army.removeUnitFromBattleGroup(explorer);
        assertEquals(1, army.getBattleGroup().size());

    }

    @Test
    public void DeadUnitTest(){
        army = new Army(explorer);

        assertEquals(explorer.getLocation(), army.getLocation());
        assertEquals(1, army.getBattleGroup().size());
        assertEquals(1, army.getBattleGroup().size());

        army.addUnitToBattleGroup(melee);
        assertEquals(2, army.getBattleGroup().size());

        army.addUnitToBattleGroup(colonist);
        assertEquals(3, army.getBattleGroup().size());

        army.addUnitToBattleGroup(ranged);
        assertEquals(4, army.getBattleGroup().size());

        explorer.killMe();
        colonist.killMe();
        army.startTurn();
        assertEquals(2, army.getBattleGroup().size());

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

        Map.reset();
        Map.setMoveDebug();

        Map.getInstance().addUnit(new Location(0,0), melee);
        Map.getInstance().addUnit(new Location(0,0), ranged);
        Map.getInstance().addUnit(new Location(0,0), colonist);
        Map.getInstance().addUnit(new Location(0,0), explorer);

        army = new Army(explorer);
        army.addUnitToBattleGroup(melee);

        assertEquals(new Location(0, 0), explorer.getLocation());
        assertEquals(new Location(0, 0), melee.getLocation());
        assertEquals(new Location(0, 0), army.getLocation());
        assertEquals(new Location(0, 0), colonist.getLocation());
        assertEquals(new Location(0, 0), ranged.getLocation());

        army.moveBattleGroup(MapDirection.getSouth());

        assertEquals(new Location(0, 1), explorer.getLocation());
        assertEquals(new Location(0, 1), melee.getLocation());
        assertEquals(new Location(0, 1), army.getLocation());
        assertEquals(new Location(0, 0), colonist.getLocation());
        assertEquals(new Location(0, 0), ranged.getLocation());

        army.removeUnitFromBattleGroup(melee);
        army.moveBattleGroup(MapDirection.getNorthEast());

        assertEquals(new Location(1, 0), explorer.getLocation());
        assertEquals(new Location(0, 1), melee.getLocation());
        assertEquals(new Location(1, 0), army.getLocation());
        assertEquals(new Location(0, 0), colonist.getLocation());
        assertEquals(new Location(0, 0), ranged.getLocation());
    }


    @Test
    public void QueuedMoveTest(){
        Map.reset();
        Map.setMoveDebug();

        Map.getInstance().addUnit(new Location(0,0), melee);
        Map.getInstance().addUnit(new Location(0,0), explorer);

        army = new Army(melee);
        army.addUnitToBattleGroup(explorer);

        //Move to Water
        army.giveOrder(new MoveCommand(army, MapDirection.getSouthEast()));
        //Move to Ground
        army.giveOrder(new MoveCommand(army, MapDirection.getNorthWest()));

        army.giveOrder(new MoveCommand(army, MapDirection.getSouthEast()));
        army.giveOrder(new MoveCommand(army, MapDirection.getNorthWest()));

        assertTrue(army.canMove());
        assertEquals(1, melee.getActionPoints());
        assertEquals(1, explorer.getActionPoints());


        army.doTurn();
        
        assertEquals(new Location(1, 0), melee.getLocation());
        assertEquals(new Location(1, 0), explorer.getLocation());
        assertEquals(new Location(1, 0), army.getLocation());

        assertEquals(3, army.getCommandQueue().size());

        assertFalse(army.canMove());
        assertEquals(-1, melee.getActionPoints());
        assertEquals(-1, explorer.getActionPoints());

        melee.refreshAP();
        explorer.refreshAP();
        army.startTurn();

        assertTrue(army.canMove());
        assertEquals(0, melee.getActionPoints());
        assertEquals(0, explorer.getActionPoints());

        army.doTurn();

        assertEquals(3, army.getCommandQueue().size());

        assertFalse(army.canMove());
        assertEquals(0, melee.getActionPoints());
        assertEquals(0, explorer.getActionPoints());

        melee.refreshAP();
        explorer.refreshAP();
        army.startTurn();

        assertTrue(army.canMove());
        assertEquals(1, melee.getActionPoints());
        assertEquals(1, explorer.getActionPoints());

        army.doTurn();

        assertEquals(2, army.getCommandQueue().size());

        assertFalse(army.canMove());
        assertEquals(0, melee.getActionPoints());
        assertEquals(0, explorer.getActionPoints());
    }

}
