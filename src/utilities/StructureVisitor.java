package utilities;

import model.Controllables.Structures.*;

public interface StructureVisitor {
	public abstract void visit(Structure structure);
}
