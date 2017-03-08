package model.Controllables;

import model.Controllables.Stats.WorkerStats;
import utilities.Visitor;

public class Worker{

	WorkerStats myStats;

	public void accept(Visitor visitor){
		visitor.visit(this);
	}
}
