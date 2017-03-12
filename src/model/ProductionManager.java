package model;

import model.Controllables.Structures.Capital;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Melee;
import model.Map.Map;
import model.player.Player;
import model.player.PlayerID;
import model.player.PlayerManager;

/**
 * Created by zrgam_000 on 3/12/2017.
 */
public class ProductionManager {
    private static ProductionManager productionManager;
    private Map map;
    private PlayerManager playerManager;

    private ProductionManager(){
        map = Map.getInstance();
        playerManager = PlayerManager.getInstance();
    }

    public static void reset(){
        productionManager = null;
    }

    public static ProductionManager getInstance(){
        if(productionManager == null){
            productionManager = new ProductionManager();
        }

        return productionManager;
    }

    public void martyrdom(Colonist martyr){
        PlayerID playerID = martyr.getPid();
        Location location = martyr.getLocation();

        Capital newCap = new Capital();
        Melee melee1 = new Melee();
        Melee melee2 = new Melee();

        melee1.setMapDirection(MapDirection.getNorth());
        melee2.setMapDirection(MapDirection.getNorth());

        if(playerManager.addUnit(playerID, melee1)){
            map.addUnit(location, melee1);
        }
        if(playerManager.addUnit(playerID, melee2)){
            map.addUnit(location, melee2);
        }
        if(playerManager.addStructure(playerID, newCap)){
            map.addStructure(location, newCap);

            //Need to add Workers
            playerManager.addWorker(playerID, 5);
            newCap.setBeingBuilt(false);
            newCap.addWorker(5);
        }

        martyr.killMe();
    }

    public void buildBase(){

    }
}
