package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.CourseDAO;
import defaultOperation.StandartFrameOperation;
import entity.Course;
import forms.CourseFrame;
import tableModal.CourseTableModal;

public class CourseController extends StandartFrameOperation {

	CourseDAO DAO;

	public CourseController(JFrame frame) {
		super(frame);
		try {
			DAO = new CourseDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionSearchButton(String currencyName, JTable table) {
		try {
			List<Course> list = null;

			if (currencyName != null && currencyName.trim().length() > 0)
				list = DAO.search(currencyName);
			else
				list = DAO.readAll();

			CourseTableModal model = new CourseTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(Course entity) {
		try {
			DAO.create(entity);
			refrechView();
			JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionUpdateButton(Course entityNew, Long id) {

		try {
			DAO.update(entityNew, id);
			refrechView();
			JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionDeleteButton(long id) {
		if (id > 0)

			try {
				DAO.Delete(id);
				refrechView();

				JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице",
						"Ошибка", JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
			}

	}

	@Override
	public void switchVisible() {
		super.switchVisible();
		refrechView();
	}

	public void refrechView() {
		((CourseFrame) getFrame()).refreshView();
	}

	public CourseDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainController.switchVisible();

	}

}
