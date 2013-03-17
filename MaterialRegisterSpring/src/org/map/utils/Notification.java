package org.map.utils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Window;

public final class Notification extends Popup {

	private final int width = 256;
	private final int gap = 32;

	public Notification() {

		this.setAutoFix(false);
		this.setHideOnEscape(true);

		VBox mainStage = new VBox();

		VBox header = new VBox();
		header.setAlignment(Pos.TOP_LEFT);
		header.getStyleClass().add("popUpHeader");
		Button closeBtn = new Button();
		closeBtn.getStyleClass().add("close-button");
		closeBtn.setPrefSize(24, 24);
		closeBtn.setMinSize(24, 24);
		closeBtn.setMaxSize(24, 24);
		closeBtn.setCursor(Cursor.HAND);
		closeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				hide();
			}
		});

		StackPane sp1 = new StackPane();
		sp1.getChildren().add(closeBtn);
		sp1.setPadding(new Insets(0, 8, 0, 0));
		sp1.setAlignment(Pos.CENTER_RIGHT);
		HBox.setHgrow(sp1, Priority.ALWAYS);

		Label titleLbl = new Label("");
		titleLbl.getStyleClass().add("popUpHeaderLbl");

		header.setPrefSize(256, 48);
		header.getChildren().addAll(titleLbl, sp1);

		VBox footer = new VBox();
		footer.setPrefSize(256, 8);
		footer.setAlignment(Pos.TOP_LEFT);
		footer.getStyleClass().add("popUpFooter");

		mainStage.getChildren().addAll(header, createContent(),
				createSeperator(), createContent(), footer);

		this.getContent().add(mainStage);
	}

	public Node createContent() {

		VBox content = new VBox();
		content.setPrefSize(256, 16);
		content.setPadding(new Insets(8, 5, 5, 5));
		content.setAlignment(Pos.TOP_LEFT);
		content.getStyleClass().add("popUpBody");

		Text contentTxt = new Text("Test");
		contentTxt.getStyleClass().add("menu-text");
		content.getChildren().add(contentTxt);
		VBox.setVgrow(content, Priority.ALWAYS);

		return content;
	}

	public Node createSeperator() {

		VBox content = new VBox();
		content.setPrefSize(256, 3);
		content.setAlignment(Pos.TOP_LEFT);
		content.getStyleClass().add("popUpSeperator");

		return content;
	}

	public void show(Window ownerWindow, MouseEvent me, Button button) {

		double screenX = me.getScreenX() - me.getX();
		double screenY = me.getScreenY() - me.getY();

		int x = (int) (((int) (screenX / button.getPrefWidth())) * button
				.getPrefWidth()) - (width / 2 - gap);
		int y = (int) (((int) (screenY / button.getPrefHeight())) * button
				.getPrefHeight()) + gap;

		if (!isShowing()) {

			show(ownerWindow, x, y);
		}
	}

	@Override
	public void show(Window ownerWindow, double screenX, double screenY) {

		super.show(ownerWindow, screenX, screenY);
	}

}
