package org.map.controls;

import org.map.utils.ViewLayout;

import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.*;

public class IntField extends TextField {

	final private IntegerProperty value;

	public int getValue() {

		return value.getValue();
	}

	public void setValue(int newValue) {

		value.setValue(newValue);
	}

	public IntegerProperty valueProperty() {

		return value;
	}

	public IntField(int initialValue) {
		setMinSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setPrefSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		setMaxSize(ViewLayout.REGION_WIDTH, ViewLayout.REGION_HEIGHT);
		
		value = new SimpleIntegerProperty(initialValue);
		setText(initialValue + "");

		final IntField intField = this;

		value.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(
					ObservableValue<? extends Number> observableValue,
					Number oldValue, Number newValue) {

				if (newValue == null) {
					intField.setText("");
				} else {
					if (!(newValue.intValue() == 0 && (textProperty().get() == null || ""
							.equals(textProperty().get())))) {
						intField.setText(newValue.toString());
					}
				}
			}
		});

		this.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent keyEvent) {

				if (!"0123456789".contains(keyEvent.getCharacter())) {
					keyEvent.consume();
				}
			}
		});

		this.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(
					ObservableValue<? extends String> observableValue,
					String oldValue, String newValue) {

				if (newValue == null || "".equals(newValue)) {
					value.setValue(0);
					return;
				}

				value.set(Integer.parseInt(textProperty().get()));
			}
		});
	}
}
