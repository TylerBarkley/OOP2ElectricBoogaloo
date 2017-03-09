package utilities;

import model.Controllables.Units.Unit;

public interface UnitObserver {

	public void update(Unit unit, boolean isAlive);
}
