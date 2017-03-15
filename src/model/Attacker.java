package model;

import model.player.PlayerID;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public interface Attacker {
    int getAttackDamage();
    PlayerID getPlayerID();
    Location getLocation();
    int getAttackRange();

}
