package org.map.controls;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;

import org.map.utils.FileUtil;
import org.map.utils.ViewLayout;

public class SquareMenuItem extends Group {

	public SquareMenuItem(String iconFile) {

		Button menuBtn = new Button();
		menuBtn.setMinSize(ViewLayout.ICON_WIDTH, ViewLayout.ICON_HEIGHT);
		menuBtn.setPrefSize(ViewLayout.ICON_WIDTH, ViewLayout.ICON_HEIGHT);
		menuBtn.setMaxSize(ViewLayout.ICON_WIDTH, ViewLayout.ICON_HEIGHT);
		menuBtn.getStyleClass().add("square-menu-item");

		ImageView menuIcon = FileUtil.getImageAsImageView(iconFile);
		menuIcon.setTranslateX((ViewLayout.ICON_WIDTH - menuIcon.getBoundsInLocal()
				.getWidth()) / 2);
		menuIcon.setTranslateY((ViewLayout.ICON_HEIGHT - menuIcon
				.getBoundsInLocal().getHeight()) / 2);

		Reflection reflection = new Reflection();
		reflection.setFraction(0.5);

		setEffect(reflection);
		getChildren().addAll(menuBtn, menuIcon);
	}
}
