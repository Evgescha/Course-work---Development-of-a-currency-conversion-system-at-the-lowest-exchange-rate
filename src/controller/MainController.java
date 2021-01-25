package controller;

import javax.swing.JFrame;

import defaultOperation.StandartFrameOperation;

public class MainController extends StandartFrameOperation {

	public MainController(JFrame frame) {
		super(frame);
	}

	public void btntActionCurrency() {
		switchVisible();
		ApplicationController.currencyController.switchVisible();		
	}

	public void btntActionCourse() {
		switchVisible();
		ApplicationController.courseController.switchVisible();
	}

	public void btntActionConvert() {
		switchVisible();
		ApplicationController.convertController.switchVisible();
	}


}
