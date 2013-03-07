package org.map.view;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.constants.ValidationType;
import org.map.controls.ErrorView;
import org.map.controls.TextBox;
import org.map.controls.ViewIntegerBox;
import org.map.controls.combobox.ValidationComboBox;
import org.map.hibernate.ddo.ValidationMaster;
import org.map.hibernate.property.ValidationProperty;
import org.map.logger.LoggerUtil;
import org.map.service.AbstractService;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("EditValidation")
public class EditValidation {

	@Resource(name = "PersistValidationDetails")
	private AbstractService<ValidationMaster, Void> abstractService;
	

	private TabPane pane;

	public EditValidation() {

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

			Context.getWindowBottomText()
					.textProperty()
					.bind(Bindings.format("%s",
							abstractService.stateProperty()));

			abstractService.stateProperty().addListener(
					new ChangeListener<State>() {

						@Override
						public void changed(
								ObservableValue<? extends State> arg0,
								State oldValue, State newState) {

							if (newState == Worker.State.SUCCEEDED) {
								Alert.showAlert(Context.getWindowStage(),
										"Alert", "Alert",
										"Agency details saved successfully.");

							} else if (newState == Worker.State.FAILED) {
								Alert.showAlert(Context.getWindowStage(),
										"Error", "Error",
										"Some error cooured. Details : "
												+ abstractService
														.getException()
														.getCause());
							}
						}
					});

			return pane;
		} catch (Exception e) {
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());

