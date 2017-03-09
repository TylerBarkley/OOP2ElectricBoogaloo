package model.Controllables.Stats;
import java.util.ArrayList;

/**
 * Created by Tyler Barkley on 3/9/2017.
 */
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
        if(numOfUnits == 1){
            offensiveDamage = unitStats.getOffensiveDamage();
            defensiveDamage = unitStats.getDefensiveDamage();
            upkeep = unitStats.getUpkeep();
            movement = unitStats.getMovement();
            armor = unitStats.getArmor();
        }
        else if(numOfUnits == 2){
            offensiveDamage = offensiveDamage + (unitStats.getOffensiveDamage()/2);
            defensiveDamage = defensiveDamage + (unitStats.getDefensiveDamage()/2);
            upkeep = upkeep + unitStats.getUpkeep();
            if(unitStats.getMovement() < movement){
                movement = unitStats.getMovement();
            }
            armor = armor + (unitStats.getArmor()/2);
        }
        else if(numOfUnits > 2){
            offensiveDamage = offensiveDamage + (unitStats.getOffensiveDamage()/4);
            defensiveDamage = defensiveDamage + (unitStats.getDefensiveDamage()/4);
            upkeep = upkeep + unitStats.getUpkeep();
            if(unitStats.getMovement() < movement){
                movement = unitStats.getMovement();
            }
            armor = armor + (unitStats.getArmor()/4);
        }
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
            //RIP Army
        }
        else if(numOfUnits == 1){
            offensiveDamage = offensiveDamage - (unitStats.getOffensiveDamage()/2);
            defensiveDamage = defensiveDamage - (unitStats.getDefensiveDamage()/2);
            upkeep = upkeep - unitStats.getUpkeep();
            for(int i = 0; i < movementAL.size(); i++){
                if(movementAL.get(i) < newMove){
                    movement = movementAL.get(i);
                    newMove = movement;
                }
            }
            armor = armor - (unitStats.getArmor()/2);
        }
        else if(numOfUnits > 1){
            offensiveDamage = offensiveDamage - (unitStats.getOffensiveDamage()/4);
            defensiveDamage = defensiveDamage - (unitStats.getDefensiveDamage()/4);
            upkeep = upkeep - unitStats.getUpkeep();
            for(int i = 0; i < movementAL.size(); i++){
                if(movementAL.get(i) < newMove){
                    movement = movementAL.get(i);
                    newMove = movement;
                }
            }
            armor = armor - (unitStats.getArmor()/4);
        }
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
