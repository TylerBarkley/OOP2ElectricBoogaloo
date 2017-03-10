package model.Controllables;

import java.util.ArrayList;

import model.Visitor;
import model.observers.ArmyObserver;
import model.observers.UnitObserver;
import model.observers.WorkerObserver;

public class Worker{

	private ArrayList<WorkerObserver> observers;

	//private PID myPID;

	public Worker(){
		observers=new ArrayList<WorkerObserver>();
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
		//TODO kill me
		//TODO me_irl
		//pm.removeWorker(myPID);
	}

	public void accept(Visitor visitor){
		visitor.visit(this);
	}

}
