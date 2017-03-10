package control;

import model.MapDirection;
import model.TurnManager;

public class InputHandler {
	private Menu menu;
	private TurnManager turn;
	
	public InputHandler(Menu menu, TurnManager turn) {
		this.menu = menu;
		this.turn = turn;
	}

	public void moveFocus(MapDirection direction) {
		// TODO Auto-generated method stub
		
	}

	public void cycleMode() {
		menu.cycleInstanceR();
	}

	public void cycleModeReverse() {
		menu.cycleInstanceL();
	}

	public void cycleType() {
		menu.cycleTypeR();
	}

	public void cycleTypeReverse() {
		menu.cycleTypeL();
	}

	public void cycleCommand() {
		menu.cycleInstructionR();
	}

	public void cycleCommandReverse() {
		menu.cycleInstructionL();
	}

	public void cycleInstance() {
		menu.cycleInstanceR();
	}

	public void cycleInstanceReverse() {
		menu.cycleInstanceL();
	}

	public void centerFocus() {
		// TODO Auto-generated method stub
		
	}

	public void endTurn() {
		turn.endTurn();
	}

}
