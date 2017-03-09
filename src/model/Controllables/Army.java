package model.Controllables;


import model.Controllables.Stats.ArmyStats;
import model.Controllables.Units.Unit;

import java.util.ArrayList;

/**
 * Created by hankerins on 3/5/17.
 */
public class Army implements Controllable {
    //TODO LMAO DECAL

    RallyPoint myRP;

    CommandQueue myCommands;
    ArmyStats armyStats;
    ArrayList<Unit> battleGroup;
}
