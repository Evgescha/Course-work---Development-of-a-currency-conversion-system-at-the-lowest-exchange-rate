package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Currency;

public class CurrencyTableModal extends AbstractTableModel {

	public static final int COL_ID = 0;
	public static final int COL_NAME = 1;

	private final String[] columnNames = { "ИД", "Название валюты" };
	private List<Currency> list;

	public CurrencyTableModal(List<Currency> list) {
		this.list = list;
	}

	@Override
	public Object getValueAt(int row, int col) {

		Currency temp = list.get(row);
		switch (col) {
		case COL_ID:
			return temp.getId();
		case COL_NAME:
			return temp.getName();
		default:
			return temp;
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
