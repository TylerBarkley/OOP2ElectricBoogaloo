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
import model.Controllables.Structures.University;
import utilities.StructureVisitor;

public class StructureOverview  extends JPanel implements StructureVisitor {

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
		// TODO Auto-generated method stub
		model.addStructure(capital); //structures have current health?
	}

	@Override
	public void visit(Farm farm) {
		// TODO Auto-generated method stub
		model.addStructure(farm);
	}

	@Override
	public void visit(Fort fort) {
		// TODO Auto-generated method stub
		model.addStructure(fort);
	}

	@Override
	public void visit(Mine mine) {
		// TODO Auto-generated method stub
		model.addStructure(mine);
	}

	@Override
	public void visit(ObservationTower tower) {
		// TODO Auto-generated method stub
		model.addStructure(tower);
	}

	@Override
	public void visit(PowerPlant powerPlant) {
		// TODO Auto-generated method stub
		model.addStructure(powerPlant);
	}

	@Override
	public void visit(University university) {
		// TODO Auto-generated method stub
		model.addStructure(university);
	}
	
}
