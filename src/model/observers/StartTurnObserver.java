package model.observers;

import model.TurnManager;

public interface StartTurnObserver {
	public void startUpdate(TurnManager turn);
}
