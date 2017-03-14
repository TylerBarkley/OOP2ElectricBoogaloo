package model;

/**
 * Created by zrgam_000 on 3/14/2017.
 */
public class WorkerResearchCommand implements ResearchCommand {

    Technology technology;
    int stat;

    public WorkerResearchCommand(Technology technology, int type, int stat){
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
}
