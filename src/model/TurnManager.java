package model;

import java.util.ArrayList;

import model.observers.EndTurnObserver;
import model.observers.StartTurnObserver;
import model.player.Player;
import model.player.PlayerID;

public class TurnManager {

	private static TurnManager instance;

	private ArrayList<EndTurnObserver> endObservers;
	private ArrayList<StartTurnObserver> startObservers;
	
	private ArrayList<Player> players;
	private int index;
	
	private TurnManager(ArrayList<Player> players){
		this.players=players;
		index=0;
		
		endObservers=new ArrayList<EndTurnObserver>();
		startObservers=new ArrayList<StartTurnObserver>();
	}
	
	public void endTurn() {
		notifyEndObservers();
		index=(index+1)%players.size();
		notifyStartObservers();
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public PlayerID getCurrentPlayerID() {
		return players.get(index).getId();
	}
	
	public void addEndObserver(EndTurnObserver observer)
	{
		endObservers.add(observer);
	}

	public void removeEndObserver(EndTurnObserver observer)
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

	public void notifyEndObserver(EndTurnObserver observer)
	{
		observer.endUpdate(this);
	}
	
	public void addStartObserver(StartTurnObserver observer)
	{
		startObservers.add(observer);
	}

	public void removeStartObserver(StartTurnObserver observer)
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

	public void notifyStartObserver(StartTurnObserver observer)
	{
		observer.startUpdate(this);
	}

	public static TurnManager getInstance(ArrayList<Player> players) {
		if(instance == null)
		{
			instance = new TurnManager(players);
		}
		else if(!instance.players.equals(players))
		{
			for(Player player:players)
			{
				if(!instance.players.contains(player))
				{
					instance.players.add(player);
				}
			}
		}
		
		return instance;
	}
	
	public static TurnManager getInstance() {
		return getInstance(new ArrayList<Player>());
	}

	public static void reset() {
		instance=null;
	}
}
