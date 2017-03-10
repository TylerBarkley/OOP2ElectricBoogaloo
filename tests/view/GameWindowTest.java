package view;

import control.UserControls;

public class GameWindowTest {
	
	//NOT A JUnit TEST. MUST TEST MANUALLY
	public static void main(String arg[]) {
		GameWindow game = new GameWindow(1080, 720, 
				new MainScreen(1080, 720, new StatusViewport(360, 720), new AreaViewport(720,720)),
				new UnitOverview(1080, 720), new StructureOverview(1080, 720),
				new ConfigurationOverview(new UserControls(), 1080, 720));
		game.openWindow();
	}
}
