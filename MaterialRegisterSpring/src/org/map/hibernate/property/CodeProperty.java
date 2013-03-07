package org.map.hibernate.property;

import org.map.hibernate.ddo.CodeMaster;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class CodeProperty extends SimpleObjectProperty<CodeMaster> implements
		ObservableValue<CodeMaster> {

	public CodeProperty() {

		super(new CodeMaster());
	}

	public CodeProperty(CodeMaster code) {

		super(code);
	}

	@Override
	public void set(CodeMaster code) {

		super.get().setCodeNumber(code.getCodeNumber());
		super.get().setCodeName(code.getCodeName());
		super.get().setCodeValue(code.getCodeValue());
		super.get().setCodeDatatype(code.getCodeDatatype());
		super.get().setCodeDesc(code.getCodeDesc());

		super.get().setStatus(code.getStatus());
		super.get().setCreatedBy(code.getCreatedBy());
		super.get().setCreatedDate(code.getCreatedDate());
	}

	@Override
	public void bind(ObservableValue<? extends CodeMaster> code) {

		super.get().codeNumberProperty()
				.bind(code.getValue().codeNumberProperty());
		super.get().codeNameProperty().bind(code.getValue().codeNameProperty());
		super.get().codeValueProperty()
				.bind(code.getValue().codeValueProperty());
		super.get().codeDatatypeProperty()
				.bind(code.getValue().codeDatatypeProperty());
		super.get().codeDescProperty().bind(code.getValue().codeDescProperty());

	}

	@Override
	public void bindBidirectional(Property<CodeMaster> code) {

		super.get().codeNumberProperty()
				.bindBidirectional(code.getValue().codeNumberProperty());
		super.get().codeNameProperty()
				.bindBidirectional(code.getValue().codeNameProperty());
		super.get().codeValueProperty()
				.bindBidirectional(code.getValue().codeValueProperty());
		super.get().codeDatatypeProperty()
				.bindBidirectional(code.getValue().codeDatatypeProperty());
		super.get().codeDescProperty()
				.bindBidirectional(code.getValue().codeDescProperty());
	}

}
