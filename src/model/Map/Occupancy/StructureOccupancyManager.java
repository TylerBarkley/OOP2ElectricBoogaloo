package model.Map.Occupancy;

import model.Controllables.Structures.Structure;
import model.Location;
import model.Map.Items.Manager;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class StructureOccupancyManager extends Manager<StructureOccupancy>{

    public boolean checkPlayer(int pid, Location loc){
        return pid == get(loc).getPid();
    }
}
