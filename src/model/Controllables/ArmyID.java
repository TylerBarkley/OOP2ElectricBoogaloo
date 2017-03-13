package model.Controllables;

import model.player.PlayerID;

public class ArmyID extends ControllableID {
	public static final int ARMY_TYPE_ID=0;
	
	public ArmyID(PlayerID playerID, int instanceNumber) {
		super(playerID, ARMY_TYPE_ID, instanceNumber);
	}

}
