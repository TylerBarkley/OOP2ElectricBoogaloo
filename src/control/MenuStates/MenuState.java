package control.MenuStates;

import control.Menu;

/**
 * Created by hankerins on 3/5/17.
 */
public interface MenuState {
    abstract void select();
    abstract void cycleModeL(Menu context);
    abstract void cycleModeR(Menu context);
    abstract void cycleTypeL(Menu context);
    abstract void cycleTypeR(Menu context);
    abstract void cycleInstanceL(Menu context);
    abstract void cycleInstanceR(Menu context);
    abstract void cycleInstructionL(Menu context);
    abstract void cycleInstructionR(Menu context);




}
