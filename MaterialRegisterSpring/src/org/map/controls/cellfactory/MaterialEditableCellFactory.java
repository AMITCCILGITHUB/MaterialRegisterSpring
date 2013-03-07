package org.map.controls.cellfactory;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

import org.map.controls.EditableBox;
import org.map.hibernate.ddo.HeatChartSheets;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialTests;
import org.map.hibernate.utils.MaterialData;

public class MaterialEditableCellFactory
		implements
		Callback<TableColumn<HeatChartSheets, String>, TableCell<HeatChartSheets, String>> {

	private ObservableList<HeatChartSheets> data = FXCollections
			.observableArrayList();

	public MaterialEditableCellFactory(ObservableList<HeatChartSheets> data) {

		this.data = data;
	}

	@Override
	public TableCell<HeatChartSheets, String> call(
			TableColumn<HeatChartSheets, String> param) {

		EditableBoxCell textFieldCell = new EditableBoxCell();
		return textFieldCell;
	}

	public class EditableBoxCell extends TableCell<HeatChartSheets, String> {

		private EditableBox textField;
		private StringProperty boundToCurrently = null;

		public EditableBoxCell() {

			String strCss;

			strCss = "-fx-padding: 0;";

			setStyle(strCss);

			textField = new EditableBox();

			strCss = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";

			textField.focusedProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							EditableBox tf = (EditableBox) getGraphic();
							String strStyleGotFocus = "-fx-background-color: purple, -fx-text-box-border, -fx-control-inner-background; -fx-background-insets: -0.4, 1, 2; -fx-background-radius: 3.4, 2, 2;";
							String strStyleLostFocus = "-fx-background-color: -fx-control-inner-background; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 3 5 3 5; -fx-prompt-text-fill: derive(-fx-control-inner-background,-30%); -fx-cursor: text;";
							if (newValue.booleanValue()) {
								tf.setStyle(strStyleGotFocus);
							} else {
								tf.setStyle(strStyleLostFocus);
							}
						}
					});

			textField.hoverProperty().addListener(
					new ChangeListener<Boolean>() {

						@Override
						public void changed(
								ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {

							EditableBox tf = (EditableBox) getGraphic();
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

			textField.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent keyEvent) {
					if (keyEvent.getCode() == KeyCode.ENTER) {
						setHeatChartSheet(textField.getText());
						
						int index = 1;
						for(HeatChartSheets testSheet : data){
							testSheet.setSequenceNumber(index);
							index++;
						}
					}
				}
			});
			textField.setStyle(strCss);
			this.setGraphic(textField);
		}

		@Override
		protected void updateItem(String item, boolean empty) {

			super.updateItem(item, empty);
			if (!empty) {
				this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				ObservableValue<String> ov = getTableColumn()
						.getCellObservableValue(getIndex());
				SimpleStringProperty sp = (SimpleStringProperty) ov;

				if (this.boundToCurrently == null) {
					this.boundToCurrently = sp;
					this.textField.textProperty().bindBidirectional(sp);
				} else {
					if (this.boundToCurrently != sp) {
						this.textField.textProperty().unbindBidirectional(
								this.boundToCurrently);
						this.boundToCurrently = sp;
						this.textField.textProperty().bindBidirectional(
								this.boundToCurrently);
					}
				}
				
				if(item.trim().length() == 0){
					data.get(getIndex()).getMaterialmaster().clean();
					data.get(getIndex()).getMaterialtests().clean();
				}
			} else {
				this.setContentDisplay(ContentDisplay.TEXT_ONLY);
			}
		}

		public void setHeatChartSheet(String ctNumber) {

			MaterialMaster master = new MaterialData().getMaterialDetails(ctNumber);

			int insertIndex = getIndex();
			HeatChartSheets insertTestSheet = data.get(insertIndex);
			for (MaterialTests materialTests : master.getMaterialTests()) {
				boolean matched = false;
				for (HeatChartSheets sheet : data) {
					if (master.getCtNumber().equalsIgnoreCase(
							sheet.getMaterialmaster().getCtNumber())
							&& materialTests.getSampleId().equalsIgnoreCase(
									sheet.getMaterialtests().getSampleId())) {
						if (insertTestSheet.getSheetNumber() == sheet.getSheetNumber()) {
							matched = true;
							break;
						}
					}
				}
				if (!matched) {
					if (insertIndex != getIndex()) {
						HeatChartSheets addTestSheet = new HeatChartSheets();
						
						addTestSheet.setHeatChartSheetCode(insertTestSheet.getHeatChartSheetCode());
						addTestSheet.setSequenceNumber(insertTestSheet.getSequenceNumber());
						addTestSheet.setSheetNumber(insertTestSheet.getSheetNumber());
						
						addTestSheet.setPartNumber(insertTestSheet.getPartNumber());
						addTestSheet.setPartName(insertTestSheet.getPartName());
						addTestSheet.setSpecifiedSize(insertTestSheet.getSpecifiedSize());
						addTestSheet.setSpecifiedGrade(insertTestSheet.getSpecifiedGrade());

						data.add(insertIndex, addTestSheet);
					}
					data.get(insertIndex).setMaterialmaster(new MaterialMaster(master));
					data.get(insertIndex).setMaterialtests(new MaterialTests(materialTests));
					insertIndex++;
				}
			}
		}
	}
}
