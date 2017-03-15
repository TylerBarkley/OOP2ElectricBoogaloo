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
import model.Controllables.Structures.StructureID;
import model.Controllables.Units.Unit;
import model.Controllables.Structures.University;
import model.Map.Map;
import model.observers.ArmyObserver;
import model.observers.EndTurnObserver;
import model.observers.MenuObserver;
import model.observers.PlayerObserver;
import model.observers.RPObserver;
import model.observers.StartTurnObserver;
import model.observers.StructureObserver;
import model.observers.StructureResourceObserver;
import model.observers.UnitObserver;
import model.observers.UnitResourceObserver;
import model.player.Player;
import model.player.PlayerID;
import model.player.PlayerManager;
import utilities.ViewVisitor;

public class ViewHandler implements UnitObserver, StructureObserver, MenuObserver, PlayerObserver,
ArmyObserver, EndTurnObserver, StartTurnObserver, RPObserver, UnitResourceObserver, StructureResourceObserver
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
		statusViewport=new StatusViewport(350,height);
		areaViewport=new AreaViewport(width-350, height);
		areaViewport.setBlankMap(Map.getInstance().getLocations());
		mainScreen=new MainScreen(width, height, statusViewport, areaViewport);
		unitOverview=new UnitOverview(width, height);
		unitOverview.addObserver(this);
		structureOverview=new StructureOverview(width, height);
		structureOverview.addObserver(this);
		configurationOverview=new ConfigurationOverview(controls, width, height);
		techViewport=new TechnologyViewport(width, height);

		InputReader ir=new InputReader(new InputHandler(menu, turn, areaViewport),controls);

		window=new GameWindow(width, height, mainScreen, unitOverview, structureOverview, configurationOverview, techViewport, ir);
		areaMomentos=new HashMap<PlayerID, AreaViewportMomento>();

		focusLocations=new HashMap<PlayerID, Location>();

		viewVisitor=new ViewVisitor(turn.getCurrentPlayerID());
	}

	@Override
	public void update(Structure structure) {
		if(structure.getLocation() ==null)
		{
			return;
		}

		if(structure.getID().getPlayerID().equals(turn.getCurrentPlayerID()))
		{
			structure.accept(structureOverview);
			structure.accept(statusViewport);
			
			
		}

		if(!structure.isAlive())
		{
			areaViewport.removeView(structure.getID());
			if(structure.getID().getType() == StructureID.UNIVERSITY_TYPE_ID) {
				//University university = (University)structure;
				//techViewport.removeUniversity(university);
				//university.removeUniversityObserver(techViewport);
			}
			return;
		}
		else
		{
			structure.accept(viewVisitor);
			updateView();
		}
	}

	@Override
	public void update(Unit unit) {
		if(unit.getLocation() ==null)
		{
			return;
		}

		if(unit.getID().getPlayerID().equals(turn.getCurrentPlayerID()))
		{
			unit.accept(unitOverview);
			unit.accept(statusViewport);
			
		}

		if(!unit.isAlive())
		{
			areaViewport.removeView(unit.getID());
			return;
		}
		else
		{
			unit.accept(viewVisitor);
			updateView();
		}
	}

	@Override
	public void update(Menu menu) {
		menu.accept(window);

		areaViewport.focusOn(menu.getFocus());
	}

	@Override
	public void update(Player player) {
		if(player.getId().equals(turn.getCurrentPlayerID())){
			unitOverview.updatePlayerResources(player.getEnergy().getAmount(), player.getOre().getAmount(), player.getFood().getAmount());
			structureOverview.updatePlayerResources(player.getEnergy().getAmount(), player.getOre().getAmount(), player.getFood().getAmount());
			//player.getTech().accept(techViewport);
		}
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
			if(structure.getID().getType() == StructureID.UNIVERSITY_TYPE_ID && structure.isAlive()) {
				//University university = (University)structure;
			//	university.addUniversityObserver(techViewport);
			}
		}
		else
		{
			
			areaViewport.removeView(structure.getID());
			structure.removeObserver(this);
			if(structure.getID().getType() == StructureID.UNIVERSITY_TYPE_ID) {
				//University university = (University)structure;
				//techViewport.removeUniversity(university);
				//university.removeUniversityObserver(techViewport);
			}
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

		if(!rp.isActive())
		{
			rp.removeObserver(this);
		}
		else
		{
			areaViewport.removeView(rp.getID());
			rp.addObserver(this);
		}
	}

	@Override
	public void update(Army army) {
		if(army.getLocation() ==null)
		{
			return;
		}
		army.accept(unitOverview);
	}

	@Override
	public void update(RallyPoint rp) {
		if(rp.getLocation() ==null)
		{
			return;
		}
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
		areaViewport.saveToMomento();
		
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

		structureOverview.reset();
		unitOverview.reset();
		statusViewport.reset();
		techViewport.reset();
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
			
			if(structure.getID().getType() == StructureID.UNIVERSITY_TYPE_ID) {
			//	University university = (University)structure;
				//university.addUniversityObserver(techViewport);
				//university.notifyUnivsersityObservers();
			}
		}

		for(Army army: pm.getArmies(id))
		{
			army.accept(unitOverview);
		}

		for(RallyPoint rp: pm.getRallyPoints(id))
		{
			rp.accept(viewVisitor);
		}
		
		
		unitOverview.updatePlayerResources(pm.getPower(id).getAmount(), pm.getMetal(id).getAmount(), pm.getNutrients(id).getAmount());
		structureOverview.updatePlayerResources(pm.getPower(id).getAmount(), pm.getMetal(id).getAmount(), pm.getNutrients(id).getAmount());
		//pm.getTechnology(id).accept(techViewport);
		updateView();
	}

	public void openWindow() {
		window.openWindow();
	}

	@Override
	public void updateStructureFood(int food) {
		// TODO Auto-generated method stub
		PlayerManager pm = PlayerManager.getInstance();
		Structure structure = (Structure) menu.getCurrentInstance();

		pm.distributeNutrients(structure.getPlayerID(), structure, food);
	}

	@Override
	public void updateStructureOre(int ore) {
		// TODO Auto-generated method stub
		PlayerManager pm = PlayerManager.getInstance();
		Structure structure = (Structure) menu.getCurrentInstance();

		pm.distributeMetal(structure.getPlayerID(), structure, ore);
	}

	@Override
	public void updateStructurePower(int power) {
		// TODO Auto-generated method stub
		PlayerManager pm = PlayerManager.getInstance();
		Structure structure = (Structure) menu.getCurrentInstance();

		pm.distributePower(structure.getPlayerID(), structure, power);
	}

	@Override
	public void updateUnitFood(int food) {

		PlayerManager pm = PlayerManager.getInstance();

		if(menu.getCurrentMode() == Menu.UNITMODE) {
			Unit unit = (Unit) menu.getCurrentInstance();
			pm.distributeNutrients(unit.getPlayerID(), unit, food);
		}
		else {
			Army army = (Army) menu.getCurrentInstance();
			pm.distributeNutrients(army.getPlayerID(), army, food);
		}
	}


}
