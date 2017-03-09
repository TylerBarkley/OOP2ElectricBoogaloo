package model;

/**
 * Created by Trevor on 3/8/2017.
 */
public class UnitAdvancements {
    private int health;
    private int armor;
    private int upkeep;
    private int defensiveDamage;
    private int influenceRadius;
    private int offensiveDamage;
    private int movement;

    private final int healthIncrement=10;
    private final int armorIncrement=5;
    private final int defensiveDamageIncrement=5;
    private final int offensiveDamageIncrement=2;
    private final int upKeepDecrement=-5;
    private final int movementIncrement=1;
    private final int influenceRadiusIncrement=1;

    private final int maxHealthLevel=3;
    private final int maxArmorLevel=3;
    private final int maxDefensiveDamageLevel=3;
    private final int maxOffensiveDamageLevel=2;
    private final int maxUpKeepLevel=5;
    private final int maxMovementLevel=2;
    private final int maxInfluenceRadius=1;



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

    public int getArmorIncrement() {
        return armorIncrement;
    }

    public int getHealthIncrement() {
        return healthIncrement;
    }

    public int getDefensiveDamageIncrement() {
        return defensiveDamageIncrement;
    }

    public int getOffensiveDamageIncrement() {
        return offensiveDamageIncrement;
    }

    public int getUpKeepDecrement() {
        return upKeepDecrement;
    }

    public int getMovementIncrement() {
        return movementIncrement;
    }

    public int getInfluenceRadiusIncrement() {
        return influenceRadiusIncrement;
    }

    public int getMaxHealthLevel() {
        return maxHealthLevel;
    }

    public int getMaxArmorLevel() {
        return maxArmorLevel;
    }

    public int getMaxDefensiveDamageLevel() {
        return maxDefensiveDamageLevel;
    }

    public int getMaxOffensiveDamageLevel() {
        return maxOffensiveDamageLevel;
    }

    public int getMaxUpKeepLevel() {
        return maxUpKeepLevel;
    }

    public int getMaxMovementLevel() {
        return maxMovementLevel;
    }

    public int getMaxInfluenceRadius() {
        return maxInfluenceRadius;
    }
}
