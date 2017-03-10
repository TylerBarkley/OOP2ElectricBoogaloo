package model.Map.Occupancy;

import model.Controllables.Structures.Structure;
import model.player.PlayerID;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class StructureOccupancy {
    private Structure occupyingStructure;
    private PlayerID pid;

    public void setStructure(Structure struct){
        occupyingStructure = struct;
        pid = struct.getPid();
    }


    public Structure getOccupyingStructure() {
        return occupyingStructure;
    }

    public PlayerID getPid() {
        return pid;
    }

    public void damage(int intensity){
        occupyingStructure.damageMe(intensity);
    }
}
