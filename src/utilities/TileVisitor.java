package utilities;

import model.Map.Tile;

public interface TileVisitor {

	public abstract void visit(Tile tile);
}
