package org.map.controls;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class CloseButton extends Button {

	private ParallelTransition startTransition;
	private ScaleTransition scaleTransition;
	private ScaleTransition initTransition;
	private FadeTransition fadeTransition;
	private SequentialTransition startsSequentialTransition;
	private DoubleProperty expandToMaxProperty;

	public CloseButton(int size) {

		expandToMaxProperty = new SimpleDoubleProperty(1.2);
		
		this.getStyleClass().add("close-button");
		this.setPrefSize(size, size);

		scaleTransition = new ScaleTransition(Duration.millis(200), this);
		scaleTransition.setCycleCount(1);
		scaleTransition.setInterpolator(Interpolator.EASE_BOTH);

		fadeTransition = new FadeTransition(Duration.millis(200), this);
		fadeTransition.setCycleCount(1);
		fadeTransition.setInterpolator(Interpolator.EASE_BOTH);

		startTransition = new ParallelTransition();
		startTransition.setCycleCount(2);
		startTransition.setAutoReverse(true);
		startTransition.getChildren().addAll(scaleTransition, fadeTransition);

		initTransition = new ScaleTransition(Duration.millis(200), this);
		initTransition.setToX(1);
		initTransition.setToY(1);
		initTransition.setCycleCount(1);
		initTransition.setInterpolator(Interpolator.EASE_BOTH);

		startsSequentialTransition = new SequentialTransition();
		startsSequentialTransition.getChildren().addAll(startTransition,
				initTransition);

		this.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				
				scaleTransition.setFromX(getScaleX());
				scaleTransition.setFromY(getScaleY());
				scaleTransition.setToX(expandToMaxProperty.get());
				scaleTransition.setToY(expandToMaxProperty.get());
				scaleTransition.playFromStart();
			}
		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				
				scaleTransition.setFromX(getScaleX());
				scaleTransition.setFromY(getScaleY());
				scaleTransition.setToX(1);
				scaleTransition.setToY(1);
				scaleTransition.playFromStart();
			}
		});

		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				
				scaleTransition.setFromX(getScaleX());
				scaleTransition.setFromY(getScaleY());
				scaleTransition.setToX(2);
				scaleTransition.setToY(2);
				fadeTransition.setFromValue(1.0f);
				fadeTransition.setToValue(0.5f);
				startsSequentialTransition.playFromStart();
			}
		});
	}

	public DoubleProperty expandToMaxProperty() {

		return expandToMaxProperty;
	}
}
