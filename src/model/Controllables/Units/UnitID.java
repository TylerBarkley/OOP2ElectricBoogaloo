package model.Controllables.Units;

import model.Controllables.ControllableID;
import model.player.PlayerID;

public class UnitID extends ControllableID {
	public static final int COLONIST_TYPE_ID=1;
	public static final int EXPLORER_TYPE_ID=2;
	public static final int MELEE_TYPE_ID=3;
	public static final int RANGED_TYPE_ID=4;
	
	public UnitID(PlayerID playerID, int type, int instanceNumber) 
	{
		super(playerID, type, instanceNumber);
	}
}
