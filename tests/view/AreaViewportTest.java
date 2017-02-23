package view;

import java.util.HashMap;

import javax.swing.JFrame;

import org.junit.Test;

import model.Location;

public class AreaViewportTest {

	@Test
	public void smokeTest() {
		JFrame frame = new JFrame();
		frame.setSize(1000,1000);

		ViewFactory f=ViewFactory.getFactory();
		HashMap<Location, TileView> map=new HashMap<Location, TileView>();
		for(int i=0; i<30; i++){
			for(int j=0; j<30; j++){
				Location loc=new Location(i,j);

				switch((int)(Math.random()*3)){
				case 0:
					map.put(loc, (TileView) f.getView("Ground", new Location(i,j)));
					break;
				case 1:
					map.put(loc, (TileView) f.getView("Water", new Location(i,j), false));
					break;
				case 2:
					map.put(loc, (TileView) f.getView("Mountain", new Location(i,j), false));
					break;
				}
			}
		}
		AreaViewport viewport=new AreaViewport(1000,1000);

		viewport.setBlankMap(map.keySet(), 30, 30);
		viewport.updateMapView(map);

		viewport.addView(f.getView("Ranged", new Location(8,8), 60));
		viewport.addView(f.getView("Melee", new Location(8,8), 120));
		viewport.addView(f.getView("EnemyColonist", new Location(8,8), 180));
		viewport.addView(f.getView("EnemyExplorer", new Location(8,8), 240));
		viewport.addView(f.getView("Worker", new Location(8,8), 300));
		viewport.addView(f.getView("Explorer", new Location(8,8), 0));

		viewport.addView(f.getView("Capital", new Location(7,8), 60));
		viewport.addView(f.getView("Mine", new Location(6, 7), 300));
		viewport.addView(f.getView("EnemyUniversity", new Location(8,7), 180));
		viewport.addView(f.getView("EnemyFarm", new Location(7,7), 240));
		viewport.addView(f.getView("Factory", new Location(8,8), 0));
		viewport.addView(f.getView("Farm", new Location(9,8), 120));

		viewport.addView(f.getView("Ore", new Location(8,8), 100));
		viewport.addView(f.getView("Food", new Location(8,8), 123));
		viewport.addView(f.getView("Energy", new Location(8,8), 32));

		frame.add(viewport);
		frame.setVisible(true);
		viewport.focusOn(new Location(8,8));
		viewport.focusOn(new Location(7,8));
		viewport.focusOn(new Location(6,8));
		viewport.focusOn(new Location(5,8));
		viewport.focusOn(new Location(4,8));
		viewport.focusOn(new Location(4,7));
		viewport.focusOn(new Location(4,6));
		viewport.focusOn(new Location(4,5));
		viewport.focusOn(new Location(4,4));
		viewport.focusOn(new Location(4,3));
		viewport.focusOn(new Location(5,3));
		viewport.focusOn(new Location(6,3));
		viewport.focusOn(new Location(7,3));
		viewport.focusOn(new Location(7,2));
		viewport.focusOn(new Location(7,1));
		viewport.focusOn(new Location(7,0));
		viewport.focusOn(new Location(7,-1));
		viewport.focusOn(new Location(7,-2));
		viewport.focusOn(new Location(7,-3));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
