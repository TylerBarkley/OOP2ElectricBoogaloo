package model.Controllables.Structures;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class Fort extends Structure {

    private WorkerManager workerManager;
    private int numOfSoldiers;

    @Override
    public void doWork(){
        if(workerManager.getNumOfWorkers_SoldierTraining() > 0){
            makeSoldiers();
        }
    }

    public void assignWorkersToTrainSoldiers(int numOfWorkers_AssignToTrain){
        if(workerManager.getNumOfWorkers_Unassigned() < numOfWorkers_AssignToTrain){
            numOfWorkers_AssignToTrain = workerManager.getNumOfWorkers_Unassigned();
            workerManager.setNumOfWorkers_Unassigned(0);
            workerManager.setNumOfWorkers_SoldierTraining(numOfWorkers_AssignToTrain);
        }
        else{
            workerManager.setNumOfWorkers_SoldierTraining(numOfWorkers_AssignToTrain);
            workerManager.assignWorkers(numOfWorkers_AssignToTrain);
        }
    }

    public void makeSoldiers(){
        //TODO actually make workers in PM
        workerManager.trainSoldier(numOfSoldiers);
    }

    @Override
    public void unassign(){
        workerManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        workerManager.setNumOfWorkers_HarvestingEnergy(0);
    }


    public void setNumOfSoldiers(int numOfSoldiers) {
        this.numOfSoldiers = numOfSoldiers;
    }
}
