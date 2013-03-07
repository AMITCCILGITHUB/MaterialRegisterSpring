package org.map.controls;

import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

import org.map.utils.ViewLayout;

public class ViewBox extends Region {

	private TextField textBox;

	public ViewBox() {

		initComponent();
	}

	public ViewBox(String textValue) {

		initComponent();
		textBox.setText(textValue);
	}

	public ViewBox(StringProperty propertyValue) {

		initComponent();
		textBox.textProperty().bindBidirectional(propertyValue);
	}

	public ViewBox(String textValue, StringProperty propertyValue) {

		initComponent();
		textBox.setText(textValue);
		textBox.textProperty().bindBidirectional(propertyValue);
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

		textBox = new TextField();
		textBox.setPrefWidth(ViewLayout.TEXTBOX_WIDTH);
		textBox.setDisable(true);

		getChildren().addAll(textBox);
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
	}
}
