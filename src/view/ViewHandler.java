package view;

import java.util.HashMap;

import control.InputHandler;
import control.InputReader;
import control.Menu;
import control.UserControls;

import model.Location;
import model.TurnManager;

import model.Controllables.Army;
import model.Controllables.RallyPoint;
import model.Controllables.Structures.Structure;
import model.Controllables.Units.Unit;

import model.Map.Map;

import model.observers.ArmyObserver;
import model.observers.EndTurnObserver;
import model.observers.MenuObserver;
import model.observers.PlayerObserver;
import model.observers.RPObserver;
import model.observers.StartTurnObserver;
import model.observers.StructureObserver;
import model.observers.UnitObserver;

import model.player.Player;
import model.player.PlayerID;
import model.player.PlayerManager;

import utilities.ViewVisitor;

public class ViewHandler implements UnitObserver, StructureObserver, MenuObserver, PlayerObserver,
ArmyObserver, EndTurnObserver, StartTurnObserver, RPObserver
{
	private int width;
	private int height;
	private Menu menu;
	private TurnManager turn;
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
		this.turn=turn;
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

		viewVisitor=new ViewVisitor(turn.getCurrentPlayerID());
	}

	@Override
	public void update(Structure structure) {
		if(structure.getID().getPlayerID().equals(turn.getCurrentPlayerID()))
		{
			structure.accept(structureOverview);
			structure.accept(statusViewport);
		}
		
		structure.accept(viewVisitor);
		updateView();
	}

	@Override
	public void update(Unit unit) {
		if(unit.getID().getPlayerID().equals(turn.getCurrentPlayerID()))
		{
			unit.accept(unitOverview);
			unit.accept(statusViewport);
		}
		
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
		update(player);

		if(unit.isAlive())
		{
			unit.addObserver(this);
		}
		else
		{
			unit.removeObserver(this);
		}
	}

	@Override
	public void update(Player player, Structure structure)
	{
		update(player);

		if(structure.isAlive()){
			structure.addObserver(this);
		}
		else
		{
			structure.removeObserver(this);
		}
	}

	@Override
	public void update(Player player, Army army)
	{
		update(player);

		if(army.isDisbanded())
		{
			army.removeObserver(this);
		}
		else
		{
			army.addObserver(this);
		}
	}

	@Override
	public void update(Player player, RallyPoint rp) {
		update(player);

		if(rp.isActive())
		{
			rp.removeObserver(this);
		}
		else
		{
			rp.addObserver(this);
		}
	}
	
	@Override
	public void update(Army army) {
		army.accept(unitOverview);
	}

	@Override
	public void update(RallyPoint rp) {
		rp.accept(viewVisitor);
		
		updateView();
	}
	
	@Override
	public void endUpdate(TurnManager turn) {
		PlayerID id=turn.getCurrentPlayerID();
		areaMomentos.put(id, areaViewport.saveToMomento());

		focusLocations.put(id, menu.getFocus());
	}

	@Override
	public void startUpdate(TurnManager turn) {
		PlayerID id=turn.getCurrentPlayerID();

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
		for(Unit unit: pm.getUnits(id))
		{
			unit.accept(unitOverview);
			unit.accept(statusViewport);
			unit.accept(viewVisitor);
		}

		for(Structure structure: pm.getStructures(id))
		{
			structure.accept(structureOverview);
			structure.accept(statusViewport);
			structure.accept(viewVisitor);
		}

		for(Army army: pm.getArmies(id))
		{
			army.accept(unitOverview);
		}
		
		for(RallyPoint rp: pm.getRallyPoints(id))
		{
			rp.accept(viewVisitor);
		}
		
		updateView();
	}

	public void openWindow() {
		window.openWindow();
	}
}
