package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import control.Menu;
import model.Controllables.Stats.StructureStats;
import model.Controllables.Structures.Capital;
import model.Controllables.Structures.Farm;
import model.Controllables.Structures.Fort;
import model.Controllables.Structures.Mine;
import model.Controllables.Structures.ObservationTower;
import model.Controllables.Structures.PowerPlant;
import model.Controllables.Structures.Structure;
import model.Controllables.Structures.StructureID;
import model.Controllables.Structures.University;
import model.observers.MenuObserver;
import model.observers.StructureObserver;
import model.observers.StructureResourceObserver;
import utilities.StructureVisitor;

public class StructureOverview  extends JPanel implements StructureVisitor {

	private int width, height;
	private CustomTable structureTable;
	private StructureTableModel model;
	private TableRenderer renderer;
	private JTextArea structureStatsArea;
	private JLabel currentMode, currentInstance,currentType,currentInstruction, playerFood,playerOre,playerPower;
	private JButton foodButton, powerButton, oreButton;
	
	private ArrayList<StructureResourceObserver> observers; 
	
	public StructureOverview(int width, int height) {
		
		this.setSize(width, height);
		this.width = width;
		this.height = height;
		
		model = new StructureTableModel();
		renderer = new TableRenderer();
		structureTable = new CustomTable(model,renderer); 
		structureTable.setEnabled(false);
		
		structureStatsArea = new JTextArea();
		structureStatsArea.setEditable(false);
		
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
		
		foodButton = new JButton("Allocate Food");
		foodButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		oreButton = new JButton("Allocate Metal");
		oreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		powerButton = new JButton("Allocate Power");
		powerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		foodButton.setFocusable(false);
		powerButton.setFocusable(false);
		oreButton.setFocusable(false);
		
		observers = new ArrayList<StructureResourceObserver>();
		
		setButtonListeners();
		displayView();
	}
	
	public void displayView() {
		
		JLabel title = new JLabel("Structure Overview");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(title);
		this.add(new FixedScrollPane(structureTable,width,structureTable.getMaximumSize().height + 23));
		this.add(playerPower);
		this.add(playerOre);
		this.add(playerFood);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
		buttonPanel.add(powerButton);
		buttonPanel.add(oreButton);
		buttonPanel.add(foodButton);
		
		this.add(buttonPanel);
		this.add(new FixedScrollPane(structureStatsArea,width,height/3));
		this.add(currentMode);
		this.add(currentType);
		this.add(currentInstance);
		this.add(currentInstruction);
		this.setBackground(Color.orange);
	}
	
	public void setButtonListeners() {
		
		foodButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				String input = JOptionPane.showInputDialog("Enter the amount of food you wish to allocate");
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
					
					if(validInput) updateObserversFood(food);
				}
			}
		});
		
		oreButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				String input = JOptionPane.showInputDialog("Enter the amount of metal you wish to allocate");
				boolean validInput = true;
				int metal = 0;
				
				try{
					metal = Integer.parseInt(input);
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null,"You must enter a Integer", "Error!", JOptionPane.ERROR_MESSAGE);
					validInput = false;
				}
				
				if(validInput) {
					if(metal < 0) {
						JOptionPane.showMessageDialog(null, "The integer must be greater than zero", "You thought!", JOptionPane.ERROR_MESSAGE);
						validInput = false;
					}
					
					if(validInput) updateObserversOre(metal);
				}
			}
		});
		
		powerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				
				String input = JOptionPane.showInputDialog("Enter the amount of power you wish to allocate");
				boolean validInput = true;
				int power = 0;
				
				try{
					power = Integer.parseInt(input);
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null,"You must enter a Integer", "Error!", JOptionPane.ERROR_MESSAGE);
					validInput = false;
				}
				
				if(validInput) {
					if(power < 0) {
						JOptionPane.showMessageDialog(null, "The integer must be greater than zero", "You thought!", JOptionPane.ERROR_MESSAGE);
						validInput = false;
					}
					
					if(validInput) updateObserversPower(power);
				}
			}
		});
	}

	@Override
	public void visit(Structure structure) {
		if(structure.isAlive())
		{
			model.addStructure(structure); //structures have current health?
		}
		else
		{
			model.removeStructure(structure);
		}
	}

	public void updateMenu(Menu menu) {
		
		this.currentMode.setText("CURRENT MODE= " + menu.modeToString());
		this.currentInstance.setText("CURRENT INSTANCE= " + menu.getCurrentInstanceNumber());
		this.currentType.setText("CURRENT TYPE= " + menu.typeToString());
		this.currentInstruction.setText("CURRENT INSTRUCTION= " + menu.getCurrentInstruction());
		
		if(menu.getCurrentMode() == Menu.STRUCTUREMODE) {
			Structure structure = (Structure)menu.getCurrentInstance();
			if(structure != null) {
				renderer.selectUnit(structure.getID().getType(), menu.getCurrentInstanceNumber());
				displayStructureStats(structure);
			}
			powerButton.setEnabled(true);
			oreButton.setEnabled(true);
			foodButton.setEnabled(true);
		}
		else {
			renderer.deSelectUnit();
			removeStats();
			powerButton.setEnabled(false);
			oreButton.setEnabled(false);
			foodButton.setEnabled(false);
		}
		
		model.update();
	}
	
	public void displayStructureStats(Structure structure) {
		StructureStats stats = structure.getMyStats();
		structureStatsArea.setText("Health: " + structure.getCurrentHealth() + "\nProduction Rate: " + stats.getProductionRate() 
				+ "\nUpkeep: " + structure.getUpkeep() + "\nEnergy Stored: " + structure.getEnergyResourceLevel() 
				+ "\nMetal Stored: " + structure.getMetalResourceLevel() + "\nFood Stored: " + structure.getNutrientResourceLevel()
				+ "\nAssigned Workers: " + structure.getNumTotalOfWorkers() 
				+ "\nAttack Power: " + stats.getOffensiveDamage() + "\nDefense Power: " + stats.getDefensiveDamage() 
				+ "\nArmor: " + stats.getArmor());
	}
	
	public void removeStats() {
		structureStatsArea.setText("");
	}
	
	public void reset() {
		removeStats();
		model.clearData();
		model.update();
	}
	
	public void updatePlayerResources(int power, int ore, int food) {
		playerPower.setText("Power: " + power);
		playerOre.setText("Metal: " + ore);
		playerFood.setText("Food: " + food);
	}
	
	public void addObserver(StructureResourceObserver obs) {
		observers.add(obs);
	}
	
	public void removeObserver(StructureResourceObserver obs) {
		observers.remove(obs);
	}
	
	private void updateObserversFood(int food) {
		for(StructureResourceObserver obs: observers) {
			obs.updateStructureFood(food);
		}
	}
	
	private void updateObserversOre(int ore) {
		for(StructureResourceObserver obs: observers) {
			obs.updateStructureOre(ore);
		}
	}
	
	public void updateObserversPower(int power) {
		for(StructureResourceObserver obs: observers) {
			obs.updateStructurePower(power);
		}
	}
}
