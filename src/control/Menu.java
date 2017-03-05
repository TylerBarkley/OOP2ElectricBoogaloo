package control;

import control.MenuStates.MenuState;
import model.Controllables.ControllableCollection;
import model.Player;

/**
 * Created by hankerins on 3/5/17.
 */
public class Menu {
    MenuState menuState;
    ControllableCollection controllableCollection;
    ControllableCollection currentControllable;

    Menu(ControllableCollection controllableCollection){
        this.controllableCollection = controllableCollection;
    }

    void setState(MenuState menuState){
        this.menuState = menuState;
    }

    void cycleModeL(){

    }
    void cycleModeR(){

    }
    void cycleTypeL(){

    }
    void cycleTypeR(){

    }
    void cycleInstanceL(){

    }
    void cycleInstanceR(){

    }
    void cycleInstructionL(){

    }
    void cycleInstructionR(){

    }




}