			return new ErrorView();
		}
	}

	public void createAgencyTab() {

		Tab agencyTab = new Tab("Agency");
		final ValidationProperty agency = new ValidationProperty(
				ValidationType.AGENCY);

		VBox main = ViewLayout.getMainVBox("Add Agency", "Details");

		GridPane agencyForm = new GridPane();
		agencyForm.setHgap(ViewLayout.H_SPACE);
		agencyForm.setVgap(ViewLayout.V_SPACE);

		Label agencyCodeLabel = new Label("Agency Code");
		agencyCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox agencyCodeBox = new ViewIntegerBox(agency.get()
				.getCode(), agency.get().codeProperty());

		Label agencyNameLabel = new Label("Agency Name");
		agencyNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ValidationComboBox agencyNameBox = new ValidationComboBox(
				ValidationType.AGENCY, "Agency Name", agency);

		Label agencyRemarksLabel = new Label("Display Text");
		agencyRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox agencyRemarksTextBox = new TextBox("Display Text", agency
				.get().displayTextProperty());

		Button agencyButton = new Button("Submit");
		agencyButton.getStyleClass().add("submit-button");
		agencyButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (agencyNameBox.getText().trim().length() > 0) {

					if (agencyRemarksTextBox.getText().trim().length() == 0) {
						agency.get().setDisplayText(agency.get().getName());
					}

					abstractService.setPersistEntity(agency.get());
					abstractService.setPersistType(PersistType.UPDATE);

					abstractService.restart();

					abstractService
							.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									agency.get().clean();
								}
							});
				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Please enter agency name.");
				}
			}
		});

		agencyForm.add(agencyCodeLabel, 0, 0);
		agencyForm.add(agencyCodeBox, 1, 0);
		agencyForm.add(agencyNameLabel, 0, 1);
		agencyForm.add(agencyNameBox, 1, 1);
		agencyForm.add(agencyRemarksLabel, 0, 2);
		agencyForm.add(agencyRemarksTextBox, 1, 2);
		agencyForm.add(agencyButton, 1, 3);

		main.getChildren().add(agencyForm);

		agencyTab.setContent(ControlsUtil.makeScrollable(main));
		agencyTab.setClosable(false);
		pane.getTabs().add(agencyTab);
	}

	public void createCustomerTab() {

		Tab CustomerTab = new Tab("Customer");
		final ValidationProperty customer = new ValidationProperty(
				ValidationType.CUSTOMER);

		VBox main = ViewLayout.getMainVBox("Add Customer", "Details");

		GridPane customerForm = new GridPane();
		customerForm.setHgap(ViewLayout.H_SPACE);
		customerForm.setVgap(ViewLayout.V_SPACE);

		Label customerCodeLabel = new Label("Customer Code");
		customerCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox customerCodeBox = new ViewIntegerBox(customer
				.get().getCode(), customer.get().codeProperty());

		Label customerNameLabel = new Label("Customer Name");
		customerNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ValidationComboBox customerNameBox = new ValidationComboBox(
				ValidationType.CUSTOMER, "Customer Name", customer);

		Label customerRemarksLabel = new Label("Display Text");
		customerRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox customerRemarksTextBox = new TextBox("Display Text",
				customer.get().displayTextProperty());

		Button customerButton = new Button("Submit");
		customerButton.getStyleClass().add("submit-button");
		customerButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (customerNameBox.getText().trim().length() > 0) {

					if (customerRemarksTextBox.getText().trim().length() == 0) {
						customer.get().setDisplayText(customer.get().getName());
					}

					abstractService.setPersistEntity(customer.get());
					abstractService.setPersistType(PersistType.UPDATE);

					abstractService.restart();

					abstractService
							.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									customer.get().clean();
								}
							});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Please enter customer name.");
				}
			}
		});

		customerForm.add(customerCodeLabel, 0, 0);
		customerForm.add(customerCodeBox, 1, 0);
		customerForm.add(customerNameLabel, 0, 1);
		customerForm.add(customerNameBox, 1, 1);
		customerForm.add(customerRemarksLabel, 0, 2);
		customerForm.add(customerRemarksTextBox, 1, 2);
		customerForm.add(customerButton, 1, 3);

		main.getChildren().add(customerForm);

		CustomerTab.setContent(ControlsUtil.makeScrollable(main));
		CustomerTab.setClosable(false);
		pane.getTabs().add(CustomerTab);
	}

	public void createItemTab() {

		Tab itemTab = new Tab("Item");
		final ValidationProperty item = new ValidationProperty(
				ValidationType.ITEM);

		VBox main = ViewLayout.getMainVBox("Add Item", "Details");

		GridPane itemForm = new GridPane();
		itemForm.setHgap(ViewLayout.H_SPACE);
		itemForm.setVgap(ViewLayout.V_SPACE);

		Label itemCodeLabel = new Label("Item Code");
		itemCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox itemCodeBox = new ViewIntegerBox(item.get()
				.getCode(), item.get().codeProperty());

		Label itemNameLabel = new Label("Item Name");
		itemNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ValidationComboBox itemNameBox = new ValidationComboBox(
				ValidationType.ITEM, "Item Name", item);

		Label itemRemarksLabel = new Label("Display Text");
		itemRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox itemRemarksTextBox = new TextBox("Display Text", item
				.get().displayTextProperty());

		Button itemButton = new Button("Submit");
		itemButton.getStyleClass().add("submit-button");
		itemButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (itemNameBox.getText().trim().length() > 0) {

					if (itemRemarksTextBox.getText().trim().length() == 0) {
						item.get().setDisplayText(item.get().getName());
					}

					abstractService.setPersistEntity(item.get());
					abstractService.setPersistType(PersistType.UPDATE);

					abstractService.restart();

					abstractService
							.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									item.get().clean();
								}
							});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Please enter item name.");
				}
			}
		});

		itemForm.add(itemCodeLabel, 0, 0);
		itemForm.add(itemCodeBox, 1, 0);
		itemForm.add(itemNameLabel, 0, 1);
		itemForm.add(itemNameBox, 1, 1);
		itemForm.add(itemRemarksLabel, 0, 2);
		itemForm.add(itemRemarksTextBox, 1, 2);
		itemForm.add(itemButton, 1, 3);

		main.getChildren().add(itemForm);

		itemTab.setContent(ControlsUtil.makeScrollable(main));
		itemTab.setClosable(false);
		pane.getTabs().add(itemTab);
	}

	public void createLaboratoryTab() {

		Tab laboratoryTab = new Tab("Laboratory");
		final ValidationProperty laboratory = new ValidationProperty(
				ValidationType.LABORATORY);

		VBox main = ViewLayout.getMainVBox("Add Laboratory", "Details");

		GridPane laboratoryForm = new GridPane();
		laboratoryForm.setHgap(ViewLayout.H_SPACE);
		laboratoryForm.setVgap(ViewLayout.V_SPACE);

		Label laboratoryCodeLabel = new Label("Laboratory Code");
		laboratoryCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox laboratoryCodeBox = new ViewIntegerBox(laboratory
				.get().getCode(), laboratory.get().codeProperty());

		Label laboratoryNameLabel = new Label("Laboratory Name");
		laboratoryNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ValidationComboBox laboratoryNameBox = new ValidationComboBox(
				ValidationType.LABORATORY, "Laboratory Name", laboratory);

		Label laboratoryRemarksLabel = new Label("Display Text");
		laboratoryRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox laboratoryRemarksTextBox = new TextBox("Display Text",
				laboratory.get().displayTextProperty());

		Button laboratoryButton = new Button("Submit");
		laboratoryButton.getStyleClass().add("submit-button");
		laboratoryButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (laboratoryNameBox.getText().trim().length() > 0) {

					if (laboratoryRemarksTextBox.getText().trim().length() == 0) {
						laboratory.get().setDisplayText(
								laboratory.get().getName());
					}

					abstractService.setPersistEntity(laboratory.get());
					abstractService.setPersistType(PersistType.UPDATE);

					abstractService.restart();

					abstractService
							.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									laboratory.get().clean();
								}
							});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Please enter laboratory name.");
				}
			}
		});

		laboratoryForm.add(laboratoryCodeLabel, 0, 0);
		laboratoryForm.add(laboratoryCodeBox, 1, 0);
		laboratoryForm.add(laboratoryNameLabel, 0, 1);
		laboratoryForm.add(laboratoryNameBox, 1, 1);
		laboratoryForm.add(laboratoryRemarksLabel, 0, 2);
		laboratoryForm.add(laboratoryRemarksTextBox, 1, 2);
		laboratoryForm.add(laboratoryButton, 1, 3);

		main.getChildren().add(laboratoryForm);

		laboratoryTab.setContent(ControlsUtil.makeScrollable(main));
		laboratoryTab.setClosable(false);
		pane.getTabs().add(laboratoryTab);
	}

	public void createResultTab() {

		Tab resultTab = new Tab("Result");
		final ValidationProperty result = new ValidationProperty(
				ValidationType.RESULT);

		VBox main = ViewLayout.getMainVBox("Add Result", "Details");

		GridPane resultForm = new GridPane();
		resultForm.setHgap(ViewLayout.H_SPACE);
		resultForm.setVgap(ViewLayout.V_SPACE);

		Label resultCodeLabel = new Label("Result Code");
		resultCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox resultCodeBox = new ViewIntegerBox(result.get()
				.getCode(), result.get().codeProperty());

		Label resultNameLabel = new Label("Result Name");
		resultNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ValidationComboBox resultNameBox = new ValidationComboBox(
				ValidationType.RESULT, "Result Name", result);

		Label resultRemarksLabel = new Label("Display Text");
		resultRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox resultRemarksTextBox = new TextBox("Display Text", result
				.get().displayTextProperty());

		Button resultButton = new Button("Submit");
		resultButton.getStyleClass().add("submit-button");
		resultButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (resultNameBox.getText().trim().length() > 0) {

					if (resultRemarksTextBox.getText().trim().length() == 0) {
						result.get().setDisplayText(result.get().getName());
					}

					abstractService.setPersistEntity(result.get());
					abstractService.setPersistType(PersistType.UPDATE);

					abstractService.restart();

					abstractService
							.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									result.get().clean();
								}
							});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Please enter result name.");
				}
			}
		});

		resultForm.add(resultCodeLabel, 0, 0);
		resultForm.add(resultCodeBox, 1, 0);
		resultForm.add(resultNameLabel, 0, 1);
		resultForm.add(resultNameBox, 1, 1);
		resultForm.add(resultRemarksLabel, 0, 2);
		resultForm.add(resultRemarksTextBox, 1, 2);
		resultForm.add(resultButton, 1, 3);

		main.getChildren().add(resultForm);

		resultTab.setContent(ControlsUtil.makeScrollable(main));
		resultTab.setClosable(false);
		pane.getTabs().add(resultTab);
	}

	public void createSpecificationTab() {

		Tab specificationTab = new Tab("Specification");
		final ValidationProperty specification = new ValidationProperty(
				ValidationType.SPECIFICATION);

		VBox main = ViewLayout.getMainVBox("Add Specification", "Details");

		GridPane specificationForm = new GridPane();
		specificationForm.setHgap(ViewLayout.H_SPACE);
		specificationForm.setVgap(ViewLayout.V_SPACE);

		Label specificationCodeLabel = new Label("Specification Code");
		specificationCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox specificationCodeBox = new ViewIntegerBox(
				specification.get().getCode(), specification.get()
						.codeProperty());

		Label specificationNameLabel = new Label("Specification Name");
		specificationNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ValidationComboBox specificationNameBox = new ValidationComboBox(
				ValidationType.SPECIFICATION, "Specification Name",
				specification);

		Label specificationRemarksLabel = new Label("Display Text");
		specificationRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox specificationRemarksTextBox = new TextBox("Display Text",
				specification.get().displayTextProperty());

		Button specificationButton = new Button("Submit");
		specificationButton.getStyleClass().add("submit-button");
		specificationButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (specificationNameBox.getText().trim().length() > 0) {

					if (specificationRemarksTextBox.getText().trim().length() == 0) {
						specification.get().setDisplayText(
								specification.get().getName());
					}

					abstractService.setPersistEntity(specification.get());
					abstractService.setPersistType(PersistType.UPDATE);

					abstractService.restart();

					abstractService
							.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									specification.get().clean();
								}
							});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Please enter specification name.");
				}
			}
		});

		specificationForm.add(specificationCodeLabel, 0, 0);
		specificationForm.add(specificationCodeBox, 1, 0);
		specificationForm.add(specificationNameLabel, 0, 1);
		specificationForm.add(specificationNameBox, 1, 1);
		specificationForm.add(specificationRemarksLabel, 0, 2);
		specificationForm.add(specificationRemarksTextBox, 1, 2);
		specificationForm.add(specificationButton, 1, 3);

		main.getChildren().add(specificationForm);

		specificationTab.setContent(ControlsUtil.makeScrollable(main));
		specificationTab.setClosable(false);
		pane.getTabs().add(specificationTab);
	}

	public void createTestTab() {

		Tab testTab = new Tab("Test");
		final ValidationProperty test = new ValidationProperty(
				ValidationType.TEST);

		VBox main = ViewLayout.getMainVBox("Add Test", "Details");

		GridPane testForm = new GridPane();
		testForm.setHgap(ViewLayout.H_SPACE);
		testForm.setVgap(ViewLayout.V_SPACE);

		Label testCodeLabel = new Label("Test Code");
		testCodeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ViewIntegerBox testCodeBox = new ViewIntegerBox(test.get()
				.getCode(), test.get().codeProperty());

		Label testNameLabel = new Label("Test Name");
		testNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final ValidationComboBox testNameBox = new ValidationComboBox(
				ValidationType.TEST, "Test Name", test);

		Label testRemarksLabel = new Label("Display Text");
		testRemarksLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);
		final TextBox testRemarksTextBox = new TextBox("Display Text", test
				.get().displayTextProperty());

		Button testButton = new Button("Submit");
		testButton.getStyleClass().add("submit-button");
		testButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (testNameBox.getText().trim().length() > 0) {

					if (testRemarksTextBox.getText().trim().length() == 0) {
						test.get().setDisplayText(test.get().getName());
					}

					abstractService.setPersistEntity(test.get());
					abstractService.setPersistType(PersistType.UPDATE);

					abstractService.restart();

					abstractService
							.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									test.get().clean();
								}
							});

				} else {
					Alert.showAlert(Context.getWindowStage(), "Error", "Error",
							"Please enter test name.");
				}
			}
		});

		testForm.add(testCodeLabel, 0, 0);
		testForm.add(testCodeBox, 1, 0);
		testForm.add(testNameLabel, 0, 1);
		testForm.add(testNameBox, 1, 1);
		testForm.add(testRemarksLabel, 0, 2);
		testForm.add(testRemarksTextBox, 1, 2);
		testForm.add(testButton, 1, 3);

		main.getChildren().add(testForm);

		testTab.setContent(ControlsUtil.makeScrollable(main));
		testTab.setClosable(false);
		pane.getTabs().add(testTab);
	}

	public AbstractService<ValidationMaster, Void> getPersistValidationDetails() {
		return abstractService;
	}

	public void setPersistValidationDetails(
			AbstractService<ValidationMaster, Void> abstractService) {
		this.abstractService = abstractService;
	}
}
