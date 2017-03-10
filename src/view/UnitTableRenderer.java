package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class UnitTableRenderer extends DefaultTableCellRenderer {

	private int currentType = -1;
	private int currentInstance = -1;
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		
		if(row == currentType && column == currentInstance + 1) isSelected = true;
		else isSelected = false;
			
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if(isSelected) {
			label.setBorder(BorderFactory.createLineBorder(Color.RED));
		} 
		else {
			label.setBorder(BorderFactory.createEmptyBorder());
		}
		
		if(value instanceof Structure ) {
			label.setText("");
			label.setBackground(Color.GREEN);
		} else label.setBackground(Color.pink);
		
		return label;
	}

	public void selectUnit(int currentType,int currentInstance) {
		
		this.currentType = currentType;
		this.currentInstance = currentInstance;
	}
	
	public void deSelectUnit() {
		
		this.currentType = -1;
		this.currentInstance = -1;
	}
}
