package org.map.view;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.map.controls.ViewBox;
import org.map.controls.ViewIntegerBox;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialTests;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.SearchBoxUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("ViewMaterial")
public class ViewMaterial implements AbstractPageView {

	private TabPane pane;

	public ViewMaterial(){

		pane = new TabPane();
	}
	
	@Override
	public Node showView() {

		Tab tab = new Tab("View Material : Search");

		try {
			VBox main = ViewLayout.getMainVBox("View Material", "Details");

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

							createViewTab(material);
						}
					}
				}

			});

			EventHandler<ActionEvent> viewMaterialEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					MaterialMaster material = table.getSelectionModel()
							.getSelectedItem();
					if (material != null) {

						createViewTab(material);
					}
				}
			};

			table.setContextMenu(TableContextMenu
					.getViewMaterialContextMenu(viewMaterialEventHandler));

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

	public void createViewTab(final MaterialMaster material) {

		for (Tab selTab : pane.getTabs()) {
			if (selTab.getId() != null
					&& selTab.getId().equalsIgnoreCase(material.getCtNumber())) {

				pane.getSelectionModel().select(selTab);
				return;
			}
		}

		Tab tab = new Tab("View Material : " + material.getCtNumber());
		tab.setId(material.getCtNumber());

		VBox main = ViewLayout.getMainVBox("Material", "Details");

		GridPane form = new GridPane();
		form.setHgap(ViewLayout.H_SPACE);
		form.setVgap(ViewLayout.V_SPACE);

		Label ctNumberLabel = new Label("CT Number");
		ctNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox ctNumberTextField = new ViewBox(material.getCtNumber(),
				material.ctNumberProperty());

		Label agencyLabel = new Label("Inspection Agency");
		agencyLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox agencyTextField = new ViewBox(material
				.getInspectionAgency().getName(), material
				.getInspectionAgency().nameProperty());

		Label specLabel = new Label("Specification");
		specLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox specTextField = new ViewBox(material.getSpecification()
				.getName(), material.getSpecification().nameProperty());

		Label itemLabel = new Label("Item");
		itemLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox itemTextField = new ViewBox(material.getItem().getName(),
				material.getItem().nameProperty());

		Label sizeLabel = new Label("Size");
		sizeLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox sizeTextField = new ViewBox(material.getSize(),
				material.sizeProperty());

		Label quantityLabel = new Label("Quantity");
		quantityLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewIntegerBox quantityTextField = new ViewIntegerBox(
				material.quantityProperty());

		Label heatNumberLabel = new Label("Heat / Lot Number");
		heatNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox heatNumberTextField = new ViewBox(
				material.getHeatNumber(), material.heatNumberProperty());

		Label plateNumberLabel = new Label("Plate / Product Number");
		plateNumberLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ViewBox plateNumberTextField = new ViewBox(
				material.getPlateNumber(), material.plateNumberProperty());

		final TableView<MaterialTests> table = TableUtil
				.createViewMaterialTable();

		final ObservableList<MaterialTests> materialTestsData = FXCollections
				.observableArrayList(material.getMaterialTests());
		table.setItems(materialTestsData);

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

		main.getChildren().addAll(form, table);

		tab.setContent(ControlsUtil.makeScrollable(main));
		pane.getTabs().add(tab);
	}
}
