package model.Controllables.Units;
import utilities.UnitVisitor;

public class Colonist extends Unit {

	public void accept(UnitVisitor visitor){
		visitor.visit(this);
	}

	public Colonist(){}

	@Override
	public boolean canEscort() {
		return false;
	}

	;
}
