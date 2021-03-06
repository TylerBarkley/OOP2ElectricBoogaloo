package application;

import java.util.ArrayList;

import control.Menu;
import control.UserControls;
import model.Location;
import model.TurnManager;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Map.Map;
import model.player.Player;
import model.player.PlayerManager;
import view.ViewHandler;

public class Game {
	private ArrayList<Player> players;
	private int currentPlayer;
	
	private UserControls controls;
	
	private Menu menu;
	private ViewHandler viewHandler;

	public Game(int width, int height){
		Map map = Map.getInstance("map.txt");
		
		Player p1=new Player();
		Player p2=new Player();
		
		
		p1.addMetal(5000);
		p1.addNutrients(5000);
		p1.addPower(5000);
		p2.addMetal(5000);
		p2.addNutrients(5000);
		p2.addPower(5000);
		
		players=new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		currentPlayer=0;	
		
		controls=new UserControls();
		
		menu=new Menu(players.get(currentPlayer).getId());		
		
		TurnManager turn=TurnManager.getInstance(players);
		viewHandler=new ViewHandler(width, height, menu, turn, controls);
		
		p1.addObserver(viewHandler);
		p2.addObserver(viewHandler);
		turn.addEndObserver(viewHandler);
		turn.addStartObserver(viewHandler);
		menu.addObserver(viewHandler);
		
		turn.addStartObserver(menu);
		p1.addObserver(menu);
		p2.addObserver(menu);
		
		PlayerManager pm = PlayerManager.getInstance();
		
		Colonist c=new Colonist();

		pm.addUnit(p1.getId(), c);
		map.addUnit(new Location(3,3), c);	
		
		Colonist c2 =new Colonist();

		pm.addUnit(p2.getId(), c2);
		map.addUnit(new Location(3,1), c2);
		
		
	}
	
	public void start(){
		viewHandler.openWindow();
	}
}
