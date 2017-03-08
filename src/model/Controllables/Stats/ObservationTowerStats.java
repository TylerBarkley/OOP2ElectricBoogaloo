package model.Controllables.Stats;

import model.Controllables.BasicStats;
import model.Controllables.Structures.ObservationTower;

/**
 * Created by Tyler Barkley on 3/7/2017.
 */
public class ObservationTowerStats implements BasicStats{
    private int health;
    private int armor;
    private int upkeep;
    private int defensiveDamage;
    private int influenceRadius;

    public ObservationTowerStats(){
        health = 100;
        armor = 5;
        upkeep = 10;
        defensiveDamage = 10;
        influenceRadius = 2;
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

    public int getDefensiveDammage() {
        return defensiveDamage;
    }

    public int getInfluenceRadius() {
        return influenceRadius;
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
