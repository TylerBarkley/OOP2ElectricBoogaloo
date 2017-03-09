package view;

import java.util.HashMap;
import java.util.Set;

import javax.swing.JFrame;

import org.junit.Test;

import model.ID;
import model.Location;
import model.Map.Map;
import java.lang.Math;

public class AreaViewportTest {

	@Test
	public void smokeTest() {
		JFrame frame = new JFrame();
		frame.setSize(1000,1000);

		ViewFactory f=ViewFactory.getFactory();
		HashMap<Location, TileView> map=new HashMap<Location, TileView>();
		for(int i=0; i<60; i++){
            int offset=Math.floorDiv(i-1, 2);
			for(int j=-offset; j<60-offset; j++){
				Location loc=new Location(i,60-i-j);

				switch((int)(Math.random()*3)){
				case 0:
					map.put(loc, (TileView) f.getView(new ID(), "Ground", loc));
					break;
				case 1:
					map.put(loc, (TileView) f.getView(new ID(), "Water", loc, false));
					break;
				case 2:
					map.put(loc, (TileView) f.getView(new ID(), "Mountain", loc, false));
					break;
				}
			}
		}
		AreaViewport viewport=new AreaViewport(1000,1000);

		Set<Location> locs=map.keySet();
		viewport.setBlankMap(locs);
		viewport.updateMapView(map);

		viewport.addView(f.getView(new ID(), "Ranged", new Location(7,8), 60));
		viewport.addView(f.getView(new ID(), "Melee", new Location(7,8), 0));
		viewport.addView(f.getView(new ID(), "EnemyColonist", new Location(7,7), 180));
		viewport.addView(f.getView(new ID(), "EnemyExplorer", new Location(8,7), 240));
		viewport.addView(f.getView(new ID(), "Worker", new Location(8,8), 300));
		viewport.addView(f.getView(new ID(), "Explorer", new Location(8,8), 120));
                                   
		viewport.addView(f.getView(new ID(), "Capital", new Location(7,8), 60));
		viewport.addView(f.getView(new ID(), "Mine", new Location(8, 9), 0));
		viewport.addView(f.getView(new ID(), "EnemyUniversity", new Location(8,7), 180));
		viewport.addView(f.getView(new ID(), "EnemyFarm", new Location(7,7), 240));
		viewport.addView(f.getView(new ID(), "Factory", new Location(8,8), 300));
		viewport.addView(f.getView(new ID(), "Farm", new Location(9,8), 120));

		viewport.updateResourceView(new Location(8,8), f.getCopositeResourceView(new ID(), new Location(8,8), 8, 46, 765));
		viewport.updateResourceView(new Location(8,9), f.getCopositeResourceView(new ID(), new Location(8,9), 845, 79, 5));
		viewport.updateResourceView(new Location(7,8), f.getCopositeResourceView(new ID(), new Location(7,8), 123, 1234, 12));

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
        viewport.focusOn(new Location(0,1));
        viewport.focusOn(new Location(0,2));
        viewport.focusOn(new Location(0,3));
        viewport.focusOn(new Location(0,4));
        viewport.focusOn(new Location(0,5));
        viewport.focusOn(new Location(0,55));
        viewport.focusOn(new Location(0,56));
        viewport.focusOn(new Location(0,57));
        viewport.focusOn(new Location(0,58));
        viewport.focusOn(new Location(0,59));
        viewport.focusOn(new Location(1,59));
        viewport.focusOn(new Location(2,58));
        viewport.focusOn(new Location(3,58));
        viewport.focusOn(new Location(4,57));
        viewport.focusOn(new Location(5,57));
        viewport.focusOn(new Location(6,56));
        viewport.focusOn(new Location(54,32));
        viewport.focusOn(new Location(55,32));
        viewport.focusOn(new Location(56,31));
        viewport.focusOn(new Location(57,31));
        viewport.focusOn(new Location(58,30));
        viewport.focusOn(new Location(59,30));
        viewport.focusOn(new Location(59,29));
        viewport.focusOn(new Location(59,28));
        viewport.focusOn(new Location(59,27));
        viewport.focusOn(new Location(59,-26));
        viewport.focusOn(new Location(59,-27));
        viewport.focusOn(new Location(59,-28));
        viewport.focusOn(new Location(59,-29));
        viewport.focusOn(new Location(58,-29));
        viewport.focusOn(new Location(57,-28));
        viewport.focusOn(new Location(56,-28));
        viewport.focusOn(new Location(55,-27));
        viewport.focusOn(new Location(54,-27));
        viewport.focusOn(new Location(53,-26));
        viewport.focusOn(new Location(52,-26));
        viewport.focusOn(new Location(7,-3));
        viewport.focusOn(new Location(6,-3));
        viewport.focusOn(new Location(5,-2));
        viewport.focusOn(new Location(4,-2));
        viewport.focusOn(new Location(3,-1));
        viewport.focusOn(new Location(2,-1));
        viewport.focusOn(new Location(1,0));
        viewport.focusOn(new Location(0,0));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Test
	public void smokeTest_lookOfMap() {
		Map map=new Map("map.txt");
		while(getClass().equals(null));
	}
}
