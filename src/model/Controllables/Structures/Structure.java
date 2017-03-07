package model.Controllables.Structures;
import model.StructureID;
import model.Controllables.Controllable;
import model.observers.DeathObservable;

public abstract class Structure extends DeathObservable implements Controllable {
	private StructureID id;

	public void killMe()
	{
		notifyObservers(id);
	}
	
	public void setID(StructureID id)
	{
		this.id=id;
	}

	public StructureID getID() 
	{
		return id;
	}
}
