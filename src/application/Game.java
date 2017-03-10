package application;

import java.util.ArrayList;

import control.Menu;
import control.UserControls;
import model.Location;
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
		
		PlayerManager pm = PlayerManager.getInstance();
		
		Colonist c=new Colonist();
/*
		pm.addUnit(p1.getId(), new Colonist());
		map.addUnit(new Location(2,2), c);		
*/		
		controls=new UserControls();
		
		menu=new Menu(players.get(currentPlayer).getId());		
		viewHandler=new ViewHandler(width, height, controls);
	}
	
	public void start(){
		viewHandler.openWindow();
	}
	
	public static void main(String[] args){
		Game game=new Game(1080,720);
		game.start();
	}
}
