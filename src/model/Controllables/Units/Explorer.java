package model.Controllables.Units;
import utilities.Visitor;

public class Explorer extends Unit {

	public void accept(Visitor visitor){
		visitor.visit(this);
	}

	public Explorer(){};
}
