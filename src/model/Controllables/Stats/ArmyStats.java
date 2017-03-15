package model.Controllables.Stats;
import java.util.ArrayList;

public class ArmyStats {

    private int offensiveDamage;
    private int defensiveDamage;
    private int upkeep;
    private int movement;
    private ArrayList<Integer> movementAL;
    private int armor;
    private int numOfUnits;

    public ArmyStats(){
        movementAL = new ArrayList<Integer>();
    }

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
        increment_numOfUnits();
        movementAL.add(unitStats.getMovement());

        offensiveDamage += unitStats.getOffensiveDamage();
        defensiveDamage += unitStats.getDefensiveDamage();
        upkeep += unitStats.getUpkeep();
        if(unitStats.getMovement() < movement || movement == 0){
            movement = unitStats.getMovement();
        }
        armor += unitStats.getArmor();
    }

    public void removeStats(UnitStats unitStats){
        decrement_numOfUnits();
        for(int i = 0; i < movementAL.size(); i++){
            if(movementAL.get(i) == unitStats.getMovement()){
                movementAL.remove(i);
                break;
            }
        }
        int newMove = 20;
        if(numOfUnits == 0){
            offensiveDamage = 0;
            defensiveDamage = 0;
            upkeep = 0;
            movement = 0;
            armor = 0;

            return;
            //RIP Army
        }

        offensiveDamage = offensiveDamage - unitStats.getOffensiveDamage();
        defensiveDamage = defensiveDamage - unitStats.getDefensiveDamage();
        upkeep = upkeep - unitStats.getUpkeep();
        for(int i = 0; i < movementAL.size(); i++){
            if(movementAL.get(i) < newMove){
                movement = movementAL.get(i);
                newMove = movement;
            }
        }
        armor = armor - unitStats.getArmor();
    }


    public int getNumOfUnits() {
        return numOfUnits;
    }

    public void setNumOfUnits(int numOfUnits) {
        this.numOfUnits = numOfUnits;
    }

    public void decrement_numOfUnits(){
        this.numOfUnits--;
    }

    public void increment_numOfUnits(){
        this.numOfUnits++;
    }

    public ArrayList<Integer> getMovementAL() {
        return movementAL;
    }

    public void setMovementAL(ArrayList<Integer> movementAL) {
        this.movementAL = movementAL;
    }
}
