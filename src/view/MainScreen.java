package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Location;

public class MainScreen extends JPanel{

	//private StatusViewport statusView;
	private AreaViewport areaView;
	
	public MainScreen(int width, int height){
		//statusView=new StatusViewport(width/3, height);
		areaView=new AreaViewport((2 * width)/3, height);

		displayView();
	}
	
	public void updateView() {
		//statusView.updateView();
		areaView.updateView();
	}

	public void updateAreaView() {
		areaView.updateView();
	}
	
//	public void updateSatusView() {
//		statusView.updateView();
//	}
	
	public void displayView() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(areaView);
//		this.add(statusView);
	}
	
	public void placeDecal(Decal decal){
		areaView.addView(decal);
	}
	
	public void focusOn(Location loc){
		areaView.focusOn(loc);
	}
}
