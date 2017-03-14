package model;

import model.Controllables.Army;
import model.Controllables.Structures.*;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Map.Map;
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
        PlayerID playerID = martyr.getPlayerID();
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

    public void buildUniversity(Army army, int workers) {
        University newStruct = new University();

        if(playerManager.addStructure(army.getPlayerID(), newStruct)){
            map.addStructure(army.getLocation(), newStruct);
            newStruct.addWorker(workers);
            army.removeWorkers(workers);
        }
    }

    public void buildObservationTower(Army army, int workers) {
        ObservationTower newStruct = new ObservationTower();

        if(playerManager.addStructure(army.getPlayerID(), newStruct)){
            map.addStructure(army.getLocation(), newStruct);
            newStruct.addWorker(workers);
            army.removeWorkers(workers);
        }
    }

    public void buildMine(Army army, int workers) {
        Mine newStruct = new Mine();

        if(playerManager.addStructure(army.getPlayerID(), newStruct)){
            map.addStructure(army.getLocation(), newStruct);
            newStruct.addWorker(workers);
            army.removeWorkers(workers);
        }
    }

    public void buildPowerPlant(Army army, int workers) {
        PowerPlant newStruct = new PowerPlant();

        if(playerManager.addStructure(army.getPlayerID(), newStruct)){
            map.addStructure(army.getLocation(), newStruct);
            newStruct.addWorker(workers);
            army.removeWorkers(workers);
        }
    }

    public void buildFarm(Army army, int workers) {
        Farm newStruct = new Farm();

        if(playerManager.addStructure(army.getPlayerID(), newStruct)){
            map.addStructure(army.getLocation(), newStruct);
            newStruct.addWorker(workers);
            army.removeWorkers(workers);
        }
    }

    public void buildFort(Army army, int workers) {
        Fort newStruct = new Fort();

        if(playerManager.addStructure(army.getPlayerID(), newStruct)){
            map.addStructure(army.getLocation(), newStruct);
            newStruct.addWorker(workers);
            army.removeWorkers(workers);
        }
    }


    public void produceRanged(Fort fort){
        Ranged ranged = new Ranged();

        if(playerManager.addUnit(fort.getPlayerID(), ranged)){
            map.addUnit(fort.getLocation(), ranged);
            ranged.setMapDirection(MapDirection.getNorth());
        }
    }

    public void produceMelee(Fort fort){
        Melee melee = new Melee();

        if(playerManager.addUnit(fort.getPlayerID(), melee)){
            map.addUnit(fort.getLocation(), melee);
            melee.setMapDirection(MapDirection.getNorth());
        }
    }

    public void produceExplorer(Capital capital){
        Explorer explorer = new Explorer();

        if(playerManager.addUnit(capital.getPlayerID(), explorer)){
            map.addUnit(capital.getLocation(), explorer);
            explorer.setMapDirection(MapDirection.getNorth());
        }
    }
}
