package utilities;

import model.Controllables.RallyPoint;

public interface RPVisitor {
	public abstract void visit(RallyPoint rp);
}
