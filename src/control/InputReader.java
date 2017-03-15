package control;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.MapDirection;

public class InputReader extends KeyAdapter {
    private UserControls userControls;
	private InputHandler handler;

	public InputReader(InputHandler handler, UserControls userControls) {
		this.handler=handler;
        this.userControls=userControls;
    }
	
    @Override
    public void keyReleased(KeyEvent e) {
    	Control input = new Control(e);
    	
    	if (userControls.getMapUp().equals(input)) {
    		handler.moveFocus(MapDirection.getNorth()); 
        }
        else if (userControls.getMapDown().equals(input)) {
        	handler.moveFocus(MapDirection.getSouth()); 
        }
        else if (userControls.getMapUpRight().equals(input)) {
        	handler.moveFocus(MapDirection.getNorthEast()); 
        }
        else if (userControls.getMapUpLeft().equals(input)) {
        	handler.moveFocus(MapDirection.getNorthWest()); 
        }
        else if (userControls.getMapDownRight().equals(input)) {
        	handler.moveFocus(MapDirection.getSouthEast()); 
        }
        else if (userControls.getMapDownLeft().equals(input)) {
        	handler.moveFocus(MapDirection.getSouthWest()); 
        }
        else if (userControls.getCycleModeUp().equals(input)) {
        	handler.cycleMode(); 
        }
        else if (userControls.getCycleModeDown().equals(input)) {
        	handler.cycleModeReverse(); 
        }
        else if (userControls.getCycleTypeLeft().equals(input)) {
        	handler.cycleType(); 
        }
        else if (userControls.getCycleTypeRight().equals(input)) {
        	handler.cycleTypeReverse(); 
        }
        else if (userControls.getCycleCommandUp().equals(input)) {
        	handler.cycleCommand(); 
        }
        else if (userControls.getCycleCommandDown().equals(input)) {
        	handler.cycleCommandReverse();
        }
        else if (userControls.getCycleInstanceLeft().equals(input)) {
        	handler.cycleInstance(); 
        }
        else if (userControls.getCycleInstanceRight().equals(input)) {
        	handler.cycleInstanceReverse(); 
        }
        else if (userControls.getCenterFocus().equals(input)) {
        	handler.centerFocus(); 
        }
        else if (userControls.getEndTurn().equals(input)) {
        	handler.endTurn(); 
        }
        else if (userControls.getToggleResources().equals(input)) {
        	handler.toggleResources(); 
        }
        else if (userControls.getSelect().equals(input)) {
        	handler.select(); 
        }
    }
}