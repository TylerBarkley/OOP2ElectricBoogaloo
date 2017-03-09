package utilities;

import model.Controllables.Structures.Structure;

public interface StructureObserver {

	public void update(Structure structure, boolean isDestroyed);
}
