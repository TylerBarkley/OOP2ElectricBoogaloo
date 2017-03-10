package model.Controllables.Structures;

import java.util.ArrayList;

import model.Location;
import model.MapDirection;
import model.Controllables.BasicStats;
import model.Controllables.Controllable;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Stats.UnitStats;
import model.observers.StructureObserver;
import model.player.PlayerID;
import utilities.StructureVisitor;

public abstract class Structure implements Controllable {
    private int currentHealth;
    private StructureStats myStats;
    private StructureID id;
    private boolean isAlive;
    private ArrayList<StructureObserver> observers;
    private MapDirection md;
    private Location location;
    private int numTotalWorkers;
    private WorkerManager workerManager;
    private Boolean beingBuilt;

    private int energyResourceLevel;
    private int metalResourceLevel;
    private int nutrientResourceLevel;

    public Structure() {
        myStats = new StructureStats();
        md = MapDirection.getNorth();
        isAlive = true;
        observers = new ArrayList<StructureObserver>();
        workerManager = new WorkerManager();
    }

    public Structure(Location loc) {
        myStats = new StructureStats();
        location = loc;
        md = MapDirection.getNorth();
        isAlive = true;
        observers = new ArrayList<StructureObserver>();
    }

    public void addObserver(StructureObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(StructureObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (StructureObserver ob : observers) {
            ob.update(this);
        }
    }

    public void accept(StructureVisitor visitor) {
        visitor.visit(this);
    }

    public void killMe() {
        isAlive = false;
        notifyObservers();
        //TODO KILLING SELF
        //REMOVING SELF FROM PLAYER REGISTRY AND OCCUPANCY MANAGER
        //POSSIBLY USING PLAYER MANAGER
    }

    public void setID(StructureID id) {
        this.id = id;

        notifyObservers();
    }

    public StructureID getID() {
        return id;
    }

    public void damageMe(int intensity) {
        currentHealth -= (intensity - myStats.getArmor());
        if (currentHealth <= 0) {
            this.killMe();
        }

        notifyObservers();
    }

    public void healMe(int intensity) {
        currentHealth += intensity;
        if (currentHealth > myStats.getHealth()) {
            currentHealth = myStats.getHealth();
        }

        notifyObservers();
    }

    public void setMyStats(StructureStats myStats) {
        this.myStats = myStats;

        notifyObservers();
    }

    public StructureStats getMyStats() {
        return this.myStats;
    }


    public PlayerID getPid() {
        return id.getPlayerID();
    }

    public Location getLocation() {
        return location;
    }

    public MapDirection getMapDirection() {
        return md;
    }

    public void setLocation(Location loc) {
        this.location = loc;

        notifyObservers();
    }

    public void setMapDirection(MapDirection md) {
        this.md = md;

        notifyObservers();
    }

    public void malnourish() {
        // TODO This is called if there aren't enough resources for upkeep
        if(energyResourceLevel<0){
            damageMe(1);
        }
        if(metalResourceLevel<0){
            damageMe(1);
        }
        if(nutrientResourceLevel<0){
            damageMe(1);
        }

    }

    public int getUpkeep() {
        return myStats.getUpkeep();
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getNumTotalOfWorkers() {
        return numTotalWorkers;
    }

    public void setNumTotalOfWorkers(int numTotalOfWorkers) {
        this.numTotalWorkers = numTotalOfWorkers;
    }

    public WorkerManager getWorkerManager() {
        return workerManager;
    }

    public void setWorkerManager(WorkerManager workerManager) {
        this.workerManager = workerManager;
    }

    public Boolean getBeingBuilt() {
        return beingBuilt;
    }

    public void setBeingBuilt(Boolean beingBuilt) {
        this.beingBuilt = beingBuilt;
    }

    public void distribute(){
        energyResourceLevel-=getUpkeep();
        metalResourceLevel-=getUpkeep();
        nutrientResourceLevel-=getUpkeep();
    }
    public int getEnergyResourceLevel() {
        return energyResourceLevel;
    }

    public void setEnergyResourceLevel(int energyResourceLevel) {
        this.energyResourceLevel = energyResourceLevel;
    }

    public int getMetalResourceLevel() {
        return metalResourceLevel;
    }

    public void setMetalResourceLevel(int metalResourceLevel) {
        this.metalResourceLevel = metalResourceLevel;
    }

    public int getNutrientResourceLevel() {
        return nutrientResourceLevel;
    }

    public void setNutrientResourceLevel(int nutrientResourceLevel) {
        this.nutrientResourceLevel = nutrientResourceLevel;
    }
    public void incrementNutrientResourceLevel(int increment){
        nutrientResourceLevel+=increment;
    }
    public void incrementEnergyResourceLevel(int increment){
        energyResourceLevel+=increment;
    }
    public void incrementMetalResourceLevel(int increment){
        metalResourceLevel+=increment;
    }
}
