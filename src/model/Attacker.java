package model;

import model.player.PlayerID;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public interface Attacker {
    public int getAttackDamage();
    public PlayerID getPlayerID();
}
