package ws4java6.gui;

import javax.swing.table.AbstractTableModel;

import ws4java6.data.IStockTimeSeries;

public class MyModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IStockTimeSeries timeSeries;
	String[] columnNames = { "Date", "Low", "High", "Open", "Close", "Volume" };

	public void setModel(IStockTimeSeries timeSeries) {
		this.timeSeries = timeSeries;
	}

	public int getColumnCount() {
		return 6;
	}

	public int getRowCount() {
		return timeSeries.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return timeSeries.getStock(rowIndex).getDate();
		case 1:
			return timeSeries.getStock(rowIndex).getLow();
		case 2:
			return timeSeries.getStock(rowIndex).getHigh();
		case 3:
			return timeSeries.getStock(rowIndex).getOpen();
		case 4:
			return timeSeries.getStock(rowIndex).getClose();
		case 5:
			return timeSeries.getStock(rowIndex).getVolume();
		}
		throw new RuntimeException("getValue called for not existing column");
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Class getColumnClass(int column) {
		Class returnValue;
		if ((column >= 0) && (column < getColumnCount())) {
			if (timeSeries.size() <= 0) {
				return returnValue = Object.class;
			}
			returnValue = getValueAt(0, column).getClass();
		} else {
			returnValue = Object.class;
		}
		return returnValue;
	}
}
