package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.ConvertSaverDAO;
import defaultOperation.StandartFrameOperation;
import entity.ConvertSaver;
import entity.Course;
import entity.Currency;
import forms.ConvertFrame;
import tableModal.ConvertSaverTableModal;

public class ConvertController extends StandartFrameOperation {

	public ConvertSaverDAO DAO;

	public ConvertController(JFrame frame) {
		super(frame);
		try {
			DAO = new ConvertSaverDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionSearchButton(String name, JTable table) {
		try {
			List<ConvertSaver> list = null;

			if (name != null && name.trim().length() > 0)
				list = DAO.search(name);
			else
				list = DAO.readAll();

			ConvertSaverTableModal model = new ConvertSaverTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(ConvertSaver mt) {
		if (mt.getCount() > 0 && mt.getCurrencyCurrent() != null && mt.getCurrencyNew() != null)
			try {
				DAO.create(mt);
				((ConvertFrame) getFrame()).refreshView();
				JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
			}
	}

	public void actionUpdateButton(ConvertSaver newEntity, Long idOld) {
		try {
			DAO.update(newEntity, idOld);
			((ConvertFrame) getFrame()).refreshView();
			JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionDeleteButton(Long id) {

		try {
			DAO.Delete(id);
			((ConvertFrame) getFrame()).refreshView();
			JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице",
					"Ошибка", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}

	}

	public ConvertSaverDAO getDAO() {
		return DAO;
	}

	public void back() {
		switchVisible();
		ApplicationController.mainController.switchVisible();

	}

	@Override
	public void switchVisible() {
		super.switchVisible();
		refrechView();
	}

	public void refrechView() {
		((ConvertFrame) getFrame()).refreshView();
	}

	public ConvertSaver actionSearchMinimumCourseButton(Currency from, Currency to, float count) {
		ConvertSaver saver = new ConvertSaver();
		saver.setCurrencyCurrent(from);
		saver.setCurrencyNew(to);
		try {
			Course minCourseByCurrency = ApplicationController.courseController.DAO.getMinCourseByCurrency(from.getId());
			Course maxCourseByCurrency = ApplicationController.courseController.DAO.getMaxCourseByCurrency(to.getId());
			
			String where1="Перевести "+ from.getName()+" в доллар в "+minCourseByCurrency.getWhere();
			String where2="Купить за полученные доллары "+to.getName()+" в "+maxCourseByCurrency.getWhere();
			
			String course1="Перевести в доллары по курсу "+minCourseByCurrency.getCourseToOneDollar();
			String course2="За доллары купить "+to.getName()+" по курсу "+maxCourseByCurrency.getCourseToOneDollar();
			
			float getDollars=count*minCourseByCurrency.getCourseToOneDollar();
			float summ=getDollars/maxCourseByCurrency.getCourseToOneDollar();
			
			saver.setCourseToOneDollar(course1+", "+course2);
			saver.setWhere(where1+", "+where2);
			saver.setSumm(summ);
			saver.setCount(count);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saver;
	}

}
