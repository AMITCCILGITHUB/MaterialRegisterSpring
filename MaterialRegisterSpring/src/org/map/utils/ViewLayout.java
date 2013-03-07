package org.map.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ViewLayout {

	public static final double LABEL_WIDTH = 100;
	public static final double LABEL_HEIGHT = 24;
	public static final double CHECKBOX_WIDTH = 130;
	public static final double CHECKBOX_HEIGHT = 50;
	public static final double REGION_WIDTH = 130;
	public static final double REGION_HEIGHT = 24;
	public static final double TEXTBOX_WIDTH = 130;
	public static final double TEXTBOX_HEIGHT = 24;
	public static final double HGAP = 8;
	public static final double VGAP = 8;
	public static final double ICON_WIDTH = 64;
	public static final double ICON_HEIGHT = 64;
	
	public static final double H_SPACE = 8;
	public static final double V_SPACE = 20;

	public static final double COLUMN_WIDTH = 132;

	public static VBox getMainVBox(String pageTitle, String categoryTitle) {

		VBox main = new VBox(ViewLayout.V_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label(pageTitle);
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailHeader = new Label(categoryTitle);
		detailHeader.setMaxWidth(Double.MAX_VALUE);
		detailHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailHeader.getStyleClass().add("category-header");
		main.getChildren().add(detailHeader);

		return main;
	}

	public static VBox getMainVBox(String pageTitle, SimpleStringProperty categoryTitle) {

		VBox main = new VBox(ViewLayout.V_SPACE);
		main.getStyleClass().add("category-page");

		Label header = new Label(pageTitle);
		header.getStyleClass().add("page-header");
		main.getChildren().add(header);

		Label detailHeader = new Label();
		detailHeader.setMaxWidth(Double.MAX_VALUE);
		detailHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailHeader.getStyleClass().add("category-header");
		detailHeader.textProperty().bindBidirectional(categoryTitle);
		main.getChildren().add(detailHeader);

		return main;
	}
	
	public static Label getCategoryLabel(String categoryTitle) {

		Label detailHeader = new Label(categoryTitle);
		detailHeader.setMaxWidth(Double.MAX_VALUE);
		detailHeader.setMinHeight(Control.USE_PREF_SIZE);
		detailHeader.getStyleClass().add("category-header");

		return detailHeader;
	}
}
