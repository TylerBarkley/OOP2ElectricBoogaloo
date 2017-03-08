package model;

import java.util.Objects;

import model.player.PlayerID;

public abstract class ControllableID  extends ID{
	private PlayerID playerID;
	private int type;
	private int instanceNumber;
	
	public ControllableID( PlayerID playerID, int type, int instanceNumber) 
	{
		this.playerID = playerID;
		this.type = type;
		this.instanceNumber = instanceNumber;
	}

	public PlayerID getPlayerID() 
	{
		return playerID;
	}
	
	public int getInstanceNumber() 
	{
		return instanceNumber;
	}
	
	public void setInstanceNumber(int instanceNumber) 
	{
		this.instanceNumber = instanceNumber;
	}
	
	public void decrementInstanceNumber(){
		instanceNumber--;
	}
	
	public void incrementInstanceNumber(){
		instanceNumber++;
	}
	
	public int getType() 
	{
		return type;
	}
	
	public int hashCode()
	{
		return Objects.hash(playerID, type, instanceNumber);
	}
	
	public boolean equals(Object o)
	{
		if(!getClass().equals(o.getClass()))
		{
			return false;
		}
		
		ControllableID c=(ControllableID)o;
		
		return c.playerID.equals(playerID)
				&& c.type == type
				&& c.instanceNumber == instanceNumber;
	}
}
