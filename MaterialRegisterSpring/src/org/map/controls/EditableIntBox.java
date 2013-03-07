package org.map.controls;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

import org.map.utils.ViewLayout;

public class EditableIntBox extends Region {

	private IntField textBox;
	private Button editButton;

	public EditableIntBox() {

		initComponent("");
	}

	public EditableIntBox(String promptText, Integer textValue) {

		initComponent(promptText);
		textBox.setText(textValue.toString());
	}

	public EditableIntBox(String promptText, IntegerProperty propertyValue) {

		initComponent(promptText);
		textBox.valueProperty().bindBidirectional(propertyValue);
	}

	public void bind(IntegerProperty propertyValue) {

		textBox.valueProperty().bind(propertyValue);
	}

	public void bindBidirectional(IntegerProperty propertyValue) {

		textBox.valueProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(String promptText) {

		setMinSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setPrefSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setMaxSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);

		textBox = new IntField(0);
		textBox.setPrefWidth(ViewLayout.TEXTBOX_WIDTH);
		textBox.setDisable(true);
		textBox.setPromptText(promptText);

		textBox.focusedProperty().addListener(new ChangeListener<Boolean>() {

			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {

				if (oldValue == true && newValue == false) {
					textBox.setDisable(true);
					editButton.setVisible(true);
				}
			}
		});
		textBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				if (keyEvent.getCode() == KeyCode.ESCAPE
						|| keyEvent.getCode() == KeyCode.ENTER
						|| keyEvent.getCode() == KeyCode.TAB) {
					textBox.setDisable(true);
					editButton.setVisible(true);
				}
			}
		});

		editButton = new Button();
		editButton.getStyleClass().add("edit-button");
		editButton.setVisible(true);
		editButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				textBox.setDisable(false);
				textBox.requestFocus();
				editButton.setVisible(false);
			}
		});

		getChildren().addAll(textBox, editButton);
	}

	public void setText(String textValue) {

		textBox.setText(textValue);
	}

	public String getText() {

		return textBox.getText();
	}

	@Override
	protected void layoutChildren() {

		textBox.resize(getWidth(), getHeight());
		editButton.resizeRelocate(getWidth() - 18, 6, 12, 13);
	}
}
