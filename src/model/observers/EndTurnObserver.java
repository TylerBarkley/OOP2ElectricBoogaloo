package model.observers;

import model.TurnManager;

public interface EndTurnObserver {
	public void update(TurnManager turn);
}
