package model.Controllables.Units;
import utilities.Visitor;

public class Colonist extends Unit {

	public void accept(Visitor visitor){
		visitor.visit(this);
	}

	public Colonist(){};
}
