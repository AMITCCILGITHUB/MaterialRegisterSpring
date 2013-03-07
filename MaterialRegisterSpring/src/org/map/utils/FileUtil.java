package org.map.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FileUtil {

	public static String getStyleAsUrl(String cssFileName) {

		File cssFile = new File("resources/style/" + cssFileName + ".css");

		try {
			if (!cssFile.exists()) {
				throw new FileNotFoundException();
			}

			return cssFile.toURI().toURL().toExternalForm();
		} catch (MalformedURLException | FileNotFoundException e) {
			return null;
		}
	}

	public static String getImageAsUrl(String imageFileName) {

		return getImageAsUrl(imageFileName, ".png");
	}

	public static String getImageAsUrl(String imageFileName, String fileExt) {

		File imageFile = new File("resources/images/" + imageFileName + fileExt);

		try {
			if (!imageFile.exists()) {
				throw new FileNotFoundException();
			}

			return imageFile.toURI().toURL().toExternalForm();
		} catch (MalformedURLException | FileNotFoundException e) {
			return null;
		}
	}

	public static Image getImageAsImage(String imageFileName) {

		return getImageAsImage(imageFileName, ".png");
	}

	public static Image getImageAsImage(String imageFileName, String fileExt) {

		File imageFile = new File("resources/images/" + imageFileName + fileExt);

		try {
			if (!imageFile.exists()) {
				throw new FileNotFoundException();
			}

			return new Image(imageFile.toURI().toURL().toExternalForm());
		} catch (MalformedURLException | FileNotFoundException e) {
			return null;
		}
	}

	public static ImageView getImageAsImageView(String imageFileName) {

		return getImageAsImageView(imageFileName, ".png");
	}

	public static ImageView getImageAsImageView(String imageFileName,
			String fileExt) {

		File imageFile = new File("resources/images/" + imageFileName + fileExt);

		try {
			if (!imageFile.exists()) {
				throw new FileNotFoundException();
			}

			ImageView imageView = new ImageView(new Image(imageFile.toURI()
					.toURL().toString()));

			return imageView;
		} catch (MalformedURLException | FileNotFoundException e) {
			return null;
		}
	}
}
