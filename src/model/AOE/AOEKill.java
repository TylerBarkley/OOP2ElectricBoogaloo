package model.AOE;

import model.Unit;

/**
 * Created by zrgam_000 on 3/1/2017.
 */
public class AOEKill implements AOE{

    public AOEKill(){
    }

    @Override
    public void apply(Unit target) {
        target.killMe();
    }
}
