package model.observers;

import java.util.ArrayList;

import model.ControllableID;

public abstract class DeathObservable
{
	private ArrayList<DeathObserver> observers;
	
	public DeathObservable() 
	{
		observers=new ArrayList<DeathObserver>();
	}

	public void addObserver(DeathObserver observer)
	{
		observers.add(observer);
	}
	
	public void removeObserver(DeathObserver observer)
	{
		observers.remove(observer);
	}
	
	public void notifyObservers(ControllableID id)
	{
		for(DeathObserver ob: observers)
		{
			ob.update(id);
		}
	}
}
