package model.observers;

import model.Controllables.Structures.Structure;

public interface StructureResourceObserver {

	public void updateFood(Structure structure, int food);
	public void updateOre(Structure structure, int ore);
	public void updatePower(Structure structure, int power);
}
