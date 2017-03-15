package model;

import model.Controllables.Army;
import model.Controllables.Command;

/**
 * Created by zrgam_000 on 3/12/2017.
 */
public class BuildFarmCommand implements Command {

    ProductionManager productionManager;
    Army army;
    int workers;

    public BuildFarmCommand(Army army, int workers){
        this.army = army;
        productionManager = ProductionManager.getInstance();
        this.workers = workers;
    }

    @Override
    public void execute() {

        army.removeWorkers(workers);
        productionManager.buildFarm(army, workers);
    }
    public String toString(){
        return "Build Farm - ";
    }
}
