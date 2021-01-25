package controller;

import forms.ConvertFrame;
import forms.CourseFrame;
import forms.CurrencyFrame;
import forms.MainFrame;

public class ApplicationController {

	public static MainController mainController = new MainController(new MainFrame());

	public static CurrencyController currencyController = new CurrencyController(new CurrencyFrame());
	public static CourseController courseController = new CourseController(new CourseFrame());
	public static ConvertController convertController = new ConvertController(new ConvertFrame());

	public static void main(String[] args) {
		mainController.showFrame();
	}

}
