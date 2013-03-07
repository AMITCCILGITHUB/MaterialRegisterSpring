package org.map.utils;

import java.net.MalformedURLException;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessagePopUp {

	private Stage stage;
	private Scene scene;
	private StackPane root;
	private double startDragX;
	private double startDragY;
	private static final double DEFAULT_WIDTH = 320d;
	private static final double DEFAULT_HEIGHT = 200d;
	private SimpleDoubleProperty wrapWidth = new SimpleDoubleProperty(
			DEFAULT_WIDTH - 58d);
	private HBox actionBox;
	StackPane mask = StackPaneBuilder
			.create()
			.style("-fx-background-color:black; -fx-opacity:.2; -fx-background-radius: 10px;")
			.build();

	public MessagePopUp(Stage parentStage, String title, String type,
			String message, double width) throws MalformedURLException {

		this(parentStage, title, type, message);
		setWrapWidth(width);
	}

	public MessagePopUp(final Stage parentStage, String title, String type,
			String message) throws MalformedURLException {

		this.root = new StackPane();
		this.root.autosize();

		this.scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT,
				Color.web("#FFFFFF"));
		this.scene.getStylesheets().add(FileUtil.getStyleAsUrl("popup"));
		this.scene.setFill(Color.TRANSPARENT);

		this.stage = new Stage();
		this.stage.setScene(scene);
		this.stage.initOwner(parentStage);
		this.stage.initModality(Modality.APPLICATION_MODAL);
		this.stage.setTitle(title);
		this.stage.initStyle(StageStyle.TRANSPARENT);
		show();

		Group mainRoot = new Group();
		StackPane rootStack = new StackPane() {

			@Override
			protected void layoutChildren() {

				super.layoutChildren();
				stage.setWidth(getWidth());
				stage.setHeight(getHeight() + 20);
			}
		};

		rootStack.autosize();
		rootStack.getStyleClass().add("popUpStage");
		mainRoot.getChildren().add(rootStack);

		this.root.setPadding(new Insets(0, 0, 0, 0));
		this.root.getChildren().add(mainRoot);

		VBox vb = new VBox();
		HBox header = new HBox();
		header.setAlignment(Pos.CENTER_LEFT);
		header.getStyleClass().add("popUpHeader");
		header.setPrefHeight(28);
		Button closeBtn = new Button();
		closeBtn.getStyleClass().add("close-button");
		closeBtn.setPrefSize(16, 16);
		closeBtn.setMinSize(16, 16);
		closeBtn.setMaxSize(16, 16);
		closeBtn.setCursor(Cursor.HAND);
		closeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				closePopUp();
			}
		});

		StackPane sp = new StackPane();
		sp.getChildren().add(closeBtn);
		sp.setAlignment(Pos.CENTER_RIGHT);
		Label titleLbl = new Label(title);
		titleLbl.getStyleClass().add("popUpHeaderLbl");

		header.getChildren().addAll(titleLbl, sp);
		HBox.setHgrow(sp, Priority.ALWAYS);
		addDragListeners(header);

		/*
		 * CONTENT
		 */
		StackPane content = new StackPane();
		content.setAlignment(Pos.TOP_LEFT);
		content.setPadding(new Insets(8, 5, 5, 5));
		content.getStyleClass().add("popUpBody");
		content.setMinHeight(50);

		HBox messageBox = new HBox(16);
		ImageView icon = null;

		if (type.equalsIgnoreCase("Error")) {
			icon = FileUtil.getImageAsImageView("error");
		} else if (type.equalsIgnoreCase("Info")) {
			icon = FileUtil.getImageAsImageView("info");
		} else if (type.equalsIgnoreCase("Confirm")) {
			icon = FileUtil.getImageAsImageView("confirm");
		} else {
			icon = FileUtil.getImageAsImageView("info");
		}

		Text contentTxt = new Text(message);
		contentTxt.wrappingWidthProperty().bind(this.wrapWidth);
		messageBox.getChildren().addAll(icon, contentTxt);
		content.getChildren().add(messageBox);
		VBox.setVgrow(content, Priority.ALWAYS);

		actionBox = new HBox();
		actionBox.setAlignment(Pos.CENTER);
		actionBox.getStyleClass().add("popUpActionBox");
		actionBox.setPadding(new Insets(5, 0, 8, 0));
		Button okBtn = new Button("Ok");
		okBtn.getStyleClass().add("submit-button");
		okBtn.setPrefWidth(75);
		okBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent actionEvent) {

				closePopUp();
			}
		});
		actionBox.getChildren().add(okBtn);

		vb.getChildren().addAll(header, content, actionBox);
		rootStack.getChildren().add(vb);

		parentStage.xProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {

				stage.setX(Double.valueOf(arg2.toString()));
			}
		});

		parentStage.yProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {

				stage.setY(Double.valueOf(arg2.toString())
						+ parentStage.getScene().getHeight() + 20);
			}
		});
	}

	protected Stage getStage() {

		return this.stage;
	}

	public double getWrapWidth() {

		return wrapWidth.get();
	}

	public final void setWrapWidth(double wrapWidth) {

		this.wrapWidth.set(wrapWidth);
	}

	public SimpleDoubleProperty wrapWidthProperty() {

		return wrapWidth;
	}

	public HBox getActionBox() {

		return actionBox;
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
	public void closePopUp() {

		Parent parentRoot = ((Stage) stage.getOwner()).getScene().getRoot();
		if (parentRoot instanceof StackPane) {
			((StackPane) parentRoot).getChildren().remove(mask);
		}
		stage.close();
	}

	/**
	 * Method to open the pop up with the background mask.
	 */
	public final void show() {

		StackPane parentRoot = (StackPane) ((Stage) stage.getOwner())
				.getScene().getRoot();
		parentRoot.getChildren().add(mask);
		stage.show();
	}
}
