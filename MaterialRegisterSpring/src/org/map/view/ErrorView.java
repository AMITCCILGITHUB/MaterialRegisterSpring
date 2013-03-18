package org.map.view;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;

import org.map.utils.FileUtil;
import org.springframework.stereotype.Repository;

@Repository("ErrorView")
public class ErrorView implements AbstractPageView {

	private String message;
	private ScrollPane pane;

	public ErrorView() {

		pane = new ScrollPane();
		this.message = "Oops! There is an error.";
	}

	public ErrorView(String errorMsg) {

		pane = new ScrollPane();
		this.message = "Oops! There is an error. Details : " + errorMsg;
	}

	@Override
	public Node showView() {

		StackPane main = new StackPane();
		main.getStyleClass().add("category-page");

		Text errorText = TextBuilder.create().text(this.message)
				.fill(Color.WHITE).build();
		errorText.setTranslateY(64);

		main.getChildren().add(FileUtil.getImageAsImageView("bug"));
		main.getChildren().add(errorText);

		pane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
		pane.setFitToWidth(true);
		pane.setFitToHeight(true);
		pane.setContent(main);

		return pane;
	}

	@Override
	public DoubleProperty opacityProperty() {

		return pane.opacityProperty();
	}
}
