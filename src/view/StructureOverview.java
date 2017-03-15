package view;

import java.awt.Color;
import java.awt.Component;

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
import utilities.StructureVisitor;

public class StructureOverview  extends JPanel implements StructureVisitor {

	private int width, height;
	private CustomTable structureTable;
	private StructureTableModel model;
	private TableRenderer renderer;
	private JTextArea structureStatsArea;
	private JLabel currentMode, currentInstance,currentType,currentInstruction, playerFood,playerOre,playerPower;
	
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
		
		displayView();
	}
	
	public void displayView() {
		
		JLabel title = new JLabel("Structure Overview");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(title);
		this.add(new FixedScrollPane(structureTable,width,height/3));
		this.add(new FixedScrollPane(structureStatsArea,width,height/3));
		this.add(currentMode);
		this.add(currentType);
		this.add(currentInstance);
		this.add(currentInstruction);
		this.setBackground(Color.orange);
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
		}
		else {
			renderer.deSelectUnit();
			removeStats();
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
}
