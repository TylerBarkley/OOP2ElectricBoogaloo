package model;

import model.Controllables.Structures.Capital;
import model.Controllables.Units.Colonist;
import model.Map.Map;
import model.player.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zrgam_000 on 3/12/2017.
 */
public class ProductionManagerTest {
    ProductionManager productionManager;

    Player p1;

    Map map;

    Colonist colonist;

    @Before
    public void Setup(){
        p1 = new Player();
        map = Map.getInstance();
        productionManager = ProductionManager.getInstance();

        colonist  = new Colonist();

        p1.addUnit(colonist);
    }

    @Test
    public void MartyrTest(){
        Map.reset();
        Map.setMoveDebug();

        map.addUnit(new Location(0, 0), colonist);

        assertNotNull(map.getUnitOccupancyAt(new Location(0, 0)));
        assertNull(map.getStructureOccupancyAt(new Location(0, 0)));

        productionManager.martyrdom(colonist);

        assertNotNull(map.getStructureOccupancyAt(new Location(0, 0)));

        assertFalse(colonist.isAlive());
        Capital capital = (Capital) map.getStructureOccupancyAt(new Location(0, 0)).getOccupyingStructure();
        assertEquals(5, capital.getCapitalManager().getNumOfWorkers_Unassigned());
        assertEquals(2, map.getUnitOccupancyAt(new Location(0,0)).getUnitCount());
    }
}
