package org.map.controls;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Vertical box with 3 small buttons for window close, minimize and maximize.
 */
public class WindowButtons extends VBox {

	private Stage stage;
	private Rectangle2D backupWindowBounds = null;
	private boolean maximized = false;

	public WindowButtons(final Stage stage) throws MalformedURLException {

		super(4);
		this.stage = stage;
		Button closeBtn = new Button();
		closeBtn.setId("window-close");
		closeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				Platform.exit();
			}
		});
		Button minBtn = new Button();
		minBtn.setId("window-min");
		minBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				stage.setIconified(true);
			}
		});
		Button maxBtn = new Button();
		maxBtn.setId("window-max");
		maxBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				toogleMaximized();
			}
		});

		Image[] icon = {
				new Image(new File("resources/images/mr_logo_16.png").toURI()
						.toURL().toString()),
				new Image(new File("resources/images/mr_logo_24.png").toURI()
						.toURL().toString()),
				new Image(new File("resources/images/mr_logo_32.png").toURI()
						.toURL().toString()) };
		stage.getIcons().addAll(icon);

		getChildren().addAll(closeBtn, minBtn, maxBtn);
	}

	public void toogleMaximized() {

		final Screen screen = Screen.getScreensForRectangle(stage.getX(),
				stage.getY(), 1, 1).get(0);
		if (maximized) {
			maximized = false;
			if (backupWindowBounds != null) {
				stage.setX(backupWindowBounds.getMinX());
				stage.setY(backupWindowBounds.getMinY());
				stage.setWidth(backupWindowBounds.getWidth());
				stage.setHeight(backupWindowBounds.getHeight());
			}
		} else {
			maximized = true;
			backupWindowBounds = new Rectangle2D(stage.getX(), stage.getY(),
					stage.getWidth(), stage.getHeight());
			stage.setX(screen.getVisualBounds().getMinX());
			stage.setY(screen.getVisualBounds().getMinY());
			stage.setWidth(screen.getVisualBounds().getWidth());
			stage.setHeight(screen.getVisualBounds().getHeight());
		}
	}

	public boolean isMaximized() {

		return maximized;
	}
}
