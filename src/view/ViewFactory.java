package view;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public View getView(Viewable viewable, boolean opponent){
		if(opponent){
			if(viewable.getTypeID().equals("Colonist")){
				return new View(enemyColonist);
			}
			if(viewable.getTypeID().equals("Melee")){
				return new View(enemyMelee);
			}
			if(viewable.getTypeID().equals("Ranged")){
				return new View(enemyRanged);
			}
			if(viewable.getTypeID().equals("Explorer")){
				return new View(enemyExplorer);
			}
			if(viewable.getTypeID().equals("Worker")){
				return new View(enemyWorker);
			}
		}
		
		return getView(viewable);
	}


	public View getView(Viewable viewable) {
		if(viewable.getTypeID().equals("Water")){
			return new View(water);
		}
		if(viewable.getTypeID().equals("Ground")){
			return new View(ground);
		}
		if(viewable.getTypeID().equals("Mountain")){
			return new View(mountain);
		}
		if(viewable.getTypeID().equals("Colonist")){
			return new View(colonist);
		}
		if(viewable.getTypeID().equals("Melee")){
			return new View(melee);
		}
		if(viewable.getTypeID().equals("Ranged")){
			return new View(ranged);
		}
		if(viewable.getTypeID().equals("Explorer")){
			return new View(explorer);
		}
		if(viewable.getTypeID().equals("Worker")){
			return new View(worker);
		}
		if(viewable.getTypeID().equals("EnemyColonist")){
			return new View(enemyColonist);
		}
		if(viewable.getTypeID().equals("EnemyMelee")){
			return new View(enemyMelee);
		}
		if(viewable.getTypeID().equals("EnemyRanged")){
			return new View(enemyRanged);
		}
		if(viewable.getTypeID().equals("EnemyExplorer")){
			return new View(enemyExplorer);
		}
		if(viewable.getTypeID().equals("EnemyWorker")){
			return new View(enemyWorker);
		}
		return null;
	}


	public static ViewFactory getFactory() {
		if(factory ==null){
			factory=new ViewFactory();
		}
		
		return factory;
	}
}
