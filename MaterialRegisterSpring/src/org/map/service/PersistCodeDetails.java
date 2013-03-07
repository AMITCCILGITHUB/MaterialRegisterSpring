package org.map.service;

import javafx.concurrent.Task;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.hibernate.ddo.CodeMaster;
import org.map.hibernate.utils.CodeData;
import org.springframework.stereotype.Repository;

@Repository("PersistCodeDetails")
public class PersistCodeDetails extends AbstractService<CodeMaster, Void> {

	@Resource(name = "CodeData")
	private CodeData codeData;

	private CodeMaster code;
	private PersistType persistType;

	public PersistCodeDetails() {

		this.code = new CodeMaster();
		this.persistType = PersistType.NONE;
	}

	public PersistCodeDetails(CodeMaster code, PersistType persistType) {

		this.code = code;
		this.persistType = persistType;
	}

	@Override
	public Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				switch (persistType) {
				case INSERT:
					codeData.insertCode(code);
					break;
				case UPDATE:
					codeData.updateCode(code);
					break;
				case DELETE:
					codeData.deleteCode(code);
					break;
				default:
					break;
				}

				return null;
			}
		};
	}

	public CodeData getCodeData() {
		return codeData;
	}

	public void setCodeData(CodeData codeData) {
		this.codeData = codeData;
	}

	public CodeMaster getCode() {
		return code;
	}

	public void setCode(CodeMaster code) {
		this.code = code;
	}

	public PersistType getPersistType() {
		return persistType;
	}

	@Override
	public void setPersistType(PersistType persistType) {
		this.persistType = persistType;
	}

	@Override
	public void setPersistEntity(CodeMaster entity) {
		this.code = entity;
	}
}
