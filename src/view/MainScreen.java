package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import control.Menu;
import model.Location;
import model.TurnManager;
import utilities.ViewVisitor;

public class MainScreen extends JPanel{

	private StatusViewport statusViewport;
	private AreaViewport areaViewport;
	
	public MainScreen(int width, int height, StatusViewport statusViewport, AreaViewport areaViewport){
		
		this.setSize(width, height);
		
		this.statusViewport=statusViewport;
		this.areaViewport=areaViewport;

		displayView();
	}
	
	public void updateView() {
		//statusView.updateView();
		areaViewport.updateView();
	}

	public void updateAreaView() {
		areaViewport.updateView();
	}
	
//	public void updateSatusView() {
//		statusView.updateView();
//	}
	
	public void displayView() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(areaViewport);
		this.add(statusViewport);
	}
	
	public void placeDecal(Decal decal){
		areaViewport.addView(decal);
	}
	
	public void focusOn(Location loc){
		areaViewport.focusOn(loc);
	}

	public void updateMenu(Menu menu) {
		statusViewport.updateMenu(menu);
		
	}

	public void setStatusViewport(StatusViewport statusViewport) {
		this.remove(this.statusViewport);
		this.statusViewport=statusViewport;
		this.add(statusViewport);
	}

	public void cheatMap() {
		ViewVisitor visitor=new ViewVisitor(TurnManager.getInstance().getCurrentPlayerID());
		visitor.cheat();
		areaViewport.accept(visitor);
	}
}
