package model.observers;

import model.Controllables.Structures.Structure;

public interface StructureResourceObserver {

	public void updateStructureFood(int food);
	public void updateStructureOre(int ore);
	public void updateStructurePower(int power);
}
