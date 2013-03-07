package org.map.controls;

import javafx.scene.layout.StackPane;

import org.map.utils.FileUtil;

public class Loading extends StackPane {

	public Loading() {

		try {
			getStyleClass().add("category-page");
			
			getChildren().add(FileUtil.getImageAsImageView("loading", ".gif"));

		} catch (Exception e) {

		}
	}
}
