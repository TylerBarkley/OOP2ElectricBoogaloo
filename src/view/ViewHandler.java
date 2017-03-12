package view;

import java.util.HashMap;

import control.InputHandler;
import control.InputReader;
import control.Menu;
import control.UserControls;

import model.Location;
import model.TurnManager;

import model.Controllables.Army;
import model.Controllables.Worker;
import model.Controllables.Structures.Structure;
import model.Controllables.Units.Unit;

import model.Map.Map;

import model.observers.ArmyObserver;
import model.observers.EndTurnObserver;
import model.observers.MenuObserver;
import model.observers.PlayerObserver;
import model.observers.StartTurnObserver;
import model.observers.StructureObserver;
import model.observers.UnitObserver;
import model.observers.WorkerObserver;

import model.player.Player;
import model.player.PlayerID;
import model.player.PlayerManager;

import utilities.ViewVisitor;

public class ViewHandler implements UnitObserver, StructureObserver, MenuObserver, PlayerObserver,
							ArmyObserver, WorkerObserver, EndTurnObserver, StartTurnObserver
{
	private int width;
	private int height;
	private Menu menu;
	private UserControls controls;
	private StatusViewport statusViewport;
	private AreaViewport areaViewport;
	private MainScreen mainScreen;
	private UnitOverview unitOverview;
	private StructureOverview structureOverview;
	private ConfigurationOverview configurationOverview;
	private GameWindow window;
	private HashMap<PlayerID, AreaViewportMomento> areaMomentos;
	private HashMap<PlayerID, Location> focusLocations;
	private ViewVisitor viewVisitor;
	private TechnologyViewport techViewport;
	
	public ViewHandler(int width, int height, Menu menu, TurnManager turn, UserControls controls){
		this.width=width;
		this.height=height;
		this.menu=menu;
		this.controls=controls;
		statusViewport=new StatusViewport(width/3,height);
		areaViewport=new AreaViewport(width*2/3, height);
		areaViewport.setBlankMap(Map.getInstance().getLocations());
		mainScreen=new MainScreen(width, height, statusViewport, areaViewport);
		unitOverview=new UnitOverview(width, height);
		structureOverview=new StructureOverview(width, height);
		configurationOverview=new ConfigurationOverview(controls, width, height);
		techViewport=new TechnologyViewport(width, height);
		
		InputReader ir=new InputReader(new InputHandler(menu, turn),controls);
		
		window=new GameWindow(width, height, mainScreen, unitOverview, structureOverview, configurationOverview, techViewport, ir);
		areaMomentos=new HashMap<PlayerID, AreaViewportMomento>();
	
		focusLocations=new HashMap<PlayerID, Location>();
		
		viewVisitor=new ViewVisitor(turn.getCurrentPlayer().getId());
	}
	
	@Override
	public void update(Structure structure) {
		structure.accept(structureOverview);
		structure.accept(statusViewport);
		
		structure.accept(viewVisitor);
		updateView();
	}

	@Override
	public void update(Unit unit) {
		unit.accept(unitOverview);
		unit.accept(statusViewport);
		
		unit.accept(viewVisitor);
		updateView();
	}

	@Override
	public void update(Menu menu) {
		menu.accept(window);
		
		areaViewport.focusOn(menu.getFocus());
	}
	
	@Override
	public void update(Player player) {
		//TODO If necessary;
	}

	@Override
	public void update(Player player, Unit unit)
	{
		unit.addObserver(this);
	}
	
	@Override
	public void update(Player player, Structure structure)
	{
		update(player);
		structure.addObserver(this);
	}
	
	@Override
	public void update(Player player, Army army)
	{
		update(player);
		army.addObserver(this);
	}
	
	@Override
	public void update(Player player, Worker worker)
	{
		update(player);
		worker.addObserver(this);
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
	public void endUpdate(TurnManager turn) {
		PlayerID id=turn.getCurrentPlayer().getId();
		areaMomentos.put(id, areaViewport.saveToMomento());
		
		focusLocations.put(id, menu.getFocus());
	}

	@Override
	public void startUpdate(TurnManager turn) {
		PlayerID id=turn.getCurrentPlayer().getId();
		
		AreaViewportMomento momento = areaMomentos.get(id);
		
		if(momento!=null)
		{
			areaViewport.restoreFromMomento(momento);
		}
		else
		{
			areaViewport.setBlankMap(Map.getInstance().getLocations());
		}
		
		Location focus=focusLocations.get(id);
		if(focus!=null)
		{
			menu.setFocus(focus);
		}
		else
		{
			menu.setFocus(new Location(0,0));
		}
		structureOverview=new StructureOverview(width, height);
		unitOverview=new UnitOverview(width, height);
		statusViewport=new StatusViewport(width/3, height);
		
		window.setUnitOverview(unitOverview);
		window.setStructureOverview(structureOverview);
		
		mainScreen.setStatusViewport(statusViewport);
		
		viewVisitor=new ViewVisitor(id);
		
		currentPlayerRefresh(id);
	}
	
	private void updateView()
	{
		Map.getInstance().accept(viewVisitor);
		areaViewport.accept(viewVisitor);
	}
	
	private void currentPlayerRefresh(PlayerID id)
	{
		PlayerManager pm =PlayerManager.getInstance();
		for(Unit u: pm.getUnits(id))
		{
			u.notifyObservers();
		}
		
		for(Structure s: pm.getStructures(id))
		{
			s.notifyObservers();
		}
		
		for(Worker w: pm.getWorkers(id))
		{
			w.notifyObservers();
		}
		
		for(Army a: pm.getArmies(id))
		{
			a.notifyObservers();
		}
	}

	public void openWindow() {
		window.openWindow();
	}
}
