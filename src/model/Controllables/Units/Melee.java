package model.Controllables.Units;
import model.Map.Tile;
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

	@Override
	public int getMovement() {
		return this.getMyStats().getMovement();
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

	@Override
	public void visit(Tile tile) {
		tile.getTerrain().visitTerrain(this);
	}
}
