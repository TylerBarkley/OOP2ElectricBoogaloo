package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapDirectionTest {

	@Test
	public void getNorth_properValues_true() 
	{
		MapDirection md=MapDirection.getNorth();
		assertEquals(0, md.getDx());
		assertEquals(-1, md.getDy());
	}

	@Test
	public void getNorth_singleInstance_true() 
	{
		MapDirection md=MapDirection.getNorth();
		MapDirection md2=MapDirection.getNorth();
		
		assertTrue(md.equals(md2));
	}
	
	@Test
	public void getNorthEast_properValues_true() 
	{
		MapDirection md=MapDirection.getNorthEast();
		
		assertEquals(1, md.getDx());
		assertEquals(-1, md.getDy());
	}

	@Test
	public void getNorthEast_singleInstance_true() 
	{
		MapDirection md=MapDirection.getNorthEast();
		MapDirection md2=MapDirection.getNorthEast();
		
		assertTrue(md.equals(md2));
	}
	
	@Test
	public void getNorthWest_properValues_true() 
	{
		MapDirection md=MapDirection.getNorthWest();
		
		assertEquals(-1, md.getDx());
		assertEquals(0, md.getDy());
	}

	@Test
	public void getNorthWest_singleInstance_true() 
	{
		MapDirection md=MapDirection.getNorthWest();
		MapDirection md2=MapDirection.getNorthWest();
		
		assertTrue(md.equals(md2));
	}
	
	@Test
	public void getSouth_properValues_true() 
	{
		MapDirection md=MapDirection.getSouth();
		
		assertEquals(0, md.getDx());
		assertEquals(1, md.getDy());
	}

	@Test
	public void getSouth_singleInstance_true() 
	{
		MapDirection md=MapDirection.getSouth();
		MapDirection md2=MapDirection.getSouth();
		
		assertTrue(md.equals(md2));
	}
	
	@Test
	public void getSouthEast_properValues_true() 
	{
		MapDirection md=MapDirection.getSouthEast();
		
		assertEquals(1, md.getDx());
		assertEquals(0, md.getDy());
	}

	@Test
	public void getSouthEast_singleInstance_true() 
	{
		MapDirection md=MapDirection.getSouthEast();
		MapDirection md2=MapDirection.getSouthEast();
		
		assertTrue(md.equals(md2));
	}
	
	@Test
	public void getSouthWest_properValues_true() 
	{
		MapDirection md=MapDirection.getSouthWest();
		
		assertEquals(-1, md.getDx());
		assertEquals(1, md.getDy());
	}

	@Test
	public void getSouthWest_singleInstance_true() 
	{
		MapDirection md=MapDirection.getSouthWest();
		MapDirection md2=MapDirection.getSouthWest();
		
		assertTrue(md.equals(md2));
	}
	
	@Test
	public void getTheta_north_0()
	{
		MapDirection md=MapDirection.getNorth();
		assertEquals(0, md.getAngle());
	}
	
	@Test
	public void getTheta_northwest_60()
	{
		MapDirection md=MapDirection.getNorthWest();
		assertEquals(60, md.getAngle());
	}
	
	@Test
	public void getTheta_southwest_120()
	{
		MapDirection md=MapDirection.getSouthWest();
		assertEquals(120, md.getAngle());
	}
	
	@Test
	public void getTheta_south_180()
	{
		MapDirection md=MapDirection.getSouth();
		assertEquals(180, md.getAngle());
	}
	
	@Test
	public void getTheta_southeast_240()
	{
		MapDirection md=MapDirection.getSouthEast();
		assertEquals(240, md.getAngle());
	}
	
	@Test
	public void getTheta_northeast_300()
	{
		MapDirection md=MapDirection.getNorthEast();
		assertEquals(300, md.getAngle());
	}
}
