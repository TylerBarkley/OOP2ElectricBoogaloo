package utilities;

import model.Map.Map;

public interface MapVisitor {

	public abstract void visit(Map map);
}
