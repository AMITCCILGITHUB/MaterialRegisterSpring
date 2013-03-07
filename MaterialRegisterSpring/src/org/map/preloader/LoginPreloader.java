package org.map.preloader;

import java.io.IOException;
import java.net.MalformedURLException;

import org.map.view.Home;

import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginPreloader extends Preloader {

	Stage stage;
	Loading loading;

	private Scene createPreloaderScene() {

		loading = new Loading();
		try {
			loading.buildComponents();
		} catch (IOException ex) {
		}
		return loading.getScene();
	}

	@Override
	public void start(Stage stage) throws Exception {

		this.stage = stage;

		stage.setScene(createPreloaderScene());
		stage.show();
		loading.animate();
	}

	@Override
	public void handleStateChangeNotification(StateChangeNotification scn) {

		if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
			stage.hide();
			Home mr = new Home();
			try {
				mr.showView();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void handleProgressNotification(ProgressNotification pn) {

		loading.update(pn.getProgress());

	}

}
