package model;


import model.Controllables.Structures.StructureID;

/**
 * Created by zrgam_000 on 3/14/2017.
 */
public class StructureResearchCommand implements ResearchCommand {

    Technology technology;
    int type;
    int stat;

    public StructureResearchCommand(Technology technology, int type, int stat){
        this.technology = technology;
        this.type = type;
        this.stat = stat;
    }

    @Override
    public int getCost() {
        return 10*technology.getCurrentStructureAdvancements(type,stat);
    }

    @Override
    public void execute() {
        technology.editStructureStats(type, stat);
    }
    @Override
    public String getResearch(){
        String research="";
        switch(type){
            case StructureID.CAPITAL_TYPE_ID: research+="Capital";break;
            case StructureID.FARM_TYPE_ID:  research+="Farm";break;
            case StructureID.FORT_TYPE_ID:research+="Fort";break;
            case StructureID.MINE_TYPE_ID:research+="Mine";break;
            case StructureID.OBSERVATIONTOWER_TYPE_ID:research+="Observation Tower"; break;
            case StructureID.POWERPLANT_TYPE_ID:research+="Power Plant";break;
            case StructureID.UNIVERSITY_TYPE_ID:research+="University";break;
        }
        switch(stat){
            case Technology.Upkeep:research+="Upkeep"; break;
            case Technology.Armor:research+="Armor";break;
            case Technology.OffensiveDamage:research+="Offensive Damage"; break;
            case Technology.DefensiveDamage:research+="Defensive Damage"; break;
            case Technology.ProductionRate:research+="Production Rate";break;
            case Technology.Health:research+="Health"; break;

        }
        return research;
    }
}
