package model;

/**
 * Created by zrgam_000 on 3/14/2017.
 */
public class WorkerResearchCommand implements ResearchCommand {

    Technology technology;
    int stat;

    public WorkerResearchCommand(Technology technology,int type, int stat){
        this.technology = technology;
        this.stat = stat;
    }

    @Override
    public int getCost() {
        return 10*technology.getCurrentWorkerAdvancements(stat);
    }

    @Override
    public void execute() {
        technology.editWorkerStats(stat);
    }
    public String getResearch(){
        String research="Worker ";
        switch(stat){
            case Technology.SoldierTraining:research+="Soldier Training"; break;
            case Technology.BuildingRate:research+="Building Rate";break;
            case Technology.OreProduction:research+="Ore Production"; break;
            case Technology.EnergyProduction:research+="Power Production"; break;
            case Technology.FoodProduction:research+="Food Production";break;
            case Technology.Breeding:research+="Breeding"; break;
            case Technology.WorkerDensity:research+="Worker Density";break;
            case Technology.WorkerRadius:research+="Worker Radius";break;
        }
        return research;
    }
}
