package model;

import model.Controllables.RallyPoint;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Structures.Capital;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Location;
import model.Map.AOE.AOEDamage;
import model.Map.AOE.AOEKill;
import model.Map.Items.HealOneShot;
import model.Map.Items.RealObstacle;
import model.Map.Map;
import model.Map.Tile;
import model.MovementManager;

import model.player.Player;
import model.player.PlayerManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Created by hankerins on 3/10/17.
 */
public class RallyPointTest {
    Player p1;
    Player p2;

    Melee ranged1;
    Ranged ranged2;
    Ranged enemyRanged1;
    Ranged enemyRanged2;

    UnitStats unitStats1;
    UnitStats unitStats2;
    UnitStats unitStats3;
    UnitStats unitStats4;

    Capital c1;
    Capital c2;

    RallyPoint rp;

    @Before
    public void TestSetup(){
        Map.reset();
        Map.setBFSDebug();

        p1 = new Player();
        p2 = new Player();

        PlayerManager.getInstance().addPlayer(p1);
        PlayerManager.getInstance().addPlayer(p2);

        ranged1 = new Melee();
        ranged2 = new Ranged();

        enemyRanged1 = new Ranged();
        enemyRanged2 = new Ranged();

        PlayerManager.getInstance().addUnit(p1.getId(), ranged1);
        PlayerManager.getInstance().addUnit(p1.getId(), ranged2);
        PlayerManager.getInstance().addUnit(p2.getId(), enemyRanged1);
        PlayerManager.getInstance().addUnit(p2.getId(), enemyRanged2);

        c1 = new Capital();
        c2 = new Capital();

        PlayerManager.getInstance().addStructure(p1.getId(), c1);
        PlayerManager.getInstance().addStructure(p1.getId(), c2);
    }


    @After
    public void tearDown(){
        Map.reset();
    }


    @Test
    public void InitRallyPointTest(){
        Map.reset();
        Map.setMoveDebug();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);

        rp = new RallyPoint(ranged1);

