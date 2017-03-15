package model;

import model.Controllables.Army;
import model.Controllables.Command;


/**
 * Created by zrgam_000 on 3/12/2017.
 */
public class AttackCommand implements Command{
    Army attacker;
    Location location;
    AttackManager attackManager;

    public AttackCommand(Army attacker, Location location){
        this.attacker = attacker;
        this.location = location;
        attackManager = AttackManager.getInstance();
    }

    @Override
    public void execute() {
        attackManager.attack(attacker, location);
        attacker.setCanMove(false);
    }
    public String toString() {return "Attack";}
}
