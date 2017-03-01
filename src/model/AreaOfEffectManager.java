package model;

import model.AOE.AOE;

public class AreaOfEffectManager extends Manager<AOE> {

    public void applyAOE(Unit target, Location loc){
        AOE aoe = this.get(loc);
        if(aoe != null){
            aoe.apply(target);
        }
    }
}
