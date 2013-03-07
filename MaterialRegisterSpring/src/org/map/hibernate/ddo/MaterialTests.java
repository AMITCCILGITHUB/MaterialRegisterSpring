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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.map.constants.RecordStatus;
import org.map.constants.ValidationType;
import org.map.hibernate.property.ValidationProperty;
import org.map.utils.Context;

@Entity
@Table(name = "MATERIAL_TEST")
public class MaterialTests implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty testCode;
	private SimpleStringProperty sampleId;
	private ValidationProperty test;
	private ValidationProperty customer;
	private SimpleStringProperty equipments;
	private ValidationProperty laboratory;
	private SimpleStringProperty reportDate;
	private SimpleStringProperty reportNumber;
	private ValidationProperty result;
	private SimpleStringProperty remarks;
	private SimpleStringProperty witnessedBy;
	private SimpleStringProperty failureReason;
	private MaterialMaster materialMaster;

	private RecordStatus status;
	private String createdBy;
	private Date createdDate;

	private StringBuilder validationMessage;

	public MaterialTests() {

		this.testCode = new SimpleIntegerProperty(0);
		this.sampleId = new SimpleStringProperty("");
		this.test = new ValidationProperty(ValidationType.TEST);
		this.customer = new ValidationProperty(ValidationType.CUSTOMER);
		this.equipments = new SimpleStringProperty("");
		this.laboratory = new ValidationProperty(ValidationType.LABORATORY);
		this.reportNumber = new SimpleStringProperty("");
		this.reportDate = new SimpleStringProperty("");
		this.result = new ValidationProperty(ValidationType.RESULT);
		this.remarks = new SimpleStringProperty("");
		this.witnessedBy = new SimpleStringProperty("");
		this.failureReason = new SimpleStringProperty("");
		this.materialMaster = new MaterialMaster();

		this.status = RecordStatus.TRUE;
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public MaterialTests(MaterialTests material) {

		this.testCode = new SimpleIntegerProperty(material.getTestCode());
		this.sampleId = new SimpleStringProperty(material.getSampleId());
		this.test = new ValidationProperty(material.getTest());
		this.customer = new ValidationProperty(material.getCustomer());
		this.equipments = new SimpleStringProperty(material.getEquipments());
		this.laboratory = new ValidationProperty(material.getLaboratory());
		this.reportNumber = new SimpleStringProperty(material.getReportNumber());
		this.reportDate = new SimpleStringProperty(material.getReportDate());
		this.result = new ValidationProperty(material.getResult());
		this.remarks = new SimpleStringProperty(material.getRemarks());
		this.witnessedBy = new SimpleStringProperty(material.getWitnessedBy());
		this.failureReason = new SimpleStringProperty(
				material.getFailureReason());

		this.materialMaster = new MaterialMaster(material.getMaterialMaster());

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
	}

	@Id
	@GeneratedValue(generator = "MATERIALTEST_CODE_GENERATOR")
	@GenericGenerator(name = "MATERIALTEST_CODE_GENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "MATERIALTEST_CODE_SEQUENCE"),
			@Parameter(name = "optimizer", value = "hilo"),
			@Parameter(name = "initial_value", value = "1000"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "MATERIALTEST_CODE", unique = true, nullable = false, length = 11)
	public int getTestCode() {

		return this.testCode.get();
	}

	public void setTestCode(int materialCode) {

		this.testCode.set(materialCode);
	}

	public SimpleIntegerProperty TestCodeProperty() {

		return this.testCode;
	}

	@Column(name = "SAMPLE_ID")
	public String getSampleId() {

		return this.sampleId.get();
	}

	public void setSampleId(String sampleId) {

		this.sampleId.set(sampleId);
	}

	public SimpleStringProperty sampleIdProperty() {

		return this.sampleId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODE", insertable = false, updatable = false)
	public ValidationMaster getTest() {

		return this.test.get();
	}

	public void setTest(ValidationMaster test) {

		this.test.set(test);
	}

	public ValidationProperty testProperty() {

		return this.test;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODE", insertable = false, updatable = false)
	public ValidationMaster getCustomer() {

		return this.customer.get();
	}

	public void setCustomer(ValidationMaster customer) {

		this.customer.set(customer);
	}

	public ValidationProperty customerProperty() {

		return this.customer;
	}

	@Column(name = "EQUIPMENTS")
	public String getEquipments() {

		return this.equipments.get();
	}

	public void setEquipments(String equipments) {

		this.equipments.set(equipments);
	}

	public SimpleStringProperty equipmentsProperty() {

		return this.equipments;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODE", insertable = false, updatable = false)
	public ValidationMaster getLaboratory() {

		return this.laboratory.get();
	}

	public void setLaboratory(ValidationMaster laboratory) {

		this.laboratory.set(laboratory);
	}

	public ValidationProperty laboratoryProperty() {

		return this.laboratory;
	}

	@Column(name = "REPORT_NUMBER")
	public String getReportNumber() {

		return this.reportNumber.get();
	}

	public void setReportNumber(String reportNumber) {

		this.reportNumber.set(reportNumber);
	}

	public SimpleStringProperty reportNumberProperty() {

		return this.reportNumber;
	}

	@Column(name = "REPORT_DATE")
	public String getReportDate() {

		return this.reportDate.get();
	}

	public void setReportDate(String reportDate) {

		this.reportDate.set(reportDate);
	}

	public SimpleStringProperty reportDateProperty() {

		return this.reportDate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODE", insertable = false, updatable = false)
	public ValidationMaster getResult() {

		return this.result.get();
	}

	public void setResult(ValidationMaster result) {

		this.result.set(result);
	}

	public ValidationProperty resultProperty() {

		return this.result;
	}

	@Column(name = "REMARKS")
	public String getRemarks() {

		return this.remarks.get();
	}

	public void setRemarks(String remarks) {

		this.remarks.set(remarks);
	}

	public SimpleStringProperty remarksProperty() {

		return this.remarks;
	}

	@Column(name = "WITNESSED_BY")
	public String getWitnessedBy() {

		return this.witnessedBy.get();
	}

	public void setWitnessedBy(String witnessedBy) {

		this.witnessedBy.set(witnessedBy);
	}

	public SimpleStringProperty witnessedByProperty() {

		return this.witnessedBy;
	}

	@Column(name = "FAILURE_REASON")
	public String getFailureReason() {

		return this.failureReason.get();
	}

	public void setFailureReason(String failureReason) {

		this.failureReason.set(failureReason);
	}

	public SimpleStringProperty failureReasonProperty() {

		return this.failureReason;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MATERIAL_CODE")
	public MaterialMaster getMaterialMaster() {

		return this.materialMaster;
	}

	public void setMaterialMaster(MaterialMaster materialMaster) {

		this.materialMaster = materialMaster;
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

	public void resetTo(MaterialTests material) {

		this.testCode.set(material.getTestCode());
		this.sampleId.set(material.getSampleId());
		this.test.set(material.getTest());
		this.customer.set(material.getCustomer());
		this.equipments.set(material.getEquipments());
		this.laboratory.set(material.getLaboratory());
		this.reportNumber.set(material.getReportNumber());
		this.reportDate.set(material.getReportDate());
		this.result.set(material.getResult());
		this.remarks.set(material.getRemarks());
		this.witnessedBy.set(material.getWitnessedBy());
		this.failureReason.set(material.getFailureReason());

		this.materialMaster = material.getMaterialMaster();

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
	}

	public void clean() {

		this.testCode.set(0);
		this.sampleId.set("");
		this.test.get().clean();
		this.customer.get().clean();
		this.equipments.set("");
		this.laboratory.get().clean();
		this.reportNumber.set("");
		this.reportDate.set("");
		this.result.get().clean();
		this.remarks.set("");
		this.witnessedBy.set("");
		this.failureReason.set("");

		if (this.materialMaster.getMaterialCode() > 1000) {
			this.materialMaster.clean();
		}

		this.status = RecordStatus.TRUE;
		this.createdBy = Context.getLoggedUser().getUserName();
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Transient
	public boolean isInvalid() {

		validationMessage = new StringBuilder();

		if (this.sampleId.get().trim().length() <= 0) {

			validationMessage.append("+  Sample ID is empty." + "\n");
		}

		if (this.test.get().getName().trim().length() <= 0) {

			validationMessage.append("+  Test is empty." + "\n");
		}

		if (this.customer.get().getName().trim().length() <= 0) {

			validationMessage.append("+  Customer is empty." + "\n");
		}

		if (this.equipments.get().trim().length() <= 0) {

			validationMessage.append("+  Equipment is empty." + "\n");
		}

		if (this.laboratory.get().getName().trim().length() <= 0) {

			validationMessage.append("+  Laboratory is empty." + "\n");
		}

		if (this.reportDate.get().trim().length() <= 0) {

			validationMessage.append("+  Report Date is empty." + "\n");
		}

		if (this.reportNumber.get().trim().length() <= 0) {

			validationMessage.append("+  Report Number is empty." + "\n");
		}

		if (this.result.get().getName().trim().length() <= 0) {

			validationMessage.append("+  Result is empty." + "\n");
		}

		if (this.result.get().getName().equalsIgnoreCase("Rejected")
				&& this.failureReason.get().trim().length() <= 0) {

			validationMessage
					.append("+  Failure reason is required for rejected tests."
							+ "\n");
		}

		return (validationMessage.length() > 0);
	}

	@Transient
	public String getValidationMessage() {

		return validationMessage.toString();
	}
}
