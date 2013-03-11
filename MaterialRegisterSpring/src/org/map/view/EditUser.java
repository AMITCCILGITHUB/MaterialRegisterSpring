package org.map.view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.constants.ValidationType;
import org.map.controls.PasswordBox;
import org.map.controls.ViewBox;
import org.map.controls.combobox.ValidationEditableComboBox;
import org.map.hibernate.ddo.UserMaster;
import org.map.hibernate.utils.UserData;
import org.map.logger.LoggerUtil;
import org.map.service.AbstractService;
import org.map.utils.Alert;
import org.map.utils.Confirm;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.map.validation.Validator;
import org.springframework.stereotype.Repository;

@Repository("EditUser")
public class EditUser implements AbstractPageView {
	
	@Resource(name="UserData")
	private UserData userData;

	@Resource(name="PersistUserDetails")
	private AbstractService<UserMaster, Void> abstractService;
	
	private TabPane pane;

	public EditUser(){
		
		pane = new TabPane();
	}
	
	@Override
	public Node showView() {

		Tab tab = new Tab("Edit User : Details");

		try {
			VBox main = ViewLayout.getMainVBox("Edit User", "Details");

			final TableView<UserMaster> table = TableUtil.createEditUserTable();

			main.getChildren().add(table);

			final ObservableList<UserMaster> mailboxData = FXCollections
					.observableArrayList(userData.getUserList());

			table.setItems(mailboxData);

			EventHandler<ActionEvent> editUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					UserMaster user = table.getSelectionModel()
							.getSelectedItem();
					if (user != null) {

						createEditTab(user);
					}
				}
			};

			EventHandler<ActionEvent> deleteUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent actionEvent) {

					final UserMaster user = table.getSelectionModel()
							.getSelectedItem();

					EventHandler<ActionEvent> delUserEvent = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {

							abstractService.setPersistEntity(user);
							abstractService.setPersistType(PersistType.DELETE);
							
							abstractService.restart();

							abstractService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

								@Override
								public void handle(WorkerStateEvent e) {

									mailboxData.remove(user);
								}
							});
						}
					};

					Confirm.showConfirm(Context.getWindowStage(), "Confirm",
							"Confirm", "Delete User : " + user.getUserName()
									+ ". Are you sure?", delUserEvent);
				}
			};

			table.setContextMenu(TableContextMenu.getEditUserContextMenu(
					editUserEventHandler, deleteUserEventHandler));

			table.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getClickCount() == 2) {

						UserMaster user = table.getSelectionModel()
								.getSelectedItem();

						if (user != null) {
							createEditTab(user);
						}
					}

				}

			});
			
			Context.getWindowBottomText().textProperty()
			.bind(Bindings.format("%s", abstractService.stateProperty()));

			abstractService.stateProperty().addListener(new ChangeListener<State>() {

		@Override
		public void changed(ObservableValue<? extends State> arg0,
				State oldValue, State newState) {

			if (newState == Worker.State.SUCCEEDED) {

				Alert.showAlert(Context.getWindowStage(), "Alert", "Alert",
						"User details saved successfully.");

			} else if (newState == Worker.State.FAILED) {

				Alert.showAlert(Context.getWindowStage(), "Error", "Error",
						"Some error cooured. Details : "
								+ abstractService.getException().getCause());
			}
		}
	});

			tab.setContent(ControlsUtil.makeScrollable(main));
			tab.setClosable(false);

			pane.getTabs().add(tab);
			pane.setSide(Side.TOP);

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

	private void createEditTab(final UserMaster user) {

		for (Tab selTab : pane.getTabs()) {
			if (selTab.getId() != null
					&& selTab.getId().equalsIgnoreCase(user.getUserName())) {
				pane.getSelectionModel().select(selTab);
				return;
			}
		}

		Tab tab = new Tab("Edit User : " + user.getUserName());
		tab.setId(user.getUserName());

		VBox main = ViewLayout.getMainVBox("User", "Details");

		GridPane form = new GridPane();
		form.setHgap(ViewLayout.H_SPACE);
		form.setVgap(ViewLayout.V_SPACE);

		Label userNameLabel = new Label("User Name");
		userNameLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		ViewBox userNameTextBox = new ViewBox(user.userNameProperty());

		Label passwordLabel = new Label("Password");
		passwordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final PasswordBox passwordBox = new PasswordBox("Password",
				user.passwordProperty());

		Label confirmPasswordLabel = new Label("Confirm Password");
		confirmPasswordLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final PasswordBox confirmPasswordBox = new PasswordBox(
				"Confirm Password", user.confirmPasswordProperty());

		Label roleLabel = new Label("Role");
		roleLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final ValidationEditableComboBox roleChoiceBox = new ValidationEditableComboBox(
				ValidationType.ROLE, "Role", user.roleProperty());

		form.add(userNameLabel, 0, 0);
		form.add(userNameTextBox, 1, 0);
		form.add(passwordLabel, 0, 1);
		form.add(passwordBox, 1, 1);
		form.add(confirmPasswordLabel, 0, 2);
		form.add(confirmPasswordBox, 1, 2);
		form.add(roleLabel, 0, 3);
		form.add(roleChoiceBox, 1, 3);

		main.getChildren().addAll(form);

		final HBox buttons = new HBox(ViewLayout.H_SPACE);
		buttons.setTranslateY(32);
		final Button updateButton = new Button("Update");
		updateButton.getStyleClass().add("submit-button");
		main.getChildren().addAll(buttons);

		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (Validator.validateUserData(user)) {

					abstractService.setPersistEntity(user);
					abstractService.setPersistType(PersistType.UPDATE);
				}
			}
		});
		buttons.getChildren().addAll(updateButton);

		tab.setContent(ControlsUtil.makeScrollable(main));
		pane.getTabs().add(tab);
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
	
	public AbstractService<UserMaster, Void> getPersistUserDetails() {
		return abstractService;
	}

	public void setPersistUserDetails(AbstractService<UserMaster, Void> abstractService) {
		this.abstractService = abstractService;
	}
}
