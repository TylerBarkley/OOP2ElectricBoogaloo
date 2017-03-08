package utilities;

import model.Controllables.Structures.*;

public interface StructureVisitor {

	public abstract void visit(Capital capital);
	public abstract void visit(Farm farm);
	public abstract void visit(Fort fort);
	public abstract void visit(Mine mine);
	public abstract void visit(ObservationTower tower);
	public abstract void visit(PowerPlant powerPlant);
	public abstract void visit(University university);
}
