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

	@Override
	public void visitWaterTerrain() {
		this.reduceAP(2);
	}

	@Override
	public void visitGroundTerrain() {
		this.reduceAP(1);
	}

	@Override
	public void visitMountainTerrain() {
		this.reduceAP(3);
	}
}
