package control;

import model.Controllables.RallyPoint;
import model.Controllables.Structures.Structure;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by hankerins on 3/14/17.
 */
public class PopUpMenuWindow {
    public static int WorkerMenu(){
        Object[] possibilities = new Object[100];
        for(int i = 0; i < 100; i++){
            int x = i+1;
            possibilities[i] = ("" + x) ;
        }
        String result = (String)JOptionPane.showInputDialog(null,
                "How many workers do you want to assign to this task? ",
                "Worker Menu", JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);
        int count = Integer.parseInt(result);
        return count;
    }
    public static RallyPoint RallyPointMenu(ArrayList<RallyPoint> rallyPoints){
        Object[] possibilities = new Object[rallyPoints.size()];
        for(int i = 0; i < rallyPoints.size(); i++){
            int x = i+1;
            possibilities[i] = ("" + x) ;
        }
        String result = (String)JOptionPane.showInputDialog(null,
                "Which Rally Point? ", "Rally Point Menu",
                JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);

        int count = Integer.parseInt(result);
        return rallyPoints.get(count - 1);
    }

}
