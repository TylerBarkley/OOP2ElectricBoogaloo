import model.Controllables.Command;
import model.Controllables.Structures.Structure;

/**
 * Created by zrgam_000 on 3/15/2017.
 */
public class UnassignCommand implements Command {

    private Structure structure;

    public UnassignCommand(Structure structure){
        this.structure = structure;
    }

    @Override
    public void execute() {
        structure.unassign();
    }
}
