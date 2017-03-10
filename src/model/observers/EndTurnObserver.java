package model.observers;

import model.TurnManager;

public interface EndTurnObserver {
	public void endUpdate(TurnManager turn);
}
