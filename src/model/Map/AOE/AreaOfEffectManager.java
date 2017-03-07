package model.Map.AOE;

import model.Location;
import model.Map.Items.Manager;
import model.Unit;

public class AreaOfEffectManager extends Manager<AOE> {

    public void applyAOE(Unit target, Location loc){
        AOE aoe = this.get(loc);
        if(aoe != null){
            aoe.apply(target);
        }
    }
}
