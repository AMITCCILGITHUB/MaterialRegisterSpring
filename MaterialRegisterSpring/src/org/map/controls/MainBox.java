package org.map.controls;

import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import org.map.utils.ViewLayout;

public class MainBox extends VBox {

	public MainBox(String pageHeader, String categoryHeader) {
		
		super(ViewLayout.H_SPACE);
		setVgrow(this, Priority.ALWAYS);
		getStyleClass().add("category-page");

		Label header = new Label(pageHeader);
		header.getStyleClass().add("page-header");
		getChildren().add(header);

		Label yearCategoryHeader = new Label(categoryHeader);
		yearCategoryHeader.setMaxWidth(Double.MAX_VALUE);
		yearCategoryHeader.setMinHeight(Control.USE_PREF_SIZE);
		yearCategoryHeader.getStyleClass().add("category-header");
		getChildren().add(yearCategoryHeader);
	}
}
