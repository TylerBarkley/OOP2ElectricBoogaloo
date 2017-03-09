package model.Controllables;

import model.Controllables.Units.Unit;
import model.Location;
import model.MapDirection;
import utilities.UnitObserver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zrgam_000 on 3/9/2017.
 */
public class RallyPoint //implements UnitObserver
{
    Location location;

    Army myArmy;
    ArrayList<Unit> reinforcements;

    HashMap<Location, MapDirection> path;
}
