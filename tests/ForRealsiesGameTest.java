import model.Controllables.RallyPoint;
import model.Controllables.Units.Colonist;
import model.Location;
import model.Map.Map;
import model.TurnManager;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by zrgam_000 on 3/13/2017.
 */
public class ForRealsiesGameTest {
    Player p1;
    Player p2;

    Colonist colonist;
    Colonist enemyColonist;

    Map map;

    TurnManager turnManager;
    PlayerManager playerManager;

    @Before
    public void TestSetup(){
        p1 = new Player();
        p2 = new Player();

        colonist = new Colonist();
        enemyColonist = new Colonist();

        Map.reset();
        Map.setMoveDebug();

        map = Map.getInstance();

        playerManager = PlayerManager.getInstance();

        playerManager.addPlayer(p1);
        playerManager.addPlayer(p2);

        playerManager.addUnit(p1.getId(), colonist);
        playerManager.addUnit(p2.getId(), enemyColonist);
    }

    @Test
    public void GameTest(){
        Map.reset();
        Map.setMoveDebug();
        Map.getInstance().addUnit(new Location(-1, 1), colonist);

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(p1);
        players.add(p2);//not lonely anymore bois

        TurnManager.getInstance(players);

        TurnManager.getInstance().endTurn();

        assertEquals(new Location(-1, 1), colonist.getLocation());

        RallyPoint pr = new RallyPoint(colonist);

        assertEquals(1, colonist.getActionPoints());

        TurnManager.getInstance().endTurn();

        pr.moveRallyPoint(new Location(1, -1));

        assertEquals(1, colonist.getActionPoints());

        TurnManager.getInstance().endTurn();

        assertEquals(1, colonist.getActionPoints());

        assertEquals(new Location(0, 0), colonist.getLocation());

        assertEquals(1, colonist.getActionPoints());

        TurnManager.getInstance().endTurn();

        assertEquals(new Location(1, -1), colonist.getLocation());
        assertEquals(0, colonist.getActionPoints());

        pr.moveRallyPoint(new Location(2, -1));

        TurnManager.getInstance().endTurn();

        assertEquals(new Location(1, -1), colonist.getLocation());
        assertEquals(1, colonist.getActionPoints());
    }
}
