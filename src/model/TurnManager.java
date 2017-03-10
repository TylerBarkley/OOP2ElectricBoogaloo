package model;

import java.util.ArrayList;

import model.observers.EndTurnObserver;
import model.observers.StartTurnObserver;
import model.player.Player;

public class TurnManager {

	private ArrayList<EndTurnObserver> endObservers;
	private ArrayList<StartTurnObserver> startObservers;
	
	private ArrayList<Player> players;
	private int index;
	
	public TurnManager(ArrayList<Player> players){
		this.players=players;
		index=0;
	}
	
	public void endTurn() {
		notifyEndObservers();
		index=(index+1)%players.size();
		notifyStartObservers();
	}

	public void addObserver(EndTurnObserver observer)
	{
		endObservers.add(observer);
		notifyObserver(observer);
	}

	public void removeObserver(EndTurnObserver observer)
	{
		endObservers.remove(observer);
	}

	public void notifyEndObservers()
	{
		for(EndTurnObserver ob: endObservers)
		{
			ob.endUpdate(this);
		}
	}

	public void notifyObserver(EndTurnObserver observer)
	{
		observer.endUpdate(this);
	}
	
	public void addObserver(StartTurnObserver observer)
	{
		startObservers.add(observer);
		notifyObserver(observer);
	}

	public void removeObserver(StartTurnObserver observer)
	{
		startObservers.remove(observer);
	}

	public void notifyStartObservers()
	{
		for(StartTurnObserver ob: startObservers)
		{
			ob.startUpdate(this);
		}
	}

	public void notifyObserver(StartTurnObserver observer)
	{
		observer.startUpdate(this);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public Player getCurrentPlayer() {
		return players.get(index);
	}
}
