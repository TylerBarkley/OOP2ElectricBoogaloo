package view;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Location;

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

	private BufferedImage capital;
	private BufferedImage university;
	private BufferedImage mine;
	private BufferedImage factoryStructure;
	private BufferedImage farm;

	private BufferedImage enemyCapital;
	private BufferedImage enemyUniversity;
	private BufferedImage enemyMine;
	private BufferedImage enemyFactory;
	private BufferedImage enemyFarm;

	private BufferedImage water;
	private BufferedImage ground;
	private BufferedImage mountain;

	private BufferedImage energy;
	private BufferedImage food;
	private BufferedImage ore;

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

			capital=ImageIO.read(getClass().getResource("/Capital.png"));
			university=ImageIO.read(getClass().getResource("/University.png"));
			mine=ImageIO.read(getClass().getResource("/Mine.png"));
			factoryStructure=ImageIO.read(getClass().getResource("/Factory.png"));
			farm=ImageIO.read(getClass().getResource("/Farm.png"));

			enemyCapital=ImageIO.read(getClass().getResource("/EnemyCapital.png"));
			enemyUniversity=ImageIO.read(getClass().getResource("/EnemyUniversity.png"));
			enemyMine=ImageIO.read(getClass().getResource("/EnemyMine.png"));
			enemyFactory=ImageIO.read(getClass().getResource("/EnemyFactory.png"));
			enemyFarm=ImageIO.read(getClass().getResource("/EnemyFarm.png"));

			water=ImageIO.read(getClass().getResource("/Water.png"));
			ground=ImageIO.read(getClass().getResource("/Ground.png"));
			mountain=ImageIO.read(getClass().getResource("/Mountain.png"));
			
			energy=ImageIO.read(getClass().getResource("/Energy.png"));
			food=ImageIO.read(getClass().getResource("/Food.png"));
			ore=ImageIO.read(getClass().getResource("/Ore.png"));

			unknown=ImageIO.read(getClass().getResource("/Unknown.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public View getView(String id, Location loc, int rotation)
	{
		switch(id)
		{
		case "Water":
		case "EnemyWater":
			return new TileView(water, loc, rotation);
		case "Ground":
		case "EnemyGround":
			return new TileView(ground, loc, rotation);
		case "Mountain":
		case "EnemyMountain":
			return new TileView(mountain, loc, rotation);		
		case "UnknownTerrain":
			return new TileView(unknown, loc, rotation);

		case "Colonist":
			return new UnitView(colonist, loc, rotation);
		case "Explorer":
			return new UnitView(explorer, loc, rotation);			
		case "Melee":
			return new UnitView(melee, loc, rotation);
		case "Ranged":
			return new UnitView(ranged, loc, rotation);
		case "Worker":
			return new UnitView(worker, loc, rotation);		
			
		case "EnemyColonist":
			return new UnitView(enemyColonist, loc, rotation);
		case "EnemyExplorer":
			return new UnitView(enemyExplorer, loc, rotation);
		case "EnemyMelee":
			return new UnitView(enemyMelee, loc, rotation);
		case "EnemyRanged":
			return new UnitView(enemyRanged, loc, rotation);
		case "EnemyWorker":
			return new UnitView(enemyWorker, loc, rotation);

		case "Capital":
			return new StructureView(capital, loc, rotation);
		case "University":
			return new StructureView(university, loc, rotation);
		case "Mine":
			return new StructureView(mine, loc, rotation);
		case "Factory":
			return new StructureView(factoryStructure, loc, rotation);
		case "Farm":
			return new StructureView(farm, loc, rotation);

		case "EnemyCapital":
			return new StructureView(enemyCapital, loc, rotation);
		case "EnemyUniversity":
			return new StructureView(enemyUniversity, loc, rotation);
		case "EnemyMine":
			return new StructureView(enemyMine, loc, rotation);
		case "EnemyFactory":
			return new StructureView(enemyFactory, loc, rotation);
		case "EnemyFarm":
			return new StructureView(enemyFarm, loc, rotation);
			
		default:
			return new ImageView(unknown, loc, rotation);
		}
	}

	public CompositeView getCopositeResourceView(Location loc, int oreQuantity, int energyQuantity, int foodQuantity)
	{
		CompositeView view=new CompositeView();
		view.add(new EnergyView(energy, loc, oreQuantity));
		view.add(new FoodView(food, loc, foodQuantity));
		view.add(new OreView(ore, loc, energyQuantity));
		return view;
	}
	
	public View getView(String id, Location loc)
	{
		return getView(id, loc, 0);
	}

	public View getView(String id, Location loc, int rotation, boolean isOpponent)
	{
		if(isOpponent && !id.startsWith("Enemy"))
		{
			id="Enemy"+id;
		}
		
		return getView(id, loc, rotation);
	}
	
	public View getView(String id, Location loc, boolean isOpponent)
	{
		return getView(id, loc, 0, isOpponent);
	}
	
	public static ViewFactory getFactory() {
		if(factory ==null){
			factory=new ViewFactory();
		}

		return factory;
	}
}
