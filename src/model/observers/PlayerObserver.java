package model.observers;

import model.Controllables.Army;
import model.Controllables.Structures.Structure;
import model.Controllables.Units.Unit;
import model.player.Player;

public interface PlayerObserver {
	public void update(Player player);
	public void update(Player player, Unit unit);
	public void update(Player player, Structure structure);
	public void update(Player player, Army army);
}
