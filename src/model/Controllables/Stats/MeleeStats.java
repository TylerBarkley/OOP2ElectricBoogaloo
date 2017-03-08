package model.Controllables.Stats;

import model.Controllables.BasicStats;

/**
 * Created by Tyler Barkley on 3/7/2017.
 */
public class MeleeStats implements BasicStats{
    private int health;
    private int armor;
    private int upkeep;
    private int defensiveDamage;
    private int influenceRadius;
    private int offensiveDamage;
    private int movement;

    public MeleeStats(){
        health = 100;
        armor = 9;
        upkeep = 10;
        defensiveDamage = 5;
        influenceRadius = 1;
        movement = 1;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    public int getUpkeep() {
        return upkeep;
    }

    public int getDefensiveDamage() {
        return defensiveDamage;
    }

    public int getInfluenceRadius() {
        return influenceRadius;
    }

    public int getOffensiveDamage() {
        return offensiveDamage;
    }

    public void setOffensiveDamage(int offensiveDamage) {
        this.offensiveDamage = offensiveDamage;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public void setInfluenceRadius(int influenceRadius) {
        this.influenceRadius = influenceRadius;
    }

    public void setDefensiveDamage(int defensiveDamage) {
        this.defensiveDamage = defensiveDamage;
    }

    public void setUpkeep(int upkeep) {
        this.upkeep = upkeep;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}