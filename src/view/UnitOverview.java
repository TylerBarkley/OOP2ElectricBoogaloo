package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import model.Controllables.Units.Unit;
import model.Controllables.Units.Unit;
import model.observers.MenuObserver;
import model.observers.UnitObserver;
import utilities.UnitVisitor;

public class UnitOverview extends JPanel implements UnitVisitor, UnitObserver, MenuObserver {

	private int width, height;
	private JTable unitTable;
	private UnitTableModel model;
	private JTextArea unitStatsArea;
	private JLabel currentMode, currentInstance,currentType,currentInstruction;
	
	public UnitOverview(int width, int height) {
		
		this.setSize(width, height);
		this.width = width;
		this.height = height;
		
		model = new UnitTableModel();
		unitTable = new JTable(model); 
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
		
		displayView();
	}
	
	public void displayView() {
		
		JLabel title = new JLabel("Unit OverView");
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(title);
		this.add(new FixedScrollPane(unitTable,width,height/3));
		this.add(new FixedScrollPane(unitStatsArea,width,height/3));
		this.add(currentMode);
		this.add(currentType);
		this.add(currentInstance);
		this.add(currentInstruction);
		this.setBackground(Color.ORANGE);
	}
	
	public void visit(Unit unit) {	
		if(unit.isAlive()) {
			model.addUnit(unit);
		}
		else {
			model.removeUnit(unit);
		}
	}

	public void updateMenu(String mode, String instance, String type, String instruction) {
		currentMode.setText(mode);
		currentInstance.setText(instance);
		currentType.setText(type);
		currentInstruction.setText(instruction);
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
	public void update(Unit unit) {
		
		if(unit.isAlive()) {
			model.addUnit(unit);
		}
		else {
			model.removeUnit(unit);
		}
	}
	
}
