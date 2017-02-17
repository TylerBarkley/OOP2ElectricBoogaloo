package view;

import javax.swing.JFrame;

import org.junit.Test;

import model.Ground;
import model.Location;
import model.Ranged;

public class AreaViewportTest {

	@Test
	public void smokeTest() {
	        JFrame frame = new JFrame();
	        frame.setSize(1000,1000);
	        
	        
	        ViewFactory f=ViewFactory.getFactory();
	        TileView[][] map=new TileView[30][30];
	        for(int i=0; i<30; i++){
	        	for(int j=0; j<30; j++){
	        		map[j][i]=(TileView) f.getView(new Ground(), new Location(i,j), false);
	        	}
	        }
	        AreaViewport viewport=new AreaViewport(1000,1000);
	        viewport.setMap(map);
	        viewport.addView(f.getView(new Ranged(), new Location(0,0), false));
	        
	        frame.add(viewport);
	        frame.setVisible(true);
	        viewport.focusOn(new Location(8,8));
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
