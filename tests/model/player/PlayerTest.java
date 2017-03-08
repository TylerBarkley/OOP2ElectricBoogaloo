package model.player;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Controllables.Army;
import model.Controllables.Worker;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Farm;
import model.Controllables.Structures.Fort;
import model.Controllables.Structures.Mine;
import model.Controllables.Structures.ObservationTower;
import model.Controllables.Structures.PowerPlant;
import model.Controllables.Structures.University;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;

public class PlayerTest {
	private Player player;
	
	@Before
	public void setUp() throws Exception {
		player=new Player();
	}

	@Test
	public void addUnit_OneOfEachTypeAdded_FourUnitsPresent() {
		player.addUnit(new Colonist());
		assertEquals(1, player.getUnits().size());
		
		player.addUnit(new Explorer());
		assertEquals(2, player.getUnits().size());
		
		player.addUnit(new Melee());
		assertEquals(3, player.getUnits().size());
		
		player.addUnit(new Ranged());
		assertEquals(4, player.getUnits().size());
	}

	@Test
	public void addStructure_OneOfEachTypeAdded_SevenStructuresPresent() {
		player.addStructure(new Capital());
		assertEquals(1, player.getStructures().size());
		
		player.addStructure(new Farm());
		assertEquals(2, player.getStructures().size());
		
		player.addStructure(new Fort());
		assertEquals(3, player.getStructures().size());
		
		player.addStructure(new Mine());
		assertEquals(4, player.getStructures().size());
		
		player.addStructure(new ObservationTower());
		assertEquals(5, player.getStructures().size());
		
		player.addStructure(new PowerPlant());
		assertEquals(6, player.getStructures().size());
		
		player.addStructure(new University());
		assertEquals(7, player.getStructures().size());
	}
	
	@Test
	public void addArmy_OneAdded_ReturnsOne() {
		player.addArmy(new Army());
		assertEquals(1, player.getArmies().size());
	}
	
	@Test
	public void addWorker_OneAdded_ReturnsOne() {
		player.addWorker(new Worker());
		assertEquals(1, player.getWorkers().size());
	}
	
	@Test
	public void addWorker_101Added_100Added() {
		for(int i=0; i<100; i++)
		{
			assertEquals(i, player.getWorkers().size());
			assertTrue(player.addWorker(new Worker()));
		}
		
		assertFalse(player.addWorker(new Worker()));
		assertEquals(100, player.getWorkers().size());
	}
}

