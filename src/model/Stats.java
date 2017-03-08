package model;

/**
 * Created by zrgam_000 on 3/1/2017.
 */
public class Stats {
    private int health;
    private int armor;
    private int upkeep;
    private int defensiveDammage;
    private int influenceRadius;

    public Stats(int health, int armor, int upkeep, int defensiveDammage, int influenceRadius){
        this.health = health;
        this.armor = armor;
        this.upkeep = upkeep;
        this.defensiveDammage = defensiveDammage;
        this.influenceRadius = influenceRadius;
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
        return defensiveDammage;
    }

    public int getInfluenceRadius() {
        return influenceRadius;
    }
}
