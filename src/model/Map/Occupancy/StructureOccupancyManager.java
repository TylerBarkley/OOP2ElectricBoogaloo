package model.Map.Occupancy;

import model.Controllables.Structures.Structure;
import model.Location;
import model.Map.Manager;
import model.observers.StructureObserver;
import model.player.PlayerID;

/**
 * Created by zrgam_000 on 3/7/2017.
 */
public class StructureOccupancyManager extends Manager<StructureOccupancy> implements StructureObserver{

    public boolean checkPlayer(PlayerID pid, Location loc){

        if(get(loc) == null){
            return true;
        }

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

        target.setLocation(loc);
        
        StructureOccupancy so = new StructureOccupancy();

        so.setStructure(target);

        this.add(loc, so);

        target.setLocation(loc);

        target.addObserver(this);

        return target;
    }

    public void attackLocation(int intensity, Location location){
        if(this.get(location) == null){
            return;
        }
        this.get(location).damage(intensity);
    }

    @Override
    public void update(Structure structure) {
        if(!structure.isAlive()){
            this.removeStructure(structure.getLocation());
        }
    }
}
