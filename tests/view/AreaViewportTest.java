package view;

import java.util.HashMap;
import java.util.Set;

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
		for(int i=0; i<60; i++){
			int offset=(i-1)/2;
			for(int j=-offset; j<60-offset; j++){
				Location loc=new Location(i,60-i-j);

				switch((int)(Math.random()*3)){
				case 0:
					map.put(loc, (TileView) f.getView("Ground", loc));
					break;
				case 1:
					map.put(loc, (TileView) f.getView("Water", loc, false));
					break;
				case 2:
					map.put(loc, (TileView) f.getView("Mountain", loc, false));
					break;
				}
			}
		}
		AreaViewport viewport=new AreaViewport(1000,1000);

		Set<Location> locs=map.keySet();
		viewport.setBlankMap(locs);
		viewport.updateMapView(map);

		viewport.addView(f.getView("Ranged", new Location(7,8), 60));
		viewport.addView(f.getView("Melee", new Location(7,8), 0));
		viewport.addView(f.getView("EnemyColonist", new Location(7,7), 180));
		viewport.addView(f.getView("EnemyExplorer", new Location(8,7), 240));
		viewport.addView(f.getView("Worker", new Location(8,8), 300));
		viewport.addView(f.getView("Explorer", new Location(8,8), 120));

		viewport.addView(f.getView("Capital", new Location(7,8), 60));
		viewport.addView(f.getView("Mine", new Location(8, 9), 0));
		viewport.addView(f.getView("EnemyUniversity", new Location(8,7), 180));
		viewport.addView(f.getView("EnemyFarm", new Location(7,7), 240));
		viewport.addView(f.getView("Factory", new Location(8,8), 300));
		viewport.addView(f.getView("Farm", new Location(9,8), 120));

		viewport.updateResourceView(new Location(8,8), f.getCopositeResourceView(new Location(8,8), 8, 46, 765));
		viewport.updateResourceView(new Location(8,9), f.getCopositeResourceView(new Location(8,9), 845, 79, 5));
		viewport.updateResourceView(new Location(7,8), f.getCopositeResourceView(new Location(7,8), 123, 1234, 12));

		frame.add(viewport);
		frame.setVisible(true);
		viewport.focusOn(new Location(8,8));
		viewport.enableResourceView();
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
		viewport.focusOn(new Location(6,0));
		viewport.focusOn(new Location(5,0));
		viewport.focusOn(new Location(4,0));
		viewport.focusOn(new Location(3,0));
		viewport.focusOn(new Location(2,0));
		viewport.focusOn(new Location(1,0));
		viewport.focusOn(new Location(0,0));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	public static void main(String args[]) {
		AreaViewportTest test = new AreaViewportTest();
		test.smokeTest();
	}
}
