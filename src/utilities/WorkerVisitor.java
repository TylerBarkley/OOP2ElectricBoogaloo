package utilities;

import model.Controllables.Worker;

public interface WorkerVisitor {

	public abstract void visit(Worker worker);
		
	
}
