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
    		System.out.println("Map Up");
        }
        else if (userControls.getMapDown().equals(input)) {
        	handler.moveFocus(MapDirection.getSouth());
    		System.out.println("Map Down");
        }
        else if (userControls.getMapUpRight().equals(input)) {
        	handler.moveFocus(MapDirection.getNorthEast());
    		System.out.println("Map Up Right");
        }
        else if (userControls.getMapUpLeft().equals(input)) {
        	handler.moveFocus(MapDirection.getNorthWest());
    		System.out.println("Map Up Left");
        }
        else if (userControls.getMapDownRight().equals(input)) {
        	handler.moveFocus(MapDirection.getSouthEast());
    		System.out.println("Map Down Right");
        }
        else if (userControls.getMapDownLeft().equals(input)) {
        	handler.moveFocus(MapDirection.getSouthWest());
    		System.out.println("Map Down Left");
        }
        else if (userControls.getCycleModeUp().equals(input)) {
        	handler.cycleMode();
    		System.out.println("Cycle Mode Up");
        }
        else if (userControls.getCycleModeDown().equals(input)) {
        	handler.cycleModeReverse();
        	System.out.println("Cycle Mode Down");
        }
        else if (userControls.getCycleTypeLeft().equals(input)) {
        	handler.cycleType();
        	System.out.println("Cycle Type Left");
        }
        else if (userControls.getCycleTypeRight().equals(input)) {
        	handler.cycleTypeReverse();
        	System.out.println("Cycle Type Right");
        }
        else if (userControls.getCycleCommandUp().equals(input)) {
        	handler.cycleCommand();
        	System.out.println("Cycle Command Up");
        }
        else if (userControls.getCycleCommandDown().equals(input)) {
        	handler.cycleCommandReverse();
        	System.out.println("Cycle Command Down");
        }
        else if (userControls.getCycleInstanceLeft().equals(input)) {
        	handler.cycleInstance();
        	System.out.println("Cycle Instance Left");
        }
        else if (userControls.getCycleInstanceRight().equals(input)) {
        	handler.cycleInstanceReverse();
        	System.out.println("Cycle Instance Right");
        }
        else if (userControls.getCenterFocus().equals(input)) {
        	handler.centerFocus();
        	System.out.println("Center Focus");
        }
        else if (userControls.getEndTurn().equals(input)) {
        	handler.endTurn();
        	System.out.println("End Turn");
        }
        else if (userControls.getToggleResources().equals(input)) {
        	handler.toggleResources();
        	System.out.println("Toggle Resources");
        }
        else if (userControls.getSelect().equals(input)) {
        	handler.select();
        	System.out.println("Select");
        }
    }
}