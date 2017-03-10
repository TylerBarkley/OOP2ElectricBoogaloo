package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.*;

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

public class StructureOverview  extends JPanel implements StructureVisitor, StructureObserver, MenuObserver {

	private int width, height;
	private JTable structureTable;
	private StructureTableModel model;
	private JTextArea structureStatsArea;
	private JLabel currentMode, currentInstance,currentType,currentInstruction;
	
	public StructureOverview(int width, int height) {
		
		this.setSize(width, height);
		this.width = width;
		this.height = height;
		
		model = new StructureTableModel();
		structureTable = new JTable(model); 
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
	public void visit(Capital capital) {
		
		if(capital.isAlive()) {
			model.addStructure(capital); 
		}
		else {
			model.removeStructure(capital);
		}
	}

	@Override
	public void visit(Farm farm) {
		
		if(farm.isAlive()) {
			model.addStructure(farm);
		}
		else {
			model.removeStructure(farm);
		}
	}

	@Override
	public void visit(Fort fort) {
		
		if(fort.isAlive()) {
			model.addStructure(fort);
		}
		else {
			model.removeStructure(fort);
		}
	}

	@Override
	public void visit(Mine mine) {
		
		if(mine.isAlive()) {
			model.addStructure(mine);
		}
		else {
			model.removeStructure(mine);
		}
	}

	@Override
	public void visit(ObservationTower tower) {
		
		if(tower.isAlive()) {
			model.addStructure(tower);
		}
		else {
			model.removeStructure(tower);
		}
	}

	@Override
	public void visit(PowerPlant powerPlant) {
		
		if(powerPlant.isAlive()) {
			model.addStructure(powerPlant);
		}
		else {
			model.removeStructure(powerPlant);
		}
	}

	@Override
	public void visit(University university) {
		
		if(university.isAlive()) {
			model.addStructure(university);
		}
		else {
			model.removeStructure(university);
		}
	}

	@Override
	public void update(String currentMode, int currentInstance,
			String currentType, String currentInstruction) {
		
		this.currentMode.setText("CURRENT MODE= " + currentMode);
		this.currentInstance.setText("CURRENT INSTANCE= " + currentInstance);
		this.currentType.setText("CURRENT TYPE= " + currentType);
		this.currentInstruction.setText("CURRENT INSTRUCTION= " + currentInstruction);
	}

	@Override
	public void update(Structure structure) {
		
		if(structure.isAlive()) {
			model.addStructure(structure);
		}
		else {
			model.removeStructure(structure);
		}
	}
	
}
