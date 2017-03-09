package model;

import static org.junit.Assert.*;

import model.Controllables.Structures.*;
import model.Controllables.Structures.Structure;
import model.Map.Occupancy.*;
import org.junit.Before;
import org.junit.Test;
import model.Controllables.Units.*;

/**
 * Created by zrgam_000 on 3/8/2017.
 */
public class OccupancyTest
{
    StructureOccupancyManager som;

    StructureOccupancy capitalOcc;
    StructureOccupancy fortOcc;
    StructureOccupancy farmOcc;
    StructureOccupancy powerplantOcc;
    StructureOccupancy mineOcc;
    StructureOccupancy observationOcc;
    StructureOccupancy universityOcc;

    UnitOccupancyManager uom;

    UnitOccupancy occ1;
    UnitOccupancy occ2;

    Structure capital;
    Structure fort;
    Structure farm;
    Structure powerplant;
    Structure mine;
    Structure observation;
    Structure university;

    Unit ranged;
    Unit melee;
    Unit colonist;
    Unit explorer;

    @Before
    public void TestSetup()
    {
        capital = new Capital();
        fort = new Fort();
        farm = new Farm();
        powerplant = new PowerPlant();
        mine = new Mine();
        observation = new ObservationTower();
        university = new University();

        occ1 = new UnitOccupancy();
        occ2 = new UnitOccupancy();

        ranged = new Ranged();
        melee = new Melee();
        colonist = new Colonist();
        explorer = new Explorer();

        som = new StructureOccupancyManager();
        uom = new UnitOccupancyManager();


        capitalOcc = new StructureOccupancy();
        fortOcc = new StructureOccupancy();
        farmOcc = new StructureOccupancy();
        powerplantOcc = new StructureOccupancy();
        mineOcc = new StructureOccupancy();
        observationOcc = new StructureOccupancy();
        universityOcc = new StructureOccupancy();
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
