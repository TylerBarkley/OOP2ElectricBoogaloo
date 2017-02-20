package utilities;

import model.Colonist;
import model.Explorer;
import model.Melee;
import model.Ranged;
import model.Tile;
import model.Worker;

public interface Visitor {
	public abstract void visit(Colonist unit);
	public abstract void visit(Explorer unit);
	public abstract void visit(Melee unit);
	public abstract void visit(Ranged unit);
	public abstract void visit(Worker unit);
	public abstract void visit(Tile tile);
	// etc...
}
