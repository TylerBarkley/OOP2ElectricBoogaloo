package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import control.Menu;
import model.Location;
import utilities.MenuVisitor;

public class GameWindow extends JFrame implements MenuVisitor{
	private int width;
	private int height;

	private JTabbedPane tabbedPane;

	private MainScreen mainScreen;
	private UnitOverview unitOverview;
	private StructureOverview structureOverview;
	private ConfigurationOverview configurationOverview;
	private TechnologyViewport techView;

	public GameWindow(int width, int height, 
			MainScreen mainScreen, 
			UnitOverview unitOverview,
			StructureOverview structureOverview, 
			ConfigurationOverview configurationOverview,
			TechnologyViewport techView,
			KeyListener inputReader) 
	{
		
		this.width = width;
		this.height = height;
		this.mainScreen = mainScreen;
		this.unitOverview = unitOverview;
		this.structureOverview = structureOverview;
		this.techView=techView;
		this.configurationOverview = configurationOverview;
		
		this.setTitle("Lost In the Sauce");
		
		this.tabbedPane=new JTabbedPane();
		removeDefaultKeyListeners();
		
		tabbedPane.setFocusable(true);
		tabbedPane.addKeyListener(inputReader);
		
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
		tabbedPane.addTab("Tech Tree", techView);
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

	@Override
	public void visit(Menu menu) {
		
		mainScreen.updateMenu(menu);                      
		unitOverview.updateMenu(menu);
		structureOverview.updateMenu(menu);
		
	}

	public void setUnitOverview(UnitOverview unitOverview) {
		tabbedPane.remove(this.unitOverview);
		this.unitOverview=unitOverview;
		tabbedPane.add(unitOverview, "Unit Overview");
	}

	public void setStructureOverview(StructureOverview structureOverview) {
		tabbedPane.remove(this.structureOverview);
		this.structureOverview=structureOverview;
		tabbedPane.add(structureOverview, "Structure Overview");
	}
	
	public void setTechView(TechnologyViewport techView) {
		tabbedPane.remove(this.techView);
		this.techView=techView;
		tabbedPane.add(techView, "Tech Tree");
	}
	
	
	private void removeDefaultKeyListeners() {
		 
		Action doNothing = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		        //do nothing
		    }
		};
		
		tabbedPane.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"doNothing");
		tabbedPane.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"doNothing");
		tabbedPane.getInputMap().put(KeyStroke.getKeyStroke("UP"),"doNothing");
		tabbedPane.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"doNothing");
		tabbedPane.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"doNothing");
		tabbedPane.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"doNothing");
		tabbedPane.getInputMap().put(KeyStroke.getKeyStroke("control DOWN"),"doNothing");
		tabbedPane.getInputMap().put(KeyStroke.getKeyStroke("control UP"),"doNothing");
		tabbedPane.getInputMap().put(KeyStroke.getKeyStroke("control"),"doNothing");
		tabbedPane.getActionMap().put("doNothing",doNothing);
		
	}
}
