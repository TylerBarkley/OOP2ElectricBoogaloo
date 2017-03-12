package model;

/**
 * Created by zrgam_000 on 3/10/2017.
 */
public interface TerrainVisitor {

    //4 commits

    public void visitWaterTerrain();
    public void visitGroundTerrain();
    public void visitMountainTerrain();
}
