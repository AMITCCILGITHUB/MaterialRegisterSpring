package org.map.controls.cellfactory;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import org.map.constants.ValidationType;
import org.map.controls.combobox.ValidationEditableComboBox;
import org.map.hibernate.ddo.ValidationMaster;
import org.map.hibernate.ddo.MaterialTests;
import org.map.hibernate.property.ValidationProperty;

public class ValidationEditableCellFactory
		implements
		Callback<TableColumn<MaterialTests, ValidationMaster>, TableCell<MaterialTests, ValidationMaster>> {

	private ValidationType type;

	public ValidationEditableCellFactory(ValidationType type) {

		this.type = type;
	}

	@Override
	public TableCell<MaterialTests, ValidationMaster> call(
			TableColumn<MaterialTests, ValidationMaster> param) {

		ValidationEditableComboBoxCell textFieldCell = new ValidationEditableComboBoxCell(
				type);

		return textFieldCell;
	}

	public static class ValidationEditableComboBoxCell extends
			TableCell<MaterialTests, ValidationMaster> {

		private ValidationEditableComboBox validationComboBox;
		private ValidationProperty boundToCurrently = null;

		public ValidationEditableComboBoxCell(ValidationType type) {

			String strCss = "-fx-padding: 0;";

			setStyle(strCss);

			validationComboBox = new ValidationEditableComboBox(type);

			strCss = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";

			validationComboBox.focusedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							ValidationEditableComboBox tf = (ValidationEditableComboBox) getGraphic();
							String strStyleGotFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
							String strStyleLostFocus = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
							if (newValue.booleanValue()) {
								tf.setStyle(strStyleGotFocus);
							} else {
								tf.setStyle(strStyleLostFocus);
							}
						}
					});

			validationComboBox.hoverProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							ValidationEditableComboBox tf = (ValidationEditableComboBox) getGraphic();
							String strStyleGotHover = "-fx-background-color: derive(purple,90%), -fx-text-box-border, derive(-fx-control-inner-background, 10%); -fx-background-insets: 1, 2.8, 3.8; -fx-background-radius: 3.4, 2, 2;";
							String strStyleLostHover = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
							String strStyleHasFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
							if (newValue.booleanValue()) {
								tf.setStyle(strStyleGotHover);
							} else {
								if (!tf.focusedProperty().get()) {
									tf.setStyle(strStyleLostHover);
								} else {
									tf.setStyle(strStyleHasFocus);
								}
							}

						}
					});

			validationComboBox.setStyle(strCss);
			this.setGraphic(validationComboBox);
		}

		@Override
		protected void updateItem(ValidationMaster validationMaster,
				boolean empty) {

			super.updateItem(validationMaster, empty);
			if (!empty) {
				this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				ObservableValue<ValidationMaster> ov = getTableColumn()
						.getCellObservableValue(getIndex());
				ValidationProperty sp = (ValidationProperty) ov;

				if (this.boundToCurrently == null) {
					this.boundToCurrently = sp;
					this.validationComboBox.validationProperty()
							.bindBidirectional(sp);
				} else {
					if (this.boundToCurrently != sp) {
						this.validationComboBox.validationProperty()
								.unbindBidirectional(this.boundToCurrently);
						this.boundToCurrently = sp;
						this.validationComboBox.validationProperty()
								.bindBidirectional(this.boundToCurrently);
					}
				}
			} else {
				this.setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}
	}
}
