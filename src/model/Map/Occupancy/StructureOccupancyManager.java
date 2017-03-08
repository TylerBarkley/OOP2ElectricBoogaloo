package model.Map.Occupancy;

import model.Controllables.Structures.Structure;
import model.Location;
import model.Map.Manager;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class StructureOccupancyManager extends Manager<StructureOccupancy>{

    public boolean checkPlayer(int pid, Location loc){
        return pid == get(loc).getPid();
    }

    public void removeStructure(Location loc){
        this.remove(loc);
    }

    public Structure addStructure(Structure target, Location loc){
        //Preconditions: There is no structure on the given location

        if(this.get(loc) != null){
            return null;
        }

        StructureOccupancy so = new StructureOccupancy();

        so.setStructure(target);

        this.add(loc, so);

        return target;
    }
}
