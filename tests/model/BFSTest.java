package model;

import model.Controllables.Stats.UnitStats;
import model.Controllables.Structures.Capital;
import model.Controllables.Units.Ranged;
import model.Location;
import model.Map.AOE.AOEDamage;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by hankerins on 3/10/17.
 */
public class BFSTest {
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


    @After
    public void tearDown(){
        Map.reset();
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
        MovementManager mm = MovementManager.getInstance();

        HashMap<Location, Location> bfs = Map.getInstance().BFS (p1.getId(), RP);

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
}
