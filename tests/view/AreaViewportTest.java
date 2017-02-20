package view;

import javax.swing.JFrame;

import org.junit.Test;

import model.Ground;
import model.Location;
import model.Mountain;
import model.Ranged;
import model.Terrain;
import model.Water;

public class AreaViewportTest {

	@Test
	public void smokeTest() {
	        JFrame frame = new JFrame();
	        frame.setSize(1000,1000);
	        
	        ViewFactory f=ViewFactory.getFactory();
	        TileView[][] map=new TileView[30][30];
	        for(int i=0; i<30; i++){
	        	for(int j=0; j<30; j++){
	        		switch((int)(Math.random()*3)){
	        		case 0:
	        			map[j][i]=(TileView) f.getView(new Water(), new Location(i,j), false);
	        			break;
	        		case 1:
	        			map[j][i]=(TileView) f.getView(new Ground(), new Location(i,j), false);
	        			break;
	        		case 2:
	        			map[j][i]=(TileView) f.getView(new Mountain(), new Location(i,j), false);
	        			break;
	        		}
	        	}
	        }
	        AreaViewport viewport=new AreaViewport(1000,1000);
	        viewport.setMap(map);
	        viewport.addView(f.getView(new Ranged(), new Location(8,8), false, 60));
	        viewport.addView(f.getView(new Ranged(), new Location(8,8), false, 120));
	        viewport.addView(f.getView(new Ranged(), new Location(8,8), false, 180));
	        viewport.addView(f.getView(new Ranged(), new Location(8,8), false, 240));
	        viewport.addView(f.getView(new Ranged(), new Location(8,8), false, 300));
	        viewport.addView(f.getView(new Ranged(), new Location(8,8), false, 0));
	        
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
