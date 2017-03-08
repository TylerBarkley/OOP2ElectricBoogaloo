package control.MenuStates;

import control.Menu;

/**
 * Created by hankerins on 3/5/17.
 */
public interface MenuState {
    void select(Menu context);

    void cycleTypeL(Menu context);
    void cycleTypeR(Menu context);
    void cycleInstanceL(Menu context);
    void cycleInstanceR(Menu context);
    void cycleInstructionL(Menu context);
    void cycleInstructionR(Menu context);
    void reset(Menu context);
    void updateControllable(Menu context);

    int getCurrentInstance();
    void setCurrentInstance(int currentInstance);
    int getCurrentType();
    void setCurrentType(int currentType);
    String typeToString();




}
