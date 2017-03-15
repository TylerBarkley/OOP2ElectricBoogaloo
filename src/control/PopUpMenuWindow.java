package control;

import model.Controllables.RallyPoint;
import model.Controllables.Structures.Structure;
import model.Controllables.Units.Unit;
import model.Technology;

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

            int count = Character.getNumericValue(result.charAt(0));
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
            System.out.println("unit is " + result);

            int count = Character.getNumericValue(result.charAt(0));
            System.out.println("count is" + count);
            System.out.println("Unit menu selects: " + battleGroup.get((count-1)).toString());
            return battleGroup.get(count - 1);
        } catch (Exception e){
            System.out.println("Unit PopUp Menu exception");
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

            int count = Character.getNumericValue(result.charAt(0));
            return structures.get(count - 1);
        } catch (Exception e){
            return null;
        }

    }
    public static int ResearchMainMenu(){
        Object[] possibilities = {"1. Worker", "2. Unit", "3. Structure"};

        try{
            String result = (String)JOptionPane.showInputDialog(null,
                    "Which Research Category? ", "Research Main Menu",
                    JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);

            int count = Character.getNumericValue(result.charAt(0));
            return count - 1;
        } catch (Exception e){
            return -1;
        }
    }
    public static int WorkerResearchMainMenu(){
        Object[] possibilities = {"1. Food Production", "2. Ore Production", "3. Energy Production",
            "4. Technology Production", "5. Soldier Training", "6. Breeding", "7. Explorer Training",
        "8. Building Rate", "9. Worker Density"};

        try{
            String result = (String)JOptionPane.showInputDialog(null,
                    "Which Worker Research Category? ", "Worker Research Main Menu",
                    JOptionPane.PLAIN_MESSAGE, null, possibilities, 1);

            int count = Character.getNumericValue(result.charAt(0));
            return count +7;
        } catch (Exception e){
            return -1;
        }
    }

}
