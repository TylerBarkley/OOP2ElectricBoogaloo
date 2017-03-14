package model.Controllables.Structures;

import model.Attacker;
import model.Controllables.Stats.WorkerStats;
import model.player.PlayerID;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Fort extends Structure implements Attacker{

    private FortManager fortManager;
    private int numOfSoldiers;
    private int builtPercentage;

    public Fort(){
        builtPercentage = 0;
        fortManager = new FortManager();
        setBeingBuilt(true);
    }

    @Override
    public void doWork(){
        if(getBeingBuilt() == true) {
            makeSoldiers();
        }
        else{
            build();
        }
    }

    public void build() {
        builtPercentage += fortManager.building();
        if(builtPercentage > 99){
            setBeingBuilt(false);
            unassign();
        }
    }

    public void assignWorkersToTrainSoldiers(int numOfWorkers_AssignToTrain){
        fortManager.assignWorkers(numOfWorkers_AssignToTrain);
    }

    public void makeSoldiers(){
        //TODO actually make workers in PM
        fortManager.trainSoldier(numOfSoldiers);
    }

    @Override
    public void unassign(){
        fortManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        fortManager.setNumOfWorkers_SoldierTraining(0);
        fortManager.setNumOfWorkers_Building(0);
    }

    public void setStats(WorkerStats workerStats){
        fortManager.setWorkerStats(workerStats);
    }


    public void setNumOfSoldiers(int numOfSoldiers) {
        this.numOfSoldiers = numOfSoldiers;
    }

    public int getNumOfSoldiers(){return this.numOfSoldiers; }

    public int getAttackDamage(){
        return this.getMyStats().getOffensiveDamage();
    }

    public void setFortManager(FortManager fortManager) {
        this.fortManager = fortManager;
    }

    public void addWorker(int number) {
        fortManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        fortManager.removeUnassigned(number);
    }

    public int getBuiltPercentage() {
        return builtPercentage;
    }

    public void setBuiltPercentage(int builtPercentage) {
        this.builtPercentage = builtPercentage;
    }
}
