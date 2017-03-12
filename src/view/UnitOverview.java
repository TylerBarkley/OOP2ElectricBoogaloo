package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import control.Menu;
import model.Controllables.Units.Unit;
import model.Controllables.Units.Unit;
import model.observers.MenuObserver;
import model.observers.UnitObserver;
import utilities.UnitVisitor;

public class UnitOverview extends JPanel implements UnitVisitor {

	private int width, height;
	private JTable unitTable;
	private UnitTableModel model;
	private TableRenderer renderer;
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
		
		renderer = new TableRenderer();
		unitTable.setDefaultRenderer(Unit.class, renderer);
		
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

	public void updateMenu(Menu menu) {
		
		this.currentMode.setText("CURRENT MODE= " + menu.modeToString());
		this.currentInstance.setText("CURRENT INSTANCE= " + menu.getCurrentInstance());
		this.currentType.setText("CURRENT TYPE= " + menu.typeToString());
		this.currentInstruction.setText("CURRENT INSTRUCTION= " + menu.getCurrentInstance());
		
		if(menu.getCurrentMode() == Menu.UNITMODE) {
			renderer.selectUnit(menu.getCurrentType(), menu.getCurrentInstance());
		} 
		else {
			renderer.deSelectUnit();
		}
		model.update();
		
		repaint();
	}
}
