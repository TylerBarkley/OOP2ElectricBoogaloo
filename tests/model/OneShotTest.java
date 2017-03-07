package model;

import static org.junit.Assert.*;

import model.Map.Items.HealOneShot;
import model.Map.Items.OneShotItem;
import model.Map.Items.OneShotManager;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zrgam_000 on 3/5/2017.
 */
public class OneShotTest {

    OneShotItem oneShot;
    OneShotManager osm;
    Unit target;

    @Before
    public void testSetup(){
        oneShot = new HealOneShot();
        osm = new OneShotManager();
    }

    @Test
    public void healTest(){
        target = new Ranged();
        target.damageMe(50);

        oneShot.applyItem(target);

        assertEquals(65, target.getCurrentHealth());
    }

    @Test
    public void osmTest(){
        target = new Ranged();

        target.damageMe(50);

        osm.add(new Location(0, 1), oneShot);
        osm.add(new Location(1, 1), oneShot);

        assertNull(osm.get(new Location(2, 1)));

        osm.useItem(new Location(0, 1), target);

        assertEquals(65, target.getCurrentHealth());

        assertNull(osm.get(new Location(0, 1)));

        osm.useItem(new Location(1, 1), target);

        assertEquals(75, target.getCurrentHealth());

        assertNull(osm.get(new Location(1, 1)));
    }

}
