package view;

import javax.swing.table.AbstractTableModel;

import model.Controllables.Army;
import model.Controllables.Units.Unit;


public class UnitTableModel extends AbstractTableModel {

	public static final int UNIT_TYPES = 4;
	public static final int MAX_PER_TYPE = 10, MAX_ARMIES = 10;
	public static final String UNITNAMES[]= {"COLONIST","EXPLORER","MELEE","RANGE","ARMY"};
	
	 private String columnNames[];
	private Object data[][]; 
	
	public UnitTableModel() {
		generateColumnNames();
		generateData();
	
	}
	
	private void generateColumnNames() {
		columnNames = new String[MAX_PER_TYPE+1];
		columnNames[0] = "Unit Types";
		
		for(int i = 1; i <= MAX_PER_TYPE;i++) {
			columnNames[i] = Integer.toString(i);
		}
	}
	
	private void generateData() {
		
		data = new Object[UNITNAMES.length][MAX_PER_TYPE+1];
		
		for(int j = 0; j <=MAX_PER_TYPE; j++) {
			for(int i = 0; i < UNITNAMES.length;i++) {
				
				if(j == 0) data[i][j] = UNITNAMES[i];
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
	
	public void addUnit(Unit unit) {
		
		int row = unit.getID().getType()-1;
		int column = unit.getID().getInstanceNumber()+1;
		
		data[row][column] = unit;
		this.fireTableCellUpdated(row, column);
	}
	
	public void removeUnit(Unit unit) {
		
		int row = unit.getID().getType	()-1;
		int column = unit.getID().getInstanceNumber()+1;
		
		data[row][column] = new String("");
		this.fireTableCellUpdated(row, column);
		
	}
	
	public void addArmy(Army army) {
		
		int row = UNITNAMES.length - 1;
		int column = army.getID().getInstanceNumber() + 1;
		
		data[row][column] = army;
		this.fireTableCellUpdated(row, column);
	}
	
	public void removeArmy(Army army) {
		
		int row = UNITNAMES.length - 1;
		int column = army.getID().getInstanceNumber() + 1;
		
		data[row][column] = new String("");
		this.fireTableCellUpdated(row, column);
	}

	public void update() {
		this.fireTableDataChanged();
	}
}
