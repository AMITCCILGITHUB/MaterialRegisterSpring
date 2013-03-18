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

import org.hibernate.HibernateException;
import org.map.constants.PersistType;
import org.map.hibernate.ddo.HeatChartMaster;
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

@Repository("EditHeatChart")
public class EditHeatChart implements AbstractPageView {

	@Resource(name = "PersistHeatChartDetails")
	private AbstractService<HeatChartMaster, Void> abstractService;

	@Resource(name = "EditHeatChartTab")
	private EditHeatChartTab editHeatChartTab;

	private TabPane pane;

	public EditHeatChart() {

		pane = new TabPane();
	}

	@Override
	public Node showView() {

		Tab tab = new Tab("Edit Heat CHart : Search");

		try {
			VBox main = ViewLayout.getMainVBox("Edit Heat Chart", "Details");

			final ObservableList<HeatChartMaster> mailboxData = FXCollections
					.observableArrayList();
			main.getChildren().addAll(
					SearchBoxUtil.getHeatChartSearchBox("Heat Chart Number",
							mailboxData));

			final TableView<HeatChartMaster> tableMailbox = TableUtil
					.createSearchHeatChartTable();

			main.getChildren()
					.addAll(ControlsUtil.makeScrollable(tableMailbox));

			tableMailbox.setItems(mailboxData);

			tableMailbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getClickCount() == 2) {

						HeatChartMaster heatChart = tableMailbox
								.getSelectionModel().getSelectedItem();
						if (heatChart != null) {

							try {
								createEditTab(heatChart);
							} catch (MalformedURLException e) {
							}
						}
					}

				}

			});

			EventHandler<ActionEvent> editEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					HeatChartMaster heatChart = tableMailbox
							.getSelectionModel().getSelectedItem();
					if (heatChart != null) {

						try {
							createEditTab(heatChart);
						} catch (MalformedURLException e) {
						}
					}
				}
			};

			EventHandler<ActionEvent> deleteEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					final HeatChartMaster heatChart = tableMailbox
							.getSelectionModel().getSelectedItem();

					EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {

							abstractService.setPersistEntity(heatChart);
							abstractService.setPersistType(PersistType.DELETE);

							abstractService.restart();

							abstractService
									.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

										@Override
										public void handle(WorkerStateEvent e) {

											mailboxData.remove(heatChart);
										}
									});
						}
					};

					Confirm.showConfirm(Context.getWindowStage(), "Confirm",
							"Confirm", "Delete " + heatChart.getChartNumber()
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
										"Heat Chart details saved successfully.");

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

			tableMailbox.setContextMenu(TableContextMenu
					.getEditHeatChartContextMenu(editEventHandler,
							deleteEventHandler));

			tab.setContent(ControlsUtil.makeScrollable(main));
			tab.setClosable(false);

			pane.getTabs().add(tab);
			pane.setSide(Side.TOP);

			return pane;
		} catch (HibernateException | MalformedURLException e) {

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

	private void createEditTab(final HeatChartMaster heatChart)
			throws MalformedURLException {

		for (Tab selTab : pane.getTabs()) {
			if (selTab.getId() != null
					&& selTab.getId().equalsIgnoreCase(
							heatChart.getChartNumber())) {
				pane.getSelectionModel().select(selTab);
				return;
			}
		}

		pane.getTabs().add(editHeatChartTab.showView(heatChart));
	}

	public AbstractService<HeatChartMaster, Void> getPersistHeatChartDetails() {
		return abstractService;
	}

	public void setPersistHeatChartDetails(
			AbstractService<HeatChartMaster, Void> abstractService) {
		this.abstractService = abstractService;
	}

	public EditHeatChartTab getEditHeatChartTab() {
		return editHeatChartTab;
	}

	public void setEditHeatChartTab(EditHeatChartTab editHeatChartTab) {
		this.editHeatChartTab = editHeatChartTab;
	}
}
