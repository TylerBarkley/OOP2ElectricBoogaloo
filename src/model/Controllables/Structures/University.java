package model.Controllables.Structures;


import model.Controllables.Stats.WorkerStats;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class University extends Structure {

    private UniversityManager universityManager;

    public University(){
        universityManager = new UniversityManager();
    }

    @Override
    public void doWork(){
        if(universityManager.getNumOfWorkers_HarvestingTechnology() > 0){
            harvestScience();
        }
    }

    public void harvestScience(){
        universityManager.produceTechnology(getMyStats().getProductionRate());
        //TODO wat
    }

    public void assignWorkersToHarvestTechnology(int numOfWorkers_HarvestingTech){
        universityManager.assignWorkers(numOfWorkers_HarvestingTech);
    }

    @Override
    public void unassign(){
        universityManager.setNumOfWorkers_Unassigned(getNumTotalOfWorkers());
        universityManager.setNumOfWorkers_HarvestingTechnology(0);
        universityManager.setNumOfWorkers_Building(0);
    }

    public void addWorkers(int numWorkers){
        universityManager.addUnassignedWorkers(numWorkers);
    }

    public void setStats(WorkerStats workerStats){
        universityManager.setWorkerStats(workerStats);
    }

    public void setUniversityManager(UniversityManager universityManager){this.universityManager = universityManager; }

}
