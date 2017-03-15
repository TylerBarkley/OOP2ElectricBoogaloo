package control;

import model.Controllables.Army;
import model.Controllables.ControllableCollection;
import model.Controllables.Structures.*;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.player.Player;
import model.player.PlayerID;

import java.util.*;

/**
 * Created by hankerins on 3/5/17.
 */
public class ControlTerminal {

    public static void main(String args[]){
    	Player p=new Player();
    	p.addArmy(new Army());
    	p.addArmy(new Army());
    	p.addArmy(new Army());
    	
    	//p.addStructure(new Capital());
        p.addStructure(new ObservationTower());
        p.addStructure(new Mine());
        p.addStructure(new PowerPlant());
    	p.addStructure(new Farm());
    	p.addStructure(new Farm());
    	p.addStructure(new Farm());
    	p.addStructure(new Farm());
    	p.addStructure(new Fort());
        p.addStructure(new Fort());
        p.addStructure(new University());
    	
    	p.addUnit(new Colonist());
    	p.addUnit(new Melee());
    	p.addUnit(new Melee());
    	p.addUnit(new Melee());
    	p.addUnit(new Melee());
    	p.addUnit(new Melee());
    	p.addUnit(new Explorer());
    
        Menu menu = new Menu(p.getId());

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
    }
}
