package org.map.utils;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import org.map.constants.ValidationType;
import org.map.controls.cellfactory.DatePickerCellFactory;
import org.map.controls.cellfactory.EditableTextCellFactory;
import org.map.controls.cellfactory.MaterialCellFactory;
import org.map.controls.cellfactory.MaterialEditableCellFactory;
import org.map.controls.cellfactory.TextCellFactory;
import org.map.controls.cellfactory.ValidationCellFactory;
import org.map.controls.cellfactory.ValidationEditableCellFactory;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.HeatChartSheets;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialTests;
import org.map.hibernate.ddo.UserMaster;
import org.map.hibernate.ddo.ValidationMaster;
import org.map.hibernate.property.ValidationProperty;
import org.map.hibernate.register.MaterialRegister;

public class TableUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<UserMaster> createViewUserTable() {
		
		TableView<UserMaster> table = new TableView<>();

		TableColumn<UserMaster, String> MCol1 = new TableColumn<UserMaster, String>("User Name");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
				"userName"));
		MCol1.prefWidthProperty().bind(table.widthProperty().divide(4));

		TableColumn<UserMaster, String> MCol2 = new TableColumn<UserMaster, String>("Password");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
				"password"));
		MCol2.setCellFactory(new Callback<TableColumn<UserMaster, String>, TableCell<UserMaster, String>>() {

			@Override
			public TableCell<UserMaster, String> call(
					TableColumn<UserMaster, String> param) {

				TableCell<UserMaster, String> cell = new TableCell<UserMaster, String>() {

					@Override
					public void updateItem(String item, boolean empty) {

						super.updateItem(item, empty);

						if (!empty) {
							this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

							ObservableValue<String> ov = getTableColumn()
									.getCellObservableValue(getIndex());
							StringProperty sp = (StringProperty) ov;

							setStyle("-fx-padding: 0;");
							if (item != null) {

								PasswordField ps = new PasswordField();
								ps.setStyle("-fx-background-color: transparent;");
								ps.setDisable(true);
								ps.textProperty().bindBidirectional(sp);
								setGraphic(ps);
							}
						} else {
							this.setContentDisplay(ContentDisplay.TEXT_ONLY);
						}
					}
				};
				return cell;
			}
		});
		MCol2.prefWidthProperty().bind(table.widthProperty().divide(4));

		TableColumn MCol3 = new TableColumn("Role");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new Callback<CellDataFeatures<UserMaster, String>, StringProperty>() {

			@Override
			public StringProperty call(CellDataFeatures<UserMaster, String> p) {

				return p.getValue().roleProperty().getValue()
						.nameProperty();
			}
		});
		MCol3.prefWidthProperty().bind(table.widthProperty().divide(4));

		TableColumn<UserMaster, String> MCol4 = new TableColumn<UserMaster, String>("User Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
				"userStatus"));
		MCol4.prefWidthProperty().bind(table.widthProperty().divide(4));

		table.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);
		
		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<UserMaster> createEditUserTable() {

		TableView<UserMaster> table = new TableView<>();

		TableColumn<UserMaster, String> MCol1 = new TableColumn<UserMaster, String>("User Name");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
				"userName"));
		MCol1.prefWidthProperty().bind(table.widthProperty().divide(4));

		TableColumn<UserMaster, String> MCol2 = new TableColumn<UserMaster, String>("Password");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
				"password"));
		MCol2.setCellFactory(new Callback<TableColumn<UserMaster, String>, TableCell<UserMaster, String>>() {

			@Override
			public TableCell<UserMaster, String> call(
					TableColumn<UserMaster, String> param) {

				TableCell<UserMaster, String> cell = new TableCell<UserMaster, String>() {

					PasswordField ps = new PasswordField();

					@Override
					public void updateItem(String item, boolean empty) {

						super.updateItem(item, empty);

						if (!empty) {
							this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

							ObservableValue<String> ov = getTableColumn()
									.getCellObservableValue(getIndex());
							StringProperty sp = (StringProperty) ov;

							setStyle("-fx-padding: 0;");
							if (item != null) {

								ps.setDisable(true);
								ps.setStyle("-fx-background-color: transparent;");
								ps.textProperty().bindBidirectional(sp);
								setGraphic(ps);
							}
						} else {
							this.setContentDisplay(ContentDisplay.TEXT_ONLY);
						}
					}
				};
				return cell;
			}
		});
		MCol2.prefWidthProperty().bind(table.widthProperty().divide(4));

		TableColumn MCol3 = new TableColumn("Role");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new Callback<CellDataFeatures<UserMaster, String>, StringProperty>() {

			@Override
			public StringProperty call(CellDataFeatures<UserMaster, String> p) {

				return p.getValue().roleProperty().getValue()
						.nameProperty();
			}
		});
		MCol3.prefWidthProperty().bind(table.widthProperty().divide(4));

		TableColumn<UserMaster, String> MCol4 = new TableColumn<UserMaster, String>("User Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<UserMaster, String>(
				"userStatus"));
		MCol4.prefWidthProperty().bind(table.widthProperty().divide(4));

		table.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		return table;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static TableView<MaterialTests> createAddMaterialTable() {

		TableView<MaterialTests> table = new TableView<>();
		table.setTranslateY(16);

		TableColumn MCol1 = new TableColumn("Sample Id");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"sampleId"));
		MCol1.setCellFactory(new TextCellFactory());

		TableColumn MCol2 = new TableColumn("Test");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, ValidationMaster>, ValidationProperty>() {

			@Override
			public ValidationProperty call(
					CellDataFeatures<MaterialTests, ValidationMaster> p) {

				return p.getValue().testProperty();
			}
		});
		MCol2.setCellFactory(new ValidationCellFactory(ValidationType.TEST));

		TableColumn MCol3 = new TableColumn("Customer");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, ValidationMaster>, ValidationProperty>() {

			@Override
			public ValidationProperty call(
					CellDataFeatures<MaterialTests, ValidationMaster> p) {

				return p.getValue().customerProperty();
			}
		});
		MCol3.setCellFactory(new ValidationCellFactory(ValidationType.CUSTOMER));

		TableColumn MCol4 = new TableColumn("Equipments");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"equipments"));
		MCol4.setCellFactory(new TextCellFactory());

		TableColumn MCol5 = new TableColumn("Laboratory");
		MCol5.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol5.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, ValidationMaster>, ValidationProperty>() {

			@Override
			public ValidationProperty call(
					CellDataFeatures<MaterialTests, ValidationMaster> p) {

				return p.getValue().laboratoryProperty();
			}
		});
		MCol5.setCellFactory(new ValidationCellFactory(ValidationType.LABORATORY));

		TableColumn MCol6 = new TableColumn("Report Date");
		MCol6.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol6.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"reportDate"));
		MCol6.setCellFactory(new DatePickerCellFactory());

		TableColumn MCol7 = new TableColumn("Report Number");
		MCol7.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol7.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"reportNumber"));
		MCol7.setCellFactory(new TextCellFactory());

		TableColumn MCol8 = new TableColumn("Result");
		MCol8.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol8.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, ValidationMaster>, ValidationProperty>() {

			@Override
			public ValidationProperty call(
					CellDataFeatures<MaterialTests, ValidationMaster> p) {

				return p.getValue().resultProperty();
			}
		});
		MCol8.setCellFactory(new ValidationCellFactory(ValidationType.RESULT));

		TableColumn MCol9 = new TableColumn("Remarks");
		MCol9.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol9.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"remarks"));
		MCol9.setCellFactory(new TextCellFactory());

		TableColumn MCol10 = new TableColumn("Witnessed By");
		MCol10.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol10.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"witnessedBy"));
		MCol10.setCellFactory(new TextCellFactory());

		TableColumn MCol11 = new TableColumn("Failure Reason");
		MCol11.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol11.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"failureReason"));
		MCol11.setCellFactory(new TextCellFactory());

		table.getColumns().addAll(MCol1, MCol2, MCol3, MCol4, MCol5, MCol6,
				MCol7, MCol8, MCol9, MCol10, MCol11);

		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<MaterialMaster> createSearchMaterialTable() {

		TableView<MaterialMaster> table = new TableView<>();

		TableColumn MCol1 = new TableColumn("CT Number");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
				"ctNumber"));

		TableColumn MCol2 = new TableColumn("Inspection Agency");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new Callback<CellDataFeatures<MaterialMaster, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialMaster, String> p) {

				return p.getValue().getInspectionAgency().nameProperty();
			}
		});

		TableColumn MCol3 = new TableColumn("Specification");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new Callback<CellDataFeatures<MaterialMaster, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialMaster, String> p) {

				return p.getValue().getSpecification()
						.nameProperty();
			}
		});

		TableColumn MCol4 = new TableColumn("Item");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new Callback<CellDataFeatures<MaterialMaster, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialMaster, String> p) {

				return p.getValue().getItem().nameProperty();
			}
		});

		TableColumn MCol5 = new TableColumn("Size");
		MCol5.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol5.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
				"size"));

		TableColumn MCol6 = new TableColumn("Quantity");
		MCol6.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol6.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
				"quantity"));

		TableColumn MCol7 = new TableColumn("Heat Number");
		MCol7.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol7.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
				"heatNumber"));

		TableColumn MCol8 = new TableColumn("Plate Number");
		MCol8.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol8.setCellValueFactory(new PropertyValueFactory<MaterialMaster, String>(
				"plateNumber"));

		table.getColumns().addAll(MCol1, MCol2, MCol3, MCol4, MCol5, MCol6,
				MCol7, MCol8);

		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<MaterialTests> createViewMaterialTable() {

		TableView<MaterialTests> table = new TableView<>();
		table.setTranslateY(16);

		TableColumn MCol1 = new TableColumn("Sample Id");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"sampleId"));

		TableColumn MCol2 = new TableColumn("Test");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, String>, StringProperty>() {

			@Override
			public StringProperty call(CellDataFeatures<MaterialTests, String> p) {

				return p.getValue().getTest().nameProperty();
			}
		});

		TableColumn MCol3 = new TableColumn("Customer");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, String>, StringProperty>() {

			@Override
			public StringProperty call(CellDataFeatures<MaterialTests, String> p) {

				return p.getValue().getCustomer().nameProperty();
			}
		});

		TableColumn MCol4 = new TableColumn("Equipments");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"equipments"));

		TableColumn MCol5 = new TableColumn("Laboratory");
		MCol5.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol5.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, String>, StringProperty>() {

			@Override
			public StringProperty call(CellDataFeatures<MaterialTests, String> p) {

				return p.getValue().getLaboratory().nameProperty();
			}
		});

		TableColumn MCol6 = new TableColumn("Report Date");
		MCol6.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol6.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"reportDate"));

		TableColumn MCol7 = new TableColumn("Report Number");
		MCol7.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol7.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"reportNumber"));

		TableColumn MCol8 = new TableColumn("Result");
		MCol8.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol8.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, String>, StringProperty>() {

			@Override
			public StringProperty call(CellDataFeatures<MaterialTests, String> p) {

				return p.getValue().getResult().nameProperty();
			}
		});

		TableColumn MCol9 = new TableColumn("Remarks");
		MCol9.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol9.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"remarks"));

		TableColumn MCol10 = new TableColumn("Witnessed By");
		MCol10.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol10.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"witnessedBy"));

		TableColumn MCol11 = new TableColumn("Failure Reason");
		MCol11.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol11.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"failureReason"));

		table.getColumns().addAll(MCol1, MCol2, MCol3, MCol4, MCol5, MCol6,
				MCol7, MCol8, MCol9, MCol10, MCol11);

		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<MaterialTests> createEditMaterialTable() {

		TableView<MaterialTests> table = new TableView<>();
		table.setTranslateY(16);

		TableColumn MCol1 = new TableColumn("Sample Id");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"sampleId"));
		MCol1.setCellFactory(new EditableTextCellFactory());

		TableColumn MCol2 = new TableColumn("Test");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, ValidationMaster>, ValidationProperty>() {

			@Override
			public ValidationProperty call(
					CellDataFeatures<MaterialTests, ValidationMaster> p) {

				return p.getValue().testProperty();
			}
		});
		MCol2.setCellFactory(new ValidationEditableCellFactory(ValidationType.TEST));

		TableColumn MCol3 = new TableColumn("Customer");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, ValidationMaster>, ValidationProperty>() {

			@Override
			public ValidationProperty call(
					CellDataFeatures<MaterialTests, ValidationMaster> p) {

				return p.getValue().customerProperty();
			}
		});
		MCol3.setCellFactory(new ValidationEditableCellFactory(ValidationType.CUSTOMER));

		TableColumn MCol4 = new TableColumn("Equipments");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"equipments"));
		MCol4.setCellFactory(new EditableTextCellFactory());

		TableColumn MCol5 = new TableColumn("Laboratory");
		MCol5.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol5.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, ValidationMaster>, ValidationProperty>() {

			@Override
			public ValidationProperty call(
					CellDataFeatures<MaterialTests, ValidationMaster> p) {

				return p.getValue().laboratoryProperty();
			}
		});
		MCol5.setCellFactory(new ValidationEditableCellFactory(ValidationType.LABORATORY));

		TableColumn MCol6 = new TableColumn("Report Date");
		MCol6.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol6.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"reportDate"));
		MCol6.setCellFactory(new DatePickerCellFactory());

		TableColumn MCol7 = new TableColumn("Report Number");
		MCol7.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol7.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"reportNumber"));
		MCol7.setCellFactory(new EditableTextCellFactory());

		TableColumn MCol8 = new TableColumn("Result");
		MCol8.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol8.setCellValueFactory(new Callback<CellDataFeatures<MaterialTests, ValidationMaster>, ValidationProperty>() {

			@Override
			public ValidationProperty call(
					CellDataFeatures<MaterialTests, ValidationMaster> p) {

				return p.getValue().resultProperty();
			}
		});
		MCol8.setCellFactory(new ValidationEditableCellFactory(ValidationType.RESULT));

		TableColumn MCol9 = new TableColumn("Remarks");
		MCol9.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol9.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"remarks"));
		MCol9.setCellFactory(new EditableTextCellFactory());

		TableColumn MCol10 = new TableColumn("Witnessed By");
		MCol10.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol10.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"witnessedBy"));
		MCol10.setCellFactory(new EditableTextCellFactory());

		TableColumn MCol11 = new TableColumn("Failure Reason");
		MCol11.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol11.setCellValueFactory(new PropertyValueFactory<MaterialTests, String>(
				"failureReason"));
		MCol11.setCellFactory(new EditableTextCellFactory());

		table.getColumns().addAll(MCol1, MCol2, MCol3, MCol4, MCol5, MCol6,
				MCol7, MCol8, MCol9, MCol10, MCol11);

		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<MaterialRegister> createViewMaterialRegisterTable() {

		TableView<MaterialRegister> table = new TableView<>();

		TableColumn Col1 = new TableColumn("CT Number");
		Col1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col1.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"ctNumber"));

		TableColumn Col2 = new TableColumn("Inspection Agency");
		Col2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col2.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"inspectionAgency"));
		Col2.setCellValueFactory(new Callback<CellDataFeatures<MaterialRegister, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialRegister, String> p) {

				return p.getValue().inspectionAgencyProperty();
			}
		});

		TableColumn Col3 = new TableColumn("Description");

		TableColumn Col31 = new TableColumn("Item");
		Col31.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col31.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"item"));
		Col31.setCellValueFactory(new Callback<CellDataFeatures<MaterialRegister, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialRegister, String> p) {

				return p.getValue().itemProperty();
			}
		});

		TableColumn Col32 = new TableColumn("Size");
		Col32.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col32.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"size"));

		TableColumn Col33 = new TableColumn("Quantity");
		Col33.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col33.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"quantity"));

		TableColumn Col34 = new TableColumn("Heat / Lot Number");
		Col34.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col34.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"heatNumber"));

		TableColumn Col35 = new TableColumn("Plate / Product Number");
		Col35.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col35.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"plateNumber"));
		Col3.getColumns().addAll(Col31, Col32, Col33, Col34, Col35);

		TableColumn Col4 = new TableColumn("Specification");
		Col4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col4.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"specification"));
		Col4.setCellValueFactory(new Callback<CellDataFeatures<MaterialRegister, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialRegister, String> p) {

				return p.getValue().specificationProperty();
			}
		});

		TableColumn Col5 = new TableColumn("Test");
		Col5.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col5.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"test"));
		Col5.setCellValueFactory(new Callback<CellDataFeatures<MaterialRegister, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialRegister, String> p) {

				return p.getValue().testProperty();
			}
		});

		TableColumn Col6 = new TableColumn("Validation");
		Col6.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col6.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"customer"));
		Col6.setCellValueFactory(new Callback<CellDataFeatures<MaterialRegister, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialRegister, String> p) {

				return p.getValue().customerProperty();
			}
		});

		TableColumn Col7 = new TableColumn("Equipments");
		Col7.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col7.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"equipments"));

		TableColumn Col8 = new TableColumn("Laboratory");
		Col8.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col8.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"laboratory"));
		Col8.setCellValueFactory(new Callback<CellDataFeatures<MaterialRegister, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialRegister, String> p) {

				return p.getValue().laboratoryProperty();
			}
		});

		TableColumn Col9 = new TableColumn("Report");

		TableColumn Col91 = new TableColumn("Number");
		Col91.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col91.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"reportNumber"));

		TableColumn Col92 = new TableColumn("Date");
		Col92.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col92.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"reportDate"));
		Col9.getColumns().addAll(Col91, Col92);

		TableColumn Col10 = new TableColumn("Result");
		Col10.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col10.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"result"));
		Col10.setCellValueFactory(new Callback<CellDataFeatures<MaterialRegister, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<MaterialRegister, String> p) {

				return p.getValue().resultProperty();
			}
		});

		TableColumn Col11 = new TableColumn("Remarks");
		Col11.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col11.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"remarks"));

		TableColumn Col12 = new TableColumn("Witnessed By");
		Col12.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col12.setCellValueFactory(new PropertyValueFactory<MaterialRegister, String>(
				"witnessedBy"));

		table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6, Col7,
				Col8, Col9, Col10, Col11, Col12);

		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<HeatChartSheets> createAddHeatChartSheetTable(
			ObservableList<HeatChartSheets> data) {

		TableView<HeatChartSheets> table = new TableView<>();

		TableColumn Col1 = new TableColumn("Sr. No.");
		Col1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col1.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

			@Override
			public ObservableIntegerValue call(
					CellDataFeatures<HeatChartSheets, Integer> p) {

				return p.getValue().sequenceNumberProperty();
			}
		});

		TableColumn Col2 = new TableColumn("Sheet No.");
		Col2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col2.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

			@Override
			public ObservableIntegerValue call(
					CellDataFeatures<HeatChartSheets, Integer> p) {

				return p.getValue().sheetNumberProperty();
			}
		});

		TableColumn Col3 = new TableColumn("Part No");
		Col3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col3.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"partNumber"));
		Col3.setCellFactory(new TextCellFactory());

		TableColumn Col4 = new TableColumn("Part Name(s)");
		Col4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col4.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"partName"));
		Col4.setCellFactory(new TextCellFactory());

		TableColumn Col5 = new TableColumn("Material Specification");
		Col5.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col51 = new TableColumn("Specified");
		Col51.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col511 = new TableColumn("Size");
		Col511.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col511.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"specifiedSize"));
		Col511.setCellFactory(new TextCellFactory());

		TableColumn Col512 = new TableColumn("Grade");
		Col512.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col512.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"specifiedGrade"));
		Col512.setCellFactory(new TextCellFactory());

		TableColumn Col52 = new TableColumn("Utilized");
		Col52.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col521 = new TableColumn("Size");
		Col521.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col521.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialmaster().sizeProperty();
			}
		});

		TableColumn Col522 = new TableColumn("Grade");
		Col522.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col522.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialmaster().getSpecification()
						.nameProperty();
			}
		});

		TableColumn Col53 = new TableColumn("Check / Testing");
		Col53.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col53.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialmaster().ctNumberProperty();
			}
		});
		Col53.setCellFactory(new MaterialCellFactory(data));

		Col51.getColumns().addAll(Col511, Col512);
		Col52.getColumns().addAll(Col521, Col522);
		Col5.getColumns().addAll(Col51, Col52, Col53);

		TableColumn Col6 = new TableColumn("Test Certificate");
		Col6.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col61 = new TableColumn("Number");
		Col61.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col61.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialtests().reportNumberProperty();
			}
		});

		TableColumn Col62 = new TableColumn("Date");
		Col62.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col62.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialtests().reportDateProperty();
			}
		});

		TableColumn Col63 = new TableColumn("Laboratory");
		Col63.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col63.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialtests().getLaboratory()
						.nameProperty();
			}
		});

		Col6.getColumns().addAll(Col61, Col62, Col63);
		table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6);

		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<HeatChartMaster> createSearchHeatChartTable() {

		TableView<HeatChartMaster> table = new TableView<>();

		TableColumn MCol1 = new TableColumn("Chart Number");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>(
				"chartNumber"));

		TableColumn MCol2 = new TableColumn("Equipment");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>(
				"equipment"));

		TableColumn MCol3 = new TableColumn("customer");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>(
				"customer"));

		TableColumn MCol4 = new TableColumn("PO Details");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>(
				"poDetails"));

		TableColumn MCol5 = new TableColumn("Drawing Number");
		MCol5.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol5.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>(
				"drawingNumber"));

		TableColumn MCol6 = new TableColumn("Surveyor");
		MCol6.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol6.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>(
				"surveyor"));

		TableColumn MCol7 = new TableColumn("Status");
		MCol7.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol7.setCellValueFactory(new PropertyValueFactory<HeatChartMaster, String>(
				"status"));

		table.getColumns().addAll(MCol1, MCol2, MCol3, MCol4, MCol5, MCol6,
				MCol7);

		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<HeatChartSheets> createViewHeatChartSheetTable() {

		TableView<HeatChartSheets> table = new TableView<>();

		TableColumn Col1 = new TableColumn("Sr. No.");
		Col1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col1.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

			@Override
			public ObservableIntegerValue call(
					CellDataFeatures<HeatChartSheets, Integer> p) {

				return p.getValue().sequenceNumberProperty();
			}
		});

		TableColumn Col2 = new TableColumn("Sheet No.");
		Col2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col2.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

			@Override
			public ObservableIntegerValue call(
					CellDataFeatures<HeatChartSheets, Integer> p) {

				return p.getValue().sheetNumberProperty();
			}
		});

		TableColumn Col3 = new TableColumn("Part No");
		Col3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col3.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"partNumber"));

		TableColumn Col4 = new TableColumn("Part Name(s)");
		Col4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col4.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"partName"));

		TableColumn Col5 = new TableColumn("Material Specification");
		Col5.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col51 = new TableColumn("Specified");
		Col51.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col511 = new TableColumn("Size");
		Col511.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col511.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"specifiedSize"));

		TableColumn Col512 = new TableColumn("Grade");
		Col512.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col512.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"specifiedGrade"));

		TableColumn Col52 = new TableColumn("Utilized");
		Col52.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col521 = new TableColumn("Size");
		Col521.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col521.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialmaster().sizeProperty();
			}
		});

		TableColumn Col522 = new TableColumn("Grade");
		Col522.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col522.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialmaster().getSpecification()
						.nameProperty();
			}
		});

		TableColumn Col53 = new TableColumn("Check / Testing");
		Col53.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col53.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialmaster().ctNumberProperty();
			}
		});

		Col51.getColumns().addAll(Col511, Col512);
		Col52.getColumns().addAll(Col521, Col522);
		Col5.getColumns().addAll(Col51, Col52, Col53);

		TableColumn Col6 = new TableColumn("Test Certificate");
		Col6.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col61 = new TableColumn("Number");
		Col61.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col61.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialtests().reportNumberProperty();
			}
		});

		TableColumn Col62 = new TableColumn("Date");
		Col62.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col62.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialtests().reportDateProperty();
			}
		});

		TableColumn Col63 = new TableColumn("Laboratory");
		Col63.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col63.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialtests().getLaboratory()
						.nameProperty();
			}
		});

		Col6.getColumns().addAll(Col61, Col62, Col63);
		table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6);

		return table;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TableView<HeatChartSheets> createEditHeatChartSheetTable(
			ObservableList<HeatChartSheets> data) {

		TableView<HeatChartSheets> table = new TableView<>();

		TableColumn Col1 = new TableColumn("Sr. No.");
		Col1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col1.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

			@Override
			public ObservableIntegerValue call(
					CellDataFeatures<HeatChartSheets, Integer> p) {

				return p.getValue().sequenceNumberProperty();
			}
		});

		TableColumn Col2 = new TableColumn("Sheet No.");
		Col2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col2.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, Integer>, ObservableIntegerValue>() {

			@Override
			public ObservableIntegerValue call(
					CellDataFeatures<HeatChartSheets, Integer> p) {

				return p.getValue().sheetNumberProperty();
			}
		});

		TableColumn Col3 = new TableColumn("Part No");
		Col3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col3.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"partNumber"));
		Col3.setCellFactory(new EditableTextCellFactory());

		TableColumn Col4 = new TableColumn("Part Name(s)");
		Col4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col4.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"partName"));
		Col4.setCellFactory(new EditableTextCellFactory());

		TableColumn Col5 = new TableColumn("Material Specification");
		Col5.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col51 = new TableColumn("Specified");
		Col51.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col511 = new TableColumn("Size");
		Col511.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col511.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"specifiedSize"));
		Col511.setCellFactory(new EditableTextCellFactory());

		TableColumn Col512 = new TableColumn("Grade");
		Col512.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col512.setCellValueFactory(new PropertyValueFactory<HeatChartSheets, String>(
				"specifiedGrade"));
		Col512.setCellFactory(new EditableTextCellFactory());

		TableColumn Col52 = new TableColumn("Utilized");
		Col52.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col521 = new TableColumn("Size");
		Col521.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col521.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialmaster().sizeProperty();
			}
		});

		TableColumn Col522 = new TableColumn("Grade");
		Col522.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col522.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialmaster().getSpecification()
						.nameProperty();
			}
		});

		TableColumn Col53 = new TableColumn("Check / Testing");
		Col53.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col53.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialmaster().ctNumberProperty();
			}
		});
		Col53.setCellFactory(new MaterialEditableCellFactory(data));

		Col51.getColumns().addAll(Col511, Col512);
		Col52.getColumns().addAll(Col521, Col522);
		Col5.getColumns().addAll(Col51, Col52, Col53);

		TableColumn Col6 = new TableColumn("Test Certificate");
		Col6.setPrefWidth(ViewLayout.COLUMN_WIDTH);

		TableColumn Col61 = new TableColumn("Number");
		Col61.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col61.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialtests().reportNumberProperty();
			}
		});

		TableColumn Col62 = new TableColumn("Date");
		Col62.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col62.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialtests().reportDateProperty();
			}
		});

		TableColumn Col63 = new TableColumn("Laboratory");
		Col63.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		Col63.setCellValueFactory(new Callback<CellDataFeatures<HeatChartSheets, String>, StringProperty>() {

			@Override
			public StringProperty call(
					CellDataFeatures<HeatChartSheets, String> p) {

				return p.getValue().getMaterialtests().getLaboratory()
						.nameProperty();
			}
		});

		Col6.getColumns().addAll(Col61, Col62, Col63);
		table.getColumns().addAll(Col1, Col2, Col3, Col4, Col5, Col6);

		return table;
	}
}
