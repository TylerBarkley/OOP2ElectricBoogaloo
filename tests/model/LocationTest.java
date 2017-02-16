package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Location;
import model.MapDirection;

public class LocationTest
{
	private Location loc;
	
	@Before
	public void TestSetup()
	{
		loc=new Location(5, 5);
	}

	@Test
	public void getAdjacent_North_ValidNorth() 
	{
		MapDirection md=MapDirection.getNorth();
		Location newLoc = loc.getAdjacent(md);
		
		assertEquals(new Location(5, 4), newLoc);
	}
	
	@Test
	public void getAdjacent_NorthEast_ValidNorthEast() 
	{
		MapDirection md=MapDirection.getNorthEast();
		Location newLoc = loc.getAdjacent(md);
		
		assertEquals(new Location(6, 4), newLoc);
	}
	
	@Test
	public void getAdjacent_NorthWest_ValidNorthWest() 
	{
		MapDirection md=MapDirection.getNorthWest();
		Location newLoc = loc.getAdjacent(md);
		
		assertEquals(new Location(4, 5), newLoc);
	}
	
	@Test
	public void getAdjacent_South_ValidSouth() 
	{
		MapDirection md=MapDirection.getSouth();
		Location newLoc = loc.getAdjacent(md);
		
		assertEquals(new Location(5, 6), newLoc);
	}
	
	@Test
	public void getAdjacent_SouthEast_ValidSouthEast() 
	{
		MapDirection md=MapDirection.getSouthEast();
		Location newLoc = loc.getAdjacent(md);
		
		assertEquals(new Location(6, 5), newLoc);
	}
	
	@Test
	public void getAdjacent_South_ValidSouthWest() 
	{
		MapDirection md=MapDirection.getSouthWest();
		Location newLoc = loc.getAdjacent(md);
		
		assertEquals(new Location(4, 6), newLoc);
	}
	
	@Test
	public void equals_Object_False() 
	{
		assertFalse(loc.equals(new Object()));
	}
	
	@Test
	public void equals_DifferentLocation_False() 
	{
		assertFalse(loc.equals(new Location(7, 6)));
	}
	
	@Test
	public void equals_DifferentX_False() 
	{
		assertFalse(loc.equals(new Location(7, 5)));
	}
	
	@Test
	public void equals_DifferentY_False() 
	{
		assertFalse(loc.equals(new Location(5, 6)));
	}
	
	@Test
	public void equals_SameInstance_True() 
	{
		assertTrue(loc.equals(loc));
	}
	
	@Test
	public void equals_SameLocation_True() 
	{
		assertTrue(loc.equals(new Location(5, 5)));
	}
	
	@Test
	public void isValid_NegativeX_False() 
	{
		Location loc=new Location(-1, 3);
		assertFalse(loc.isValid(10, 10));
	}
	
	@Test
	public void isValid_NegativeY_False() 
	{
		Location loc=new Location(1, -3);
		assertFalse(loc.isValid(10, 10));
	}
	
	@Test
	public void isValid_HighX_False() 
	{
		Location loc=new Location(10, 3);
		assertFalse(loc.isValid(10, 10));
	}
	
	@Test
	public void isValid_HighY_False() 
	{
		Location loc=new Location(1, 10);
		assertFalse(loc.isValid(10, 10));
	}
	
	@Test
	public void isValid_Origin_True() 
	{
		Location loc=new Location(0, 0);
		assertTrue(loc.isValid(10, 10));
	}
	
	@Test
	public void isValid_HighBoundary_True() 
	{
		Location loc=new Location(9, 9);
		assertTrue(loc.isValid(10, 10));
	}
	
	@Test
	public void wrapAround_HighX_Xreduced() 
	{
		loc = loc.wrapAround(3, 6);
		assertEquals(new Location(2, 5), loc);
	}
	
	@Test
	public void wrapAround_HighY_Yreduced() 
	{
		loc = loc.wrapAround(6, 2);
		assertEquals(new Location(5, 1), loc);
	}
	
	@Test
	public void wrapAround_LowX_Xincreased() 
	{
		loc=new Location(-4,5);
		loc = loc.wrapAround(6, 6);
		assertEquals(new Location(2, 5), loc);
	}
	
	@Test
	public void wrapAround_LowY_Yincreased() 
	{
		loc=new Location(5,-3);
		loc = loc.wrapAround(6, 6);
		assertEquals(new Location(5, 3), loc);
	}
	
