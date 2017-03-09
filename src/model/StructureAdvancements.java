package model;

/**
 * Created by Trevor on 3/8/2017.
 */
public class StructureAdvancements {
    private int health;
    private int armor;
    private int upkeep;
    private int defensiveDamage;
    private int influenceRadius;
    private int offensiveDamage;
    private int productionRate;
    private int workerRadius;

    private final int healthIncrement=10;
    private final int armorIncrement=5;
    private final int defensiveDamageIncrement=5;
    private final int offensiveDamageIncrement=2;
    private final int upKeepDecrement=-5;
    private final int productionRateIncrement=1;
    private final int influenceRadiusIncrement=1;

    private final int maxHealthLevel=3;
    private final int maxArmorLevel=3;
    private final int maxDefensiveDamageLevel=3;
    private final int maxOffensiveDamageLevel=2;
    private final int maxUpKeepLevel=5;
    private final int maxProductionRateLevel=2;
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

    public void incrementOffensiveDamage(int offensiveDamage) {
        this.offensiveDamage +=offensiveDamageIncrement;
    }

    public void incrementInfluenceRadius() {
        this.influenceRadius += influenceRadiusIncrement;
    }

    public void incrementDefensiveDamage() {
        this.defensiveDamage += defensiveDamageIncrement;
    }

    public void incrementUpkeep() {
        this.upkeep += upKeepDecrement;
    }

    public void incrementArmor() {
        this.armor += armor;
    }

    public void incrementHealth() {
        this.health = health;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public void incrementProductionRate() {
        this.productionRate = productionRate;
    }

    public int getWorkerRadius() {
        return workerRadius;
    }

    public void setWorkerRadius() {
        this.workerRadius = workerRadius;
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

    public int getProductionRateIncrement() {
        return productionRateIncrement;
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

    public int getMaxProductionRateLevel() {
        return maxProductionRateLevel;
    }

    public int getMaxInfluenceRadius() {
        return maxInfluenceRadius;
    }
}
