package view;

import control.UserControls;

public class GameWindowTest {
	
	//NOT A JUnit TEST. MUST TEST MANUALLY
	public static void main(String arg[]) {
		GameWindow game = new GameWindow(1080,720,new UserControls());
		game.openWindow();
	}
}
