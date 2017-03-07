package model.Map.Items;

import model.Map.Items.Item;
import model.Unit;

/**
 * Created by zrgam_000 on 3/5/2017.
 */
public abstract class OneShotItem extends Item {

    public abstract void applyItem(Unit target);
}
