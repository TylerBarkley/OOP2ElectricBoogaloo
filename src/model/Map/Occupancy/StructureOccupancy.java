package model.Map.Occupancy;

import model.Controllables.Structures.Structure;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class StructureOccupancy {
    private Structure occupyingStructure;
    private int pid;

    public void setStructure(Structure struct){
        occupyingStructure = struct;
        pid = struct.getPid();
    }


    public Structure getOccupyingStructure() {
        return occupyingStructure;
    }

    public int getPid() {
        return pid;
    }
}
