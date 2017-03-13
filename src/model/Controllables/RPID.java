package model.Controllables;

import model.player.PlayerID;

public class RPID extends ControllableID{
	public static final int RALLY_POINT=0;
	
	
	public RPID(PlayerID playerID, int instanceNumber) {
		super(playerID, RALLY_POINT, instanceNumber);
	}

}
