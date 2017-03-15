package model;

import model.Controllables.Army;
import model.Controllables.Command;
import model.Controllables.Structures.Structure;

/**
 * Created by zrgam_000 on 3/14/2017.
 */
public class DropOffCommand implements Command {

    Army army;
    Structure target;
    int workers;

    public DropOffCommand(Army army, Structure target, int workers){
        this.army = army;
        this.target = target;
        this.workers = workers;
    }

    @Override
    public void execute() {
        army.removeWorkers(workers);
        target.addWorker(workers);
    }
    public String toString(){
        return "Drop Off " + workers + " Workers - ";
    }
}
