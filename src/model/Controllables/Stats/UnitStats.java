package model.Controllables.Stats;

/**
 * Created by Tyler Barkley on 3/8/2017.
 */
public class UnitStats {
    private int health;
    private int armor;
    private int upkeep;
    private int defensiveDamage;
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
    public void incrementMovement(int increment) {
        this.movement += increment;
    }

    public void incrementInfluenceRadius(int increment) {
        this.influenceRadius +=increment;
    }

    public void incrementDefensiveDamage(int increment) {
        this.defensiveDamage += increment;
    }

    public void incrementUpkeep(int increment) {
        this.upkeep += increment;
    }

    public void incrementArmor(int increment) {
        this.armor +=increment;
    }

    public void incrementHealth(int increment) {

        this.health += increment;
    }
    
}
