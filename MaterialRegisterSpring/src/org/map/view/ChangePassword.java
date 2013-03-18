package org.map.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.controls.PasswordBox;
import org.map.hibernate.ddo.UserMaster;
import org.map.logger.LoggerUtil;
import org.map.service.AbstractService;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("ChangePassword")
public class ChangePassword implements AbstractPageView {

	@Resource(name = "PersistUserDetails")
	private AbstractService<UserMaster, Void> abstractService;

	private ScrollPane pane;
	
	public ChangePassword(){
		
		pane = new ScrollPane();
	}
	
	@Override
	public Node showView() {

		try {
			VBox main = ViewLayout.getMainVBox("Change Password", "Details");

			final UserMaster um = new UserMaster(Context.getLoggedUser());
			um.setPassword("");
			um.setConfirmPassword("");

			GridPane form = new GridPane();
			form.setHgap(ViewLayout.H_SPACE);
			form.setVgap(ViewLayout.V_SPACE);

			Label passwordLabel = new Label("Password");
			passwordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final PasswordBox passwordBox = new PasswordBox("Password",
					um.passwordProperty());

			Label confirmPasswordLabel = new Label("Confirm Password");
			confirmPasswordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

			final PasswordBox confirmPasswordBox = new PasswordBox(
					"Confirm Password", um.confirmPasswordProperty());

			form.add(passwordLabel, 0, 0);
			form.add(passwordBox, 1, 0);
			form.add(confirmPasswordLabel, 0, 1);
			form.add(confirmPasswordBox, 1, 1);

			main.getChildren().add(form);

			final HBox buttons = new HBox(ViewLayout.H_SPACE);
			buttons.setTranslateY(32);
			final Button updateButton = new Button("Update");
			updateButton.getStyleClass().add("submit-button");
			updateButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					abstractService.setPersistEntity(um);
					abstractService.setPersistType(PersistType.UPDATE);
					abstractService.restart();

					abstractService
							.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
								@Override
								public void handle(WorkerStateEvent e) {

									um.setPassword("");
									um.setConfirmPassword("");

								}
							});
				}
			});
			buttons.getChildren().addAll(updateButton);

			Context.getWindowBottomText()
					.textProperty()
					.bind(Bindings.format("%s", abstractService.stateProperty()));

			abstractService.stateProperty().addListener(
					new ChangeListener<State>() {

						@Override
						public void changed(
								ObservableValue<? extends State> arg0,
								State oldValue, State newState) {

							if (newState == Worker.State.SUCCEEDED) {

								Alert.showAlert(Context.getWindowStage(),
										"Alert", "Alert",
										"User details saved successfully.");

							} else if (newState == Worker.State.FAILED) {

								Alert.showAlert(Context.getWindowStage(),
										"Error", "Error",
										"Some error cooured. Details : "
												+ abstractService
														.getException()
														.getCause());
							}
						}
					});

			main.getChildren().addAll(buttons);

			pane.getStyleClass().addAll("noborder-scroll-pane", "texture-bg");
			pane.setFitToWidth(true);
			pane.setContent(main);

			return pane;
		} catch (Exception e) {
			LoggerUtil.getLogger().debug(e);
			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					"Some error occured. Details...\n" + e.getMessage());

			return new ErrorView().showView();
		}
	}
	
	@Override
	public DoubleProperty opacityProperty() {
		
		return pane.opacityProperty();
	}

	public AbstractService<UserMaster, Void> getPersistUserDetails() {
		return abstractService;
	}

	public void setPersistUserDetails(
			AbstractService<UserMaster, Void> abstractService) {
		this.abstractService = abstractService;
	}
}
