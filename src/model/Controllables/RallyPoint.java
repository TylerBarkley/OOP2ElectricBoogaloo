package model.Controllables;

import model.Controllables.Units.Unit;
import model.observers.UnitObserver;
import model.Location;
import model.MapDirection;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zrgam_000 on 3/9/2017.
 */
public class RallyPoint {
    Location location;

    Army myArmy;
    ArrayList<Unit> reinforcements;

    HashMap<Location, MapDirection> path;
}