package org.map.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.controls.ErrorView;
import org.map.controls.combobox.CodeComboBox;
import org.map.hibernate.ddo.CodeMaster;
import org.map.hibernate.property.CodeProperty;
import org.map.logger.LoggerUtil;
import org.map.service.AbstractService;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("Settings")
public class Settings {

	@Resource(name = "PersistCodeDetails")
	private AbstractService<CodeMaster, Void> abstractService;

	public Node showView() {

		ScrollPane pane = new ScrollPane();

		try {
			VBox main = ViewLayout
					.getMainVBox("Application Setting", "Details");

			final CodeProperty code = new CodeProperty();

			CodeComboBox codeComboBox = new CodeComboBox("Code", code);

			main.getChildren().addAll(codeComboBox);

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button submitButton = new Button("Submit");
			submitButton.getStyleClass().add("submit-button");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					if (code.get().getCodeNumber() > 1000) {

						abstractService.setPersistEntity(code.get());
						abstractService.setPersistType(PersistType.UPDATE);

						abstractService.restart();
					} else {

						Alert.showAlert(Context.getWindowStage(), "Info",
								"Info", "Please select proper value!");
					}
				}
			});

			buttons.getChildren().addAll(submitButton);

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

	public AbstractService<CodeMaster, Void> getPersistCodeDetails() {
		return abstractService;
	}

	public void setPersistCodeDetails(
			AbstractService<CodeMaster, Void> abstractService) {
		this.abstractService = abstractService;
	}
}
