package tableModal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Course;

public class CourseTableModal extends AbstractTableModel {

	public static final int COL_ID = 0;
	public static final int COL_CURRENCY = 1;
	public static final int COL_COURSE_TO_ONE_DOLLAR = 2;
	public static final int COL_WHERE = 3;

	private final String[] columnNames = { "ИД", "Валюта", "Курс к одному доллару", "В каком банке" };
	private List<Course> list;

	public CourseTableModal(List<Course> list) {
		this.list = list;
	}

	@Override
	public Object getValueAt(int row, int col) {

		Course temp = list.get(row);
		switch (col) {
		case COL_ID:
			return temp.getId();
		case COL_CURRENCY:
			return temp.getCurrency();
		case COL_COURSE_TO_ONE_DOLLAR:
			return temp.getCourseToOneDollar();
		case COL_WHERE:
			return temp.getWhere();
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
