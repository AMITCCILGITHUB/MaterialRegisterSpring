package org.map.service;

import javafx.concurrent.Task;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.constants.ValidationType;
import org.map.hibernate.ddo.ValidationMaster;
import org.map.hibernate.utils.ValidationData;
import org.springframework.stereotype.Repository;

@Repository("PersistValidationDetails")
public class PersistValidationDetails extends
		AbstractService<ValidationMaster, Void> {

	@Resource(name = "ValidationData")
	private ValidationData validationData;

	ValidationMaster validation;
	PersistType persistType;

	public PersistValidationDetails() {

		this.validation = new ValidationMaster(ValidationType.NONE);
		this.persistType = PersistType.NONE;
	}

	public PersistValidationDetails(ValidationMaster validation,
			PersistType persistType) {

		this.validation = validation;
		this.persistType = persistType;
	}

	@Override
	public Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {
				switch (persistType) {
				case INSERT:
					validationData.insertValidation(validation);
					break;
				case UPDATE:
					validationData.updateValidation(validation);
					break;
				case DELETE:
					validationData.deleteValidation(validation);
					break;
				default:
					break;
				}

				return null;
			}
		};
	}

	public ValidationData getValidationData() {
		return validationData;
	}

	public void setValidationData(ValidationData validationData) {
		this.validationData = validationData;
	}

	public ValidationMaster getValidation() {
		return validation;
	}

	public void setValidation(ValidationMaster validation) {
		this.validation = validation;
	}

	public PersistType getPersistType() {
		return persistType;
	}

	@Override
	public void setPersistType(PersistType persistType) {
		this.persistType = persistType;
	}

	@Override
	public void setPersistEntity(ValidationMaster entity) {
		this.validation = entity;
	}

}