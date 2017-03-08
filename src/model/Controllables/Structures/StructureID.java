package model.Controllables.Structures;

import model.Controllables.ControllableID;
import model.player.PlayerID;

public class StructureID extends ControllableID 
{
	public static final int CAPITAL_TYPE_ID=1;
	public static final int FARM_TYPE_ID=2;
	public static final int FORT_TYPE_ID=3;
	public static final int MINE_TYPE_ID=4;
	public static final int OBSERVATIONTOWER_TYPE_ID=5;
	public static final int POWERPLANT_TYPE_ID=6;
	public static final int UNIVERSITY_TYPE_ID=7;
	
	public StructureID(PlayerID playerID, int type, int instanceNumber) 
	{
		super(playerID, type, instanceNumber);
	}
}

