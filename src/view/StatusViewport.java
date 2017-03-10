package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

import control.Menu;
import utilities.StructureVisitor;
import utilities.UnitVisitor;
import model.Controllables.Structures.Structure;
import model.Controllables.Structures.StructureID;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;
import model.Controllables.Units.UnitID;
import model.observers.MenuObserver;
import model.observers.UnitObserver;

public class StatusViewport extends JPanel implements UnitVisitor, StructureVisitor{

	private int width;
	private int height;

	private JTextArea unitArea;
	private JTextArea structureArea;
	private JLabel currentMode, currentInstance,currentType,currentInstruction;

	public StatusViewport(int width, int height) {

		this.setSize(width, height);
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width,height));
		this.setMaximumSize(new Dimension(width,height));

		unitArea = new JTextArea();
		unitArea.setEditable(false);
		structureArea = new JTextArea(); 
		structureArea.setEditable(false);

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

		FixedPanel unitPanel = new FixedPanel(width/2,height/2);
		unitPanel.setLayout(new BoxLayout(unitPanel,BoxLayout.Y_AXIS));

		unitPanel.add(new JLabel("Unit Status Port",JLabel.CENTER));
		unitPanel.add(new JScrollPane(unitArea));

		FixedPanel structurePanel = new FixedPanel(width/2,height/2);
		structurePanel.setLayout(new BoxLayout(structurePanel,BoxLayout.Y_AXIS));

		structurePanel.add(new JLabel("Structure Status Port",JLabel.CENTER)); 
		structurePanel.add(new JScrollPane(structureArea));

		JPanel mixedPanel = new JPanel();
		mixedPanel.setLayout(new BoxLayout(mixedPanel,BoxLayout.X_AXIS));

		mixedPanel.add(Box.createHorizontalStrut(10));
		mixedPanel.add(unitPanel);
		mixedPanel.add(Box.createHorizontalStrut(10));
		mixedPanel.add(structurePanel);
		mixedPanel.add(Box.createHorizontalStrut(10));

		JLabel title = new JLabel("Status ViewPort");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

		this.add(title);
		this.add(mixedPanel);
		this.add(currentMode);
		this.add(currentType);
		this.add(currentInstance);
		this.add(currentInstruction);
		this.setBackground(Color.orange);
	}

	public void displayUnitStats(Unit unit, int instance, String unitName) {

		String unitStats = unitName + " " + instance + "\n" 
				+ "Health: " + unit.getCurrentHealth() + "\n"
				+ "Movement: " + unit.getMyStats().getMovement() + "\n"
				+ "Upkeep: " + unit.getMyStats().getUpkeep();

		String areaText = unitArea.getText();
		int index = areaText.indexOf(unitName + " " + instance);

		if(index >= 0) {

			String oldStats = areaText.substring(index, areaText.indexOf("\n\n",index));
			areaText = areaText.replace(oldStats, unitStats);
		}
		else {
			areaText = areaText + unitStats + "\n\n";
		}

		unitArea.setText(areaText);

	}

	public void removeUnitStats(String unitName, int instance) {

		String areaText = unitArea.getText();
		int index = areaText.indexOf(unitName + " " + instance);
		String unitStats = "\n\n" + areaText.substring(index, areaText.indexOf("\n\n",index));

		areaText = areaText.replace(unitStats, "");
		unitArea.setText(areaText);

	}

	public void visit(Unit unit) {
		String type="";
		switch(unit.getID().getType())
		{
		case UnitID.COLONIST_TYPE_ID:
			type="Colonist";
			break;
		case UnitID.EXPLORER_TYPE_ID:
			type="Explorer";
			break;
		case UnitID.MELEE_TYPE_ID:
			type="Melee Unit";
			break;
		case UnitID.RANGED_TYPE_ID:
			type="Ranged Unit";
			break;
		}

		if(unit.isAlive()) {
			displayUnitStats(unit,unit.getID().getInstanceNumber(),type);
		}
		else {
			removeUnitStats(type,unit.getID().getInstanceNumber());
		}
	}

	private void displayStructureStats(Structure structure, int instance, String type) {
		String structureStats = type + " " + instance + "\n" 
				+ "Health: " + structure.getCurrentHealth() + "\n"
				+ "Production Rate: " + structure.getMyStats().getProductionRate() + "\n"
				+ "Upkeep: " + structure.getMyStats().getUpkeep();

		String areaText = structureArea.getText();
		int index = areaText.indexOf(type + " " + instance);

		if(index >= 0) {

			String oldStats = areaText.substring(index, areaText.indexOf("\n\n",index));
			areaText = areaText.replace(oldStats, structureStats);
		}
		else {
			areaText = areaText + structureStats + "\n\n";
		}

		structureArea.setText(areaText);
	}

	private void removeStructureStats(String type, int instance) {
		String areaText = structureArea.getText();
		int index = areaText.indexOf(type + " " + instance);
		String unitStats = "\n\n" + areaText.substring(index, areaText.indexOf("\n\n",index));

		areaText = areaText.replace(unitStats, "");
		structureArea.setText(areaText);
	}
	
	@Override
	public void visit(Structure structure) {
		String type="";
		switch(structure.getID().getType())
		{
		case StructureID.CAPITAL_TYPE_ID:
			type="Capital";
			break;
		case StructureID.FARM_TYPE_ID:
			type="Farm";
			break;
		case StructureID.FORT_TYPE_ID:
			type="Fort";
			break;
		case StructureID.MINE_TYPE_ID:
			type="Mine";
			break;
		case StructureID.OBSERVATIONTOWER_TYPE_ID:
			type="Observation Tower";
			break;
		case StructureID.POWERPLANT_TYPE_ID:
			type="Power Plant";
			break;
		case StructureID.UNIVERSITY_TYPE_ID:
			type="University";
			break;
		}
		if(structure.isAlive()) {
			displayStructureStats(structure,structure.getID().getInstanceNumber(),type);
		}
		else {
			removeStructureStats(type,structure.getID().getInstanceNumber());
		}
	}

	public void updateMenu(String mode, String instance, String type, String instruction) {
		this.currentMode.setText("CURRENT MODE= " + currentMode);
		this.currentInstance.setText("CURRENT INSTANCE= " + currentInstance);
		this.currentType.setText("CURRENT TYPE= " + currentType);
		this.currentInstruction.setText("CURRENT INSTRUCTION= " + currentInstruction);
	}
	
	public void update(Unit unit) {
		
		int unitType = unit.getID().getType();
		
		if(unit.isAlive()) {
			
			if(unitType == UnitID.COLONIST_TYPE_ID) {
				displayUnitStats(unit,unit.getID().getInstanceNumber(),"Colonist");
			}
			
			if(unitType == UnitID.EXPLORER_TYPE_ID) {
				displayUnitStats(unit,unit.getID().getInstanceNumber(),"Explorer");
			}
			
			if(unitType == UnitID.MELEE_TYPE_ID) {
				displayUnitStats(unit,unit.getID().getInstanceNumber(),"Melee Unit");
			}
			
			if(unitType == UnitID.RANGED_TYPE_ID) {
				displayUnitStats(unit,unit.getID().getInstanceNumber(),"Ranged Unit");
			}
		}
		else {
			
			if(unitType == UnitID.COLONIST_TYPE_ID) {
				removeUnitStats("Colonist",unit.getID().getInstanceNumber());
			}
			
			if(unitType == UnitID.EXPLORER_TYPE_ID) {
				removeUnitStats("Explorer",unit.getID().getInstanceNumber());
			}
			
			if(unitType == UnitID.MELEE_TYPE_ID) {
				removeUnitStats("Melee Unit",unit.getID().getInstanceNumber());
			}
			
			if(unitType == UnitID.RANGED_TYPE_ID) {
				removeUnitStats("Ranged Unit",unit.getID().getInstanceNumber());
			}
		}
	}
}
