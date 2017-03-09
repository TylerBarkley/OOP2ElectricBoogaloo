package control;

import model.Controllables.Army;
import model.Controllables.ControllableCollection;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Farm;
import model.Controllables.Structures.Fort;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import java.util.*;

/**
 * Created by hankerins on 3/5/17.
 */
public class RunTest {

    public static void main(String args[]){

       //DUMMY DATA TO TEST NAVIGATION
        ControllableCollection cc = new ControllableCollection();
        cc.getColonists().set(0, new Colonist());
        cc.getMelees().set(0, new Melee());
        cc.getMelees().set(4, new Melee());
        cc.getMelees().set(8, new Melee());
        cc.getExpolorers().set(7, new Explorer());

        cc.getCapitals().set(1, new Capital());
        cc.getFarms().set(3, new Farm());
        cc.getFarms().set(4, new Farm());
        cc.getFarms().set(6, new Farm());
        cc.getFarms().set(9, new Farm());
        cc.getForts().set(5, new Fort());

        cc.getArmies().set(4, new Army());
        cc.getArmies().set(1, new Army());
        cc.getArmies().set(2, new Army());



        Menu menu = new Menu(cc);

        menu.printState();

        while(true){
            debugMenu(menu);
        }

    }

    static void debugMenu(Menu menu){
        Scanner in = new Scanner(System.in);
        int input = 0;
        System.out.println("0: Mode Left    1: Mode Right\n" +
                "2: Type Left   3: Type Right\n" +
                "4: Instance Left   5: Instance Right\n" +
                "6: Instruction Left    7: Instruction Right");
        input = in.nextInt();
        switch(input){
            case 0:
                menu.cycleModeL();
                break;
            case 1:
                menu.cycleModeR();
                break;
            case 2:
                menu.cycleTypeL();
                break;
            case 3:
                menu.cycleTypeR();
                break;
            case 4:
                menu.cycleInstanceL();
                break;
            case 5:
                menu.cycleInstanceR();
                break;
            case 6:
                menu.cycleInstructionL();
                break;
            case 7:
                menu.cycleInstructionR();
                break;
        }
        menu.printState();
        in.close();
    }
}
