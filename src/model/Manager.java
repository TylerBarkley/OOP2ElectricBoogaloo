package model;

import java.util.HashMap;

public abstract class Manager<T> 
{
	private HashMap<Location, T> map;
	
	//TODO Constructors
	
	public T remove(Location loc)
	{
		return map.remove(loc);
	}
	
	public void add(Location loc, T value)
	{
		map.put(loc, value);
	}
	
	public T get(Location loc)
	{
		return map.get(loc);
	}
}
