import model.Controllables.Units.Colonist;
import model.Map.Map;
import model.TurnManager;
import model.player.Player;
import model.player.PlayerManager;
import org.junit.Before;
import org.junit.Test;

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

    }
}
