package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.ConvertSaver;

public class ConvertSaverTableModal extends AbstractTableModel {

	public static final int COL_ID = 0;
	public static final int COL_CURRENCY_CURRENT = 1;
	public static final int COL_CURRENCY_NEW = 2;
	public static final int COL_COUNT = 3;
	public static final int COL_COURSE_TO_ONE_DOLLAR = 4;
	public static final int COL_WHERE = 5;
	public static final int COL_DATES = 6;
	public static final int COL_SUMM = 7;

	private final String[] columnNames = { "ИД", "Из", "В", "Сколько", "Курс", "Где", "Дата", "Получится"};
	private List<ConvertSaver> list;

	public ConvertSaverTableModal(List<ConvertSaver> list) {
		this.list = list;
	}

	@Override
	public Object getValueAt(int row, int col) {

		ConvertSaver temp = list.get(row);
		switch (col) {
		case COL_ID:
			return temp.getId();
		case COL_CURRENCY_CURRENT:
			return temp.getCurrencyCurrent();
		case COL_CURRENCY_NEW:
			return temp.getCurrencyNew();
		case COL_COUNT:
			return temp.getCount();
		case COL_COURSE_TO_ONE_DOLLAR:
			return temp.getCourseToOneDollar();
		case COL_WHERE:
			return temp.getWhere();
		case COL_DATES:
			return temp.getDates();
		case COL_SUMM:
			return temp.getSumm();
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
