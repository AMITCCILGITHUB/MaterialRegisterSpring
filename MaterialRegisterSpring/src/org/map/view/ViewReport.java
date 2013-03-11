package org.map.view;

import javax.annotation.Resource;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.map.hibernate.register.MaterialRegister;
import org.map.hibernate.utils.MaterialData;
import org.map.logger.LoggerUtil;
import org.map.service.PrintMaterialRegister;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.SearchBoxUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("ViewReport")
public class ViewReport implements AbstractPageView {

	@Resource(name = "MaterialData")
	private MaterialData materialData;

	@Resource(name = "PrintMaterialRegister")
	private PrintMaterialRegister printMaterialRegister;

	private ScrollPane pane;

	public ViewReport() {

		pane = new ScrollPane();
	}

	@Override
	public Node showView() {

		try {
			VBox main = ViewLayout.getMainVBox("Material Register", "Details");

			final ObservableList<MaterialRegister> data = FXCollections
					.observableArrayList(MaterialRegister
							.getMaterialRegisterList(materialData
									.getMaterialList()));

			main.getChildren().addAll(
					SearchBoxUtil.getRegisterSearchBox("CT Number", data));

			TableView<MaterialRegister> table = TableUtil
					.createViewMaterialRegisterTable();

			table.setItems(data);

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button printButton = new Button("Print");
			printButton.getStyleClass().add("submit-button");
			printButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					printMaterialRegister.setData(data);
					printMaterialRegister.restart();
				}
			});

			Context.getWindowBottomText()
					.textProperty()
					.bind(Bindings.format("%s",
							printMaterialRegister.stateProperty()));

			printMaterialRegister.stateProperty().addListener(
					new ChangeListener<State>() {

						@Override
						public void changed(
								ObservableValue<? extends State> arg0,
								State oldValue, State newState) {

							if (newState == Worker.State.SUCCEEDED) {
								Alert.showAlert(
										Context.getWindowStage(),
										"Alert",
										"Alert",
										"The report has been saved as "
												+ printMaterialRegister
														.getValue());
								Context.getHostServices().showDocument(
										printMaterialRegister.getValue());
							} else if (newState == Worker.State.FAILED) {
								Alert.showAlert(Context.getWindowStage(),
										"Error", "Error",
										"Some error cooured. Details : "
												+ printMaterialRegister
														.getException()
														.getCause());
							}
						}
					});

			buttons.getChildren().addAll(printButton);
			main.getChildren().addAll(table, buttons);

			EventHandler<ActionEvent> printEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					printButton.fire();
				}
			};

			table.setContextMenu(TableContextMenu
					.getPrintMaterialContextMenu(printEventHandler));

			pane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
			pane.setFitToWidth(true);
			pane.setContent(main);

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

	public MaterialData getMaterialData() {
		return materialData;
	}

	public void setMaterialData(MaterialData materialData) {
		this.materialData = materialData;
	}

	public PrintMaterialRegister getPrintMaterialRegister() {
		return printMaterialRegister;
	}

	public void setPrintMaterialRegister(
			PrintMaterialRegister printMaterialRegister) {
		this.printMaterialRegister = printMaterialRegister;
	}
}
