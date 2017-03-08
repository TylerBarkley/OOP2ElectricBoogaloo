package control;

import model.Controllables.ControllableCollection;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;

/**
 * Created by hankerins on 3/5/17.
 */
public class RunTest {

    public static void main(String args[]){
        ControllableCollection cc = new ControllableCollection();
        cc.getColonists().set(0, new Colonist());
        cc.getMelees().set(0, new Melee());
        cc.getMelees().set(4, new Melee());
        cc.getMelees().set(8, new Melee());
        cc.getExpolorers().set(7, new Explorer());

        Menu menu = new Menu(cc);

    }

    void debugMenu(){
        String input = "0";
        System.out.println("0: Mode Left    1: Mode Right\n" +
                "2: Type Left   3: Type Right\n" +
                "4: Instance Left   5: Instance Right\n" +
                "6: Instruction Left    7: Instruction Right");
        input = System.console().readLine();
        switch(input){
            case "0":
        }

    }
}
