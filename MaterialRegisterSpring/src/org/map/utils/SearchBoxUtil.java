package org.map.utils;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import org.map.controls.TextBox;
import org.map.controls.calendar.DatePicker;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.register.MaterialRegister;
import org.map.hibernate.utils.HeatChartData;
import org.map.hibernate.utils.MaterialData;
import org.map.logger.LoggerUtil;

public class SearchBoxUtil {

	public static Node getMaterialSearchBox(String searchFor,
			final ObservableList<MaterialMaster> data) {
		VBox searchVBox = new VBox(ViewLayout.V_SPACE);

		GridPane searchForm = new GridPane();
		searchForm.setHgap(ViewLayout.H_SPACE);
		searchForm.setVgap(ViewLayout.V_SPACE);

		Label numberFromLabel = new Label(searchFor + " From");
		numberFromLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final TextBox numberFromTextField = new TextBox(searchFor + " From");

		Label numberToLabel = new Label(searchFor + " To");
		numberToLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final TextBox numberToTextField = new TextBox(searchFor + " To");

		final Button searchRecordButton1 = new Button("Search");
		searchRecordButton1.getStyleClass().add("submit-button");

		Label fromDateLabel = new Label("Date From");
		fromDateLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final DatePicker fromDateTextField = new DatePicker();

		Label toDateLabel = new Label("Date To");
		toDateLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final DatePicker toDateTextField = new DatePicker();

		final Button searchRecordButton2 = new Button("Search");
		searchRecordButton2.getStyleClass().add("submit-button");

		searchForm.add(numberFromLabel, 0, 0);
		searchForm.add(numberFromTextField, 1, 0);
		searchForm.add(numberToLabel, 2, 0);
		searchForm.add(numberToTextField, 3, 0);
		searchForm.add(searchRecordButton1, 4, 0);

		searchForm.add(fromDateLabel, 0, 1);
		searchForm.add(fromDateTextField, 1, 1);
		searchForm.add(toDateLabel, 2, 1);
		searchForm.add(toDateTextField, 3, 1);
		searchForm.add(searchRecordButton2, 4, 1);

		searchRecordButton1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				try {
					List<MaterialMaster> mailboxCtMasters = new MaterialData()
							.searchMaterialDetailsCt(
									numberFromTextField.getText(),
									numberToTextField.getText());

					if (mailboxCtMasters.size() > 0) {
						data.setAll(mailboxCtMasters);
					} else {
						Alert.showAlert(Context.getWindowStage(), "Error",
								"Error", "No data found!");
					}
				} catch (Exception ex) {
					Alert.showAlert(
							Context.getWindowStage(),
							"Error",
							"Error",
							"Some error occured. Details...\n"
									+ ex.getMessage());
					LoggerUtil.getLogger().debug(ex);
				}
			}
		});

		searchRecordButton2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				try {
					List<MaterialMaster> mailboxCtMasters = new MaterialData()
							.searchMaterialDetailsDt(
									fromDateTextField.getSelectedDate(),
									toDateTextField.getSelectedDate());

					if (mailboxCtMasters.size() > 0) {
						data.setAll(mailboxCtMasters);
					} else {
						Alert.showAlert(Context.getWindowStage(), "Error",
								"Error", "No data found!");
					}
				} catch (Exception ex) {
					Alert.showAlert(
							Context.getWindowStage(),
							"Error",
							"Error",
							"Some error occured. Details...\n"
									+ ex.getMessage());
					LoggerUtil.getLogger().debug(ex);
				}
			}
		});

		numberFromTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton1.fire();
				}
			}
		});

		numberToTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton1.fire();
				}
			}
		});

		fromDateTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton2.fire();
				}
			}
		});

		toDateTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton2.fire();
				}
			}
		});

		searchVBox.getChildren().addAll(searchForm,
				ViewLayout.getCategoryLabel("List"));

		return searchVBox;
	}

	public static Node getHeatChartSearchBox(String searchFor,
			final ObservableList<HeatChartMaster> data) {
		VBox searchVBox = new VBox(ViewLayout.V_SPACE);

		GridPane searchForm = new GridPane();
		searchForm.setHgap(ViewLayout.H_SPACE);
		searchForm.setVgap(ViewLayout.V_SPACE);

		Label numberFromLabel = new Label(searchFor + " From");
		numberFromLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final TextBox numberFromTextField = new TextBox(searchFor + " From");

		Label numberToLabel = new Label(searchFor + " To");
		numberToLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final TextBox numberToTextField = new TextBox(searchFor + " To");

		final Button searchRecordButton1 = new Button("Search");
		searchRecordButton1.getStyleClass().add("submit-button");

		Label fromDateLabel = new Label("Date From");
		fromDateLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final DatePicker fromDateTextField = new DatePicker();

		Label toDateLabel = new Label("Date To");
		toDateLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final DatePicker toDateTextField = new DatePicker();

		final Button searchRecordButton2 = new Button("Search");
		searchRecordButton2.getStyleClass().add("submit-button");

		searchForm.add(numberFromLabel, 0, 0);
		searchForm.add(numberFromTextField, 1, 0);
		searchForm.add(numberToLabel, 2, 0);
		searchForm.add(numberToTextField, 3, 0);
		searchForm.add(searchRecordButton1, 4, 0);

		searchForm.add(fromDateLabel, 0, 1);
		searchForm.add(fromDateTextField, 1, 1);
		searchForm.add(toDateLabel, 2, 1);
		searchForm.add(toDateTextField, 3, 1);
		searchForm.add(searchRecordButton2, 4, 1);

		searchRecordButton1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				try {
					List<HeatChartMaster> mailboxHcMasters = new HeatChartData()
							.searchHeatChartDetailsHc(
									numberFromTextField.getText(),
									numberToTextField.getText());

					if (mailboxHcMasters.size() > 0) {
						data.setAll(mailboxHcMasters);
					} else {
						Alert.showAlert(Context.getWindowStage(), "Error",
								"Error", "No data found!");
					}
				} catch (Exception ex) {
					Alert.showAlert(
							Context.getWindowStage(),
							"Error",
							"Error",
							"Some error occured. Details...\n"
									+ ex.getMessage());
					LoggerUtil.getLogger().debug(ex);
				}
			}
		});

		searchRecordButton2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				try {
					List<HeatChartMaster> mailboxHcMasters = new HeatChartData()
							.searchHeatChartDetailsDt(
									fromDateTextField.getSelectedDate(),
									toDateTextField.getSelectedDate());

					if (mailboxHcMasters.size() > 0) {
						data.setAll(mailboxHcMasters);
					} else {
						Alert.showAlert(Context.getWindowStage(), "Error",
								"Error", "No data found!");
					}
				} catch (Exception ex) {
					Alert.showAlert(
							Context.getWindowStage(),
							"Error",
							"Error",
							"Some error occured. Details...\n"
									+ ex.getMessage());
					LoggerUtil.getLogger().debug(ex);
				}
			}
		});

		numberFromTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton1.fire();
				}
			}
		});

		numberToTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton1.fire();
				}
			}
		});

		fromDateTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton2.fire();
				}
			}
		});

		toDateTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton2.fire();
				}
			}
		});

		searchVBox.getChildren().addAll(searchForm,
				ViewLayout.getCategoryLabel("List"));

		return searchVBox;
	}

	public static Node getRegisterSearchBox(String searchFor,
			final ObservableList<MaterialRegister> data) {
		VBox searchVBox = new VBox(ViewLayout.V_SPACE);

		GridPane searchForm = new GridPane();
		searchForm.setHgap(ViewLayout.H_SPACE);
		searchForm.setVgap(ViewLayout.V_SPACE);

		Label numberFromLabel = new Label(searchFor + " From");
		numberFromLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final TextBox numberFromTextField = new TextBox(searchFor + " From");

		Label numberToLabel = new Label(searchFor + " To");
		numberToLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final TextBox numberToTextField = new TextBox(searchFor + " To");

		final Button searchRecordButton1 = new Button("Search");
		searchRecordButton1.getStyleClass().add("submit-button");

		Label fromDateLabel = new Label("Date From");
		fromDateLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final DatePicker fromDateTextField = new DatePicker();

		Label toDateLabel = new Label("Date To");
		toDateLabel.setPrefWidth(ViewLayout.LABEL_WIDTH);

		final DatePicker toDateTextField = new DatePicker();

		final Button searchRecordButton2 = new Button("Search");
		searchRecordButton2.getStyleClass().add("submit-button");

		searchForm.add(numberFromLabel, 0, 0);
		searchForm.add(numberFromTextField, 1, 0);
		searchForm.add(numberToLabel, 2, 0);
		searchForm.add(numberToTextField, 3, 0);
		searchForm.add(searchRecordButton1, 4, 0);

		searchForm.add(fromDateLabel, 0, 1);
		searchForm.add(fromDateTextField, 1, 1);
		searchForm.add(toDateLabel, 2, 1);
		searchForm.add(toDateTextField, 3, 1);
		searchForm.add(searchRecordButton2, 4, 1);

		searchRecordButton1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				try {
					List<MaterialRegister> materials = MaterialRegister
							.getMaterialRegisterList(new MaterialData()
									.searchMaterialDetailsCt(
											numberFromTextField.getText(),
											numberToTextField.getText()));

					if (materials.size() > 0) {
						data.setAll(materials);
					} else {
						Alert.showAlert(Context.getWindowStage(), "Error",
								"Error", "No data found!");
					}
				} catch (Exception ex) {
					Alert.showAlert(
							Context.getWindowStage(),
							"Error",
							"Error",
							"Some error occured. Details...\n"
									+ ex.getMessage());
					LoggerUtil.getLogger().debug(ex);
				}
			}
		});

		searchRecordButton2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				try {
					List<MaterialRegister> materials = MaterialRegister.getMaterialRegisterList(new MaterialData()
							.searchMaterialDetailsDt(
									fromDateTextField.getSelectedDate(),
									toDateTextField.getSelectedDate()));

					if (materials.size() > 0) {
						data.setAll(materials);
					} else {
						Alert.showAlert(Context.getWindowStage(), "Error",
								"Error", "No data found!");
					}
				} catch (Exception ex) {
					Alert.showAlert(
							Context.getWindowStage(),
							"Error",
							"Error",
							"Some error occured. Details...\n"
									+ ex.getMessage());
					LoggerUtil.getLogger().debug(ex);
				}
			}
		});

		numberFromTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton1.fire();
				}
			}
		});

		numberToTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton1.fire();
				}
			}
		});

		fromDateTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton2.fire();
				}
			}
		});

		toDateTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					searchRecordButton2.fire();
				}
			}
		});

		searchVBox.getChildren().addAll(searchForm,
				ViewLayout.getCategoryLabel("List"));

		return searchVBox;
	}
}