package org.map.controls;

import javafx.scene.layout.VBox;
import javafx.scene.text.TextBuilder;

public class ErrorView extends VBox {

	public ErrorView() {

		this.getChildren().add(
				TextBuilder.create().text("Oops! There is an error.").build());
	}

}
