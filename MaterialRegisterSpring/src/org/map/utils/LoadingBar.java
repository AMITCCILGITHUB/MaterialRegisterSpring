package org.map.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoadingBar {

	private Stage stage;
	private Scene scene;
	private StackPane root;
	private double statusBarHeight = 26d;
	private double statusBarPos;

	public LoadingBar() {

		this.statusBarPos = -40d;
	}

	public LoadingBar(double statusBarPos) {

		this.statusBarPos = statusBarPos;
	}

	public void initComponents(final Stage parentStage, String message) {

		this.root = new StackPane();
		this.root.autosize();

		this.scene = new Scene(root, parentStage.getScene().getWidth(),
				statusBarHeight, Color.web("#FFFFFF"));
		this.scene.getStylesheets().add(FileUtil.getStyleAsUrl("loadingbar"));
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
				stage.setY(parentStage.getY()
						+ parentStage.getScene().getHeight() + statusBarPos);
			}
		};

		mainRoot.getChildren().add(rootStack);
		this.root.getChildren().add(mainRoot);

		VBox vb = new VBox();

		HBox content = new HBox(10);
		HBox.setHgrow(content, Priority.ALWAYS);
		content.setAlignment(Pos.CENTER_LEFT);
		content.getStyleClass().add("status-bar-content");

		ProgressIndicator pb = new ProgressIndicator();
		pb.setPrefSize(12, 12);
		Text statusTxt = new Text(message);
		statusTxt.setWrappingWidth(parentStage.getScene().getWidth() - 40);
		statusTxt.getStyleClass().add("status-bar-label");
		content.getChildren().addAll(pb, statusTxt);

		vb.getChildren().addAll(content);
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
						+ parentStage.getScene().getHeight() + statusBarPos);
			}
		});

		parentStage.heightProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {

				stage.setY(parentStage.getY()
						+ parentStage.getScene().getHeight() + statusBarPos);
			}
		});

		parentStage.widthProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {

				stage.setWidth(parentStage.getScene().getWidth());
				stage.setX(parentStage.getX());
			}
		});
	}

	/**
	 * Method to close the pop up and remove the background mask.
	 */
	public void hide() {

		stage.close();
	}

	public final void show() {

		stage.show();
	}
}
