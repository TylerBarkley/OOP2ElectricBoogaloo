package model.Controllables.Units;
import model.Location;
import model.Map.Tile;
import utilities.UnitVisitor;

public class Explorer extends Unit {

	boolean prospecting = false;

	public void accept(UnitVisitor visitor){
		visitor.visit(this);
	}

	public Explorer(){}

	public Explorer(Location loc){
		super(loc);
	}

	public void setProspecting(){
		if(prospecting){
			prospecting = false;
			this.resetAP();
		}

		else{
			prospecting = true;
			this.resetAP();
		}

	}
	
	@Override
	public boolean canEscort() {
		return false;
	}

	@Override
	public int getMovement() {
		return prospecting ? 1 : this.getMyStats().getMovement();
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
		if(prospecting) {
			tile.prospect();
		}
		tile.getTerrain().visitTerrain(this);
	}
	public String toString(){return "Explorer";}
}
