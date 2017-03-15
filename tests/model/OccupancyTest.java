package model;

import static org.junit.Assert.*;

import model.Controllables.Structures.*;
import model.Map.Occupancy.*;
import model.player.Player;
import org.junit.Before;
import org.junit.Test;
import model.Controllables.Units.*;

public class OccupancyTest
{
    Player p1;

    StructureOccupancyManager som;

    StructureOccupancy capitalOcc;
    StructureOccupancy fortOcc;


    UnitOccupancyManager uom;

    UnitOccupancy occ1;
    UnitOccupancy occ2;

    Capital capital;
    Fort fort;


    Ranged ranged;
    Melee melee;
    Colonist colonist;
    Explorer explorer;

    @Before
    public void TestSetup()
    {
        p1 = new Player();

        capital = new Capital();
        fort = new Fort();

        p1.addStructure(capital);
        p1.addStructure(fort);

        occ1 = new UnitOccupancy();
        occ2 = new UnitOccupancy();

        ranged = new Ranged();
        melee = new Melee();
        colonist = new Colonist();
        explorer = new Explorer();

        p1.addUnit(ranged);
        p1.addUnit(melee);
        p1.addUnit(colonist);
        p1.addUnit(explorer);

        som = new StructureOccupancyManager();
        uom = new UnitOccupancyManager();

        capitalOcc = new StructureOccupancy();
        fortOcc = new StructureOccupancy();

    }

    @Test
    public void StructureOccupancyTest(){

        assertNull(capitalOcc.getOccupyingStructure());
        capitalOcc.setStructure(capital);
        assertEquals(capital, capitalOcc.getOccupyingStructure());

        assertNull(fortOcc.getOccupyingStructure());
        fortOcc.setStructure(fort);
        assertEquals(fort, fortOcc.getOccupyingStructure());

        //TODO Add PID stuff when that gets implemented
    }

    @Test
    public void UnitOccupancyTest(){
        assertEquals(0, occ1.getUnitCount());
        assertEquals(0, occ2.getUnitCount());

        occ1.addUnit(ranged);

        assertEquals(1, occ1.getUnitCount());
        assertEquals(0, occ2.getUnitCount());

        occ1.addUnit(melee);
        occ2.addUnit(melee);

        assertEquals(2, occ1.getUnitCount());
        assertEquals(1, occ2.getUnitCount());

        occ1.removeUnit(colonist);

        assertEquals(2, occ1.getUnitCount());
        assertEquals(1, occ2.getUnitCount());

        occ1.removeUnit(melee);

        assertEquals(1, occ1.getUnitCount());
        assertEquals(1, occ2.getUnitCount());
    }

    @Test
    public void UnitOccupancyManagerTest(){
        Location loc1 = new Location(0, 0);
        Location loc2 = new Location(0, 1);
        Location loc3 = new Location(0, 2);

        assertNull(uom.get(loc1));
        assertNull(uom.get(loc2));
        assertNull(uom.get(loc3));

        assertEquals(ranged, uom.addUnit(ranged, loc1));

        assertEquals(1, uom.get(loc1).getUnitCount());

        assertNotNull(uom.get(loc1));
        assertNull(uom.get(loc2));
        assertNull(uom.get(loc3));

        uom.removeUnit(ranged, loc1);

        assertNull(uom.get(loc1));

        assertEquals(ranged, uom.addUnit(ranged, loc1));
        assertEquals(melee, uom.addUnit(melee, loc1));
        assertEquals(melee, uom.addUnit(melee, loc2));
        assertEquals(colonist, uom.addUnit(colonist, loc3));
        assertEquals(explorer, uom.addUnit(explorer, loc3));
        assertEquals(ranged, uom.addUnit(ranged, loc3));

        assertEquals(2, uom.get(loc1).getUnitCount());
        assertEquals(1, uom.get(loc2).getUnitCount());
        assertEquals(3, uom.get(loc3).getUnitCount());
    }
}
