import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.map.controls.WindowButtons;
import org.map.utils.FileUtil;
import org.map.utils.Notification;

public class Test extends Application {

	private double mouseDragOffsetX = 0;
	private double mouseDragOffsetY = 0;

	private Notification notification;

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
				FileUtil.getStyleAsUrl("menu"),
				FileUtil.getStyleAsUrl("notification"));

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

		ToolBar pageToolBar = new ToolBar();
		pageToolBar.setId("page-toolbar");
		pageToolBar.setMinHeight(29);
		pageToolBar.setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);

		final Button logoutButton = new Button();
		logoutButton.setPrefSize(24, 24);
		logoutButton.getStyleClass().add("logout-button");
		Region spacer3 = new Region();
		HBox.setHgrow(spacer3, Priority.ALWAYS);

		logoutButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
				new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent me) {

						notification.show(primaryStage, me, logoutButton);
					}
				});

		logoutButton.setMaxHeight(Double.MAX_VALUE);
		pageToolBar.getItems().addAll(spacer3, logoutButton);

		BorderPane mainPane = new BorderPane();

		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(4, 4, 4, 4));
		flow.setVgap(4);
		flow.setHgap(4);
		flow.setPrefWrapLength(32);
		flow.setStyle("-fx-background-color: DAE6F3;");

		flow.getChildren()
				.add(StackPaneBuilder
						.create()
						.children(
								RectangleBuilder
										.create()
										.width(32)
										.height(32)
										.fill(Color.TRANSPARENT)
										.style("-fx-fill: linear-gradient(#d6d6d6, #ffffff); -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.4), 20, 0, 0, 0)")
										.build(),
								FileUtil.getImageAsImageView("user-add"))
						.build());

		flow.getChildren()
				.add(StackPaneBuilder
						.create()
						.children(
								RectangleBuilder
										.create()
										.width(32)
										.height(32)
										.fill(Color.TRANSPARENT)
										.style("-fx-fill: linear-gradient(#d6d6d6, #ffffff); -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.4), 20, 0, 0, 0)")
										.build(),
								FileUtil.getImageAsImageView("user-delete"))
						.build());

		flow.getChildren()
				.add(StackPaneBuilder
						.create()
						.children(
								RectangleBuilder
										.create()
										.width(32)
										.height(32)
										.fill(Color.TRANSPARENT)
										.style("-fx-fill: linear-gradient(#d6d6d6, #ffffff); -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.4), 20, 0, 0, 0)")
										.build(),
								FileUtil.getImageAsImageView("user-info"))
						.build());

		mainPane.setTop(pageToolBar);
		mainPane.setCenter(HBoxBuilder.create().prefWidth(40).children(flow)
				.build());

		root.setTop(toolBar);
		root.setCenter(mainPane);

		primaryStage.setScene(scene);
		primaryStage.show();

		notification = new Notification();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
