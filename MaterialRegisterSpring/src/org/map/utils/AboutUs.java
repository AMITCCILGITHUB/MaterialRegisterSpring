package org.map.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AboutUs {

	private Stage stage;
	private Scene scene;
	private StackPane root;
	private double startDragX;
	private double startDragY;
	private double aboutWindowsWidth = 1020d;
	private double aboutWindowsHeight = 700d;
	private double aboutWindowsPos;
	StackPane mask = StackPaneBuilder
			.create()
			.style("-fx-background-color:black; -fx-opacity:.2; -fx-background-radius: 10px;")
			.build();

	public AboutUs() {

		this.aboutWindowsPos = 0d;
	}

	public AboutUs(double aboutWindowsPos) {

		this.aboutWindowsPos = aboutWindowsPos;
	}

	public void initComponents(final Stage parentStage)
			throws MalformedURLException {

		this.root = new StackPane();
		this.root.autosize();

		this.scene = new Scene(root, aboutWindowsWidth, aboutWindowsHeight,
				Color.web("#FFFFFF"));
		this.scene.getStylesheets().add(FileUtil.getStyleAsUrl("style"));
		this.scene.setFill(Color.TRANSPARENT);

		this.stage = new Stage();
		this.stage.setScene(scene);
		this.stage.initOwner(parentStage);
		this.stage.initModality(Modality.NONE);
		this.stage.initStyle(StageStyle.UNDECORATED);

		Group mainRoot = new Group();
		StackPane rootStack = new StackPane() {

			@Override
			protected void layoutChildren() {

				super.layoutChildren();
				stage.setWidth(scene.getWidth());
				stage.setHeight(scene.getHeight());
				stage.setX(parentStage.getX());
				stage.setY(parentStage.getY() + aboutWindowsPos);
			}
		};

		mainRoot.getChildren().add(rootStack);
		this.root.getChildren().add(mainRoot);

		HBox vb = new HBox();
		addDragListeners(vb);

		VBox leftContent = new VBox();
		leftContent.getStyleClass().add("about-content");

		Text aboutTitle = new Text("Our Address");
		Text company = new Text("MAP Consultancy Services");
		Text address1 = new Text("A/201, Yugandhar Sudama, Manpada Road,");
		Text address2 = new Text("Dombivli (E), Thane, Maharashtra");
		Text telephone = new Text("+91 9324788024 / +91 7738610004");
		Text email = new Text("info@mapconsultancyservices.com");
		Text website = new Text(
				"website : http://www.mapconsultancyservices.com/");

		Button submitBtn = new Button("Submit");
		submitBtn.getStyleClass().add("map-submit-button");
		submitBtn.setMinWidth(79);
		submitBtn.setPrefHeight(29);
		leftContent.getChildren().addAll(aboutTitle, company, address1,
				address2, telephone, email, website, submitBtn);

		VBox rightContent = new VBox();
		rightContent.setPrefSize(515, 410);
		rightContent.getStyleClass().add("map-content");

		File aboutUsFile = new File("resources/aboutus/aboutus.html");
		final URL urlGoogleMaps = aboutUsFile.toURI().toURL();
		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		webEngine.load(urlGoogleMaps.toExternalForm());
		rightContent.getChildren().addAll(webView);

		vb.getChildren().addAll(leftContent, rightContent);
		rootStack.getChildren().add(vb);
	}

	public final void addDragListeners(final Node n) {

		n.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {

				startDragX = me.getSceneX();
				startDragY = me.getSceneY();
				root.setStyle("-fx-opacity:.7;");
			}
		});
		n.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {

				root.setStyle("-fx-opacity:1;");
			}
		});
		n.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent me) {

				stage.setX(me.getScreenX() - startDragX);
				stage.setY(me.getScreenY() - startDragY);
			}
		});
	}

	/**
	 * Method to close the pop up and remove the background mask.
	 */
	public void hide() {

		Parent parentRoot = ((Stage) stage.getOwner()).getScene().getRoot();
		if (parentRoot instanceof StackPane) {
			((StackPane) parentRoot).getChildren().remove(mask);
		}
		stage.close();
	}

	public final void show() {

		StackPane parentRoot = (StackPane) ((Stage) stage.getOwner())
				.getScene().getRoot();
		parentRoot.getChildren().add(mask);
		stage.show();
	}
}
