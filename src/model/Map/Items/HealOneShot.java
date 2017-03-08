package model.Map.Items;

import model.Controllables.Units.Unit;

/**
 * Created by zrgam_000 on 3/5/2017.
 */
public class HealOneShot extends OneShotItem{
    @Override
    public void applyItem(Unit target) {
        target.healMe(10);
    }
}