	@Test
	public void getLocationsAtRadius_radius1_6correctLocations(){
		ArrayList<Location> actual=loc.getLocationsAtRadius(1);
		ArrayList<Location> expected=new ArrayList<Location>();
		expected.add(new Location(5,4));
		expected.add(new Location(6,4));
		expected.add(new Location(6,5));
		expected.add(new Location(5,6));
		expected.add(new Location(4,6));
		expected.add(new Location(4,5));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getLocationsAtRadius_radius2_12correctLocations(){
		ArrayList<Location> actual=loc.getLocationsAtRadius(2);
		ArrayList<Location> expected=new ArrayList<Location>();
		expected.add(new Location(5,3));
		expected.add(new Location(6,3));
		expected.add(new Location(7,3));
		expected.add(new Location(7,4));
		expected.add(new Location(7,5));
		expected.add(new Location(6,6));
		expected.add(new Location(5,7));
		expected.add(new Location(4,7));
		expected.add(new Location(3,7));
		expected.add(new Location(3,6));
		expected.add(new Location(3,5));
		expected.add(new Location(4,4));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getAllLocationsWithinRadius_radius2_18correctLocations(){
		ArrayList<Location> actual=loc.getAllLocationsWithinRadius(2);
		ArrayList<Location> expected=new ArrayList<Location>();
		expected.add(new Location(5,3));
		expected.add(new Location(6,3));
		expected.add(new Location(7,3));
		expected.add(new Location(7,4));
		expected.add(new Location(7,5));
		expected.add(new Location(6,6));
		expected.add(new Location(5,7));
		expected.add(new Location(4,7));
		expected.add(new Location(3,7));
		expected.add(new Location(3,6));
		expected.add(new Location(3,5));
		expected.add(new Location(4,4));
		expected.add(new Location(5,3));
		expected.add(new Location(6,3));
		expected.add(new Location(7,3));
		expected.add(new Location(7,4));
		expected.add(new Location(7,5));
		expected.add(new Location(6,6));
		expected.add(new Location(5,7));
		expected.add(new Location(4,7));
		expected.add(new Location(3,7));
		expected.add(new Location(3,6));
		expected.add(new Location(3,5));
		expected.add(new Location(4,4));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getLocationsAtRadius_radius1WithWrapAround_6correctLocations(){
		ArrayList<Location> actual=loc.getLocationsAtRadius(1, 6, 7);
		ArrayList<Location> expected=new ArrayList<Location>();
		expected.add(new Location(5,4));
		expected.add(new Location(0,4));
		expected.add(new Location(0,5));
		expected.add(new Location(5,6));
		expected.add(new Location(4,6));
		expected.add(new Location(4,5));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getLocationsAtRadius_radius2WithWrapAround_12correctLocations(){
		ArrayList<Location> actual=loc.getLocationsAtRadius(2, 6, 7);
		ArrayList<Location> expected=new ArrayList<Location>();
		expected.add(new Location(5,3));
		expected.add(new Location(0,3));
		expected.add(new Location(1,3));
		expected.add(new Location(1,4));
		expected.add(new Location(1,5));
		expected.add(new Location(0,6));
		expected.add(new Location(5,0));
		expected.add(new Location(4,0));
		expected.add(new Location(3,0));
		expected.add(new Location(3,6));
		expected.add(new Location(3,5));
		expected.add(new Location(4,4));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getAllLocationsWithinRadius_radius2WithWrapAround_18correctLocations(){
		ArrayList<Location> actual=loc.getAllLocationsWithinRadius(2, 6, 7);
		ArrayList<Location> expected=new ArrayList<Location>();
		expected.add(new Location(5,3));
		expected.add(new Location(0,3));
		expected.add(new Location(1,3));
		expected.add(new Location(1,4));
		expected.add(new Location(1,5));
		expected.add(new Location(0,6));
		expected.add(new Location(5,0));
		expected.add(new Location(4,0));
		expected.add(new Location(3,0));
		expected.add(new Location(3,6));
		expected.add(new Location(3,5));
		expected.add(new Location(4,4));
		expected.add(new Location(5,3));
		expected.add(new Location(0,3));
		expected.add(new Location(1,3));
		expected.add(new Location(1,4));
		expected.add(new Location(1,5));
		expected.add(new Location(0,6));
		expected.add(new Location(5,0));
		expected.add(new Location(4,0));
		expected.add(new Location(3,0));
		expected.add(new Location(3,6));
		expected.add(new Location(3,5));
		expected.add(new Location(4,4));
		
		assertEquals(expected, actual);
	}
}
