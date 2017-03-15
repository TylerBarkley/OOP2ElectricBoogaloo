package view;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.ID;
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
	private BufferedImage tower;
	private BufferedImage fort;
	
	private BufferedImage enemyCapital;
	private BufferedImage enemyUniversity;
	private BufferedImage enemyMine;
	private BufferedImage enemyFactory;
	private BufferedImage enemyFarm;
	private BufferedImage enemyTower;
	private BufferedImage enemyFort;
	
	private BufferedImage water;
	private BufferedImage ground;
	private BufferedImage mountain;

	private BufferedImage energy;
	private BufferedImage food;
	private BufferedImage ore;
	
	private BufferedImage rallyPoint;
	
	private BufferedImage healthItem;
	private BufferedImage obstacle;
	
	private BufferedImage unknown;
	private BufferedImage invisible;
	
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
			tower=ImageIO.read(getClass().getResource("/ObservationTower.png"));
			fort=ImageIO.read(getClass().getResource("/Fort.png"));
			
			enemyCapital=ImageIO.read(getClass().getResource("/EnemyCapital.png"));
			enemyUniversity=ImageIO.read(getClass().getResource("/EnemyUniversity.png"));
			enemyMine=ImageIO.read(getClass().getResource("/EnemyMine.png"));
			enemyFactory=ImageIO.read(getClass().getResource("/EnemyFactory.png"));
			enemyFarm=ImageIO.read(getClass().getResource("/EnemyFarm.png"));
			enemyTower=ImageIO.read(getClass().getResource("/EnemyObservationTower.png"));
			enemyFort=ImageIO.read(getClass().getResource("/EnemyFort.png"));
			
			water=ImageIO.read(getClass().getResource("/Water.png"));
			ground=ImageIO.read(getClass().getResource("/Ground.png"));
			mountain=ImageIO.read(getClass().getResource("/Mountain.png"));
			
			energy=ImageIO.read(getClass().getResource("/Energy.png"));
			food=ImageIO.read(getClass().getResource("/Food.png"));
			ore=ImageIO.read(getClass().getResource("/Ore.png"));
			
			rallyPoint=ImageIO.read(getClass().getResource("/Rallypoint.png"));
			
			healthItem=ImageIO.read(getClass().getResource("/HealthItem.png"));
			obstacle=ImageIO.read(getClass().getResource("/Obstacle.png"));
			
			unknown=ImageIO.read(getClass().getResource("/Unknown.png"));
			invisible=ImageIO.read(getClass().getResource("/Invisible.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public View getView(ID id, String type, Location loc, int rotation)
	{
		switch(type)
		{
		case "Water":
		case "EnemyWater":
			return new TileView(id, water, loc, rotation);
		case "Ground":
		case "EnemyGround":
			return new TileView(id, ground, loc, rotation);
		case "Mountain":
		case "EnemyMountain":
			return new TileView(id, mountain, loc, rotation);		
		case "UnknownTerrain":
			return new TileView(id, unknown, loc, rotation);

		case "Colonist":
			return new UnitView(id, colonist, loc, rotation);
		case "Explorer":
			return new UnitView(id, explorer, loc, rotation);			
		case "Melee":
			return new UnitView(id, melee, loc, rotation);
		case "Ranged":
			return new UnitView(id, ranged, loc, rotation);
		case "Worker":
			return new UnitView(id, worker, loc, rotation);		
			
		case "EnemyColonist":
			return new UnitView(id, enemyColonist, loc, rotation);
		case "EnemyExplorer":
			return new UnitView(id, enemyExplorer, loc, rotation);
		case "EnemyMelee":
			return new UnitView(id, enemyMelee, loc, rotation);
		case "EnemyRanged":
			return new UnitView(id, enemyRanged, loc, rotation);
		case "EnemyWorker":
			return new UnitView(id, enemyWorker, loc, rotation);

		case "Capital":
			return new StructureView(id, capital, loc, rotation);
		case "University":
			return new StructureView(id, university, loc, rotation);
		case "Mine":
			return new StructureView(id, mine, loc, rotation);
		case "Factory":
			return new StructureView(id, factoryStructure, loc, rotation);
		case "Farm":
			return new StructureView(id, farm, loc, rotation);
		case "Tower":
			return new StructureView(id, tower, loc, rotation);
		case "Fort":
			return new StructureView(id, fort, loc, rotation);
			
		case "EnemyCapital":
			return new StructureView(id, enemyCapital, loc, rotation);
		case "EnemyUniversity":
			return new StructureView(id, enemyUniversity, loc, rotation);
		case "EnemyMine":
			return new StructureView(id, enemyMine, loc, rotation);
		case "EnemyFactory":
			return new StructureView(id, enemyFactory, loc, rotation);
		case "EnemyFarm":
			return new StructureView(id, enemyFarm, loc, rotation);
		case "EnemyTower":
			return new StructureView(id, enemyTower, loc, rotation);
		case "EnemyFort":
			return new StructureView(id, enemyFort, loc, rotation);

		case "Skull":
			return new SkullCrossbones(id, loc);
		case "RedCross":
			return new RedCross(id, loc);
			
		case "RallyPoint":
			return new RPView(id, rallyPoint, loc);
			
		case "HealthItem":
			return new ImageView(id, healthItem, loc);
		case "Obstacle":
			return new ImageView(id, obstacle, loc);
			
		case "Invisible":
			return new ImageView(id, invisible, loc, rotation);
			
		default:
			return new ImageView(id, unknown, loc, rotation);
		}
	}

	public CompositeView getCompositeResourceView(ID id, Location loc, int oreQuantity, int energyQuantity, int foodQuantity)
	{
		CompositeView view=new CompositeView(id, loc);
		view.add(new EnergyView(id, energy, loc, oreQuantity));
		view.add(new FoodView(id, food, loc, foodQuantity));
		view.add(new OreView(id, ore, loc, energyQuantity));
		return view;
	}
	
	public View getView(ID id, String type, Location loc)
	{
		return getView(id, type, loc, 0);
	}

	public View getView(ID id, String type, Location loc, int rotation, boolean isOpponent)
	{
		if(isOpponent && !type.startsWith("Enemy"))
		{
			type="Enemy"+type;
		}
		
		return getView(id, type, loc, rotation);
	}
	
	public View getView(ID id, String type, Location loc, boolean isOpponent)
	{
		return getView(id, type, loc, 0, isOpponent);
	}
	
	public static ViewFactory getFactory() {
		if(factory ==null){
			factory=new ViewFactory();
		}

		return factory;
	}
}
