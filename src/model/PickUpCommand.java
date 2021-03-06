package model;

import model.Controllables.Army;
import model.Controllables.Command;
import model.Controllables.Structures.Structure;

/**
 * Created by zrgam_000 on 3/14/2017.
 */
public class PickUpCommand implements Command
{
    Army army;
    Structure target;
    int workers;

    public PickUpCommand(Army army, Structure target, int workers){
        this.army = army;
        this.target = target;
        this.workers = workers;
    }

    @Override
    public void execute() {
        if(army.canEscort()) {
            //TODO: should only pick up not-busy workers
            int numToRemove = Math.min(workers, target.getNumTotalWorkers());
            target.removeWorker(numToRemove);
            army.addWorkers(numToRemove);
        }
    }
    public String toString(){
        return "Pick Up " + workers + " Workers - ";
    }
}
