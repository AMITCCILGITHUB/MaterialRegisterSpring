package org.map.view;

import java.io.IOException;
import java.net.URISyntaxException;

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
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRException;

import org.map.constants.PersistType;
import org.map.controls.ErrorView;
import org.map.controls.SubmitButton;
import org.map.controls.TextBox;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.HeatChartSheets;
import org.map.hibernate.utils.HeatChartData;
import org.map.hibernate.utils.Reporter;
import org.map.logger.LoggerUtil;
import org.map.service.AbstractService;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("AddHeatChart")
public class AddHeatChart {

	@Resource(name = "HeatChartData")
	private HeatChartData heatChartData;

	@Resource(name = "PersistHeatChartDetails")
	private AbstractService<HeatChartMaster, Void> abstractService;

	private ScrollPane pane;

	public AddHeatChart() {

		pane = new ScrollPane();
	}

	public Node showView() {

		try {
			final HeatChartMaster heatChart = new HeatChartMaster();
			heatChart.setChartNumber(heatChartData.getNextChartNumber());

			VBox main = ViewLayout.getMainVBox("Add Heat Chart",
					heatChart.chartNumberProperty());

			GridPane form = new GridPane();
			form.setHgap(ViewLayout.H_SPACE);
			form.setVgap(ViewLayout.V_SPACE);

			Label equipmentLabel = new Label("Equipment");
			equipmentLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final TextBox equipmentTextField = new TextBox("Equipment",
					heatChart.equipmentProperty());

			Label customerLabel = new Label("Customer");
			customerLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final TextBox customerTextField = new TextBox("Customer",
					heatChart.customerProperty());

			Label poDetailsLabel = new Label("PO Details");
			poDetailsLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final TextBox poDetailsTextField = new TextBox("PO Details",
					heatChart.poDetailsProperty());

			Label drawingLabel = new Label("Drawing No.");
			drawingLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final TextBox drawingTextField = new TextBox("Drawing No.",
					heatChart.drawingNumberProperty());

			Label suryeyorLabel = new Label("Surveyor");
			suryeyorLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final TextBox suryeyorTextField = new TextBox("Suryeyor",
					heatChart.surveyorProperty());

			Label tagNumberLabel = new Label("Tag Number");
			suryeyorLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final TextBox tagNumberTextField = new TextBox("Tag Number",
					heatChart.tagNumberProperty());

			form.add(equipmentLabel, 0, 0);
			form.add(equipmentTextField, 1, 0);
			form.add(customerLabel, 2, 0);
			form.add(customerTextField, 3, 0);
			form.add(poDetailsLabel, 4, 0);
			form.add(poDetailsTextField, 5, 0);
			form.add(drawingLabel, 0, 1);
			form.add(drawingTextField, 1, 1);
			form.add(suryeyorLabel, 2, 1);
			form.add(suryeyorTextField, 3, 1);
			form.add(tagNumberLabel, 4, 1);
			form.add(tagNumberTextField, 5, 1);

			main.getChildren().add(form);

			final ObservableList<HeatChartSheets> data = FXCollections
					.observableArrayList();

			final TableView<HeatChartSheets> table = TableUtil
					.createAddHeatChartSheetTable(data);

			main.getChildren().add(ControlsUtil.makeScrollable(table));

			HeatChartSheets hs = new HeatChartSheets();

			heatChart.getHeatChartSheets().add(hs);
			data.setAll(hs);
			table.setItems(data);

			HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			SubmitButton addRecordButton = new SubmitButton("Add Record");
			addRecordButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					HeatChartSheets hs = new HeatChartSheets();

					if (!data.isEmpty()) {
						hs.setSequenceNumber(data.size() + 1);
						hs.setSheetNumber(data.get(data.size() - 1)
								.getSheetNumber());
					}

					heatChart.getHeatChartSheets().add(hs);
					data.add(hs);
				}
			});
			SubmitButton addSheetButton = new SubmitButton("Add Sheet");
			addSheetButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					HeatChartSheets hs = new HeatChartSheets();

					if (!data.isEmpty()) {
						hs.setSequenceNumber(data.size() + 1);
						hs.setSheetNumber(data.get(data.size() - 1)
								.getSheetNumber() + 1);
					}

					heatChart.getHeatChartSheets().add(hs);
					data.add(hs);
				}
			});
			SubmitButton saveRecordButton = new SubmitButton("Save Heat Chart");
			saveRecordButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					abstractService.setPersistEntity(heatChart);
					abstractService.setPersistType(PersistType.INSERT);

					abstractService.restart();

					abstractService
							.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									heatChart.clean();
									HeatChartSheets hs = new HeatChartSheets();
									data.setAll(hs);
								}
							});
				}
			});

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

			SubmitButton printButton = new SubmitButton("Print");
			printButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					try {

						Reporter.printHeatChartReport(heatChart);
					} catch (JRException | IOException | URISyntaxException ex) {
						LoggerUtil.getLogger().debug(ex);
					}
				}
			});

			buttons.getChildren().addAll(addRecordButton, addSheetButton,
					saveRecordButton, printButton);

			EventHandler<ActionEvent> addRecordEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					HeatChartSheets hs = new HeatChartSheets();

					if (!data.isEmpty()) {
						hs.setSequenceNumber(data.size() + 1);
						hs.setSheetNumber(data.get(data.size() - 1)
								.getSheetNumber());
					}

					heatChart.getHeatChartSheets().add(hs);
					data.add(hs);
				}
			};

			EventHandler<ActionEvent> addSheetEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					HeatChartSheets hs = new HeatChartSheets();

					if (!data.isEmpty()) {
						hs.setSequenceNumber(data.size() + 1);
						hs.setSheetNumber(data.get(data.size() - 1)
								.getSheetNumber() + 1);
					}

					heatChart.getHeatChartSheets().add(hs);
					data.add(hs);
				}
			};

			EventHandler<ActionEvent> removeRecordEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					int index = table.getSelectionModel().getSelectedIndex();
					if (index >= 0) {

						heatChart.getHeatChartSheets().remove(data.get(index));
						data.remove(index);
					}
				}
			};

			table.setContextMenu(TableContextMenu.getAddHeatChartContextMenu(
					addRecordEventHandler, addSheetEventHandler,
					removeRecordEventHandler));

			main.getChildren().add(buttons);

			pane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
			pane.setFitToWidth(true);
			pane.setContent(main);

			return pane;
		} catch (Exception e) {

			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());

			return new ErrorView();
		}
	}

	public HeatChartData getHeatChartData() {
		return heatChartData;
	}

	public void setHeatChartData(HeatChartData heatChartData) {
		this.heatChartData = heatChartData;
	}

	public AbstractService<HeatChartMaster, Void> getPersistHeatChartDetails() {
		return abstractService;
	}

	public void setPersistHeatChartDetails(
			AbstractService<HeatChartMaster, Void> abstractService) {
		this.abstractService = abstractService;
	}
}
