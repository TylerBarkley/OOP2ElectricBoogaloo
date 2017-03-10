package model.Controllables.Units;
import utilities.UnitVisitor;

public class Ranged extends Unit {


	public void accept(UnitVisitor visitor){
		visitor.visit(this);
	}

	public Ranged(){

		this.getMyStats().setHealth(100);
		this.getMyStats().setArmor(5);
		this.getMyStats().setUpkeep(5);
		this.getMyStats().setDefensiveDamage(10);
		this.getMyStats().setInfluenceRadius(2);
		this.getMyStats().setOffensiveDamage(10);
		this.getMyStats().setMovement(2);
		this.setCurrentHealth(100);

	}

	@Override
	public boolean canEscort() {
		return true;
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
