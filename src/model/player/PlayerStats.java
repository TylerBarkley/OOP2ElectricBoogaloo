package model.player;

import com.sun.org.glassfish.external.statistics.Stats;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Stats.WorkerStats;
import model.Controllables.Structures.StructureID;
import model.Controllables.Units.Unit;
import model.Controllables.Units.UnitID;

import java.util.ArrayList;

/**
 * Created by zrgam_000 on 3/11/2017.
 */
public class PlayerStats {

    ArrayList<UnitStats> myUnitStats;

    ArrayList<StructureStats> myStructureStats;

    WorkerStats workerStats = new WorkerStats();

    public PlayerStats(){
        myUnitStats = new ArrayList<UnitStats>();

        for(int i = 0; i < 5; i++){
            myUnitStats.add(null);
        }

        myStructureStats = new ArrayList<StructureStats>();

        for(int i = 0; i < 8; i++){
            myStructureStats.add(null);
        }

        //Colonist
        UnitStats unitStats = new UnitStats();
        unitStats.setArmor(5);
        unitStats.setDefensiveDamage(2);
        unitStats.setHealth(100);
        unitStats.setOffensiveDamage(5);
        unitStats.setInfluenceRadius(1);
        unitStats.setUpkeep(5);
        unitStats.setMovement(2);

        myUnitStats.set(UnitID.COLONIST_TYPE_ID, unitStats);

        //Explorer
        unitStats = new UnitStats();
        unitStats.setArmor(5);
        unitStats.setDefensiveDamage(2);
        unitStats.setHealth(100);
        unitStats.setOffensiveDamage(10);
        unitStats.setInfluenceRadius(2);
        unitStats.setUpkeep(5);
        unitStats.setMovement(3);

        myUnitStats.set(UnitID.EXPLORER_TYPE_ID, unitStats);

        //Melee
        unitStats = new UnitStats();
        unitStats.setArmor(5);
        unitStats.setDefensiveDamage(20);
        unitStats.setHealth(100);
        unitStats.setOffensiveDamage(20);
        unitStats.setInfluenceRadius(1);
        unitStats.setUpkeep(5);
        unitStats.setMovement(2);

        myUnitStats.set(UnitID.MELEE_TYPE_ID, unitStats);

        //Ranged
        unitStats = new UnitStats();
        unitStats.setArmor(5);
        unitStats.setDefensiveDamage(15);
        unitStats.setHealth(100);
        unitStats.setOffensiveDamage(15);
        unitStats.setInfluenceRadius(2);
        unitStats.setUpkeep(5);
        unitStats.setMovement(2);

        myUnitStats.set(UnitID.RANGED_TYPE_ID, unitStats);


        //Capital
        StructureStats structureStats = new StructureStats();
        structureStats.setHealth(100);
        structureStats.setArmor(5);
        structureStats.setUpkeep(20);
        structureStats.setDefensiveDamage(5);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(10);
        structureStats.setProductionRate(5);

        myStructureStats.set(StructureID.CAPITAL_TYPE_ID, structureStats);

        //Farm
        structureStats = new StructureStats();
        structureStats.setHealth(100);
        structureStats.setArmor(5);
        structureStats.setUpkeep(20);
        structureStats.setDefensiveDamage(5);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(10);
        structureStats.setProductionRate(5);

        myStructureStats.set(StructureID.FARM_TYPE_ID, structureStats);

        //Fort
        structureStats = new StructureStats();
        structureStats.setHealth(100);
        structureStats.setArmor(5);
        structureStats.setUpkeep(20);
        structureStats.setDefensiveDamage(5);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(10);
        structureStats.setProductionRate(5);

        myStructureStats.set(StructureID.FORT_TYPE_ID, structureStats);

        //Mine
        structureStats = new StructureStats();
        structureStats.setHealth(100);
        structureStats.setArmor(5);
        structureStats.setUpkeep(20);
        structureStats.setDefensiveDamage(5);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(10);
        structureStats.setProductionRate(5);

        myStructureStats.set(StructureID.MINE_TYPE_ID, structureStats);

        //ObservationTower
        structureStats = new StructureStats();
        structureStats.setHealth(100);
        structureStats.setArmor(5);
        structureStats.setUpkeep(20);
        structureStats.setDefensiveDamage(5);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(10);
        structureStats.setProductionRate(5);

        myStructureStats.set(StructureID.OBSERVATIONTOWER_TYPE_ID, structureStats);

        //PowerPlant
        structureStats = new StructureStats();
        structureStats.setHealth(100);
        structureStats.setArmor(5);
        structureStats.setUpkeep(20);
        structureStats.setDefensiveDamage(5);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(10);
        structureStats.setProductionRate(5);

        myStructureStats.set(StructureID.POWERPLANT_TYPE_ID, structureStats);

        //University
        structureStats = new StructureStats();
        structureStats.setHealth(100);
        structureStats.setArmor(5);
        structureStats.setUpkeep(20);
        structureStats.setDefensiveDamage(5);
        structureStats.setInfluenceRadius(1);
        structureStats.setOffensiveDamage(10);
        structureStats.setProductionRate(5);

        myStructureStats.set(StructureID.UNIVERSITY_TYPE_ID, structureStats);

    }

    public UnitStats getExplorerStats(){
        return myUnitStats.get(UnitID.EXPLORER_TYPE_ID);
    }

    public UnitStats getColonistStats(){
        return myUnitStats.get(UnitID.COLONIST_TYPE_ID);
    }

    public UnitStats getMeleeStats(){
        return myUnitStats.get(UnitID.MELEE_TYPE_ID);
    }

    public UnitStats getRangedStats(){
        return myUnitStats.get(UnitID.RANGED_TYPE_ID);
    }

    public StructureStats getCapitalStats(){ return myStructureStats.get(StructureID.CAPITAL_TYPE_ID);}

    public StructureStats getFarmStats(){ return myStructureStats.get(StructureID.FARM_TYPE_ID);}

    public StructureStats getFortStats(){ return myStructureStats.get(StructureID.FORT_TYPE_ID);}

    public StructureStats getMineStats(){ return myStructureStats.get(StructureID.MINE_TYPE_ID);}

    public StructureStats getObservationTowerStats(){ return myStructureStats.get(StructureID.OBSERVATIONTOWER_TYPE_ID);}

    public StructureStats getPowerPlantStats(){ return myStructureStats.get(StructureID.POWERPLANT_TYPE_ID);}

    public StructureStats getUniversityStats(){ return myStructureStats.get(StructureID.UNIVERSITY_TYPE_ID);}

    public WorkerStats getWorkerStats(){
        return workerStats;
    }
}
