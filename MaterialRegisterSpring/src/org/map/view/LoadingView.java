package org.map.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

import org.map.utils.FileUtil;
import org.springframework.stereotype.Repository;

@Repository("LoadingView")
public class LoadingView implements AbstractPageView {

	private ScrollPane pane;

	public LoadingView() {

		pane = new ScrollPane();
	}

	@Override
	public Node showView() {
		try {
			StackPane main = new StackPane();
			main.getStyleClass().add("category-page");

			main.getChildren().add(FileUtil.getImageAsImageView("loading", ".gif"));

			pane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
			pane.setFitToWidth(true);
			pane.setFitToHeight(true);
			pane.setContent(main);

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
