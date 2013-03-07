package org.map.utils;

import javafx.application.HostServices;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import org.map.hibernate.ddo.UserMaster;

public class Context {

	private static HostServices hostServices = null;
	private static UserMaster loggedUser = new UserMaster();
	private static Stage loginStage = new Stage();
	private static Stage windowStage = new Stage();
	private static LoadingBar loginSatusbar = new LoadingBar();
	private static Text windowBottomText = new Text();
	private static Pane pageArea = null;

	public static HostServices getHostServices() {

		return hostServices;
	}

	public static void setHostServices(HostServices hostServices) {

		Context.hostServices = hostServices;
	}

	public static UserMaster getLoggedUser() {

		return loggedUser;
	}

	public static void setLoggedUser(UserMaster loggedUser) {

		Context.loggedUser = loggedUser;
	}

	public static Stage getLoginStage() {

		return loginStage;
	}

	public static void setLoginStage(Stage loginStage) {

		Context.loginStage = loginStage;
	}

	public static Stage getWindowStage() {

		return windowStage;
	}

	public static void setWindowStage(Stage windowStage) {

		Context.windowStage = windowStage;
	}

	public static LoadingBar getLoginSatusbar() {

		return loginSatusbar;
	}

	public static void setLoginSatusbar(LoadingBar loginSatusbar) {

		Context.loginSatusbar = loginSatusbar;
	}

	public static Text getWindowBottomText() {

		return windowBottomText;
	}

	public static void setWindowBottomText(Text windowBottomText) {

		Context.windowBottomText = windowBottomText;
	}

	public static Pane getPageArea() {

		return pageArea;
	}

	public static void setPageArea(Pane pageArea) {

		Context.pageArea = pageArea;
	}
}
