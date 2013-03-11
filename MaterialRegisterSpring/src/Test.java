import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.map.controls.WindowButtons;
import org.map.utils.FileUtil;

public class Test extends Application {

	private double mouseDragOffsetX = 0;
	private double mouseDragOffsetY = 0;

	@Override
	public void start(final Stage primaryStage) throws MalformedURLException {

		primaryStage.setTitle("Material Register Test");
		primaryStage.initStyle(StageStyle.UNDECORATED);

		BorderPane root = new BorderPane();
		root.getStyleClass().add("applet");
		root.setId("root");

		StackPane layerPane = new StackPane();
		layerPane.setDepthTest(DepthTest.DISABLE);
		layerPane.getChildren().add(root);

		Scene scene = new Scene(layerPane, 400, 400, false);
		scene.getStylesheets().addAll(FileUtil.getStyleAsUrl("style"),
				FileUtil.getStyleAsUrl("calendar"),
				FileUtil.getStyleAsUrl("controls"),
				FileUtil.getStyleAsUrl("menu"));

		ToolBar toolBar = new ToolBar();
		toolBar.setId("mainToolBar");

		final WindowButtons windowButtons = new WindowButtons(primaryStage);
		toolBar.getItems().add(windowButtons);
		toolBar.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() == 2) {
					windowButtons.toogleMaximized();
				}
			}
		});
		toolBar.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				mouseDragOffsetX = event.getSceneX();
				mouseDragOffsetY = event.getSceneY();
			}
		});
		toolBar.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (!windowButtons.isMaximized()) {

					primaryStage.setX(event.getScreenX() - mouseDragOffsetX);
					primaryStage.setY(event.getScreenY() - mouseDragOffsetY);
				}
			}
		});

		StackPane content = new StackPane();

		Rectangle menuItem = RectangleBuilder
				.create()
				.width(32)
				.height(32)
				.clip(CircleBuilder.create().translateX(16).translateY(16)
						.radius(22).build())
				.fill(Color.TRANSPARENT)
				.style("-fx-fill: linear-gradient(#d6d6d6, #ffffff); -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.4), 20, 0, 0, 0)")
				.build();

		content.getChildren().add(menuItem);
		content.getChildren().add(FileUtil.getImageAsImageView("user_info"));

		root.setTop(toolBar);
		root.setCenter(content);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
