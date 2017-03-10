package utilities;

import model.Controllables.Units.Unit;

public interface UnitVisitor {
	public abstract void visit(Unit unit);
}