        assertEquals(rp.getLocation(), new Location(0,0));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);

        rp.doTurn();

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(0,1));
        assertEquals(rp.getLocation(), new Location(0,0));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);

        rp.orderArmyMove();

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(0,1));
        assertEquals(rp.getLocation(), new Location(0,0));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);

        rp = new RallyPoint(ranged1, ranged1.getLocation());

        assertEquals(rp.getLocation(), new Location(0,0));
        assertNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 1);
        assertEquals(rp.getWaitingSize(), 0);

        rp.doTurn();

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(0,1));
        assertEquals(rp.getLocation(), new Location(0,0));
        assertNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 1);

        rp.startTurn();

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(0,1));
        assertEquals(rp.getLocation(), new Location(0,0));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);
    }

    @Test
    public void RPMoveTest(){
        Map.reset();
        Map.setMoveDebug();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);

        rp = new RallyPoint(ranged1);

        //rp.moveRallyPoint(new Location(0,1));

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(0,1));
        assertEquals(rp.getLocation(), new Location(0,0));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);
        assertEquals(rp.getArmy().getCommandQueue().size(), 0);

        rp.moveRallyPoint(new Location(-1, 1));

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(0,1));
        assertEquals(rp.getLocation(), new Location(-1,1));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);
        assertEquals(rp.getArmy().getCommandQueue().size(), 1);

        rp.reinforce(ranged2);

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(0,1));
        assertEquals(rp.getLocation(), new Location(-1,1));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 1);
        assertEquals(rp.getWaitingSize(), 0);
        assertEquals(rp.getArmy().getCommandQueue().size(), 1);

        rp.doTurn();

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(-1,1));
        assertEquals(rp.getLocation(), new Location(-1,1));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 1);
        assertEquals(rp.getWaitingSize(), 0);
        assertEquals(rp.getArmy().getCommandQueue().size(), 1);

        ranged1.refreshAP();
        ranged2.refreshAP();

        rp.doTurn();
        rp.startTurn();

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(-1,1));
        assertEquals(rp.getLocation(), new Location(-1,1));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 1);
        assertEquals(rp.getArmy().getCommandQueue().size(), 1);

        rp.getArmy().doTurn();

        assertEquals(ranged1.getLocation(), new Location(-1,1));
        assertEquals(ranged2.getLocation(), new Location(-1,1));
        assertEquals(rp.getLocation(), new Location(-1,1));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 1);
        assertEquals(rp.getArmy().getCommandQueue().size(), 0);

        rp.startTurn();

        assertEquals(ranged1.getLocation(), new Location(-1,1));
        assertEquals(ranged2.getLocation(), new Location(-1,1));
        assertEquals(rp.getLocation(), new Location(-1,1));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);
        assertEquals(rp.getArmy().getCommandQueue().size(), 0);
        assertEquals(rp.getArmy().getBattleGroup().size(), 2);

        ranged1.refreshAP();
        ranged2.refreshAP();
        rp.getArmy().startTurn();

        rp.moveRallyPoint(new Location(1, -1));

        assertEquals(ranged1.getLocation(), new Location(-1,1));
        assertEquals(ranged2.getLocation(), new Location(-1,1));
        assertEquals(rp.getLocation(), new Location(1,-1));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);
        assertEquals(rp.getArmy().getCommandQueue().size(), 2);
        assertEquals(rp.getArmy().getBattleGroup().size(), 2);

        assertEquals(ranged1.getActionPoints(), 0);
        assertEquals(ranged2.getActionPoints(), 1);

        rp.getArmy().doTurn();

        rp.getArmy().doTurn();
        ranged1.refreshAP();
        ranged2.refreshAP();
        rp.getArmy().startTurn();

        assertEquals(ranged1.getActionPoints(), 1);
        assertEquals(ranged2.getActionPoints(), 1);

        assertEquals(ranged1.getLocation(), new Location(-1,1));
        assertEquals(ranged2.getLocation(), new Location(-1,1));
        assertEquals(rp.getLocation(), new Location(1,-1));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);
        assertEquals(rp.getArmy().getCommandQueue().size(), 2);
        assertEquals(rp.getArmy().getBattleGroup().size(), 2);

        rp.getArmy().doTurn();

        assertEquals(ranged1.getActionPoints(), 0);
        assertEquals(ranged2.getActionPoints(), 0);

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(0,0));
        assertEquals(rp.getLocation(), new Location(1,-1));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);
        assertEquals(rp.getArmy().getCommandQueue().size(), 1);
        assertEquals(rp.getArmy().getBattleGroup().size(), 2);

    }

    @Test
    public void appropriateLocTest(){
        Map.reset();
        Map.setMoveDebug();
        Map.getInstance().addUnit(new Location(0,0), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);

        rp = new RallyPoint(ranged1);

        rp.moveRallyPoint(new Location(3,-1));

        assertEquals(ranged1.getLocation(), new Location(0,0));
        assertEquals(ranged2.getLocation(), new Location(0,1));
        assertEquals(rp.getLocation(), new Location(2,0));
        assertNotNull(rp.getArmy());
        assertEquals(rp.getReinforcementSize(), 0);
        assertEquals(rp.getWaitingSize(), 0);
        assertEquals(rp.getArmy().getCommandQueue().size(), 2);
    }

    @Test
    public void BFSTest(){
        System.out.println("BFS TEST\n");

        Map.reset();
        Map.setBFSDebug();
        Map.getInstance().addUnit(new Location(8,2), ranged1);
        Map.getInstance().addUnit(new Location(8,3), ranged2);
        Map.getInstance().addUnit(new Location(1,5), enemyRanged1); //in the way
        Map.getInstance().addUnit(new Location(8,8), enemyRanged2);



        Location RP = new Location(7, 8);

        RallyPoint myRP = new RallyPoint(ranged1, RP);
        MovementManager mm = MovementManager.getInstance();

        HashMap<Location, Location> bfs = Map.getInstance().BFS(ranged1.getPid(), RP);

        boolean neverCrossesWall = true;

        while(ranged1.getLocation() != RP){
            System.out.println(ranged1.getLocation().toString());
            Location nextLocation = bfs.get(ranged1.getLocation());
            mm.makeMove(ranged1, nextLocation);
            if(nextLocation.getY() == 5 && nextLocation.getX() > 0){
                neverCrossesWall = false;
            }
        }
        assertTrue(neverCrossesWall);
    }

    @Test
    public void DeadOnPathTest(){
        Map.reset();
        Map.setMoveDebug();
        Map.getInstance().addUnit(new Location(0,-1), ranged1);
        Map.getInstance().addUnit(new Location(0,1), ranged2);
        Map.getInstance().addAOE(new Location(-1, 1), new AOEKill());

        RallyPoint rp = new RallyPoint(ranged1, new Location(-2, 1));
        rp.reinforce(ranged2);

        assertEquals(new Location(-2, 1), rp.getLocation());
        assertEquals(new Location(0, 1), ranged2.getLocation());
        assertEquals(new Location(0, -1), ranged1.getLocation());

        assertEquals(2, rp.getReinforcementSize());

        rp.doTurn();

        assertEquals(new Location(-2, 1), rp.getLocation());
        assertEquals(new Location(-1, 1), ranged2.getLocation());
        assertEquals(new Location(-1, 0), ranged1.getLocation());

        assertEquals(1, rp.getReinforcementSize());
    }
}
