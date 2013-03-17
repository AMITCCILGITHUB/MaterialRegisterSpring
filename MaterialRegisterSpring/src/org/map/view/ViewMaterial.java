package org.map.view;

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

import org.map.hibernate.ddo.MaterialMaster;
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

	@Resource(name = "ViewMaterialTab")
	private ViewMaterialTab viewMaterialTab;

	private TabPane pane;

	public ViewMaterial() {

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

		pane.getTabs().add(viewMaterialTab.showView(material));
	}

	public ViewMaterialTab getViewMaterialTab() {
		return viewMaterialTab;
	}

	public void setViewMaterialTab(ViewMaterialTab viewMaterialTab) {
		this.viewMaterialTab = viewMaterialTab;
	}
}
