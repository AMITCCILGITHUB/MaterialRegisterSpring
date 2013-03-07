package org.map.view;

import java.net.MalformedURLException;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.constants.ValidationType;
import org.map.controls.EditableBox;
import org.map.controls.EditableIntBox;
import org.map.controls.ErrorView;
import org.map.controls.ViewBox;
import org.map.controls.combobox.ValidationEditableComboBox;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialTests;
import org.map.logger.LoggerUtil;
import org.map.service.AbstractService;
import org.map.utils.Alert;
import org.map.utils.Confirm;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.SearchBoxUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("EditMaterial")
public class EditMaterial {

	@Resource(name = "PersistMaterialDetails")
	private AbstractService<MaterialMaster, Void> abstractService;

	private TabPane pane;

	public EditMaterial() {

		pane = new TabPane();
	}

	public Node showView() {

		Tab tab = new Tab("Edit Material : Search");

		try {
			VBox main = ViewLayout.getMainVBox("Edit Material", "Details");

			final ObservableList<MaterialMaster> mailboxData = FXCollections
					.observableArrayList();
			main.getChildren().addAll(
					SearchBoxUtil
							.getMaterialSearchBox("CT Number", mailboxData));

			final TableView<MaterialMaster> table = TableUtil
					.createSearchMaterialTable();

			main.getChildren().add(ControlsUtil.makeScrollable(table));

			table.setItems(mailboxData);

			table.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getClickCount() == 2) {

						MaterialMaster material = table.getSelectionModel()
								.getSelectedItem();
						if (material != null) {

							try {
								createEditTab(material);
							} catch (MalformedURLException e) {

							}
						}
					}
				}

			});

			EventHandler<ActionEvent> editEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					MaterialMaster material = table.getSelectionModel()
							.getSelectedItem();
					if (material != null) {

						try {
							createEditTab(material);
						} catch (MalformedURLException e) {
						}
					}
				}
			};

			EventHandler<ActionEvent> deleteEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					final MaterialMaster material = table.getSelectionModel()
							.getSelectedItem();

					EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {

							abstractService.setPersistEntity(material);
							abstractService.setPersistType(PersistType.DELETE);

							abstractService.restart();

							abstractService
									.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

										@Override
										public void handle(WorkerStateEvent e) {

											mailboxData.remove(material);
										}
									});
						}
					};

					Confirm.showConfirm(Context.getWindowStage(), "Confirm",
							"Confirm", "Delete " + material.getCtNumber()
									+ ". Are you sure?", delUserEvent);
				}
			};

			Context.getWindowBottomText()
					.textProperty()
					.bind(Bindings.format("%s", abstractService.stateProperty()));

			abstractService.stateProperty().addListener(
					new ChangeListener<State>() {

						@Override
						public void changed(
								ObservableValue<? extends State> arg0,
								State oldValue, State newState) {

							if (newState == Worker.State.SUCCEEDED) {
								Alert.showAlert(Context.getWindowStage(),
										"Alert", "Alert",
										"Material details saved successfully.");

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

			table.setContextMenu(TableContextMenu.getEditMaterialContextMenu(
					editEventHandler, deleteEventHandler));

			tab.setContent(ControlsUtil.makeScrollable(main));
			tab.setClosable(false);

			pane.getTabs().add(tab);
			pane.setSide(Side.TOP);

			return pane;
		} catch (Exception e) {

			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());

			return new ErrorView();
		}
	}

	private void createEditTab(final MaterialMaster material)
			throws MalformedURLException {

		for (Tab selTab : pane.getTabs()) {
			if (selTab.getId() != null
					&& selTab.getId().equalsIgnoreCase(material.getCtNumber())) {
				pane.getSelectionModel().select(selTab);
				return;
			}
		}

		Tab tab = new Tab("Edit Material : " + material.getCtNumber());
		tab.setId(material.getCtNumber());

		VBox main = ViewLayout.getMainVBox("Material", "Details");

		GridPane form = new GridPane();
		form.setHgap(ViewLayout.H_SPACE);
		form.setVgap(ViewLayout.V_SPACE);

		Label ctNumberLabel = new Label("CT Number");
		ctNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox ctNumberTextField = new ViewBox("",
				material.ctNumberProperty());

		Label agencyLabel = new Label("Inspection Agency");
		agencyLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ValidationEditableComboBox agencyTextField = new ValidationEditableComboBox(
				ValidationType.AGENCY, "Inspection Agency",
				material.inspectionAgencyProperty());

		Label specLabel = new Label("Specification");
		specLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ValidationEditableComboBox specTextField = new ValidationEditableComboBox(
				ValidationType.SPECIFICATION, "Specification",
				material.specificationProperty());

		Label itemLabel = new Label("Item");
		itemLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ValidationEditableComboBox itemTextField = new ValidationEditableComboBox(
				ValidationType.ITEM, "Item Name", material.itemProperty());

		Label sizeLabel = new Label("Size");
		sizeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableBox sizeTextField = new EditableBox("Size",
				material.sizeProperty());

		Label quantityLabel = new Label("Quantity");
		quantityLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableIntBox quantityTextField = new EditableIntBox("",
				material.quantityProperty());

		Label heatNumberLabel = new Label("Heat / Lot Number");
		heatNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableBox heatNumberTextField = new EditableBox(
				"Heat / Lot Number", material.heatNumberProperty());

		Label plateNumberLabel = new Label("Plate / Product Number");
		plateNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final EditableBox plateNumberTextField = new EditableBox(
				"Plate / Product Number", material.plateNumberProperty());

		form.add(ctNumberLabel, 0, 0);
		form.add(ctNumberTextField, 1, 0);
		form.add(agencyLabel, 2, 0);
		form.add(agencyTextField, 3, 0);
		form.add(specLabel, 4, 0);
		form.add(specTextField, 5, 0);
		form.add(itemLabel, 0, 1);
		form.add(itemTextField, 1, 1);
		form.add(sizeLabel, 2, 1);
		form.add(sizeTextField, 3, 1);
		form.add(quantityLabel, 4, 1);
		form.add(quantityTextField, 5, 1);
		form.add(heatNumberLabel, 0, 2);
		form.add(heatNumberTextField, 1, 2);
		form.add(plateNumberLabel, 2, 2);
		form.add(plateNumberTextField, 3, 2);

		main.getChildren().add(form);

		final TableView<MaterialTests> table = TableUtil
				.createEditMaterialTable();

		main.getChildren().add(table);

		final ObservableList<MaterialTests> materialTestsData = FXCollections
				.observableArrayList(material.getMaterialTests());
		table.setItems(materialTestsData);

		final HBox buttons = new HBox(ViewLayout.H_SPACE);
		buttons.setTranslateY(32);
		final Button addTestButton = new Button("Add Test");
		addTestButton.getStyleClass().add("submit-button");
		addTestButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				MaterialTests mts = new MaterialTests();
				material.getMaterialTests().add(mts);
				materialTestsData.add(mts);

			}
		});
		final Button updateButton = new Button("Update");
		updateButton.getStyleClass().add("submit-button");
		buttons.getChildren().addAll(addTestButton, updateButton);
		main.getChildren().add(buttons);

		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				abstractService.setPersistEntity(material);
				abstractService.setPersistType(PersistType.UPDATE);
			}
		});

		EventHandler<ActionEvent> addTestEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				MaterialTests mts = new MaterialTests();
				material.getMaterialTests().add(mts);
				materialTestsData.add(mts);
			}
		};

		EventHandler<ActionEvent> removeTestEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				int index = table.getSelectionModel().getSelectedIndex();
				if (index >= 0) {

					material.getMaterialTests().remove(
							materialTestsData.get(index));
					materialTestsData.remove(index);
				}
			}
		};

		EventHandler<ActionEvent> duplicateTestEventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				MaterialTests mtsDup = table.getSelectionModel()
						.getSelectedItem();

				if (mtsDup != null) {

					MaterialTests mts = new MaterialTests();
					mts.resetTo(mtsDup);
					mts.setTestCode(0);
					material.getMaterialTests().add(mts);

					materialTestsData.add(mts);
				}
			}
		};

		table.setContextMenu(TableContextMenu.getAddMaterialContextMenu(
				addTestEventHandler, removeTestEventHandler,
				duplicateTestEventHandler));

		tab.setContent(ControlsUtil.makeScrollable(main));
		pane.getTabs().add(tab);
	}

	public AbstractService<MaterialMaster, Void> getPersistMaterialDetails() {
		return abstractService;
	}

	public void setPersistMaterialDetails(
			AbstractService<MaterialMaster, Void> abstractService) {
		this.abstractService = abstractService;
	}
}
