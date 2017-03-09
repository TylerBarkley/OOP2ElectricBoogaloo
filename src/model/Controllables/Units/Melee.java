package model.Controllables.Units;
import utilities.UnitVisitor;

public class Melee extends Unit {

	public void accept(UnitVisitor visitor){
		visitor.visit(this);
	}

	public Melee(){}

	@Override
	public boolean canEscort() {
		return true;
	}

	;
}
