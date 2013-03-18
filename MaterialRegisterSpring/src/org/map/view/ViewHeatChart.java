package org.map.view;

import java.net.MalformedURLException;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.logger.LoggerUtil;
import org.map.service.PrintHeatChart;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.SearchBoxUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("ViewHeatChart")
public class ViewHeatChart implements AbstractPageView {

	@Resource(name = "PrintHeatChart")
	private PrintHeatChart printHeatChart;

	@Resource(name = "ViewHeatChartTab")
	private ViewHeatChartTab viewHeatChartTab;

	private TabPane pane;

	public ViewHeatChart() {

		pane = new TabPane();
	}

	@Override
	public Node showView() {

		Tab tab = new Tab("View Heat CHart : Search");

		try {
			VBox main = ViewLayout.getMainVBox("View Heat Chart", "Details");

			final ObservableList<HeatChartMaster> mailboxData = FXCollections
					.observableArrayList();
			main.getChildren().addAll(
					SearchBoxUtil.getHeatChartSearchBox("Heat Chart Number",
							mailboxData));

			final TableView<HeatChartMaster> tableMailbox = TableUtil
					.createSearchHeatChartTable();

			main.getChildren().add(ControlsUtil.makeScrollable(tableMailbox));

			tableMailbox.setItems(mailboxData);

			tableMailbox.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getClickCount() == 2) {

						HeatChartMaster selHeatChart = tableMailbox
								.getSelectionModel().getSelectedItem();
						if (selHeatChart != null) {

							createViewTab(selHeatChart);
						}
					}

				}

			});

			EventHandler<ActionEvent> viewHeatChartEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					HeatChartMaster heatChart = tableMailbox
							.getSelectionModel().getSelectedItem();
					if (heatChart != null) {

						createViewTab(heatChart);
					}
				}
			};

			tableMailbox.setContextMenu(TableContextMenu
					.getViewHeatChartContextMenu(viewHeatChartEventHandler));

			tab.setContent(ControlsUtil.makeScrollable(main));
			tab.setClosable(false);
			pane.getTabs().add(tab);
			pane.setSide(Side.TOP);

			return pane;
		} catch (HibernateException e) {
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());

			return new ErrorView().showView();
		} catch (MalformedURLException ex) {
			LoggerUtil.getLogger().debug(ex);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + ex.getMessage());

			return new ErrorView().showView();
		}
	}

	@Override
	public DoubleProperty opacityProperty() {

		return pane.opacityProperty();
	}

	private void createViewTab(final HeatChartMaster heatChart) {

		for (Tab selTab : pane.getTabs()) {
			if (selTab.getId() != null
					&& selTab.getId().equalsIgnoreCase(
							heatChart.getChartNumber())) {
				pane.getSelectionModel().select(selTab);
				return;
			}
		}

		pane.getTabs().add(viewHeatChartTab.showView(heatChart));
	}

	public PrintHeatChart getPrintHeatChart() {
		return printHeatChart;
	}

	public void setPrintHeatChart(PrintHeatChart printHeatChart) {
		this.printHeatChart = printHeatChart;
	}

	public ViewHeatChartTab getViewHeatChartTab() {
		return viewHeatChartTab;
	}

	public void setViewHeatChartTab(ViewHeatChartTab viewHeatChartTab) {
		this.viewHeatChartTab = viewHeatChartTab;
	}
}
