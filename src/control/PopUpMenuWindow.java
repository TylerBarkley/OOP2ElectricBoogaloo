package control;

import model.Controllables.RallyPoint;
import model.Controllables.Structures.Structure;
import model.Controllables.Units.Unit;

import javax.swing.*;
import java.util.ArrayList;

public class PopUpMenuWindow {
    public static int WorkerMenu(){
        Object[] possibilities = new Object[101];
        for(int i = 0; i <= 100; i++){
            possibilities[i] = ("" + i) ;
        }
        try {
            String result = (String)JOptionPane.showInputDialog(null,
                    "How many workers do you want to assign to this task? ",
                    "Worker Menu", JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);
            int count = Integer.parseInt(result);
            return count;
        } catch (Exception e){
            return 0;
        }
    }
    public static RallyPoint RallyPointMenu(ArrayList<RallyPoint> rallyPoints){
        Object[] possibilities = new Object[rallyPoints.size()];
        for(int i = 0; i < rallyPoints.size(); i++){
            int x = i+1;
            possibilities[i] = ("" + x) ;
        }
        try{
            String result = (String)JOptionPane.showInputDialog(null,
                    "Which Rally Point? ", "Rally Point Menu",
                    JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);

            int count = Integer.parseInt(result);
            return rallyPoints.get(count - 1);
        } catch (Exception e){
            return null;
        }

    }
    public static Unit UnitMenu(ArrayList<Unit> battleGroup){
        Object[] possibilities = new Object[battleGroup.size()];
        for(int i = 0; i < battleGroup.size(); i++){
            int x = i + 1;
            possibilities[i] = (x + ". " + battleGroup.get(i).toString()) ;
        }
        try{
            String result = (String)JOptionPane.showInputDialog(null,
                    "Which Unit? ", "Unit Menu",
                    JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);

            int count = Integer.parseInt(result);
            return battleGroup.get(count - 1);
        } catch (Exception e){
            return null;
        }

    }
    public static Structure StructureMenu(ArrayList<Structure> structures){
        Object[] possibilities = new Object[structures.size()];
        for(int i = 0; i < structures.size(); i++){
            int x = i + 1;
            possibilities[i] = (x + ". " + structures.get(i).toString()) ;
        }
        try{
            String result = (String)JOptionPane.showInputDialog(null,
                    "Which Structure? ", "Structure Menu",
                    JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);

            int count = Integer.parseInt(result);
            return structures.get(count - 1);
        } catch (Exception e){
            return null;
        }

    }

}
