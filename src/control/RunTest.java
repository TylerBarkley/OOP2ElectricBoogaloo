package control;

import model.Controllables.ControllableCollection;
import model.Controllables.Units.Melee;

/**
 * Created by hankerins on 3/5/17.
 */
public class RunTest {

    public static void main(String args[]){
        ControllableCollection cc = new ControllableCollection();
        cc.getMelees().set(3, new Melee());
        System.out.println("inserted melee unit, melee size = " + cc.getMelees().size());
    }
}
