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

	/**
	 * @param id a unique identifier for the type of View to generate
	 * @param loc the location where this view corresponds
	 * @param value the angle of rotation or quantity of a resource
	 * @return view to be displayed on the map
	 */
	public View getView(String id, Location loc, int value)
	{
		switch(id)
		{
		case "Water":
		case "EnemyWater":
			return new TileView(water, loc, value);
		case "Ground":
		case "EnemyGround":
			return new TileView(ground, loc, value);
		case "Mountain":
		case "EnemyMountain":
			return new TileView(mountain, loc, value);		
		case "UnknownTerrain":
			return new TileView(unknown, loc, value);

		case "Colonist":
			return new UnitView(colonist, loc, value);
		case "EnemyColonist":
			return new UnitView(enemyColonist, loc, value);
		case "Explorer":
			return new UnitView(explorer, loc, value);
		case "EnemyExplorer":
			return new UnitView(enemyExplorer, loc, value);
		case "Melee":
			return new UnitView(melee, loc, value);
		case "EnemyMelee":
			return new UnitView(enemyMelee, loc, value);
		case "Ranged":
			return new UnitView(ranged, loc, value);
		case "EnemyRanged":
			return new UnitView(enemyRanged, loc, value);
		case "Worker":
			return new UnitView(worker, loc, value);
		case "EnemyWorker":
			return new UnitView(enemyWorker, loc, value);

		case "Capital":
			return new StructureView(capital, loc, value);
		case "University":
			return new StructureView(university, loc, value);
		case "Mine":
			return new StructureView(mine, loc, value);
		case "Factory":
			return new StructureView(factoryStructure, loc, value);
		case "Farm":
			return new StructureView(farm, loc, value);

		case "EnemyCapital":
			return new StructureView(enemyCapital, loc, value);
		case "EnemyUniversity":
			return new StructureView(enemyUniversity, loc, value);
		case "EnemyMine":
			return new StructureView(enemyMine, loc, value);
		case "EnemyFactory":
			return new StructureView(enemyFactory, loc, value);
		case "EnemyFarm":
			return new StructureView(enemyFarm, loc, value);
			
		case "Energy":
			return new EnergyView(energy, loc, value);
		case "Food":
			return new FoodView(food, loc, value);
		case "Ore":
			return new OreView(ore, loc, value);
		default:
			return new View(unknown, loc, value);
		}
	}

	public ResourceView getResourceView(String id, Location loc, int quantity)
	{
		return (ResourceView)getView(id, loc, quantity);
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
