package org.map.controls;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.layout.Region;

import org.map.utils.ViewLayout;

public class ViewIntegerBox extends Region {

	private IntField textBox;

	public ViewIntegerBox() {

		initComponent();
		textBox.setValue(1);
	}

	public ViewIntegerBox(int value) {

		initComponent();
		textBox.setValue(value);
	}

	public ViewIntegerBox(IntegerProperty propertyValue) {

		initComponent();
		textBox.valueProperty().bindBidirectional(propertyValue);
	}

	public ViewIntegerBox(int value, IntegerProperty propertyValue) {

		initComponent();
		textBox.setValue(value);
		textBox.valueProperty().bindBidirectional(propertyValue);
	}

	public void bind(StringProperty propertyValue) {

		textBox.textProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		textBox.textProperty().bindBidirectional(propertyValue);
	}

	private void initComponent() {

		getStyleClass().add("view-text-box");

		setMinSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setPrefSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setMaxSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);

		textBox = new IntField(0);
		textBox.setPrefWidth(ViewLayout.TEXTBOX_WIDTH);

		textBox.setDisable(true);

		getChildren().addAll(textBox);
	}

	public void setValue(int value) {

		textBox.setValue(value);
	}

	public int getValue() {

		return textBox.getValue();
	}

	@Override
	protected void layoutChildren() {

		textBox.resizeRelocate(0, 0, getWidth(), getHeight());
	}
}
