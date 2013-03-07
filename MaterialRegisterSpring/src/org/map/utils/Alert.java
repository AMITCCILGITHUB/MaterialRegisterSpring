package org.map.utils;

import java.net.MalformedURLException;

import javafx.stage.Stage;

public class Alert extends MessagePopUp {

	private Alert(Stage parentStage, String title, String type, String message)
			throws MalformedURLException {

		super(parentStage, title, type, message);
	}

	private Alert(Stage parentStage, String title, String type, String message,
			double width) throws MalformedURLException {

		super(parentStage, title, type, message, width);
	}

	public static Alert showAlert(Stage parentStage, String title, String type,
			String message) {

		Alert alert = null;
		try {
			alert = new Alert(parentStage, title, type, message);
		} catch (Exception ex) {

		}
		return alert;
	}

	public static Alert showAlert(Stage parentStage, String title, String type,
			String message, double width) {

		Alert alert = null;
		try {
			alert = new Alert(parentStage, title, type, message, width);
		} catch (Exception ex) {

		}
		return alert;
	}
}
