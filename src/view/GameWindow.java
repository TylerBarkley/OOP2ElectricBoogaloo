package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import control.UserControls;
import model.Location;

public class GameWindow extends JFrame {

	private int width;
	private int height;

	private JTabbedPane tabbedPane;

	private MainScreen mainScreen;
	private UnitOverview unitOverview;
	private StructureOverview structureOverview;
	private ConfigurationOverview configurationOverview;
	
	public GameWindow(int width, int height, UserControls controls) {
		this.width = width;
		this.height = height;
		this.setTitle("Lost In the Sauce");
		
		this.tabbedPane=new JTabbedPane();
		
		this.mainScreen=new MainScreen(width,height);
		this.unitOverview=new UnitOverview(width, height);
		this.structureOverview=new StructureOverview(width, height);
		this.configurationOverview=new ConfigurationOverview(controls, width, height);
		
		tabbedPane.setFocusable(true);
		addComponentListener(new ComponentListener() {
		    public void componentResized(ComponentEvent e) { updateView();}

			public void componentMoved(ComponentEvent e) {updateView();}

			public void componentShown(ComponentEvent e) {updateView();}

			public void componentHidden(ComponentEvent e) {updateView();}
		});
		
		setUpTabbedPane();
		addGameMenu();
	}

	private void setUpTabbedPane() {
		tabbedPane.addTab("Main Screen", mainScreen);
		tabbedPane.addTab("Structure Overview", structureOverview);
		tabbedPane.addTab("Unit Overview", unitOverview);
		this.add(tabbedPane);
	}

	public void openWindow(){
		this.setSize(width, height);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void closeWindow(){
		this.closeWindow();
	}

	public void switchToUnitOverview(){
		tabbedPane.setSelectedComponent(unitOverview);
	}

	public void switchToStructureOverview(){
		tabbedPane.setSelectedComponent(structureOverview);
	}

	public void switchToMainScreen(){
		tabbedPane.setSelectedComponent(mainScreen);
	}

	public void updateView(){
		mainScreen.updateAreaView();
//		mainScreen.updateSatusView();
//		unitOverview.updateView();
//		structureOverview.updateView();
	}

	public void focusOn(Location loc){
		mainScreen.focusOn(loc);
	}

	private void addGameMenu() {


		JMenu fileMenu = new JMenu("File");

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem controls = new JMenuItem("Configure Controls");
		controls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame=new JFrame();
				frame.setSize(500, 500);
				frame.add(configurationOverview);
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		
		fileMenu.add(controls);
		fileMenu.add(exitItem);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);

	}
}
