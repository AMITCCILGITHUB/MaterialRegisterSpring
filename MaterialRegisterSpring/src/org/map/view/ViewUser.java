package org.map.view;

import javax.annotation.Resource;

import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.map.controls.ViewBox;
import org.map.hibernate.ddo.UserMaster;
import org.map.hibernate.utils.UserData;
import org.map.logger.LoggerUtil;
import org.map.utils.Alert;
import org.map.utils.Context;
import org.map.utils.ControlsUtil;
import org.map.utils.TableContextMenu;
import org.map.utils.TableUtil;
import org.map.utils.ViewLayout;
import org.springframework.stereotype.Repository;

@Repository("ViewUser")
public class ViewUser implements AbstractPageView {

	@Resource(name = "UserData")
	private UserData userData;

	private TabPane pane;

	public ViewUser(){

		pane = new TabPane();
	}
	
	@Override
	public Node showView() {

		Tab tab = new Tab("View User : Details");

		try {
			VBox main = ViewLayout.getMainVBox("View User", "Details");

			final TableView<UserMaster> table = TableUtil.createViewUserTable();

			main.getChildren().add(table);

			final ObservableList<UserMaster> mailboxData = FXCollections
					.observableArrayList(userData.getUserList());
			table.setItems(mailboxData);

			EventHandler<ActionEvent> showPasswordEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					UserMaster selUser = table.getSelectionModel()
							.getSelectedItem();

					Alert.showAlert(Context.getWindowStage(), "Info", "Info",
							"Password : " + selUser.getPassword());
				}
			};

			EventHandler<ActionEvent> showUserEventHandler = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent e) {

					UserMaster user = table.getSelectionModel()
							.getSelectedItem();
					if (user != null) {

						createViewTab(user);
					}
				}
			};

			table.setContextMenu(TableContextMenu.getViewUserContextMenu(
					showPasswordEventHandler, showUserEventHandler));

			table.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getClickCount() == 2) {

						UserMaster user = table.getSelectionModel()
								.getSelectedItem();
						if (user != null) {

							createViewTab(user);
						}
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

	private void createViewTab(final UserMaster user) {

		for (Tab selTab : pane.getTabs()) {
			if (selTab.getId() != null
					&& selTab.getId().equalsIgnoreCase(user.getUserName())) {
				pane.getSelectionModel().select(selTab);
				return;
			}
		}

		Tab tab = new Tab("View User : " + user.getUserName());
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

		ViewBox passwordBox = new ViewBox(user.passwordProperty());

		Label roleLabel = new Label("Role");
		roleLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		ViewBox roleTextBox = new ViewBox(user.getRole().nameProperty());

		form.add(userNameLabel, 0, 0);
		form.add(userNameTextBox, 1, 0);
		form.add(passwordLabel, 0, 1);
		form.add(passwordBox, 1, 1);
		form.add(roleLabel, 0, 2);
		form.add(roleTextBox, 1, 2);

		main.getChildren().addAll(form);

		tab.setContent(ControlsUtil.makeScrollable(main));
		pane.getTabs().add(tab);
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}
}
