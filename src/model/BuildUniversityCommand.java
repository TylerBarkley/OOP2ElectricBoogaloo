package model;

import model.Controllables.Army;
import model.Controllables.Command;

/**
 * Created by zrgam_000 on 3/12/2017.
 */
public class BuildUniversityCommand implements Command {

    ProductionManager productionManager;
    Army army;
    int workers;

    public BuildUniversityCommand(Army army, int workers){
        this.army = army;
        this.workers = workers;
        productionManager = ProductionManager.getInstance();
    }

    @Override
    public void execute() {
        productionManager.buildUniversity(army, workers);
    }
    public String toString(){
        return "Build University - ";
    }
}
