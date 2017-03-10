package application;

import control.UserControls;
import view.ViewHandler;

public class Game {
	public Game(){
		UserControls controls=new UserControls();
		ViewHandler viewHandler=new ViewHandler(1080, 720, controls);
	}
}
