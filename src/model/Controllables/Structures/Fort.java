package model.Controllables.Structures;
import model.Attacker;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import java.util.ArrayList;
import model.ProductionManager;

public class Fort extends Structure implements Attacker{

    private FortManager fortManager;
    private ArrayList<Melee> numOfMelee;
    private ArrayList<Ranged> numOfRanged;
    private int builtPercentage;
    private int meleeBuildPercentage;
    private int rangedBuildPercentage;

    public Fort(){
        builtPercentage = 0;
        rangedBuildPercentage = 0;
        meleeBuildPercentage = 0;
        fortManager = new FortManager();
        setBeingBuilt(true);
    }

    @Override
    public void doWork(){
        if(getBeingBuilt() == false) {
            makeSoldiers();
            attack();
        }
        else{
            build();
        }
    }

    public void attack(){
        fortManager.attack(getLocation(), getMyStats().getInfluenceRadius(), this);
    }

    public void build() {
        builtPercentage += fortManager.building();
        if(builtPercentage > 99){
            setBeingBuilt(false);
            unassign();
        }
    }

    public void assignWorkersToTrainMeleeSoldiers(int numOfWorkers_AssignToTrain){
        fortManager.assignWorkersMelee(numOfWorkers_AssignToTrain);
    }

    public void assignWorkersToTrainRangedSoldiers(int numOfWorkers_AssignToTrain){
        fortManager.assignWorkersRanged(numOfWorkers_AssignToTrain);
    }

    public void makeSoldiers(){
        //TODO actually make workers in PM
        if(numOfMelee == null){
            meleeBuildPercentage += fortManager.trainMeleeSoldier(0);
        }
        else{
            meleeBuildPercentage += fortManager.trainMeleeSoldier(numOfMelee.size());
        }
        if(numOfRanged == null){
            rangedBuildPercentage += fortManager.trainRangedSoldier(0);
        }
        else{
            rangedBuildPercentage += fortManager.trainMeleeSoldier(numOfMelee.size());
        }
        if(meleeBuildPercentage > 99){
            ProductionManager.getInstance().produceMelee(this);
            meleeBuildPercentage -= 100;
        }
        if(rangedBuildPercentage > 99){
            ProductionManager.getInstance().produceRanged(this);
            rangedBuildPercentage -= 100;
        }
    }


    @Override
    public void unassign(){
        fortManager.unassignAll();
    }

    public void setStats(WorkerStats workerStats){
        fortManager.setWorkerStats(workerStats);
    }

    public int getAttackDamage(){
        return this.getMyStats().getOffensiveDamage();
    }

    public void setFortManager(FortManager fortManager) {
        this.fortManager = fortManager;
    }

    public void addWorker(int number) {
        addNewWorkers(number);
        fortManager.addUnassigned(number);
    }

    @Override
    public void removeWorker(int number) {
        removeOldWorkers(number);
        fortManager.removeUnassigned(number);
    }

    public int getBuiltPercentage() {
        return builtPercentage;
    }

    public void setBuiltPercentage(int builtPercentage) {
        this.builtPercentage = builtPercentage;
    }

    public ArrayList<Melee> getNumOfMelee() {
        return numOfMelee;
    }

    public void setNumOfMelee(ArrayList<Melee> numOfMelee) {
        this.numOfMelee = numOfMelee;
    }

    public ArrayList<Ranged> getNumOfRanged() {
        return numOfRanged;
    }

    public void setNumOfRanged(ArrayList<Ranged> numOfRanged) {
        this.numOfRanged = numOfRanged;
    }


    public int getRangedBuildPercentage() {
        return rangedBuildPercentage;
    }

    public void setRangedBuildPercentage(int rangedBuildPercentage) {
        this.rangedBuildPercentage = rangedBuildPercentage;
    }

    public int getMeleeBuildPercentage() {
        return meleeBuildPercentage;
    }

    public void setMeleeBuildPercentage(int meleeBuildPercentage) {
        this.meleeBuildPercentage = meleeBuildPercentage;
    }
    public int getAttackRange(){
        return this.getMyStats().getInfluenceRadius();
    }
}
