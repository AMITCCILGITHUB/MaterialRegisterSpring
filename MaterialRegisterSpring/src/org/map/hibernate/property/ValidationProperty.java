package org.map.hibernate.property;

import org.map.constants.ValidationType;
import org.map.hibernate.ddo.ValidationMaster;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class ValidationProperty extends SimpleObjectProperty<ValidationMaster>
		implements ObservableValue<ValidationMaster> {

	public ValidationProperty(ValidationType type) {

		super(new ValidationMaster(type));
	}

	public ValidationProperty(ValidationMaster validation) {

		super(validation);
	}

	@Override
	public void set(ValidationMaster validation) {

		super.get().setCode(validation.getCode());
		super.get().setName(validation.getName());
		super.get().setType(validation.getType());
		super.get().setDisplayText(validation.getDisplayText());
		super.get().setStatus(validation.getStatus());
		super.get().setCreatedBy(validation.getCreatedBy());
		super.get().setCreatedDate(validation.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends ValidationMaster> validation) {

		super.get().codeProperty().bind(validation.getValue().codeProperty());
		super.get().nameProperty().bind(validation.getValue().nameProperty());
		super.get().displayTextProperty()
				.bind(validation.getValue().displayTextProperty());
	}

	@Override
	public void bindBidirectional(Property<ValidationMaster> validation) {

		super.get().codeProperty()
				.bindBidirectional(validation.getValue().codeProperty());
		super.get().nameProperty()
				.bindBidirectional(validation.getValue().nameProperty());
		super.get().displayTextProperty()
				.bindBidirectional(validation.getValue().displayTextProperty());
	}

}
