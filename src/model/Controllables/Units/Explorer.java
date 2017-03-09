package model.Controllables.Units;
import utilities.UnitVisitor;

public class Explorer extends Unit {

	public void accept(UnitVisitor visitor){
		visitor.visit(this);
	}

	public Explorer(){}

	@Override
	public boolean canEscort() {
		return false;
	}

	;
}
