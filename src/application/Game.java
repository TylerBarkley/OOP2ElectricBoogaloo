package application;

import java.util.ArrayList;

import control.Menu;
import control.UserControls;
import model.Location;
import model.TurnManager;
import model.Controllables.Units.Colonist;
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
		
		players=new ArrayList<Player>();
		players.add(p1);
		players.add(p2);
		currentPlayer=0;	
		
		controls=new UserControls();
		
		menu=new Menu(players.get(currentPlayer).getId());		
		
		TurnManager turn=new TurnManager(players);
		viewHandler=new ViewHandler(width, height, menu, turn, controls);
		
		p1.addObserver(viewHandler);
		p2.addObserver(viewHandler);
		turn.addEndObserver(viewHandler);
		turn.addStartObserver(viewHandler);
		menu.addObserver(viewHandler);
		
		PlayerManager pm = PlayerManager.getInstance();
		
		Colonist c=new Colonist(new Location(2,2));

		pm.addUnit(p1.getId(), c);
		map.addUnit(new Location(2,2), c);	
	}
	
	public void start(){
		viewHandler.openWindow();
	}
	
	public static void main(String[] args){
		Game game=new Game(1080,720);
		game.start();
	}
}
