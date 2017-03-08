package model.Controllables.Units;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.player.PlayerID;

public class UnitManagerTest {
	private UnitManager unitManager;
	
	@Before
	public void setUp()
	{
		unitManager = new UnitManager(new PlayerID());
	}
	
	@Test
	public void add_OneOfEachType_FourTotal()
	{
		unitManager.add(new Colonist());
		assertEquals(1, unitManager.getUnits().size());
		
		unitManager.add(new Explorer());
		assertEquals(2, unitManager.getUnits().size());
		
		unitManager.add(new Melee());
		assertEquals(3, unitManager.getUnits().size());
		
		unitManager.add(new Ranged());
		assertEquals(4, unitManager.getUnits().size());
	}
	
	@Test
	public void add_MaxTenColonist_TenTotal()
	{
		for(int i=0; i<10; i++)
		{
			assertEquals(i, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Colonist()));
		}
		
		assertFalse(unitManager.add(new Colonist()));
		assertEquals(10, unitManager.getUnits().size());		
	}
	
	@Test
	public void add_MaxTenExplorer_TenTotal()
	{
		for(int i=0; i<10; i++)
		{
			assertEquals(i, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Explorer()));
		}
		
		assertFalse(unitManager.add(new Explorer()));
		assertEquals(10, unitManager.getUnits().size());		
	}
	
	@Test
	public void add_MaxTenMelee_TenTotal()
	{
		for(int i=0; i<10; i++)
		{
			assertEquals(i, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Melee()));
		}
		
		assertFalse(unitManager.add(new Melee()));
		assertEquals(10, unitManager.getUnits().size());		
	}
	
	@Test
	public void add_MaxTenRanged_TenTotal()
	{
		for(int i=0; i<10; i++)
		{
			assertEquals(i, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Ranged()));
		}
		
		assertFalse(unitManager.add(new Ranged()));
		assertEquals(10, unitManager.getUnits().size());		
	}
	
	@Test
	public void add_AddColonistAfter25_25Total()
	{
		for(int i=0; i<9; i++)
		{
			assertEquals(i, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Colonist()));
		}
		
		
		for(int i=0; i<8; i++)
		{
			assertEquals(i+9, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Explorer()));
		}
		
		for(int i=0; i<8; i++)
		{
			assertEquals(i+17, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Ranged()));
		}
		
		assertFalse(unitManager.add(new Colonist()));
		assertEquals(25, unitManager.getUnits().size());		
	}
	
	@Test
	public void add_AddExplorerAfter25_25Total()
	{
		for(int i=0; i<9; i++)
		{
			assertEquals(i, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Colonist()));
		}
		
		
		for(int i=0; i<8; i++)
		{
			assertEquals(i+9, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Explorer()));
		}
		
		for(int i=0; i<8; i++)
		{
			assertEquals(i+17, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Ranged()));
		}
		
		assertFalse(unitManager.add(new Explorer()));
		assertEquals(25, unitManager.getUnits().size());		
	}
	
	@Test
	public void add_AddMeleeAfter25_25Total()
	{
		for(int i=0; i<9; i++)
		{
			assertEquals(i, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Colonist()));
		}
		
		
		for(int i=0; i<8; i++)
		{
			assertEquals(i+9, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Explorer()));
		}
		
		for(int i=0; i<8; i++)
		{
			assertEquals(i+17, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Ranged()));
		}
		
		assertFalse(unitManager.add(new Melee()));
		assertEquals(25, unitManager.getUnits().size());		
	}
	
	@Test
	public void add_AddRangedAfter25_25Total()
	{
		for(int i=0; i<9; i++)
		{
			assertEquals(i, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Colonist()));
		}
		
		
		for(int i=0; i<8; i++)
		{
			assertEquals(i+9, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Explorer()));
		}
		
		for(int i=0; i<8; i++)
		{
			assertEquals(i+17, unitManager.getUnits().size());
			assertTrue(unitManager.add(new Ranged()));
		}
		
		assertFalse(unitManager.add(new Ranged()));
		assertEquals(25, unitManager.getUnits().size());		
	}
}
