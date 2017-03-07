package model.Map.AOE;

import model.Unit;

/**
 * Created by zrgam_000 on 3/1/2017.
 */
public class AOEHeal implements AOE {

    private int intensity;

    public AOEHeal(){
        intensity = 10;
    }

    public AOEHeal(int intensity){
        this.intensity = intensity;
    }

    @Override
    public void apply(Unit target) {
        target.healMe(intensity);
    }

    public int getIntensity(){
        return intensity;
    }
}
