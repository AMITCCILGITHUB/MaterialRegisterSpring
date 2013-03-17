package org.map.view;

import java.net.MalformedURLException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.hibernate.ddo.MaterialMaster;
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
public class EditMaterial implements AbstractPageView {

	@Resource(name = "PersistMaterialDetails")
	private AbstractService<MaterialMaster, Void> abstractService;

	@Resource(name = "EditMaterialTab")
	private EditMaterialTab editMaterialTab;

	private TabPane pane;

	public EditMaterial() {

		pane = new TabPane();
	}

	@Override
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

			return new ErrorView().showView();
		}
	}

	@Override
	public DoubleProperty opacityProperty() {

		return pane.opacityProperty();
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

		pane.getTabs().add(editMaterialTab.showView(material));
	}

	public AbstractService<MaterialMaster, Void> getPersistMaterialDetails() {
		return abstractService;
	}

	public void setPersistMaterialDetails(
			AbstractService<MaterialMaster, Void> abstractService) {
		this.abstractService = abstractService;
	}

	public EditMaterialTab getEditMaterialTab() {
		return editMaterialTab;
	}

	public void setEditMaterialTab(EditMaterialTab editMaterialTab) {
		this.editMaterialTab = editMaterialTab;
	}
}
