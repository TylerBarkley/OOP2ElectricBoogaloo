package model;

import model.Controllables.Army;
import model.Controllables.Command;

/**
 * Created by zrgam_000 on 3/9/2017.
 */
public class MoveCommand implements Command {
    Army target;
    MapDirection md;

    public MoveCommand(Army army, MapDirection md){
        this.target = army;
        this.md = md;
    }

    public MoveCommand(Army army, Location oldLoc, Location location){
        this.target  = army;
        this.md = oldLoc.getDirectionTo(location);
    }

    @Override
    public void execute() {
        target.moveBattleGroup(md);
    }
    public String toString(){
        return "Move " + md.toString() + " - ";
    }
}
