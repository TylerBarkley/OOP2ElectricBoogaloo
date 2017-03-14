package view;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class CustomTable extends JTable {

	DefaultTableCellRenderer renderer;
	
	public CustomTable(AbstractTableModel model, DefaultTableCellRenderer renderer) {
		super(model);
		this.renderer = renderer;
	}
	
	public TableCellRenderer getCellRenderer(int row, int column) {
		
		if(column != 0) return renderer;
		return super.getCellRenderer(row, column);
	}
}
