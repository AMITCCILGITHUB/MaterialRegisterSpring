package org.map.view;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import javax.annotation.Resource;

import org.map.controls.ErrorView;
import org.map.logger.LoggerUtil;
import org.map.service.PersistDatabase;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("DatabaseBackup")
public class DatabaseBackup {

	@Resource(name = "PersistDatabase")
	private PersistDatabase persistDatabase;

	public Node showView() {

		ScrollPane pane = new ScrollPane();

		try {
			VBox main = ViewLayout.getMainVBox("Backup Database", "Details");

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button submitButton = new Button("Submit");
			submitButton.getStyleClass().add("submit-button");
			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Save As");

					FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
							"SQL files (*.sql)", "*.sql");
					fileChooser.getExtensionFilters().add(extFilter);

					File file = fileChooser.showSaveDialog(Context
							.getWindowStage());
					if (file != null) {

						persistDatabase.setFileName(file.getAbsolutePath());

						persistDatabase.restart();
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

	public PersistDatabase getPersistCodeDetails() {
		return persistDatabase;
	}

	public void setPersistCodeDetails(PersistDatabase persistDatabase) {
		this.persistDatabase = persistDatabase;
	}
}
