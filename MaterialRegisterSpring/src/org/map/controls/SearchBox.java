package org.map.controls;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;

import org.map.hibernate.utils.MaterialData;

public class SearchBox extends Region {

	private TextField textBox;
	private Button clearButton;
	private ContextMenu contextMenu = new ContextMenu();
	private Popup extraInfoPopup = new Popup();
	private Label infoName;
	private Label infoDescription;
	private VBox infoBox;

	public SearchBox() {

		setId("SearchBox");
		setMinHeight(24);
		setPrefSize(150, 24);
		setMaxHeight(24);
		textBox = new TextField();
		textBox.setPromptText("Search");
		clearButton = new Button();
		clearButton.setVisible(false);
		getChildren().addAll(textBox, clearButton);
		clearButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				textBox.setText("");
				textBox.requestFocus();
			}
		});
		textBox.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				if (keyEvent.getCode() == KeyCode.DOWN) {
					contextMenu.requestFocus();
				}
			}
		});

		textBox.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				clearButton.setVisible(textBox.getText().length() != 0);
				if (textBox.getText().trim().length() <= 2) {
					if (contextMenu != null) {
						contextMenu.hide();
					}
				} else {
					ArrayList<String> results = (ArrayList<String>) new MaterialData()
							.searchMaterialDetails(textBox.getText().trim());

					if (results.size() > 0) {
						populateMenu(results.iterator());
					} else {
						populateMenu("No matches");
					}
					if (!contextMenu.isShowing()) {
						contextMenu.show(textBox, Side.BOTTOM, 10, -5);
					}
					contextMenu.requestFocus();
				}
			}
		});
		// create info popup
		infoBox = new VBox();
		infoBox.setId("search-info-box");
		infoBox.setFillWidth(true);
		infoBox.setMinWidth(USE_PREF_SIZE);
		infoBox.setPrefWidth(350);
		infoName = new Label();
		infoName.setId("search-info-name");
		infoName.setMinHeight(USE_PREF_SIZE);
		infoName.setPrefHeight(28);
		infoDescription = new Label();
		infoDescription.setId("search-info-description");
		infoDescription.setWrapText(true);
		infoDescription.setPrefWidth(infoBox.getPrefWidth() - 24);
		infoBox.getChildren().addAll(infoName, infoDescription);
		extraInfoPopup.getContent().add(infoBox);
		// hide info popup when context menu is hidden
		contextMenu.setOnHidden(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent windowEvent) {

				extraInfoPopup.hide();
			}
		});
	}

	private void populateMenu(String errorMsg) {
		contextMenu.getItems().clear();

		final HBox hBox = new HBox();
		hBox.setFillHeight(true);
		Label itemLabel = new Label(errorMsg);
		itemLabel.getStyleClass().add("item-label");
		hBox.getChildren().addAll(itemLabel);

		final Region popRegion = new Region();
		popRegion.getStyleClass().add("result-menu-item-popup-region");
		popRegion.setPrefSize(10, 10);
		hBox.getChildren().add(popRegion);

		CustomMenuItem menuItem = new CustomMenuItem(hBox, true);
		menuItem.getStyleClass().add("result-menu-item");
		contextMenu.getItems().add(menuItem);
	}

	private void populateMenu(Iterator<String> results) {

		contextMenu.getItems().clear();
		while (results.hasNext()) {
			final String result = results.next();
			final HBox hBox = new HBox();
			hBox.setFillHeight(true);
			Label itemLabel = new Label(result);
			itemLabel.getStyleClass().add("item-label");
			hBox.getChildren().addAll(itemLabel);

			final Region popRegion = new Region();
			popRegion.getStyleClass().add("result-menu-item-popup-region");
			popRegion.setPrefSize(10, 10);
			hBox.getChildren().add(popRegion);

			CustomMenuItem menuItem = new CustomMenuItem(hBox, true);
			menuItem.getStyleClass().add("result-menu-item");
			contextMenu.getItems().add(menuItem);
			menuItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {
					//Context.changePageArea("View Material", result);
				}
			});
		}
	}

	@Override
	protected void layoutChildren() {

		textBox.resize(getWidth(), getHeight());
		clearButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
	}
}
