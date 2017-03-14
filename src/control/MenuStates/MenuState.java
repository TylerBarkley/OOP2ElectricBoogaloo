package control.MenuStates;

import control.Menu;
import model.Controllables.Controllable;

/**
 * Created by hankerins on 3/5/17.
 *
 * Additional states needed:
 * Structure: harvest Tile selection (works same as RP entry, requires worker count)
 * for Capital, must also select which resource to harvest
 * Get rid of structure attack state
 * RP: pickup workers (requires number, rp must be on structure)
 * RP: dropoff workers (requires number, rp must be on structure)
 * RP:pickup/dropoff soldiers at a fort
 * RP:build (requires structure type and worker count)
 * Structure:assign workers to task - maybe just one task + resource harvest
 * Structure:unassign all workers
 * Unit: go to rally point? how?  DON"T IMPLEMENT THIS
 * Structure: produceUnit(Unit unit) options are structure dependent
 * Structure: heal - select tile to heal
 * University: select research
 *
 * Structure order: farm, mine, powerharvest, produce worker, produce explorer, produce ranged, produce melee, research
 *
 *
 *
 *
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

    int getCurrentInstanceNumber();
    void setCurrentInstance(int currentInstance);
    int getCurrentType();
    void setCurrentType(int currentType);
    String typeToString();

	Controllable getCurrentInstance();




}
