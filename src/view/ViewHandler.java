package view;

import control.Menu;
import control.UserControls;
import model.TurnManager;
import model.Controllables.Army;
import model.Controllables.Worker;
import model.Controllables.Structures.Structure;
import model.Controllables.Units.Unit;
import model.observers.ArmyObserver;
import model.observers.EndTurnObserver;
import model.observers.MenuObserver;
import model.observers.StartTurnObserver;
import model.observers.StructureObserver;
import model.observers.UnitObserver;
import model.observers.WorkerObserver;

public class ViewHandler implements UnitObserver, StructureObserver, MenuObserver, 
							ArmyObserver, WorkerObserver, EndTurnObserver, StartTurnObserver{
	private int width;
	private int height;
	private UserControls controls;
	private StatusViewport statusViewport;
	private AreaViewport areaViewport;
	private MainScreen mainScreen;
	private UnitOverview unitOverview;
	private StructureOverview structureOverview;
	private ConfigurationOverview configurationOverview;
	private GameWindow window;

	public ViewHandler(){
		width=1080;
		height=720;
		controls=new UserControls();
		statusViewport=new StatusViewport(width/3,height);
		areaViewport=new AreaViewport(width*2/3, height);
		mainScreen=new MainScreen(width, height, statusViewport, areaViewport);
		unitOverview=new UnitOverview(width, height);
		structureOverview=new StructureOverview(width, height);
		configurationOverview=new ConfigurationOverview(controls, width, height);
		window=new GameWindow(width, height, mainScreen, unitOverview, structureOverview, configurationOverview);
	}
	
	public ViewHandler(int width, int height, UserControls controls){
		this.width=width;
		this.height=height;
		this.controls=controls;
		statusViewport=new StatusViewport(width/3,height);
		areaViewport=new AreaViewport(width*2/3, height);
		mainScreen=new MainScreen(width, height, statusViewport, areaViewport);
		unitOverview=new UnitOverview(width, height);
		structureOverview=new StructureOverview(width, height);
		configurationOverview=new ConfigurationOverview(controls, width, height);
		window=new GameWindow(width, height, mainScreen, unitOverview, structureOverview, configurationOverview);
	}
	
	@Override
	public void update(Structure structure) {
		structure.accept(structureOverview);
		structure.accept(statusViewport);
	}

	@Override
	public void update(Unit unit) {
		unit.accept(unitOverview);
		unit.accept(statusViewport);
	}

	@Override
	public void update(Menu menu) {
		menu.accept(window);
	}

	@Override
	public void update(Army army) {
		//army.accept(unitOverview);
	}

	@Override
	public void update(Worker worker) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(TurnManager turn) {
		// TODO Auto-generated method stub
		
	}

}
