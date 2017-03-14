package control;

import model.MapDirection;
import model.TurnManager;
import model.Controllables.Controllable;
import view.AreaViewport;

public class InputHandler {
	private Menu menu;
	private TurnManager turn;
	private AreaViewport areaView;
	
	public InputHandler(Menu menu, TurnManager turn, AreaViewport areaView) {
		this.menu = menu;
		this.turn = turn;
		this.areaView=areaView;
	}

	public void moveFocus(MapDirection direction) {
		menu.setFocus(direction);
	}

	public void cycleMode() {
		menu.cycleModeR();
	}

	public void cycleModeReverse() {
		menu.cycleModeL();
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
		Controllable c=menu.getCurrentInstance();
		
		if(c!=null && c.getLocation()!=null)
		{
			menu.setFocus(c.getLocation());
		}
	}

	public void endTurn() {
		turn.endTurn();
	}

	public void toggleResources() {
		areaView.toggleResourceView();
	}

	public void select() {
		menu.select();
	}
}
