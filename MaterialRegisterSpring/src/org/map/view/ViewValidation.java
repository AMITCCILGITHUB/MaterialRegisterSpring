package org.map.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;

import org.map.controls.ErrorView;
import org.map.hibernate.ddo.ValidationMaster;
import org.map.hibernate.utils.ValidationData;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("ViewValidation")
public class ViewValidation {

	@Resource(name = "ValidationData")
	private ValidationData validationData;

	private TabPane pane;

	public ViewValidation() {

		pane = new TabPane();
	}

	public Node showView() {

		try {
			createAgencyTab();

			createCustomerTab();

			createItemTab();

			createLaboratoryTab();

			createResultTab();

			createSpecificationTab();

			createTestTab();

			return pane;
		} catch (Exception e) {
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());

			return new ErrorView();
		}
	}

	@SuppressWarnings("unchecked")
	public void createAgencyTab() {

		Tab agencyTab = new Tab("Agency");

		VBox main = ViewLayout.getMainVBox("Agency", "Details");

		TableView<ValidationMaster> tableAgency = new TableView<>();

		TableColumn<ValidationMaster, Integer> MCol1 = new TableColumn<ValidationMaster, Integer>(
				"Agency Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ValidationMaster, Integer>(
				"agencyCode"));
		MCol1.prefWidthProperty().bind(tableAgency.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol2 = new TableColumn<ValidationMaster, String>(
				"Agency Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"agencyName"));
		MCol2.prefWidthProperty().bind(tableAgency.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol3 = new TableColumn<ValidationMaster, String>(
				"Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableAgency.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol4 = new TableColumn<ValidationMaster, String>(
				"Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableAgency.widthProperty().divide(4));

		tableAgency.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<ValidationMaster> agencyData = FXCollections
				.observableArrayList();
		agencyData.setAll(validationData.getValidationList());
		tableAgency.setItems(agencyData);

		main.getChildren().add(tableAgency);

		agencyTab.setContent(ControlsUtil.makeScrollable(main));
		agencyTab.setClosable(false);

		pane.getTabs().add(agencyTab);
	}

	@SuppressWarnings("unchecked")
	public void createCustomerTab() {

		Tab CustomerTab = new Tab("Customer");

		VBox main = ViewLayout.getMainVBox("Customer", "Details");

		TableView<ValidationMaster> tableCustomer = new TableView<>();

		TableColumn<ValidationMaster, Integer> MCol1 = new TableColumn<ValidationMaster, Integer>(
				"Customer Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ValidationMaster, Integer>(
				"customerCode"));
		MCol1.prefWidthProperty().bind(tableCustomer.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol2 = new TableColumn<ValidationMaster, String>(
				"Customer Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"customerName"));
		MCol2.prefWidthProperty().bind(tableCustomer.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol3 = new TableColumn<ValidationMaster, String>(
				"Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableCustomer.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol4 = new TableColumn<ValidationMaster, String>(
				"Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableCustomer.widthProperty().divide(4));

		tableCustomer.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<ValidationMaster> customerData = FXCollections
				.observableArrayList();
		customerData.setAll(validationData.getValidationList());
		tableCustomer.setItems(customerData);

		main.getChildren().add(tableCustomer);

		CustomerTab.setContent(ControlsUtil.makeScrollable(main));
		CustomerTab.setClosable(false);

		pane.getTabs().add(CustomerTab);
	}

	@SuppressWarnings("unchecked")
	public void createItemTab() {

		Tab itemTab = new Tab("Item");

		VBox main = ViewLayout.getMainVBox("Item", "Details");

		TableView<ValidationMaster> tableItem = new TableView<>();

		TableColumn<ValidationMaster, Integer> MCol1 = new TableColumn<ValidationMaster, Integer>(
				"Item Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ValidationMaster, Integer>(
				"itemCode"));
		MCol1.prefWidthProperty().bind(tableItem.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol2 = new TableColumn<ValidationMaster, String>(
				"Item Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"itemName"));
		MCol2.prefWidthProperty().bind(tableItem.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol3 = new TableColumn<ValidationMaster, String>(
				"Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableItem.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol4 = new TableColumn<ValidationMaster, String>(
				"Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableItem.widthProperty().divide(4));

		tableItem.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<ValidationMaster> itemData = FXCollections
				.observableArrayList();
		itemData.setAll(validationData.getValidationList());
		tableItem.setItems(itemData);

		main.getChildren().add(tableItem);

		itemTab.setContent(ControlsUtil.makeScrollable(main));
		itemTab.setClosable(false);

		pane.getTabs().add(itemTab);
	}

	@SuppressWarnings("unchecked")
	public void createLaboratoryTab() {

		Tab laboratoryTab = new Tab("Laboratory");

		VBox main = ViewLayout.getMainVBox("Laboratory", "Details");

		TableView<ValidationMaster> tableLaboratory = new TableView<>();

		TableColumn<ValidationMaster, Integer> MCol1 = new TableColumn<ValidationMaster, Integer>(
				"Laboratory Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ValidationMaster, Integer>(
				"laboratoryCode"));
		MCol1.prefWidthProperty().bind(
				tableLaboratory.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol2 = new TableColumn<ValidationMaster, String>(
				"Laboratory Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"laboratoryName"));
		MCol2.prefWidthProperty().bind(
				tableLaboratory.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol3 = new TableColumn<ValidationMaster, String>(
				"Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(
				tableLaboratory.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol4 = new TableColumn<ValidationMaster, String>(
				"Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(
				tableLaboratory.widthProperty().divide(4));

		tableLaboratory.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<ValidationMaster> laboratoryData = FXCollections
				.observableArrayList();
		laboratoryData.setAll(validationData.getValidationList());
		tableLaboratory.setItems(laboratoryData);

		main.getChildren().add(tableLaboratory);

		laboratoryTab.setContent(ControlsUtil.makeScrollable(main));
		laboratoryTab.setClosable(false);

		pane.getTabs().add(laboratoryTab);
	}

	@SuppressWarnings("unchecked")
	public void createResultTab() {

		Tab resultTab = new Tab("Result");

		VBox main = ViewLayout.getMainVBox("Result", "Details");

		TableView<ValidationMaster> tableResult = new TableView<>();

		TableColumn<ValidationMaster, Integer> MCol1 = new TableColumn<ValidationMaster, Integer>(
				"Result Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ValidationMaster, Integer>(
				"resultCode"));
		MCol1.prefWidthProperty().bind(tableResult.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol2 = new TableColumn<ValidationMaster, String>(
				"Result Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"resultName"));
		MCol2.prefWidthProperty().bind(tableResult.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol3 = new TableColumn<ValidationMaster, String>(
				"Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableResult.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol4 = new TableColumn<ValidationMaster, String>(
				"Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableResult.widthProperty().divide(4));

		tableResult.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<ValidationMaster> resultData = FXCollections
				.observableArrayList();
		resultData.setAll(validationData.getValidationList());
		tableResult.setItems(resultData);

		main.getChildren().add(tableResult);

		resultTab.setContent(ControlsUtil.makeScrollable(main));
		resultTab.setClosable(false);

		pane.getTabs().add(resultTab);
	}

	@SuppressWarnings("unchecked")
	public void createSpecificationTab() {

		Tab specificationTab = new Tab("Specification");

		VBox main = ViewLayout.getMainVBox("Specification", "Details");

		TableView<ValidationMaster> tableSpecification = new TableView<>();

		TableColumn<ValidationMaster, Integer> MCol1 = new TableColumn<ValidationMaster, Integer>(
				"Specification Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ValidationMaster, Integer>(
				"specificationCode"));
		MCol1.prefWidthProperty().bind(
				tableSpecification.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol2 = new TableColumn<ValidationMaster, String>(
				"Specification Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"specificationName"));
		MCol2.prefWidthProperty().bind(
				tableSpecification.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol3 = new TableColumn<ValidationMaster, String>(
				"Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(
				tableSpecification.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol4 = new TableColumn<ValidationMaster, String>(
				"Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(
				tableSpecification.widthProperty().divide(4));

		tableSpecification.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<ValidationMaster> specificationData = FXCollections
				.observableArrayList();
		specificationData.setAll(validationData.getValidationList());
		tableSpecification.setItems(specificationData);

		main.getChildren().add(tableSpecification);

		specificationTab.setContent(ControlsUtil.makeScrollable(main));
		specificationTab.setClosable(false);

		pane.getTabs().add(specificationTab);
	}

	@SuppressWarnings("unchecked")
	public void createTestTab() {

		Tab testTab = new Tab("Test");

		VBox main = ViewLayout.getMainVBox("Test", "Details");

		TableView<ValidationMaster> tableTest = new TableView<>();

		TableColumn<ValidationMaster, Integer> MCol1 = new TableColumn<ValidationMaster, Integer>(
				"Test Code");
		MCol1.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol1.setCellValueFactory(new PropertyValueFactory<ValidationMaster, Integer>(
				"testCode"));
		MCol1.prefWidthProperty().bind(tableTest.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol2 = new TableColumn<ValidationMaster, String>(
				"Test Name");
		MCol2.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol2.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"testName"));
		MCol2.prefWidthProperty().bind(tableTest.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol3 = new TableColumn<ValidationMaster, String>(
				"Remarks");
		MCol3.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol3.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"remarks"));
		MCol3.prefWidthProperty().bind(tableTest.widthProperty().divide(4));

		TableColumn<ValidationMaster, String> MCol4 = new TableColumn<ValidationMaster, String>(
				"Status");
		MCol4.setPrefWidth(ViewLayout.COLUMN_WIDTH);
		MCol4.setCellValueFactory(new PropertyValueFactory<ValidationMaster, String>(
				"status"));
		MCol4.prefWidthProperty().bind(tableTest.widthProperty().divide(4));

		tableTest.getColumns().addAll(MCol1, MCol2, MCol3, MCol4);

		ObservableList<ValidationMaster> testData = FXCollections
				.observableArrayList();
		testData.setAll(validationData.getValidationList());
		tableTest.setItems(testData);

		main.getChildren().add(tableTest);

		testTab.setContent(ControlsUtil.makeScrollable(main));
		testTab.setClosable(false);

		pane.getTabs().add(testTab);
	}

	public ValidationData getValidationData() {
		return validationData;
	}

	public void setValidationData(ValidationData validationData) {
		this.validationData = validationData;
	}
}
