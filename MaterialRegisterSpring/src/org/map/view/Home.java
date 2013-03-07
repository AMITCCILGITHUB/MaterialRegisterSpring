package org.map.view;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.DepthTest;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.annotation.Resource;

import org.map.controls.SearchBox;
import org.map.controls.WindowButtons;
import org.map.hibernate.utils.MenuData;
import org.map.service.LoadPageView;
import org.map.utils.Context;
import org.map.utils.FileUtil;
import org.springframework.stereotype.Repository;

@Repository("Home")
public class Home {

	private Pane pageArea;
	private double mouseDragOffsetX = 0;
	private double mouseDragOffsetY = 0;

	@Resource(name = "MenuData")
	private MenuData menuData;

	@Resource(name = "LoadPageView")
	private LoadPageView loadPageView;

	public void showView() throws MalformedURLException {

		final Stage primaryStage = new Stage();

		Context.setWindowStage(primaryStage);

		primaryStage.setTitle("Material Register");
		primaryStage.initStyle(StageStyle.UNDECORATED);

		BorderPane root = new BorderPane();
		root.getStyleClass().add("applet");
		root.setId("root");

		StackPane layerPane = new StackPane();
		layerPane.setDepthTest(DepthTest.DISABLE);
		layerPane.getChildren().add(root);

		Scene scene = new Scene(layerPane, 1020, 700, false);
		scene.getStylesheets().addAll(FileUtil.getStyleAsUrl("style"),
				FileUtil.getStyleAsUrl("calendar"),
				FileUtil.getStyleAsUrl("controls"),
				FileUtil.getStyleAsUrl("menu"));

		ToolBar toolBar = new ToolBar();
		toolBar.setId("mainToolBar");
		ImageView logo = FileUtil.getImageAsImageView("logo");

		HBox.setMargin(logo, new Insets(0, 0, 0, 5));
		toolBar.getItems().add(logo);
		Region spacer = new Region();
		HBox.setHgrow(spacer, Priority.ALWAYS);
		toolBar.getItems().add(spacer);

		Region spacer2 = new Region();
		HBox.setHgrow(spacer2, Priority.ALWAYS);
		toolBar.getItems().add(spacer2);

		final SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		VBox rightBar = new VBox(2);
		SearchBox searchBox = new SearchBox();
		final Text timeText = new Text(df.format(Calendar.getInstance()
				.getTime()));
		timeText.setFill(Color.WHITE);
		VBox.setMargin(timeText, new Insets(5, 0, 0, 12));
		rightBar.getChildren().addAll(searchBox, timeText);
		Timeline delayTimeline = new Timeline();
		delayTimeline.setCycleCount(Timeline.INDEFINITE);
		delayTimeline.getKeyFrames().add(
				new KeyFrame(Duration.ONE, new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						timeText.setText(df.format(Calendar.getInstance()
								.getTime()));
					}
				}));
		delayTimeline.play();

		HBox.setMargin(rightBar, new Insets(0, 5, 0, 0));
		toolBar.getItems().add(rightBar);

		toolBar.setPrefHeight(66);
		toolBar.setMinHeight(66);
		toolBar.setMaxHeight(66);
		GridPane.setConstraints(toolBar, 0, 0);

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

		final TreeItem<String> tiRoot = new TreeItem<String>();

		if (Context.getLoggedUser().getRole().getName()
				.equalsIgnoreCase("Admin")) {
			tiRoot.getChildren().add(new TreeItem<>("Add User"));
			tiRoot.getChildren().add(new TreeItem<>("View User"));
			tiRoot.getChildren().add(new TreeItem<>("Edit User"));

			tiRoot.getChildren().add(new TreeItem<>("Add Validation"));
			tiRoot.getChildren().add(new TreeItem<>("View Validation"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Validation"));

			tiRoot.getChildren().add(new TreeItem<>("Add Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material Register"));

			tiRoot.getChildren().add(new TreeItem<>("Add Heat Chart"));
			tiRoot.getChildren().add(new TreeItem<>("View Heat Chart"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Heat Chart"));

			tiRoot.getChildren().add(new TreeItem<>("Settings"));
			
			tiRoot.getChildren().add(new TreeItem<>("Backup"));
		}
		if (Context.getLoggedUser().getRole().getName()
				.equalsIgnoreCase("Maker")) {
			tiRoot.getChildren().add(new TreeItem<>("Add Validation"));
			tiRoot.getChildren().add(new TreeItem<>("View Material"));

			tiRoot.getChildren().add(new TreeItem<>("Add Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material"));

			tiRoot.getChildren().add(new TreeItem<>("Add Heat Chart"));
			tiRoot.getChildren().add(new TreeItem<>("View Heat Chart"));
		}
		if (Context.getLoggedUser().getRole().getName()
				.equalsIgnoreCase("Normal")) {
			tiRoot.getChildren().add(new TreeItem<>("View Validation"));
			tiRoot.getChildren().add(new TreeItem<>("View Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material Register"));
			tiRoot.getChildren().add(new TreeItem<>("View Heat Chart"));
		}
		if (Context.getLoggedUser().getRole().getName()
				.equalsIgnoreCase("Checker")) {
			tiRoot.getChildren().add(new TreeItem<>("View Validation"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Validation"));

			tiRoot.getChildren().add(new TreeItem<>("View Material"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Material"));
			tiRoot.getChildren().add(new TreeItem<>("View Material Register"));

			tiRoot.getChildren().add(new TreeItem<>("View Heat Chart"));
			tiRoot.getChildren().add(new TreeItem<>("Edit Heat Chart"));
		}
		tiRoot.getChildren().add(new TreeItem<>("Change Password"));

		final TreeView<String> pageTree = new TreeView<>(tiRoot);
		pageTree.setId("page-tree");
		pageTree.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		pageTree.setShowRoot(false);
		pageTree.setEditable(false);
		pageTree.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		pageTree.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Object>() {

					@Override
					public void changed(
							ObservableValue<? extends Object> observable,
							Object oldValue, Object newValue) {

						TreeItem<String> selectedPage = (TreeItem<String>) pageTree
								.getSelectionModel().getSelectedItem();

						if (selectedPage != null) {

							loadPageView.setPageName(selectedPage.getValue()
									.toString());
						} else {
							loadPageView.setPageName("Change Password");
						}

						Context.getWindowBottomText()
								.textProperty()
								.bind(Bindings.format("%s",
										loadPageView.stateProperty()));

						loadPageView.restart();
					}
				});

		ToolBar pageToolBar = new ToolBar();
		pageToolBar.setId("page-toolbar");
		pageToolBar.setMinHeight(29);
		pageToolBar.setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);

		ToolBar pageTreeToolBar = new ToolBar();
		pageTreeToolBar.setId("page-tree-toolbar");
		pageTreeToolBar.setMinHeight(29);
		pageToolBar.setMaxSize(Double.MAX_VALUE, Control.USE_PREF_SIZE);

		BorderPane leftSplitPane = new BorderPane();
		leftSplitPane.setTop(pageTreeToolBar);
		leftSplitPane.setCenter(pageTree);

		Button logoutButton = new Button();
		Region spacer3 = new Region();
		HBox.setHgrow(spacer3, Priority.ALWAYS);
		logoutButton.setId("LogoutButton");

		logoutButton.setGraphic(FileUtil.getImageAsImageView("logout"));
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Context.getLoggedUser().clean();
				primaryStage.hide();
				Context.getLoginStage().show();
			}
		});
		logoutButton.setMaxHeight(Double.MAX_VALUE);
		pageToolBar.getItems().addAll(spacer3, logoutButton);

		pageArea = new StackPane() {

			@Override
			protected void layoutChildren() {

				for (Node child : pageArea.getChildren()) {
					child.resizeRelocate(0, 0, pageArea.getWidth(),
							pageArea.getHeight());
				}
			}
		};
		pageArea.setId("page-area");
		BorderPane rightSplitPane = new BorderPane();
		rightSplitPane.setTop(pageToolBar);
		rightSplitPane.setCenter(pageArea);
		Context.setPageArea(pageArea);

		SplitPane splitPane = new SplitPane();
		splitPane.setId("page-splitpane");
		splitPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		GridPane.setConstraints(splitPane, 0, 1);
		splitPane.getItems().addAll(leftSplitPane, rightSplitPane);
		splitPane.setDividerPosition(0, 0.25);

		root.setTop(toolBar);
		root.setCenter(splitPane);

		Text statusText = TextBuilder.create().build();

		Text copyrightText = TextBuilder
				.create()
				.textAlignment(TextAlignment.CENTER)
				.text(" Developed and Maintened by \n\u00A9 MAP Consultancy Services")
				.build();

		BorderPane bottom = BorderPaneBuilder.create().minHeight(32)
				.maxWidth(Double.MAX_VALUE).maxHeight(Double.MAX_VALUE)
				.styleClass("bottom-bar").left(statusText).right(copyrightText)
				.build();

		root.setBottom(bottom);

		Context.setWindowBottomText(statusText);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public MenuData getMenuData() {
		return menuData;
	}

	public void setMenuData(MenuData menuData) {
		this.menuData = menuData;
	}

	public LoadPageView getLoadPageView() {
		return loadPageView;
	}

	public void setLoadPageView(LoadPageView loadPageView) {
		this.loadPageView = loadPageView;
	}
}
