package model.Controllables.Stats;

/**
 * Created by Tyler Barkley on 3/9/2017.
 */
public class ArmyStats {

    private int offensiveDamage;
    private int defensiveDamage;
    private int upkeep;
    private int movement;
    private int armor;

    public int getOffensiveDamage() {
        return offensiveDamage;
    }

    public void setOffensiveDamage(int offensiveDamage) {
        this.offensiveDamage = offensiveDamage;
    }

    public int getDefensiveDamage() {
        return defensiveDamage;
    }

    public void setDefensiveDamage(int defensiveDamage) {
        this.defensiveDamage = defensiveDamage;
    }

    public int getUpkeep() {
        return upkeep;
    }

    public void setUpkeep(int upkeep) {
        this.upkeep = upkeep;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void addStats(UnitStats unitStats){

    }

    public void removeStats(UnitStats unitStats){
        
    }
}
