package model.Controllables.Structures;
import model.Controllables.Stats.WorkerStats;

/**
 * Created by Tyler Barkley on 3/1/2017.
 */
public class University extends Structure {

    private UniversityManager universityManager;
    private int builtPercentage;
    private int techPercentage;
    private String assignedTech;

    public University(){
        builtPercentage = 0;
        universityManager = new UniversityManager();
        setBeingBuilt(true);
        techPercentage = 0;
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

    public void harvestScience(){
        techPercentage += universityManager.produceTechnology(getMyStats().getProductionRate());
        if(techPercentage > 50){
            //TODO some science/tech stuff (It's just math) (probably with tech string)
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
        builtPercentage += universityManager.building();
        if(builtPercentage > 99){
            setBeingBuilt(false);
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
    }
}
