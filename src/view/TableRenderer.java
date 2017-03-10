package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRenderer extends DefaultTableCellRenderer {

	private int currentType = -1;
	private int currentInstance = -1;
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		
		if(row == currentType -1 && column == currentInstance + 1) isSelected = true;
		else isSelected = false;
			
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if(isSelected) {
			label.setBorder(BorderFactory.createLineBorder(Color.RED));
		} 
		else {
			label.setBorder(BorderFactory.createEmptyBorder());
		}
		
		if(value.toString() != "") {
			label.setText("");
			label.setBackground(Color.GREEN);
		} else label.setBackground(Color.white);
		
		return label;
	}
	
	public void selectUnit(int unitType, int unitInstance) {
		
		currentType = unitType;
		currentInstance = unitInstance;
	}
	
	public void deSelectUnit() {
		
		currentType = -1;
		currentInstance = -1;
	}
}
