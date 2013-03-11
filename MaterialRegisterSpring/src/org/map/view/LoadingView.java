package org.map.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import org.map.utils.FileUtil;
import org.springframework.stereotype.Repository;

@Repository("LoadingView")
public class LoadingView implements AbstractPageView {

	private StackPane pane;

	public LoadingView() {

		pane = new StackPane();
	}

	@Override
	public Node showView() {
		try {
			pane.getStyleClass().add("category-page");

			pane.getChildren().add(
					FileUtil.getImageAsImageView("loading", ".gif"));

			return pane;
		} catch (Exception e) {

			return new ErrorView().showView();
		}
	}
	
	@Override
	public DoubleProperty opacityProperty() {
		
		return pane.opacityProperty();
	}
}
