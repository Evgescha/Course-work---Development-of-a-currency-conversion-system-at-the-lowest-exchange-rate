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

import controller.ApplicationController;
import entity.Course;
import entity.Currency;


public class CourseFrame extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JComboBox<Currency> comboBoxCurrency;
	private JTextField textField_2;

	public CourseFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}
		});
		setTitle("Расход");
		setBounds(100, 100, 823, 490);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Введите название диска для поиска");
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

		JLabel lblNewLabel = new JLabel("Валюта");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Добавить");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCreateButton();
			}
		});

		JButton btnNewButton_3 = new JButton("Удалить");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDeleteButton();
			}
		});
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		panel_1.add(lblNewLabel);

		comboBoxCurrency = new JComboBox();
		panel_1.add(comboBoxCurrency);

		JLabel lblNewLabel_1_2 = new JLabel("Курс к 1 доллару");
		panel_1.add(lblNewLabel_1_2);
		panel_1.add(textField_1);

		JLabel lblNewLabel_1_2_1 = new JLabel("Название банка");
		panel_1.add(lblNewLabel_1_2_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_1.add(textField_2);
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
			ApplicationController.courseController.actionDeleteButton(Long.parseLong(id));
		}
	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				Course entity = (Course) table.getModel().getValueAt(row, 99);

				Currency currency = entity.getCurrency();

				comboBoxCurrency.getModel().setSelectedItem(currency);
				textField_1.setText(entity.getCourseToOneDollar() + "");
				textField_2.setText(entity.getWhere() + "");
			} catch (Exception e) {
			}
	}

	private void actionCreateButton() {
		if (allFieldIsRight()) {
			Course course = new Course((Currency) comboBoxCurrency.getSelectedItem(),Float.parseFloat(textField_1.getText()),textField_2.getText());
			ApplicationController.courseController.actionCreateButton(course);
		}
	}

	private void actionSearchButton() {
		ApplicationController.courseController.actionSearchButton(textField.getText().trim(), table);
		refreshComboBoxes();
	}

	public void refreshView() {

		ApplicationController.courseController.actionSearchButton("", table);
		refreshComboBoxes();
	}

	public void refreshComboBoxes() {
		try {
			comboBoxCurrency.setModel(
					new DefaultComboBoxModel(ApplicationController.currencyController.getDAO().readAll().toArray()));
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void back() {
		ApplicationController.courseController.back();
	}

	private boolean isEmpty() {
		boolean flag = false;
		if (textField_1.getText().length() <= 0)
			flag = true;
		if (textField_2.getText().length() <= 0)
			flag = true;	
		
		try{Float.parseFloat(textField_1.getText());}catch (Exception e) {
			flag = true;
		}
		
		if (flag)
			JOptionPane.showMessageDialog(this, "Проверьте корректное заполнение всех полей.");
		return flag;
	}

	private boolean allFieldIsRight() {
		return !isEmpty();
	}
}
