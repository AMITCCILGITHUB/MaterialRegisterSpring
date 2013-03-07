package org.map.utils;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;

public class ControlsUtil {

	public static Node makeScrollable(Node content) {
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(content);

		return scrollPane;
	}

	public static Node makeScrollable(TableView<?> table) {
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setPrefHeight(300);
		scrollPane.setContent(table);

		return scrollPane;
	}
}
