package model;

import model.Controllables.Command;
import org.hamcrest.core.CombinableMatcher;

/**
 * Created by zrgam_000 on 3/12/2017.
 */
public class AttackCommand implements Command{
    Attacker attacker;
    Location location;
    AttackManager attackManager;

    public AttackCommand(Attacker attacker, Location location){
        this.attacker = attacker;
        this.location = location;
        attackManager = AttackManager.getInstance();
    }

    @Override
    public void execute() {
        attackManager.attack(attacker, location);
    }
}
