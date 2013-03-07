package org.map.controls;

import org.map.utils.ViewLayout;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.Region;

public class ViewCheckBox extends Region {

	private Label label;
	private CheckBox checkBox;
	private String selectedTextValue = "YES";
	private String deselectedTextValue = "NO";
	private String indeterminateTextValue = "N/A";

	public ViewCheckBox() {

		initComponent("");
		checkBox.setText(deselectedTextValue);
		checkBox.setSelected(false);
	}

	public ViewCheckBox(String labelText, int defValue) {

		initComponent(labelText);
		checkBox.setText((defValue == 0) ? indeterminateTextValue
				: (defValue == 1) ? selectedTextValue : deselectedTextValue);
		if (defValue == 0) {
			checkBox.setIndeterminate(true);
		} else if (defValue < 0) {
			checkBox.setSelected(false);
		} else {
			checkBox.setSelected(true);
		}
		checkBox.setText(labelText);
	}

	public ViewCheckBox(String labelText, int defValue,
			final SimpleIntegerProperty value, boolean bidirectionnal) {

		initComponent(labelText);
		value.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldValue, Number newValue) {

				if (newValue == null) {
					checkBox.setText(indeterminateTextValue);
				} else {
					if (newValue.intValue() >= -1 && newValue.intValue() <= 1) {
						checkBox.setText((newValue.intValue() == 0) ? indeterminateTextValue
								: (newValue.intValue() == 1) ? selectedTextValue
										: deselectedTextValue);
					} else {
						checkBox.setText(indeterminateTextValue);
					}
				}
			}
		});
		if (bidirectionnal) {
			checkBox.selectedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(ObservableValue<? extends Boolean> ov,
								Boolean old_val, Boolean new_val) {

							value.set(checkBox.isSelected() ? 1 : (checkBox
									.isIndeterminate() ? 0 : -1));
						}
					});
		}

		checkBox.setText((defValue == 0) ? indeterminateTextValue
				: (defValue == 1) ? selectedTextValue : deselectedTextValue);
	}

	private void initComponent(final String labelText) {

		getStyleClass().add("view-box");

		setMinSize(ViewLayout.LABEL_WIDTH + ViewLayout.CHECKBOX_WIDTH,
				ViewLayout.REGION_HEIGHT);
		setPrefSize(ViewLayout.LABEL_WIDTH + ViewLayout.CHECKBOX_WIDTH,
				ViewLayout.REGION_HEIGHT);
		setMaxSize(ViewLayout.LABEL_WIDTH + ViewLayout.CHECKBOX_WIDTH,
				ViewLayout.REGION_HEIGHT);

		label = new Label(labelText);
		label.setPrefWidth(ViewLayout.LABEL_WIDTH);

		checkBox = CheckBoxBuilder.create().allowIndeterminate(true)
				.disable(true).prefHeight(ViewLayout.CHECKBOX_HEIGHT)
				.prefWidth(ViewLayout.CHECKBOX_WIDTH).build();
		checkBox.getStyleClass().add("view-check-box");
		checkBox.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String oldValue, String newValue) {

				if (newValue.equals(indeterminateTextValue)) {
					checkBox.setIndeterminate(true);
				} else if (newValue.equals(selectedTextValue)) {
					checkBox.setSelected(true);
				} else {
					checkBox.setSelected(false);
				}
			}
		});

		getChildren().addAll(label, checkBox);
	}

	public String getLabelText() {

		return label.getText();
	}

	public void setLabelText(String textVal) {

		label.setText(textVal);
	}

	public int getValue() {

		return (checkBox.isSelected() ? 1 : (checkBox.isIndeterminate() ? 0
				: -1));
	}

	public void setValue(int val) {

		checkBox.setText((val == 0) ? indeterminateTextValue
				: (val == 1) ? selectedTextValue : deselectedTextValue);
	}

	@Override
	protected void layoutChildren() {

		label.resizeRelocate(0, 0, label.getPrefWidth(), getHeight());
		checkBox.resizeRelocate(label.getPrefWidth() + 12, 0,
				checkBox.getPrefWidth(), getHeight());
	}
}
