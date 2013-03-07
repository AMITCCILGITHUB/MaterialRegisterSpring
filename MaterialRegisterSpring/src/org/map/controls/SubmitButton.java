package org.map.controls;

import javafx.scene.control.Button;

public class SubmitButton extends Button {

	public SubmitButton(String displayText) {

		this.getStyleClass().add("submit-button");
		this.setMaxSize(94, 22);
		this.setMinSize(94, 22);
		this.setPrefSize(94, 22);
		this.setText(displayText);
	}
}
