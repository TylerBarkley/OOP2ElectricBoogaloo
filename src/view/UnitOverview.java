package view;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import control.Menu;
import model.Controllables.Army;
import model.Controllables.ArmyID;
import model.Controllables.Stats.UnitStats;
import model.Controllables.Units.Unit;
import model.Controllables.Units.Unit;
import model.observers.MenuObserver;
import model.observers.UnitObserver;
import model.observers.UnitResourceObserver;
import utilities.ArmyVisitor;
import utilities.UnitVisitor;

public class UnitOverview extends JPanel implements UnitVisitor, ArmyVisitor {

	private int width, height;
	private CustomTable unitTable;
	private UnitTableModel model;
	private TableRenderer renderer;
	private JTextArea unitStatsArea;
	private JLabel currentMode, currentInstance,currentType,currentInstruction;
	
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
		this.currentInstance.setText("CURRENT INSTANCE= " + menu.getCurrentInstanceNumber());
		this.currentType.setText("CURRENT TYPE= " + menu.typeToString());
		this.currentInstruction.setText("CURRENT INSTRUCTION= " + menu.getCurrentInstruction());
		
		if(menu.getCurrentMode() == Menu.UNITMODE) {
			renderer.selectUnit(menu.getCurrentType(), menu.getCurrentInstanceNumber());
			Unit unit = (Unit) menu.getCurrentInstance();
			if(unit != null) displayStats(unit);
			
		} 
		else if(menu.getCurrentMode() == Menu.ARMYMODE) {
			renderer.selectUnit(ArmyID.ARMY_TYPE_ID, menu.getCurrentInstanceNumber());
		}
		else {
			renderer.deSelectUnit();
			removeStats();
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
				+ "\nMovement: " + stats.getMovement() + "\nInfluence Radius: " + stats.getInfluenceRadius() 
				+ "\nOffensive Damage: " + stats.getOffensiveDamage() + "\nDefensive Damage: " + stats.getDefensiveDamage()
				+ "\nArmor: " + stats.getArmor());
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
	
	 
}
