package application;

import java.util.ArrayList;

import control.Menu;
import control.UserControls;
import model.player.Player;
import view.ViewHandler;

public class Game {
	private ArrayList<Player> players;
	private int currentPlayer;
	
	private UserControls controls;
	
	private Menu menu;
	private ViewHandler viewHandler;

	public Game(int width, int height){
		players=new ArrayList<Player>();
		players.add(new Player());
		players.add(new Player());
		currentPlayer=0;
		
		controls=new UserControls();
		
		menu=new Menu(players.get(currentPlayer).getId());		
		viewHandler=new ViewHandler(width, height, controls);
	}
}
