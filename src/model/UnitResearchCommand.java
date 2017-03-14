package model;

import model.Controllables.Command;

/**
 * Created by zrgam_000 on 3/14/2017.
 */
public class UnitResearchCommand implements ResearchCommand {

    Technology technology;
    int type;
    int stat;

    public UnitResearchCommand(Technology technology, int type, int stat){
        this.technology = technology;
        this.type = type;
        this.stat = stat;
    }

    @Override
    public int getCost() {
        return 10;
    }

    @Override
    public void execute() {
        technology.editUnitStats(type, stat);
    }
}