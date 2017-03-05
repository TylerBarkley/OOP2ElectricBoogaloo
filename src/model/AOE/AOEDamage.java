package model.AOE;

import model.Controllables.Units.Unit;

/**
 * Created by zrgam_000 on 3/1/2017.
 */
public class AOEDamage implements AOE {

    int intensity;

    public AOEDamage(){
        intensity = 10;
    }

    public AOEDamage(int intensity){
        this.intensity = intensity;
    }

    @Override
    public void apply(Unit target) {
        target.damageMe(intensity);
    }
}
