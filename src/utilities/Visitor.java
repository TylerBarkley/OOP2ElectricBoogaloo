package utilities;

import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Map.Tile;
import model.Controllables.Worker;

public interface Visitor {
	public abstract void visit(Colonist unit);
	public abstract void visit(Explorer unit);
	public abstract void visit(Melee unit);
	public abstract void visit(Ranged unit);
	public abstract void visit(Worker unit);
	public abstract void visit(Tile tile);
	// etc...
}
