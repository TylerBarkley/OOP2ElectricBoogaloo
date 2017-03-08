package model;

import static org.junit.Assert.*;

import model.Map.AOE.*;
import org.junit.Before;
import org.junit.Test;
import model.Controllables.Units.*;


/**
 * Created by zrgam_000 on 3/5/2017.
 */
public class AOETest {

    Ranged target;
    AOE heal;
    AOE healInt;
    AOE damage;
    AOE damageInt;
    AOE kill;
    AreaOfEffectManager aoeMan;

    @Before
    public void TestSetup()
    {

        heal = new AOEHeal();
        healInt = new AOEHeal(50);
        damage = new AOEDamage();
        damageInt = new AOEDamage(50);
        kill = new AOEKill();
        aoeMan = new AreaOfEffectManager();

    }

    @Test
    public void damageTest(){
        target = new Ranged();
        assertEquals(10, ((AOEDamage) damage).getIntensity());

        assertEquals(100, target.getCurrentHealth());

        damage.apply(target);

        assertEquals(95, target.getCurrentHealth());
    }

    @Test
    public void damageIntTest(){
        target = new Ranged();

        assertEquals(50, ((AOEDamage) damageInt).getIntensity());

        assertEquals(100, target.getCurrentHealth());

        damageInt.apply(target);

        assertEquals(55, target.getCurrentHealth());
    }

    @Test
    public void healTest(){
        target = new Ranged();

        assertEquals(10, ((AOEHeal) heal).getIntensity());

        assertEquals(100, target.getCurrentHealth());

        target.damageMe(95);

        assertEquals(10, target.getCurrentHealth());

        heal.apply(target);

        assertEquals(20, target.getCurrentHealth());

    }

    @Test
    public void healIntTest(){
        target = new Ranged();

        assertEquals(50, ((AOEHeal) healInt).getIntensity());

        assertEquals(100, target.getCurrentHealth());

        target.damageMe(95);

        assertEquals(10, target.getCurrentHealth());

        healInt.apply(target);

        assertEquals(60, target.getCurrentHealth());

        healInt.apply(target);

        assertEquals(100, target.getCurrentHealth());
    }

    @Test
    public void managerTest(){
        target = new Ranged();

        aoeMan.add(new Location(0, 0), heal);
        aoeMan.add(new Location(1, 0), healInt);
        aoeMan.add(new Location(0, 1), damage);
        aoeMan.add(new Location(1, 1), damageInt);
        aoeMan.add(new Location(2, 0), kill);

        assertNull(aoeMan.get(new Location(5, 1)));

        aoeMan.applyAOE(target, new Location(0, 1));
        assertEquals(95, target.getCurrentHealth());

        aoeMan.applyAOE(target, new Location(0, 1));
        assertEquals(90, target.getCurrentHealth());

        aoeMan.applyAOE(target, new Location(1, 1));
        assertEquals(45, target.getCurrentHealth());

        aoeMan.applyAOE(target, new Location(0, 0));
        assertEquals(55, target.getCurrentHealth());

        aoeMan.applyAOE(target, new Location(0, 1));
        assertEquals(50, target.getCurrentHealth());

        aoeMan.applyAOE(target, new Location(1, 0));
        assertEquals(100, target.getCurrentHealth());
    }
}
