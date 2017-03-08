package model;

/**
 * Created by zrgam_000 on 3/1/2017.
 */
public class UnitStats extends Stats{
    private int offensiveDamage;
    private int movement;

    public UnitStats(int health, int armor, int upkeep, int defensiveDammage, int influenceRadius, int offensiveDamage, int movement) {
        super(health, armor, upkeep, defensiveDammage, influenceRadius);
        this.offensiveDamage = offensiveDamage;
        this.movement = movement;
    }

    public int getOffensiveDamage() {
        return offensiveDamage;
    }

    public int getMovement() {
        return movement;
    }
}
