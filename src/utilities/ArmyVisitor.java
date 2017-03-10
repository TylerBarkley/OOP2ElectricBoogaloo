package utilities;

import model.Controllables.Army;

public interface ArmyVisitor {
	public void visit(Army army);
}
