package forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import controller.ApplicationController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class MainFrame extends JFrame{
	public MainFrame() {
		setTitle("Обменник валют");
		initialize();
	}

	private void initialize() {
	
	setBounds(100, 100, 344, 150);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(null);
	
	JButton btnNewButton = new JButton("Валюта");
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ApplicationController.mainController.btntActionCurrency();
		}

	});
	btnNewButton.setBounds(10, 11, 308, 23);
	getContentPane().add(btnNewButton);
	
	JButton btnNewButton_1 = new JButton("Курсы банков");
	btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ApplicationController.mainController.btntActionCourse();
		}
	});
	btnNewButton_1.setBounds(10, 45, 308, 23);
	getContentPane().add(btnNewButton_1);
	
	JButton btnNewButton_2 = new JButton("Обмен валют");
	btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			ApplicationController.mainController.btntActionConvert();
		}
	});
	btnNewButton_2.setBounds(10, 77, 308, 23);
	getContentPane().add(btnNewButton_2);
	}
}
