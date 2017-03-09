package control;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InputReader extends KeyAdapter {
    private UserControls userControls;

	public InputReader(UserControls userControls) {
        this.userControls=userControls;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	Control input = new Control(e);
    	
    	if (userControls.getMapUp().equals(input)) {
    		System.out.println("Map Up");
        }
        else if (userControls.getMapDown().equals(input)) {
    		System.out.println("Map Down");
        }
        else if (userControls.getMapUpRight().equals(input)) {
    		System.out.println("Map Up Right");
        }
        else if (userControls.getMapUpLeft().equals(input)) {
    		System.out.println("Map Up Left");
        }
        else if (userControls.getMapDownRight().equals(input)) {
    		System.out.println("Map Down Right");
        }
        else if (userControls.getMapDownLeft().equals(input)) {
    		System.out.println("Map Down Left");
        }
        else if (userControls.getCycleModeUp().equals(input)) {
    		System.out.println("Cycle Mode Up");
        }
        else if (userControls.getCycleModeDown().equals(input)) {
        	System.out.println("Cycle Mode Down");
        }
        else if (userControls.getCycleTypeLeft().equals(input)) {
        	System.out.println("Cycle Type Left");
        }
        else if (userControls.getCycleTypeRight().equals(input)) {
        	System.out.println("Cycle Type Right");
        }
        else if (userControls.getCycleCommandUp().equals(input)) {
        	System.out.println("Cycle Command Up");
        }
        else if (userControls.getCycleCommandDown().equals(input)) {
        	System.out.println("Cycle Command Down");
        }
        else if (userControls.getCycleInstanceLeft().equals(input)) {
        	System.out.println("Cycle Instance Left");
        }
        else if (userControls.getCycleInstanceRight().equals(input)) {
        	System.out.println("Cycle Instance Right");
        }
        else if (userControls.getCenterFocus().equals(input)) {
        	System.out.println("Center Focus");
        }
        else if (userControls.getEndTurn().equals(input)) {
        	System.out.println("End Turn");
        }
    }
}