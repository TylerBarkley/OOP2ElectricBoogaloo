package utilities;

import model.Controllables.Units.Unit;
import model.Map.Tile;
import model.player.PlayerID;
import model.ID;
import model.Controllables.Worker;
import model.Controllables.Structures.Structure;

public class MyVisitor implements UnitVisitor,TileVisitor,WorkerVisitor,StructureVisitor {

	private PlayerID playerID;

	public MyVisitor(PlayerID playerID){
		this.playerID=playerID;
	}
	
	@Override
	public void visit(Unit unit) {
		ID id=unit.getID();
	}

	@Override
	public void visit(Worker unit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Tile tile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Structure structure) {
		// TODO Auto-generated method stub
		
	}

}
