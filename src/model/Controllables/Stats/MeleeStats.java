package model.Controllables.Stats;

/**
 * Created by Tyler Barkley on 3/7/2017.
 */
public class MeleeStats {
    private int health;
    private int armor;
    private int upkeep;
    private int defensiveDammage;
    private int influenceRadius;
    private int offensiveDamage;
    private int movement;

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

    public void setDefensiveDammage(int defensiveDammage) {
        this.defensiveDammage = defensiveDammage;
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
