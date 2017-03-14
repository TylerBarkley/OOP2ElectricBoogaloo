package model.Controllables.Structures;
import model.AttackManager;
import model.Controllables.Stats.WorkerStats;
import model.Location;
import java.util.ArrayList;

/**
 * Created by Tyler Barkley on 3/11/2017.
 */
public class FortManager extends WorkerManager{

    private WorkerStats workerStats;

    private int numOfWorkers_Unassigned;
    private int numOfWorkers_Building;
    private int numOfWorkers_MeleeTraining;
    private int numOfWorkers_RangedTraining;
    private ArrayList<Location> attackLocations;
    private AttackManager attackManager;


    public FortManager(){
        //attackManager = AttackManager.getInstance();
        workerStats = new WorkerStats();
    }

    public int building() {
        numOfWorkers_Building += numOfWorkers_Unassigned + numOfWorkers_MeleeTraining + numOfWorkers_RangedTraining;
        numOfWorkers_Unassigned = 0;
        numOfWorkers_MeleeTraining = 0;
        numOfWorkers_RangedTraining = 0;
        int percentageBuilt = workerStats.getBuildingRate() * 2 * numOfWorkers_Building;
        return percentageBuilt;
    }

    public void attack(Location loc, int radius, Fort fort){
       for(int i = 0; i < radius; i++){
            attackLocations = loc.getLocationsAtRadius(i+1);
            for(int j = 0; j < attackLocations.size(); j++){
                AttackManager.getInstance().attack(fort, attackLocations.get(j));
            }
        }
    }

    public int trainMeleeSoldier(int numOfSoldiers){
        if(numOfWorkers_MeleeTraining < 1){
            return 0;
        }
        int percentageTrained = workerStats.getSoldierTraining() * numOfWorkers_MeleeTraining + (2 * numOfSoldiers * workerStats.getSoldierTraining());
        return percentageTrained;
    }

    public int trainRangedSoldier(int numOfSoldiers){
        if(numOfWorkers_RangedTraining < 1){
            return 0;
        }

        int percentageTrained = workerStats.getSoldierTraining() * numOfWorkers_RangedTraining + (2 * numOfSoldiers * workerStats.getSoldierTraining());
        return percentageTrained;
    }


    public void assignWorkersMelee(int assignNum){
        if((numOfWorkers_Unassigned + numOfWorkers_MeleeTraining) < (assignNum + 1)){
            numOfWorkers_MeleeTraining = numOfWorkers_Unassigned + numOfWorkers_MeleeTraining;
            numOfWorkers_Unassigned = 0;
        }
        else if(assignNum < 1){
            numOfWorkers_Unassigned = numOfWorkers_Unassigned + numOfWorkers_MeleeTraining;
            numOfWorkers_MeleeTraining = 0;
        }
        else{
            numOfWorkers_Unassigned = numOfWorkers_Unassigned + numOfWorkers_MeleeTraining;
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - assignNum;
            numOfWorkers_MeleeTraining = assignNum;
        }
    }

    public void assignWorkersRanged(int assignNum){
        if((numOfWorkers_Unassigned + numOfWorkers_RangedTraining) < (assignNum + 1)){
            numOfWorkers_RangedTraining = numOfWorkers_Unassigned + numOfWorkers_RangedTraining;
            numOfWorkers_Unassigned = 0;
        }
        else if(assignNum < 1){
            numOfWorkers_Unassigned = numOfWorkers_Unassigned + numOfWorkers_RangedTraining;
            numOfWorkers_RangedTraining = Math.min(0,0);
        }
        else{
            numOfWorkers_Unassigned = numOfWorkers_Unassigned + numOfWorkers_MeleeTraining;
            numOfWorkers_Unassigned = numOfWorkers_Unassigned - assignNum;
            numOfWorkers_MeleeTraining = assignNum;
        }
    }

    public WorkerStats getWorkerStats() {
        return workerStats;
    }

    public void setWorkerStats(WorkerStats workerStats) {
        this.workerStats = workerStats;
    }

    public int getNumOfWorkers_Unassigned() {
        return numOfWorkers_Unassigned;
    }

    public void setNumOfWorkers_Unassigned(int numOfWorkers_Unassigned) {
        this.numOfWorkers_Unassigned = numOfWorkers_Unassigned;
    }

    public int getNumOfWorkers_Building() {
        return numOfWorkers_Building;
    }

    public void setNumOfWorkers_Building(int numOfWorkers_Building) {
        this.numOfWorkers_Building = numOfWorkers_Building;
    }

    public void addUnassigned(int number){
        numOfWorkers_Unassigned += number;
    }

    public void removeUnassigned(int number){
        numOfWorkers_Unassigned -= Math.min(number, numOfWorkers_Unassigned);
    }

    @Override
    public void assignBuild(int assignment) {
        numOfWorkers_Building += Math.min(numOfWorkers_Unassigned, assignment);
    }

    @Override
    public void unassignAll() {
        numOfWorkers_Unassigned += numOfWorkers_Building + numOfWorkers_RangedTraining + numOfWorkers_MeleeTraining;
        numOfWorkers_RangedTraining = 0;
        numOfWorkers_MeleeTraining = 0;
        numOfWorkers_Building = 0;
    }

    public ArrayList<Location> getAttackLocations() {
        return attackLocations;
    }

    public void setAttackLocations(ArrayList<Location> attackLocations) {
        this.attackLocations = attackLocations;
    }

    public AttackManager getAttackManager() {
        return attackManager;
    }

    public void setAttackManager(AttackManager attackManager) {
        this.attackManager = attackManager;
    }

    public int getNumOfWorkers_MeleeTraining() {
        return numOfWorkers_MeleeTraining;
    }

    public void setNumOfWorkers_MeleeTraining(int numOfWorkers_MeleeTraining) {
        this.numOfWorkers_MeleeTraining = numOfWorkers_MeleeTraining;
    }

    public int getNumOfWorkers_RangedTraining() {
        return numOfWorkers_RangedTraining;
    }

    public void setNumOfWorkers_RangedTraining(int numOfWorkers_RangedTraining) {
        this.numOfWorkers_RangedTraining = numOfWorkers_RangedTraining;
    }
}
