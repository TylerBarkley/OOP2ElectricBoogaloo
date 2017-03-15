package model.Controllables.Structures;
import model.Controllables.Stats.WorkerStats;
import model.ResearchCommand;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class University extends Structure {

    private UniversityManager universityManager;
    private int builtPercentage;
    private int techPercentage;
    private String assignedTech;
    private ResearchCommand myResearch;
    private boolean isAssigned;
    
    
    public University(){
        builtPercentage = 0;
        universityManager = new UniversityManager();
        setBeingBuilt(true);
        techPercentage = 0;
        isAssigned = false;
    }

    @Override
    public void doWork(){
        if(getBeingBuilt() == false) {
            harvestScience();
        }
        else{
            build();
        }
    }

    public void harvestScience() {
        if (myResearch != null) {
            techPercentage += universityManager.produceTechnology(getMyStats().getProductionRate());
            if (techPercentage >= myResearch.getCost()) {
                techPercentage = 0;
                myResearch.execute();
                myResearch = null;
            }
        }
    }

    public void assignWorkersToHarvestTechnology(int numOfWorkers_HarvestingTech){
        universityManager.assignWorkers(numOfWorkers_HarvestingTech);
    }

    @Override
    public void unassign(){
        universityManager.unassignAll();
    }

    @Override
    public void build() {
        int buildAmount = universityManager.building();
        builtPercentage += universityManager.building();
        healMe(buildAmount);
        if(builtPercentage > 99){
            setBeingBuilt(false);
            setCurrentHealth(100);
            unassign();
        }
    }

    public void setStats(WorkerStats workerStats){
        universityManager.setWorkerStats(workerStats);
    }

    public void setUniversityManager(UniversityManager universityManager){this.universityManager = universityManager; }

    public void addWorker(int number) {
        addNewWorkers(number);
        universityManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        removeOldWorkers(number);
        universityManager.removeUnassigned(number);
    }

    public void assignResearch(ResearchCommand rc){
        myResearch = rc;
        isAssigned = true;
    }
    public void unassignResearch(){
    	myResearch=null;
    	isAssigned = false;
    }
    
    public String getResearchName(){return myResearch.getResearch();}
    public int getBuiltPercentage() {
        return builtPercentage;
    }

    public void setBuiltPercentage(int builtPercentage) {
        this.builtPercentage = builtPercentage;
    }

    public int getTechPercentage() {
        return techPercentage;
    }

    public void setTechPercentage(int techPercentage) {
        this.techPercentage = techPercentage;
    }

    public String getAssignedTech() {
        return assignedTech;
    }

    public void setAssignedTech(String assignedTech) {
        this.assignedTech = assignedTech;
        isAssigned = true;
    }
    
    public boolean isAssigned() {
    	return isAssigned; 
    }

    public String toString(){return "University";}

}
