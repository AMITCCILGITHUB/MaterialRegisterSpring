package org.map.controls.combobox;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Region;

import org.map.hibernate.utils.CodeData;
import org.map.hibernate.utils.ValidationData;
import org.map.utils.AppProperties;
import org.map.utils.ViewLayout;

public class CustomComboBox extends Region {

	private String type;
	private ComboBox<String> comboBox;

	public CustomComboBox() {

		initComponent();
	}

	public CustomComboBox(String type) {

		setType(type);

		initComponent();
	}

	public CustomComboBox(String type, String textValue) {

		setType(type);

		initComponent();
		comboBox.getSelectionModel().select(textValue);
	}

	public CustomComboBox(String type, StringProperty propertyValue) {

		setType(type);

		initComponent();
		comboBox.valueProperty().bindBidirectional(propertyValue);
	}

	public void bind(StringProperty propertyValue) {

		comboBox.valueProperty().bind(propertyValue);
	}

	public void bindBidirectional(StringProperty propertyValue) {

		comboBox.valueProperty().bindBidirectional(propertyValue);
	}

	private void initComponent() {

		setMinSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setPrefSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setMaxSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);

		comboBox = new ComboBox<String>();
		comboBox.getStyleClass().add("result-combo-box");
		comboBox.setPrefWidth(ViewLayout.TEXTBOX_WIDTH);

		if (type.equalsIgnoreCase("Material")) {
			comboBox.getItems().addAll(
					AppProperties.getValue("material.year.list").split(","));
		} else if (type.equalsIgnoreCase("HeatChart")) {
			comboBox.getItems().addAll(
					AppProperties.getValue("heatchart.year.list").split(","));

		} else if (type.equalsIgnoreCase("Agency")) {
			comboBox.getItems().addAll(
					new ValidationData().getValidationNameList("Agency"));
		} else if (type.equalsIgnoreCase("Customer")) {
			comboBox.getItems().addAll(
					new ValidationData().getValidationNameList("Customer"));
		} else if (type.equalsIgnoreCase("Item")) {
			comboBox.getItems().addAll(
					new ValidationData().getValidationNameList("Item"));
		} else if (type.equalsIgnoreCase("Laboratory")) {
			comboBox.getItems().addAll(
					new ValidationData().getValidationNameList("Laboratory"));
		} else if (type.equalsIgnoreCase("Result")) {
			comboBox.getItems().addAll(
					new ValidationData().getValidationNameList("Result"));
		} else if (type.equalsIgnoreCase("Role")) {
			comboBox.getItems().addAll(
					new ValidationData().getValidationNameList("Role"));
		} else if (type.equalsIgnoreCase("Specification")) {
			comboBox.getItems().addAll(
					new ValidationData().getValidationNameList("Specification"));
		} else if (type.equalsIgnoreCase("Test")) {
			comboBox.getItems().addAll(
					new ValidationData().getValidationNameList("Test"));
		} else if (type.equalsIgnoreCase("Code")) {
			comboBox.getItems().addAll(new CodeData().getCodeNameList());
		} else if (type.equalsIgnoreCase("ValidationType")) {
			//TODO
			//comboBox.getItems().addAll(Arrays.asList(ValidationType.values()));
		}

		getChildren().addAll(comboBox);
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public void setText(String textValue) {

		comboBox.getSelectionModel().select(textValue);
	}

	public String getText() {

		return comboBox.getSelectionModel().getSelectedItem();
	}

	@Override
	protected void layoutChildren() {

		comboBox.resize(getWidth(), getHeight());
	}

	public void addChangeListener(
			ChangeListener<? super String> selectionChangeListener) {

		comboBox.getSelectionModel().selectedItemProperty()
				.addListener(selectionChangeListener);
	}

	public ObjectProperty<String> valueProperty() {

		return comboBox.valueProperty();
	}
}
