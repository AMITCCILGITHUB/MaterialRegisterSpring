package org.map.view;

import org.springframework.stereotype.Repository;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextBuilder;

@Repository("ErrorView")
public class ErrorView implements AbstractPageView {

	private String message;
	private VBox pane;

	public ErrorView() {

		pane = new VBox();
		this.message = "Oops! There is an error.";
	}

	public ErrorView(String errorMsg) {

		pane = new VBox();
		this.message = "Oops! There is an error. Details : " + errorMsg;
	}

	@Override
	public Node showView() {

		pane.getChildren().add(TextBuilder.create().text(this.message).build());

		return pane;
	}
	
	@Override
	public DoubleProperty opacityProperty() {
		
		return pane.opacityProperty();
	}
}
