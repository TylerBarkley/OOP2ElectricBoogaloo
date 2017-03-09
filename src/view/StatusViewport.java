package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

import utilities.UnitVisitor;
import model.Controllables.Units.Colonist;
import model.Controllables.Units.Explorer;
import model.Controllables.Units.Melee;
import model.Controllables.Units.Ranged;
import model.Controllables.Units.Unit;

public class StatusViewport extends JPanel implements UnitVisitor {

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

	
	public void visit(Colonist unit) {
		
		if(unit.getCurrentHealth() > 0) {
			
			displayUnitStats(unit,unit.getID().getInstanceNumber(),"Colonist");
		}
		else {
			
			removeUnitStats("Colonist",unit.getID().getInstanceNumber());
		}
	}

	
	public void visit(Explorer unit) {
		
		if(unit.getCurrentHealth() > 0) {
			
			displayUnitStats(unit,unit.getID().getInstanceNumber(),"Explorer");
		}
		else {
			
			removeUnitStats("Explorer",unit.getID().getInstanceNumber());
		}
	}

	
	public void visit(Melee unit) {
		
		if(unit.getCurrentHealth() > 0) {
			
			displayUnitStats(unit,unit.getID().getInstanceNumber(),"Melee Unit");
		}
		else {
			
			removeUnitStats("Melee Unit",unit.getID().getInstanceNumber());
		}
	}

	
	public void visit(Ranged unit) {
		
		if(unit.getCurrentHealth() > 0) {
			
			displayUnitStats(unit,unit.getID().getInstanceNumber(),"Ranged Unit");
		}
		else {
			
			removeUnitStats("Ranged Unit",unit.getID().getInstanceNumber());
		}
	}
	
}
