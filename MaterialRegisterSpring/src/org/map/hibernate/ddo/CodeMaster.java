package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.map.constants.RecordStatus;
import org.map.utils.Context;

@Entity
@Table(name = "CODE_MASTER")
public class CodeMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty codeNumber;
	private SimpleStringProperty codeName;
	private SimpleStringProperty codeValue;
	private SimpleStringProperty codeDatatype;
	private SimpleStringProperty codeDesc;

	private RecordStatus status;
	private String createdBy;
	private Date createdDate;

	public CodeMaster() {

		this.codeNumber = new SimpleIntegerProperty(0);
		this.codeName = new SimpleStringProperty("");
		this.codeValue = new SimpleStringProperty("");
		this.codeDatatype = new SimpleStringProperty("");
		this.codeDesc = new SimpleStringProperty("");

		this.status = RecordStatus.TRUE;
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public CodeMaster(CodeMaster vm) {

		this.codeNumber = new SimpleIntegerProperty(vm.getCodeNumber());
		this.codeName = new SimpleStringProperty(vm.getCodeName());
		this.codeValue = new SimpleStringProperty(vm.getCodeValue());
		this.codeDatatype = new SimpleStringProperty(vm.getCodeDatatype());
		this.codeDesc = new SimpleStringProperty(vm.getCodeDesc());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	@Id
	@TableGenerator(name = "CODE_NUMBER_GENERATOR", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "CODE_NUMBER", initialValue = 1000, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CODE_NUMBER_GENERATOR")
	@Column(name = "CODE_NUMBER", unique = true, nullable = false, length = 11)
	public Integer getCodeNumber() {

		return this.codeNumber.get();
	}

	public void setCodeNumber(Integer codeNumber) {

		this.codeNumber.set(codeNumber);
	}

	public SimpleIntegerProperty codeNumberProperty() {

		return codeNumber;
	}

	@Column(name = "CODE_NAME")
	public String getCodeName() {

		return this.codeName.get();
	}

	public void setCodeName(String codeName) {

		this.codeName.set(codeName);
	}

	public SimpleStringProperty codeNameProperty() {

		return this.codeName;
	}

	@Column(name = "CODE_VALUE")
	public String getCodeValue() {

		return this.codeValue.get();
	}

	public void setCodeValue(String codeValue) {

		this.codeValue.set(codeValue);
	}

	public SimpleStringProperty codeValueProperty() {

		return this.codeValue;
	}

	@Column(name = "CODE_DATA_TYPE")
	public String getCodeDatatype() {

		return this.codeDatatype.get();
	}

	public void setCodeDatatype(String codeDatatype) {

		this.codeDatatype.set(codeDatatype);
	}

	public SimpleStringProperty codeDatatypeProperty() {

		return this.codeDatatype;
	}

	@Column(name = "CODE_DESC")
	public String getCodeDesc() {

		return this.codeDesc.get();
	}

	public void setCodeDesc(String codeDesc) {

		this.codeDesc.set(codeDesc);
	}

	public SimpleStringProperty codeDescProperty() {

		return this.codeDesc;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	public RecordStatus getStatus() {
		return status;
	}

	public void setStatus(RecordStatus status) {
		this.status = status;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void resetTo(CodeMaster vm) {

		this.codeNumber.set(vm.getCodeNumber());
		this.codeName.set(vm.getCodeName());
		this.codeValue.set(vm.getCodeValue());
		this.codeDatatype.set(vm.getCodeDatatype());
		this.codeDesc.set(vm.getCodeDesc());

		this.status = vm.getStatus();
		this.createdBy = vm.getCreatedBy();
		this.createdDate = vm.getCreatedDate();
	}

	public void clean() {

		this.codeNumber.set(0);
		this.codeName.set("");
		this.codeValue.set("");
		this.codeDatatype.set("");
		this.codeDesc.set("");

		status = RecordStatus.TRUE;
		createdBy = Context.getLoggedUser().getUserName();
		createdDate = Calendar.getInstance().getTime();
	}
}
