package org.map.controls;

import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.Region;

import org.map.utils.ViewLayout;

public class IntBox extends Region {

	private IntField textBox;

	public IntBox() {

		initComponent("");
	}

	public IntBox(Integer textValue, String promptText) {

		initComponent(promptText);
		textBox.setValue(textValue);
	}

	public IntBox(String promptText, IntegerProperty propertyValue) {

		initComponent(promptText);
		textBox.valueProperty().bindBidirectional(propertyValue);
	}

	private void initComponent(String promptText) {

		setMinSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setPrefSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setMaxSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);

		textBox = new IntField(0);
		textBox.setPrefWidth(ViewLayout.TEXTBOX_WIDTH);
		textBox.setPromptText(promptText);

		getChildren().addAll(textBox);
	}

	public void setText(String textValue) {

		textBox.setText(textValue);
	}

	public String getText() {

		return textBox.getText();
	}

	public IntegerProperty valueProperty() {
		return textBox.valueProperty();
	}

	@Override
	protected void layoutChildren() {

		textBox.resizeRelocate(0, 0, getWidth(), getHeight());
	}
}
