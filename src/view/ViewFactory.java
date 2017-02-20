package view;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Colonist;
import model.Explorer;
import model.Ground;
import model.Location;
import model.Melee;
import model.Mountain;
import model.Ranged;
import model.Water;
import model.Worker;

public class ViewFactory {
	private static ViewFactory factory;
	
	private BufferedImage explorer;
	private BufferedImage colonist;
	private BufferedImage ranged;
	private BufferedImage melee;
	private BufferedImage worker;
	private BufferedImage enemyExplorer;
	private BufferedImage enemyColonist;
	private BufferedImage enemyRanged;
	private BufferedImage enemyMelee;
	private BufferedImage enemyWorker;
	private BufferedImage water;
	private BufferedImage ground;
	private BufferedImage mountain;

	private BufferedImage unknown;
	
	private ViewFactory(){
		try {
			explorer=ImageIO.read(getClass().getResource("/Explorer.png"));
			colonist=ImageIO.read(getClass().getResource("/Colonist.png"));
			ranged=ImageIO.read(getClass().getResource("/Ranged.png"));
			melee=ImageIO.read(getClass().getResource("/Melee.png"));
			worker=ImageIO.read(getClass().getResource("/Worker.png"));
			enemyExplorer=ImageIO.read(getClass().getResource("/EnemyExplorer.png"));
			enemyColonist=ImageIO.read(getClass().getResource("/EnemyColonist.png"));
			enemyRanged=ImageIO.read(getClass().getResource("/EnemyRanged.png"));
			enemyMelee=ImageIO.read(getClass().getResource("/EnemyMelee.png"));
			enemyWorker=ImageIO.read(getClass().getResource("/EnemyWorker.png"));
			water=ImageIO.read(getClass().getResource("/Water.png"));
			ground=ImageIO.read(getClass().getResource("/Ground.png"));
			mountain=ImageIO.read(getClass().getResource("/Mountain.png"));
			unknown=ImageIO.read(getClass().getResource("/Mountain.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public UnitView getView(Explorer unit, Location loc, boolean isOpponent)
	{
		BufferedImage image=isOpponent ? enemyExplorer : explorer;
		return new UnitView(image, loc, unit.getFacingDirection().getAngle());
	}
	
	public UnitView getView(Colonist unit, Location loc, boolean isOpponent)
	{
		BufferedImage image=isOpponent ? enemyColonist : colonist;
		return new UnitView(image, loc, unit.getFacingDirection().getAngle());
	}

	public UnitView getView(Melee unit, Location loc, boolean isOpponent)
	{
		BufferedImage image=isOpponent ? enemyMelee : melee;
		return new UnitView(image, loc, unit.getFacingDirection().getAngle());
	}
	
	public UnitView getView(Ranged unit, Location loc, boolean isOpponent)
	{
		BufferedImage image=isOpponent ? enemyRanged : ranged;
		return new UnitView(image, loc, unit.getFacingDirection().getAngle());
	}

	public UnitView getView(Ranged unit, Location loc, boolean isOpponent, int angle)
	{
		BufferedImage image=isOpponent ? enemyRanged : ranged;
		return new UnitView(image, loc, angle);
	}
	
	public UnitView getView(Worker unit, Location loc, boolean isOpponent)
	{
		BufferedImage image=isOpponent ? enemyWorker : worker;
		return new UnitView(image, loc, unit.getFacingDirection().getAngle());
	}
	
	public TileView getView(Water terrain, Location loc, boolean isOpponent)
	{
		return new TileView(water, loc);
	}
	
	public TileView getView(Ground terrain, Location loc, boolean isOpponent)
	{
		return new TileView(ground, loc);
	}
	
	public TileView getView(Mountain terrain, Location loc, boolean isOpponent)
	{
		return new TileView(mountain, loc);
	}
	
	public View getView(View view, Location loc, boolean isOpponent)
	{
		return new View(view.getImage(), loc, view.getRotation());
	}
	
	public View getView(Object obj, Location loc, boolean isOpponent)
	{
		return new View(unknown, loc);
	}
	
	public View[] getView(Object[] objArr, Location loc, boolean isOpponent)
	{
		int len=objArr.length;		
		View[] views=new View[len];
		
		for(int i=0; i<len; i++)
		{
			views[i]=getView(objArr[i], loc, isOpponent);
		}
		
		return views;
	}
	
	public View getView(Object obj, Location loc)
	{
		return getView(obj, loc, false);
	}
	
	public View[] getView(Object[] objArr, Location loc)
	{
		return getView(objArr, loc, false);
	}
	
	public static ViewFactory getFactory() {
		if(factory ==null){
			factory=new ViewFactory();
		}
		
		return factory;
	}
}
