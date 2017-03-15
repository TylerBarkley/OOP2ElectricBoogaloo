package model;

import model.Map.Tile;

/**
 * Created by zrgam_000 on 3/14/2017.
 */
public interface TileVisitor {
    public void visit(Tile tile);
}
