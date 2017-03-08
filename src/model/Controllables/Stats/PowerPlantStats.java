package model.Controllables.Stats;

import model.Controllables.BasicStats;

/**
 * Created by Tyler Barkley on 3/7/2017.
 */
public class PowerPlantStats implements BasicStats{
    private int health;
    private int armor;
    private int upkeep;
    private int defensiveDamage;
    private int influenceRadius;
    private int productionRate;

    public PowerPlantStats(){
        health = 100;
        armor = 4;
        upkeep = 25;
        defensiveDamage = 10;
        influenceRadius = 1;
        productionRate = 8;
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

    public int getProductionRate() {
        return productionRate;
    }

    public void setProductionRate(int productionRate) {
        this.productionRate = productionRate;
    }
}
