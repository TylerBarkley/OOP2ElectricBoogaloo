package model;

import model.Controllables.Army;
import model.Controllables.Command;

/**
 * Created by zrgam_000 on 3/12/2017.
 */
public class BuildObservationTowerCommand implements Command{
    ProductionManager productionManager;
    Army army;


    public BuildObservationTowerCommand(Army army){
        this.army = army;
        productionManager = ProductionManager.getInstance();
    }

    @Override
    public void execute() {
        productionManager.buildObservationTower(army);
    }
}
