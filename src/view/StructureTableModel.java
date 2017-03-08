package view;

import javax.swing.table.AbstractTableModel;

public class StructureTableModel extends AbstractTableModel {

	public static final int STRUCTURE_TYPES = 4;
	public static final int MAX_PER_TYPE = 10;
	public static final String STRUCTURENAMES[]= {"BASE","MINE","FARM","RESEACH CENTER"};
	
	 private String columnNames[];
	private Object data[][]; 
	
	public StructureTableModel() {
		generateColumnNames();
		generateData();
	
	}
	
	private void generateColumnNames() {
		columnNames = new String[MAX_PER_TYPE+1];
		columnNames[0] = "Structure Types";
		
		for(int i = 1; i <= MAX_PER_TYPE;i++) {
			columnNames[i] = Integer.toString(i);
		}
	}
	
	private void generateData() {
		
		data = new Object[STRUCTURENAMES.length][MAX_PER_TYPE+1];
		
		for(int j = 0; j <=MAX_PER_TYPE; j++) {
			for(int i = 0; i < STRUCTURENAMES.length;i++) {
				
				if(j == 0) data[i][j] = STRUCTURENAMES[i];
				else data[i][j] = new String("");
			}
		}
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0,c).getClass();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	
}
