package org.map.hibernate.property;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import org.map.hibernate.ddo.RoleMaster;

public class RoleProperty extends SimpleObjectProperty<RoleMaster> implements
		ObservableValue<RoleMaster> {

	public RoleProperty() {

		super(new RoleMaster());
	}

	public RoleProperty(RoleMaster role) {

		super(role);
	}

	@Override
	public void set(RoleMaster role) {

		super.get().setCode(role.getCode());
		super.get().setName(role.getName());
		super.get().setDisplayText(role.getDisplayText());
		super.get().setStatus(role.getStatus());
		super.get().setCreatedBy(role.getCreatedBy());
		super.get().setCreatedDate(role.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends RoleMaster> role) {

		super.get().codeProperty().bind(role.getValue().codeProperty());
		super.get().nameProperty().bind(role.getValue().nameProperty());
		super.get().displayTextProperty()
				.bind(role.getValue().displayTextProperty());
	}

	@Override
	public void bindBidirectional(Property<RoleMaster> role) {

		super.get().codeProperty()
				.bindBidirectional(role.getValue().codeProperty());
		super.get().nameProperty()
				.bindBidirectional(role.getValue().nameProperty());
		super.get().displayTextProperty()
				.bindBidirectional(role.getValue().displayTextProperty());
	}

}
