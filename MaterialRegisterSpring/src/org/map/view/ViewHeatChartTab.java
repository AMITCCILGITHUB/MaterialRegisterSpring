package org.map.view;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;

import org.map.controls.ViewBox;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.HeatChartSheets;
import org.map.service.PrintHeatChart;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("ViewHeatChartTab")
public class ViewHeatChartTab {

	@Resource(name = "PrintHeatChart")
	private PrintHeatChart printHeatChart;

	public Tab showView(final HeatChartMaster heatChart) {

		Tab tab = new Tab("View Heat Chart : " + heatChart.getChartNumber());
		tab.setId(heatChart.getChartNumber());

		VBox main = ViewLayout.getMainVBox("Heat Chart", "Details");

		GridPane form = new GridPane();
		form.setHgap(ViewLayout.H_SPACE);
		form.setVgap(ViewLayout.V_SPACE);

		Label equipmentLabel = new Label("Equipment");
		equipmentLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox equipmentTextField = new ViewBox("Equipment",
				heatChart.equipmentProperty());

		Label customerLabel = new Label("Customer");
		customerLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox customerTextField = new ViewBox("Customer",
				heatChart.customerProperty());

		Label poDetailsLabel = new Label("PO Details");
		poDetailsLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox poDetailsTextField = new ViewBox("PO Details",
				heatChart.poDetailsProperty());

		Label drawingLabel = new Label("Drawing No.");
		drawingLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox drawingTextField = new ViewBox("Drawing No.",
				heatChart.drawingNumberProperty());

		Label suryeyorLabel = new Label("Surveyor");
		suryeyorLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox suryeyorTextField = new ViewBox("Suryeyor",
				heatChart.surveyorProperty());

		Label tagNumberLabel = new Label("Tag Number");
		suryeyorLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox tagNumberTextField = new ViewBox("Tag Number",
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

		main.getChildren().addAll(form);

		final ObservableList<HeatChartSheets> data = FXCollections
				.observableArrayList();

		TableView<HeatChartSheets> table = TableUtil
				.createViewHeatChartSheetTable();

		data.setAll(heatChart.getHeatChartSheets());
		table.setItems(data);

		main.getChildren().addAll(ControlsUtil.makeScrollable(table));

		final HBox buttons = new HBox(ViewLayout.H_SPACE);
		buttons.setTranslateY(32);
		final Button printButton = new Button("Print");
		printButton.getStyleClass().add("submit-button");
		buttons.getChildren().addAll(printButton);

		main.getChildren().addAll(buttons);

		printButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				printHeatChart.setHeatChart(heatChart);
				printHeatChart.restart();
			}
		});

		Context.getWindowBottomText().textProperty()
				.bind(Bindings.format("%s", printHeatChart.stateProperty()));

		printHeatChart.stateProperty().addListener(
				new ChangeListener<Worker.State>() {

					@Override
					public void changed(
							ObservableValue<? extends Worker.State> observable,
							Worker.State oldValue, Worker.State newState) {

						if (newState == Worker.State.SUCCEEDED) {
							Alert.showAlert(Context.getWindowStage(), "Alert",
									"Alert", "The report has been saved as "
											+ printHeatChart.getValue());
							Context.getHostServices().showDocument(
									printHeatChart.getValue());
						} else if (newState == Worker.State.FAILED) {
							Alert.showAlert(Context.getWindowStage(), "Error",
									"Error", "Some error cooured. Details : "
											+ printHeatChart.getException()
													.getCause());
						}
					}
				});

		tab.setContent(ControlsUtil.makeScrollable(main));

		return tab;
	}

	public PrintHeatChart getPrintHeatChart() {
		return printHeatChart;
	}

	public void setPrintHeatChart(PrintHeatChart printHeatChart) {
		this.printHeatChart = printHeatChart;
	}
}
