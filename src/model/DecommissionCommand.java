package model;

import model.Controllables.Command;

/**
 * Created by hankerins on 3/13/17.
 */
public class DecommissionCommand implements Command {
    @Override
    public void execute() {

    }
    public String toString(){
        return "Decommission - ";
    }
}
