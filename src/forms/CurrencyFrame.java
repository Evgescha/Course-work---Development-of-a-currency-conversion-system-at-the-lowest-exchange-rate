package forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import controller.ApplicationController;
import tableModal.CurrencyTableModal;

public class CurrencyFrame extends JFrame {
	private JTable table;
	private JTextField textField;
	private JTextField textField_4;

	public CurrencyFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				back();
			}
		});
		setTitle("Валюта");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 592, 428);
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Введите название валюты для поиска");
		panel.add(lblNewLabel_4);

		textField_4 = new JTextField();
		panel.add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton_1 = new JButton("Искать");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSearchButton();
			}
		});
		panel.add(btnNewButton_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(3, 3, 3, 3));
		getContentPane().add(panel_1, BorderLayout.WEST);

		JLabel lblNewLabel = new JLabel("Название");

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Добавить");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionCreateButton();
			}
		});

		JButton btnNewButton_2 = new JButton("Редактировать");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionUpdateButton();
			}
		});

		JButton btnNewButton_3 = new JButton("Удалить");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDeleteButton();
			}
		});

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false).addComponent(lblNewLabel)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addComponent(lblNewLabel).addGap(1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnNewButton)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_2)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton_3).addGap(223)));
		panel_1.setLayout(gl_panel_1);

		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				actionTableMouseClicked();
			}
		});
		getContentPane().add(scrollPane, BorderLayout.CENTER);

	}

	private void actionDeleteButton() {
		if (table.getRowCount() > 0 && table.getSelectedRowCount() > 0) {
			int row = table.getSelectedRow();
			Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());
			ApplicationController.currencyController.actionDeleteButton(id);
		}
	}

	private void actionUpdateButton() {
		if (allFieldIsRight()) {

			int row = table.getSelectedRow();
			Long id = Long.parseLong(table.getModel().getValueAt(row, 0).toString());

			ApplicationController.currencyController.actionUpdateButton(textField.getText(), id);
			refreshView();
		}
	}

	private void actionCreateButton() {
		if (allFieldIsRight())
			ApplicationController.currencyController.actionCreateButton(textField.getText());

	}

	private void actionTableMouseClicked() {
		if (table.getRowCount() > 0)
			try {
				int row = table.getSelectedRow();
				textField.setText(table.getModel().getValueAt(row, CurrencyTableModal.COL_NAME).toString());
			} catch (Exception e) {
			}

	}

	private void actionSearchButton() {
		ApplicationController.currencyController.actionSearchButton(textField_4.getText().trim(), table);
	}

	public void refreshView() {
		ApplicationController.currencyController.actionSearchButton("", table);
	}

	public void back() {
		ApplicationController.currencyController.back();
	}

	private boolean isHasNumbers() {
		Pattern p = Pattern.compile("([0-9])");
		Matcher m = p.matcher(textField.getText());

		if (m.find()) {
			JOptionPane.showMessageDialog(this, "Ошибка. Поле содержат Цифры.");
			return true;
		}

		return false;
	}

	private boolean isEmpty() {
		boolean flag = false;
		if (textField.getText().length() <= 0)
			flag = true;
		return flag;
	}

	private boolean allFieldIsRight() {
		return !isEmpty() && !isHasNumbers();
	}
}
