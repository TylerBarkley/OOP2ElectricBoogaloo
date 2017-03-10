package model.Controllables;

import java.util.ArrayList;

import model.Visitor;
import model.observers.ArmyObserver;
import model.observers.UnitObserver;
import model.observers.WorkerObserver;

public class Worker{

	private ArrayList<WorkerObserver> observers;
	private boolean isAlive;
	//private PID myPID;

	public Worker(){
		observers=new ArrayList<WorkerObserver>();
		isAlive=true;
	}
	
	public void addObserver(WorkerObserver observer)
	{
		observers.add(observer);
		notifyObserver(observer);
	}
	
	public void removeObserver(UnitObserver observer)
	{
		observers.remove(observer);
	}
	
	public void notifyObservers()
	{
		for(WorkerObserver ob: observers)
		{
			ob.update(this);
		}
	}
	
	public void notifyObserver(WorkerObserver observer)
	{
		observer.update(this);
	}
	
	public void upkeep(int food) {
		//TODO just bulking
		//if(food < 1){
			//this.killMe();
		//}

	}

	public void killMe(){
		isAlive=false;
		notifyObservers();
		//TODO kill me
		//TODO me_irl
		//pm.removeWorker(myPID);
	}

	public boolean isAlive(){
		return isAlive;
	}
	
	public void accept(Visitor visitor){
		visitor.visit(this);
	}

}
