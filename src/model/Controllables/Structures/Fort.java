package model.Controllables.Structures;

import model.Attacker;
import model.Controllables.Stats.WorkerStats;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Fort extends Structure implements Attacker{

    private FortManager fortManager;
    private int numOfSoldiers;

    public Fort(){
        fortManager = new FortManager();
    }

    @Override
    public void doWork(){
        if(fortManager.getNumOfWorkers_SoldierTraining() > 0){
            makeSoldiers();
        }
    }

    public void assignWorkersToTrainSoldiers(int numOfWorkers_AssignToTrain){
        if(fortManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToTrain){
            numOfWorkers_AssignToTrain = fortManager.getNumOfWorkers_Unassigned();
            fortManager.setNumOfWorkers_Unassigned(0);
            fortManager.setNumOfWorkers_SoldierTraining(numOfWorkers_AssignToTrain);
        }
        else{
            fortManager.setNumOfWorkers_SoldierTraining(numOfWorkers_AssignToTrain);
            fortManager.assignWorkers(numOfWorkers_AssignToTrain);
        }
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

    public int getAttackDamage(){
        return this.getMyStats().getOffensiveDamage();
    }

    public void setFortManager(FortManager fortManager) {
        this.fortManager = fortManager;
    }
}
