package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import control.Menu;
import model.Controllables.Army;
import model.Controllables.ArmyID;
import model.Controllables.Command;
import model.Controllables.Stats.ArmyStats;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Units.Unit;
import model.observers.UnitResourceObserver;
import utilities.ArmyVisitor;
import utilities.UnitVisitor;

public class UnitOverview extends JPanel implements UnitVisitor, ArmyVisitor {

	private int width, height;
	private CustomTable unitTable;
	private UnitTableModel model;
	private TableRenderer renderer;
	private JTextArea unitStatsArea;
	private JLabel currentMode, currentInstance,currentType,currentInstruction, playerFood,playerOre,playerPower;
	private JButton resourceButton;
	
	private ArrayList<UnitResourceObserver> observers; 
	
	
	public UnitOverview(int width, int height) {
		
		this.setSize(width, height);
		this.width = width;
		this.height = height;
		
		model = new UnitTableModel();
		renderer = new TableRenderer();
		unitTable = new CustomTable(model,renderer); 
		unitTable.setEnabled(false);
		
		unitStatsArea = new JTextArea();
		unitStatsArea.setEditable(false);
		
		currentMode = new JLabel("CURRENT MODE= "); 
		currentMode.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentInstance = new JLabel("CURRENT INSTANCE= "); 
		currentInstance.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentType = new JLabel("CURRENT TYPE= ");
		currentType.setAlignmentX(Component.CENTER_ALIGNMENT);
		currentInstruction = new JLabel("CURRENT INSTRUCTION= ");
		currentInstruction.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerFood = new JLabel("Total Food: ");
		playerFood.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerOre = new JLabel("\nTotal Metal: ");
		playerOre.setAlignmentX(Component.CENTER_ALIGNMENT);
		playerPower = new JLabel("Total Power: ");
		playerPower.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		resourceButton = new JButton("Allocate Food");  
		resourceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		resourceButton.setFocusable(false);
		
		observers = new ArrayList<UnitResourceObserver>();
		
		setButtonListener();
		displayView();
	}
	
	public void displayView() {
		
		JLabel title = new JLabel("Unit OverView");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(title);
		this.add(new FixedScrollPane(unitTable,width,unitTable.getMaximumSize().height + 23));
		this.add(playerPower);
		this.add(playerOre);
		this.add(playerFood);
		this.add(resourceButton);
		this.add(new FixedScrollPane(unitStatsArea,width,height/3));
		this.add(currentMode);
		this.add(currentType);
		this.add(currentInstance);
		this.add(currentInstruction);
		this.setBackground(Color.ORANGE);
	}
	
	private void setButtonListener() {
		resourceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String input = JOptionPane.showInputDialog(null, "Enter the amount of food you wish to allocate");
				boolean validInput = true;
				int food = 0;
				
				try{
					food = Integer.parseInt(input);
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null,"You must enter a Integer", "Error!", JOptionPane.ERROR_MESSAGE);
					validInput = false;
				}
				
				if(validInput) {
					if(food < 0) {
						JOptionPane.showMessageDialog(null, "The integer must be greater than zero", "You thought!", JOptionPane.ERROR_MESSAGE);
						validInput = false;
					}
					
					if(validInput) notifyObservers(food);
				}
			}
		});
	}
	
	public void visit(Unit unit) {	
		if(unit.isAlive()) {
			model.addUnit(unit);
		}
		else {
			model.removeUnit(unit);
		}
	}

	public void updateMenu(Menu menu) {
		
		this.currentMode.setText("CURRENT MODE= " + menu.modeToString());
		this.currentInstance.setText("CURRENT INSTANCE= " + menu.getCurrentInstanceNumber());
		this.currentType.setText("CURRENT TYPE= " + menu.typeToString());
		this.currentInstruction.setText("CURRENT INSTRUCTION= " + menu.getCurrentInstruction());
		
		if(menu.getCurrentMode() == Menu.UNITMODE) {
			Unit unit = (Unit) menu.getCurrentInstance();
			if(unit != null)renderer.selectUnit(unit.getID().getType(), menu.getCurrentInstanceNumber());
			if(unit != null) displayStats(unit);
			resourceButton.setEnabled(true);
		} 
		else if(menu.getCurrentMode() == Menu.ARMYMODE) {
			Army army = (Army) menu.getCurrentInstance();
			if(army != null)displayStats(army);
			renderer.selectUnit(ArmyID.ARMY_TYPE_ID, menu.getCurrentInstanceNumber());
			resourceButton.setEnabled(true);
		}
		else {
			renderer.deSelectUnit();
			removeStats();
			resourceButton.setEnabled(false);
		}
		model.update();
		
	}

	@Override
	public void visit(Army army) {
		
		if(!army.isDisbanded()) {
			model.addArmy(army);
		}
		else {
			model.removeArmy(army);
		}
	}
	
	public void displayStats(Unit unit) {
		UnitStats stats = unit.getMyStats();
		unitStatsArea.setText("Health: " + unit.getCurrentHealth() + "\nUpkeep: " + unit.getUpkeep()
				+ "\nStoredFood: " + unit.getNutrientResourceLevel() + "\nMovement: " + stats.getMovement() 
				+ "\nInfluence Radius: " + stats.getInfluenceRadius() 
				+ "\nOffensive Damage: " + stats.getOffensiveDamage() + "\nDefensive Damage: " + stats.getDefensiveDamage()
				+ "\nArmor: " + stats.getArmor());
	}
	
	public void displayStats(Army army) {
		ArmyStats stats = army.getArmyStats();
		String displayedStats = "UpKeep: " + stats.getUpkeep() + "\n"
				+ "Food Stored: " + army.getNutrientResourceLevel() + "\n"
				+ "Movement: " + stats.getMovement() + "\n"
				+ "Offensive Damge: " + army.getAttackDamage() + "\n"
				+ "Defensive Damage: " + stats.getDefensiveDamage() + "\n" 
				+ "Armor: " + stats.getArmor() + "\n"
				+ "Total Units: " + stats.getNumOfUnits() + "\n"
				+"Queued Commands: ";
		
		Queue<Command> armyCommands = army.getCommandQueue().getCommandQueue();
		Iterator<Command> iterator = armyCommands.iterator();
		String commands = "";
		while(iterator.hasNext()) {
			commands += iterator.next().toString() + " "; 
		}
		displayedStats += commands;
		unitStatsArea.setText(displayedStats);
		
	}
	
	public void removeStats() {
		unitStatsArea.setText("");
	}
	
	public void addObserver(UnitResourceObserver obs) {
		observers.add(obs);
	}
	
	public void removeObserver(UnitResourceObserver obs) {
		observers.remove(obs);
	}
	
	public void notifyObservers(int food) {
		for(UnitResourceObserver obs: observers ) {
			obs.updateUnitFood(food);
		}
	}
	
	public void updatePlayerResources(int power, int ore, int food) {
		playerPower.setText("Power: " + power);
		playerOre.setText("Metal: " + ore);
		playerFood.setText("Food: " + food);
	}
	
	public void reset() {
		model.clearData();
		removeStats();
		model.update();
	}
}
