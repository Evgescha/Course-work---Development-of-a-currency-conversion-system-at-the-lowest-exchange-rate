package controller;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import dao.CurrencyDAO;
import defaultOperation.StandartFrameOperation;
import entity.Currency;
import forms.CurrencyFrame;
import tableModal.CurrencyTableModal;

public class CurrencyController extends StandartFrameOperation {

	public CurrencyDAO DAO;

	public CurrencyController(JFrame frame) {
		super(frame);
		try {
			DAO = new CurrencyDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionSearchButton(String name, JTable table) {
		try {
			List<Currency> list = null;

			if (name != null && name.trim().length() > 0)
				list = DAO.search(name);
			else
				list = DAO.readAll();

			CurrencyTableModal model = new CurrencyTableModal(list);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionCreateButton(String name) {
		Currency mt = new Currency(name);
		try {
			DAO.create(mt);
			((CurrencyFrame) getFrame()).refreshView();
			JOptionPane.showMessageDialog(getFrame(), "Успешно добавлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionUpdateButton(String nameNew, Long idOld) {
		try {
			DAO.update(nameNew, idOld);
			((CurrencyFrame) getFrame()).refreshView();
			JOptionPane.showMessageDialog(getFrame(), "Успешно обновлено", "Успех", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка: " + e, "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void actionDeleteButton(Long id) {

		try {
			DAO.Delete(id);
			((CurrencyFrame) getFrame()).refreshView();
			JOptionPane.showMessageDialog(getFrame(), "Успешно удалено", "Успех", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getFrame(), "Ошибка. Возможно элемент используется в другой таблице",
					"Ошибка", JOptionPane.ERROR_MESSAGE);
			System.out.println(e);
		}

	}

	public CurrencyDAO getDAO() {
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
		((CurrencyFrame) getFrame()).refreshView();
	}

}
