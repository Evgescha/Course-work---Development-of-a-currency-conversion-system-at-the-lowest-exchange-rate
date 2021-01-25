package forms;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import controller.ApplicationController;
import entity.ConvertSaver;
import entity.Currency;

public class ConvertFrame extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JComboBox<Currency> comboBoxCurrency;
	private JComboBox<Currency> comboBoxCurrency2;
	private JDateChooser dateChooser;
	private JTextField textField_2;

	
	private ConvertSaver convertSaver=null;
	
	
	public ConvertFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}
		});
		setTitle("Обмен");
		setBounds(100, 100, 823, 490);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Введите название исходной валюты для поиска");
		panel.add(lblNewLabel_4);

		textField = new JTextField();
		textField.setColumns(10);
		panel.add(textField);

		JButton btnNewButton_1 = new JButton("Поиск");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSearchButton();
			}
		});
		panel.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(3, 3, 3, 3));
		getContentPane().add(panel_1, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Исходная валюта");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Сохранить в базу");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionCreateButton();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton btnNewButton_3 = new JButton("Удалить с базы");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDeleteButton();
			}
		});
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		panel_1.add(lblNewLabel);

		comboBoxCurrency = new JComboBox();
		panel_1.add(comboBoxCurrency);

		JLabel lblNewLabel_1_1 = new JLabel("На какую меняем");
		panel_1.add(lblNewLabel_1_1);

		comboBoxCurrency2 = new JComboBox();
		panel_1.add(comboBoxCurrency2);

		JLabel lblNewLabel_1_2 = new JLabel("Количество старой валюты");
		panel_1.add(lblNewLabel_1_2);
		panel_1.add(textField_1);

		JLabel lblNewLabel_1_2_1 = new JLabel("Дата обмена");
		panel_1.add(lblNewLabel_1_2_1);

		dateChooser = new JDateChooser();
		panel_1.add(dateChooser);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Итого получится");
		panel_1.add(lblNewLabel_1_2_2);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		panel_1.add(textField_2);
		
		JButton btnNewButton_2 = new JButton("Поиск наименьшего курса");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findMinimumCourse();
			}
		});
		panel_1.add(btnNewButton_2);
		panel_1.add(btnNewButton);
		panel_1.add(btnNewButton_3);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				actionTableMouseClicked();
			}
		});
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

	}

	private void actionDeleteButton() {
		if (table.getRowCount() > 0 && table.getSelectedRowCount() > 0) {
			int row = table.getSelectedRow();
			String id = table.getModel().getValueAt(row, 0).toString();
			ApplicationController.convertController.actionDeleteButton(Long.parseLong(id));
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				ConvertSaver entity = (ConvertSaver) table.getModel().getValueAt(row, 99);

				Currency CurrencyCur = entity.getCurrencyCurrent();
				Currency CurrencyNew = entity.getCurrencyNew();

				comboBoxCurrency.getModel().setSelectedItem(CurrencyCur);
				comboBoxCurrency2.getModel().setSelectedItem(CurrencyNew);
				textField_1.setText(entity.getCount() + "");
				dateChooser.setDate(entity.getDates());
			} catch (Exception e) {
			}
	}

	private void actionCreateButton() throws Exception {
		if (allFieldIsRight() && convertSaver!=null) {
			ApplicationController.convertController.actionCreateButton(convertSaver);
			convertSaver=null;
		}
	}

	private void actionSearchButton() {
		ApplicationController.convertController.actionSearchButton(textField.getText().trim(), table);
		refreshComboBoxes();
	}

	public void refreshView() {

		ApplicationController.convertController.actionSearchButton("", table);
		refreshComboBoxes();
	}

	public void refreshComboBoxes() {
		try {
			comboBoxCurrency.setModel(
					new DefaultComboBoxModel(ApplicationController.currencyController.getDAO().readAll().toArray()));
			comboBoxCurrency2.setModel(
					new DefaultComboBoxModel(ApplicationController.currencyController.getDAO().readAll().toArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void back() {
		ApplicationController.convertController.back();
	}

	private boolean isEmpty() {
		boolean flag = false;
		if (textField_1.getText().length() <= 0)
			flag = true;
		if (textField_2.getText().length() <= 0)
			flag = true;
		try {
			new Date(dateChooser.getDate().getTime());
			Float.parseFloat(textField_1.getText());
			Float.parseFloat(textField_2.getText());
		} catch (Exception e) {
			flag = true;
			e.printStackTrace();
		}
		if (flag)
			JOptionPane.showMessageDialog(this, "Проверьте корректное заполнение всех полей.");
		return flag;
	}

	private boolean allFieldIsRight() {
		return !isEmpty();
	}
	
	private void findMinimumCourse() {
		//!!!!!!!!!!!!
		Currency from = (Currency)comboBoxCurrency.getSelectedItem();
		Currency to = (Currency)comboBoxCurrency2.getSelectedItem();
		convertSaver=ApplicationController.convertController.actionSearchMinimumCourseButton(from, to, Float.parseFloat(textField_1.getText()));
			convertSaver.setDates(new Date(dateChooser.getDate().getTime()));
			textField_2.setText(convertSaver.getSumm()+"");
		System.out.println(convertSaver);

		JOptionPane.showMessageDialog(this, convertSaver);
		
	}
}
