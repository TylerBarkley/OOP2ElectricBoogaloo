package model;

import model.Controllables.Units.UnitID;

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
        return 10*technology.getCurrentUnitAdvancements(type,stat);
    }

    @Override
    public void execute() {
        technology.editUnitStats(type, stat);
    }
    @Override
    public String getResearch(){
        String research="";
        switch(type){
            case UnitID.COLONIST_TYPE_ID: research+="Colonist";
                break;
            case UnitID.EXPLORER_TYPE_ID:  research+="Explorer";
                break;
            case UnitID.MELEE_TYPE_ID:research+="Melee";
                break;
            case UnitID.RANGED_TYPE_ID:research+="Ranged";
                break;
        }
        switch(stat){
            case Technology.Upkeep:research+="Upkeep"; break;
            case Technology.Armor:research+="Armor";break;
            case Technology.OffensiveDamage:research+="Offensive Damage"; break;
            case Technology.DefensiveDamage:research+="Defensive Damage"; break;
            case Technology.Movement:research+="Movement";break;
            case Technology.Health:research+="Health"; break;

        }
        return research;
    }
}
