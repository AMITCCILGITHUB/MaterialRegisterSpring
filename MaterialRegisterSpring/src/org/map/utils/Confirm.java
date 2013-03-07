package org.map.utils;

import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Confirm extends MessagePopUp {

	private Confirm(Stage parentStage, String title, String type,
			String message, EventHandler<ActionEvent> event)
			throws MalformedURLException {

		super(parentStage, title, type, message);
		setActionBox(event);
	}

	private Confirm(Stage parentStage, String title, String type,
			String message, double width, EventHandler<ActionEvent> event)
			throws MalformedURLException {

		super(parentStage, title, type, message, width);
		setActionBox(event);
	}

	public static Confirm showConfirm(Stage parentStage, String title,
			String type, String message, EventHandler<ActionEvent> event) {

		Confirm confirm = null;
		try {
			confirm = new Confirm(parentStage, title, type, message, event);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return confirm;
	}

	public static Confirm showConfirm(Stage parentStage, String title,
			String type, String message, double width,
			EventHandler<ActionEvent> event) {

		Confirm confirm = null;
		try {
			confirm = new Confirm(parentStage, title, type, message, width,
					event);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return confirm;
	}

	private void setActionBox(final EventHandler<ActionEvent> event) {

		final HBox actionBox = super.getActionBox();
		actionBox.getChildren().clear();
		actionBox.setSpacing(15);

		Button okBtn = new Button("Yes");
		okBtn.getStyleClass().add("submit-button");
		okBtn.setPrefWidth(75);
		okBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				event.handle(actionEvent);
				closePopUp();
			}
		});

		Button cancelBtn = new Button("No");
		cancelBtn.getStyleClass().add("submit-button");
		cancelBtn.setPrefWidth(75);
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				closePopUp();
			}
		});
		actionBox.getChildren().addAll(okBtn, cancelBtn);
	}
}
