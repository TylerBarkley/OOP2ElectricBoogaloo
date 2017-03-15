package model;

import model.Controllables.RallyPoint;
import model.Controllables.Units.Explorer;
import model.Map.Map;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProspectingTest {

    Player p1;

    Explorer explorer;
    Explorer melee;

    @Before
    public void TestSetup(){
        p1 = new Player();

        explorer = new Explorer();
        melee = new Explorer();

        PlayerManager.getInstance().addPlayer(p1);
        PlayerManager.getInstance().addUnit(p1.getId(), explorer);
        PlayerManager.getInstance().addUnit(p1.getId(), melee);
    }

    @Test
    public void ProspectTest(){
        Map.reset();
        Map.setMoveDebug();
        Map.getInstance().addUnit(new Location(0, -1), explorer);
        Map.getInstance().addUnit(new Location(0, -1), melee);

        assertEquals(explorer.getMovement(), 2);
        explorer.setProspecting();
        assertEquals(explorer.getMovement(), 1);
        explorer.setProspecting();
        assertEquals(explorer.getMovement(), 2);
        explorer.setProspecting();
        RallyPoint rp = new RallyPoint(explorer);
        rp.reinforce(melee);

        rp.startTurn();
        rp.doTurn();

        rp.moveRallyPoint(new Location(0,1));

        assertFalse(Map.getInstance().getTileAt(new Location(0,0)).isProspected());

        rp.getArmy().doTurn();

        assertEquals(rp.getArmy().getLocation(), new Location(0,0));

        assertTrue(Map.getInstance().getTileAt(new Location(0,0)).isProspected());
        assertFalse(Map.getInstance().getTileAt(new Location(0,1)).isProspected());
    }
}
