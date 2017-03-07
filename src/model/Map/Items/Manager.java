package model.Map.Items;

import model.Location;

import java.util.HashMap;

public abstract class Manager<T> 
{
	private HashMap<Location, T> map;
	
	public Manager(){
		map = new HashMap<Location, T>();
	}

	
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
