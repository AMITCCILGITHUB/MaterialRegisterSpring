package org.map.controls.combobox;

import java.util.Iterator;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import org.map.controls.TextBox;
import org.map.hibernate.ddo.CodeMaster;
import org.map.hibernate.property.CodeProperty;
import org.map.hibernate.utils.CodeData;
import org.map.utils.ViewLayout;

public class CodeComboBox extends Region {

	private CodeProperty codeProperty;
	private TextBox textNameBox;
	private TextBox textValueBox;
	private Button errorButton;
	private ContextMenu resultContextMenu = new ContextMenu();

	public CodeComboBox() {

		codeProperty = new CodeProperty();
		initComponent("", codeProperty);
	}

	public CodeComboBox(String promptText) {

		codeProperty = new CodeProperty();
		initComponent(promptText, codeProperty);
	}

	public CodeComboBox(String promptText, CodeProperty code) {

		codeProperty = new CodeProperty();
		initComponent(promptText, codeProperty);

		codeProperty.bindBidirectional(code);
	}

	private void initComponent(String promptText, CodeProperty code) {

		setMinSize(ViewLayout.TEXTBOX_WIDTH * 2 + ViewLayout.HGAP,
				ViewLayout.TEXTBOX_HEIGHT);
		setPrefSize(ViewLayout.TEXTBOX_WIDTH * 2 + ViewLayout.HGAP,
				ViewLayout.TEXTBOX_HEIGHT);
		setMaxSize(ViewLayout.TEXTBOX_WIDTH * 2 + ViewLayout.HGAP,
				ViewLayout.TEXTBOX_HEIGHT);

		textNameBox = new TextBox(promptText, codeProperty.get()
				.codeNameProperty());

		textValueBox = new TextBox(promptText, codeProperty.get()
				.codeValueProperty());

		errorButton = new Button();
		errorButton.getStyleClass().add("error-button");
		errorButton.setVisible(false);
		errorButton.setFocusTraversable(false);

		textNameBox.focusedProperty().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {

						if (oldValue == true && newValue == false) {
							if (textNameBox.getText().length() == 0) {
								errorButton.setVisible(true);
							} else {
								errorButton.setVisible(false);
							}
						}
					}
				});

		textNameBox.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				errorButton.setVisible(textNameBox.getText().length() == 0);
			}
		});

		getChildren().addAll(textNameBox, errorButton, textValueBox);
		resultContextMenu.setAutoFix(true);
		showResults();
	}

	public void setText(String textValue) {

		textValueBox.setText(textValue);
	}

	public String getText() {

		return textValueBox.getText();
	}

	@Override
	protected void layoutChildren() {

		textNameBox.resizeRelocate(0, 0, ViewLayout.TEXTBOX_WIDTH,
				ViewLayout.TEXTBOX_HEIGHT);
		errorButton.resizeRelocate(ViewLayout.TEXTBOX_WIDTH - 18, 6, 12, 13);
		textValueBox.resizeRelocate(ViewLayout.TEXTBOX_WIDTH + ViewLayout.HGAP, 0,
				ViewLayout.TEXTBOX_WIDTH, ViewLayout.TEXTBOX_HEIGHT);
	}

	private void showResults() {

		textNameBox.focusedProperty().addListener(
				new ChangeListener<Boolean>() {

					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {

						if (oldValue == true && newValue == false) {
							resultContextMenu.hide();
						}
					}
				});

		textNameBox.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				if (keyEvent.getCode() == KeyCode.DOWN) {
					resultContextMenu.requestFocus();
				} else if (keyEvent.getCode() == KeyCode.ENTER) {
					resultContextMenu.hide();
				} else if (keyEvent.getCode() == KeyCode.ESCAPE) {
					resultContextMenu.hide();
				}
			}
		});

		textNameBox.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

				if (textNameBox.getText().length() == 0) {
					if (resultContextMenu != null) {
						resultContextMenu.hide();
					}
				} else {
					List<CodeMaster> resultList = new CodeData().getCodes(textNameBox.getText());

					if (resultList.size() > 0) {
						populateMenu(resultList);
						if (!resultContextMenu.isShowing()) {
							resultContextMenu.show(textNameBox, Side.BOTTOM,
									10, -5);
						}
					} else {
						populateMenu("No matches");
						if (!resultContextMenu.isShowing()) {
							resultContextMenu.show(textNameBox, Side.BOTTOM,
									10, -5);
						}
					}
					resultContextMenu.requestFocus();
				}
			}
		});
	}

	private void populateMenu(String errorMsg) {
		resultContextMenu.getItems().clear();

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
		resultContextMenu.getItems().add(menuItem);
	}

	private void populateMenu(List<CodeMaster> resultList) {

		resultContextMenu.getItems().clear();
		Iterator<CodeMaster> results = resultList.iterator();

		while (results.hasNext()) {
			final CodeMaster result = (CodeMaster) results.next();
			final HBox hBox = new HBox();
			hBox.setFillHeight(true);
			Label itemLabel = new Label(result.getCodeName());
			itemLabel.getStyleClass().add("item-label");
			hBox.getChildren().addAll(itemLabel);

			final Region popRegion = new Region();
			popRegion.getStyleClass().add("result-menu-item-popup-region");
			popRegion.setPrefSize(10, 10);
			hBox.getChildren().add(popRegion);

			CustomMenuItem menuItem = new CustomMenuItem(hBox, true);
			menuItem.getStyleClass().add("result-menu-item");
			resultContextMenu.getItems().add(menuItem);
			menuItem.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					codeProperty.set(result);
				}
			});
		}
	}

	public CodeProperty codeProperty() {

		return codeProperty;
	}
}
