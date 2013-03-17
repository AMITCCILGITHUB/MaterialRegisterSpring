package org.map.hibernate.register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialTests;

public class MaterialRegister implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty materialCode;
	private SimpleStringProperty ctNumber;
	private SimpleStringProperty inspectionAgency;
	private SimpleStringProperty specification;
	private SimpleStringProperty item;
	private SimpleStringProperty size;
	private SimpleIntegerProperty quantity;
	private SimpleStringProperty heatNumber;
	private SimpleStringProperty plateNumber;
	private SimpleIntegerProperty testCode;
	private SimpleStringProperty sampleId;
	private SimpleStringProperty test;
	private SimpleStringProperty customer;
	private SimpleStringProperty equipments;
	private SimpleStringProperty laboratory;
	private SimpleStringProperty reportDate;
	private SimpleStringProperty reportNumber;
	private SimpleStringProperty result;
	private SimpleStringProperty remarks;
	private SimpleStringProperty witnessedBy;
	private SimpleStringProperty failureReason;

	private String status;
	private String createdBy;
	private Date createdDate;

	public MaterialRegister() {

		this.materialCode = new SimpleIntegerProperty(1);
		this.ctNumber = new SimpleStringProperty("");
		this.inspectionAgency = new SimpleStringProperty("");
		this.specification = new SimpleStringProperty("");
		this.item = new SimpleStringProperty("");
		this.size = new SimpleStringProperty("");
		this.quantity = new SimpleIntegerProperty(1);
		this.heatNumber = new SimpleStringProperty("");
		this.plateNumber = new SimpleStringProperty("");

		this.testCode = new SimpleIntegerProperty(0);
		this.sampleId = new SimpleStringProperty("");
		this.test = new SimpleStringProperty("");
		this.customer = new SimpleStringProperty("");
		this.equipments = new SimpleStringProperty("");
		this.laboratory = new SimpleStringProperty("");
		this.reportNumber = new SimpleStringProperty("");
		this.reportDate = new SimpleStringProperty("");
		this.result = new SimpleStringProperty("");
		this.remarks = new SimpleStringProperty("");
		this.witnessedBy = new SimpleStringProperty("");
		this.failureReason = new SimpleStringProperty("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public MaterialRegister(MaterialRegister material) {

		this.materialCode = new SimpleIntegerProperty(
				material.getMaterialCode());
		this.ctNumber = new SimpleStringProperty(material.getCtNumber());
		this.inspectionAgency = new SimpleStringProperty(
				material.getInspectionAgency());
		this.specification = new SimpleStringProperty(
				material.getSpecification());
		this.item = new SimpleStringProperty(material.getItem());
		this.size = new SimpleStringProperty(material.getSize());
		this.quantity = new SimpleIntegerProperty(material.getQuantity());
		this.heatNumber = new SimpleStringProperty(material.getHeatNumber());
		this.plateNumber = new SimpleStringProperty(material.getPlateNumber());

		this.testCode = new SimpleIntegerProperty(material.getTestCode());
		this.sampleId = new SimpleStringProperty(material.getSampleId());
		this.test = new SimpleStringProperty(material.getTest());
		this.customer = new SimpleStringProperty(material.getCustomer());
		this.equipments = new SimpleStringProperty(material.getEquipments());
		this.laboratory = new SimpleStringProperty(material.getLaboratory());
		this.reportNumber = new SimpleStringProperty(material.getReportNumber());
		this.reportDate = new SimpleStringProperty(material.getReportDate());
		this.result = new SimpleStringProperty(material.getResult());
		this.remarks = new SimpleStringProperty(material.getRemarks());
		this.witnessedBy = new SimpleStringProperty(material.getWitnessedBy());
		this.failureReason = new SimpleStringProperty(
				material.getFailureReason());

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
	}

	public Integer getMaterialCode() {

		return this.materialCode.get();
	}

	public void setMaterialCode(Integer materialCode) {

		this.materialCode.set(materialCode);
	}

	public SimpleIntegerProperty MaterialCodeProperty() {

		return this.materialCode;
	}

	public String getCtNumber() {

		return this.ctNumber.get();
	}

	public void setCtNumber(String ctNumber) {

		this.ctNumber.set(ctNumber);
	}

	public SimpleStringProperty ctNumberProperty() {

		return this.ctNumber;
	}

	public String getInspectionAgency() {

		return this.inspectionAgency.get();
	}

	public void setInspectionAgency(String inspectionAgency) {

		this.inspectionAgency.set(inspectionAgency);
	}

	public SimpleStringProperty inspectionAgencyProperty() {

		return this.inspectionAgency;
	}

	public String getItem() {

		return this.item.get();
	}

	public void setItem(String item) {

		this.item.set(item);
	}

	public SimpleStringProperty itemProperty() {

		return this.item;
	}

	public String getSize() {

		return this.size.get();
	}

	public void setSize(String size) {

		this.size.set(size);
	}

	public SimpleStringProperty sizeProperty() {

		return this.size;
	}

	public Integer getQuantity() {

		return this.quantity.get();
	}

	public void setQuantity(Integer quantity) {

		this.quantity.set(quantity);
	}

	public SimpleIntegerProperty quantityProperty() {

		return this.quantity;
	}

	public String getHeatNumber() {

		return this.heatNumber.get();
	}

	public void setHeatNumber(String heatNumber) {

		this.heatNumber.set(heatNumber);
	}

	public SimpleStringProperty heatNumberProperty() {

		return this.heatNumber;
	}

	public String getPlateNumber() {

		return this.plateNumber.get();
	}

	public void setPlateNumber(String plateNumber) {

		this.plateNumber.set(plateNumber);
	}

	public SimpleStringProperty plateNumberProperty() {

		return this.plateNumber;
	}

	public String getSpecification() {

		return this.specification.get();
	}

	public void setSpecification(String specification) {

		this.specification.set(specification);
	}

	public SimpleStringProperty specificationProperty() {

		return this.specification;
	}

	public Integer getTestCode() {

		return this.testCode.get();
	}

	public void setTestCode(Integer materialCode) {

		this.testCode.set(materialCode);
	}

	public SimpleIntegerProperty TestCodeProperty() {

		return this.testCode;
	}

	public String getSampleId() {

		return this.sampleId.get();
	}

	public void setSampleId(String sampleId) {

		this.sampleId.set(sampleId);
	}

	public SimpleStringProperty sampleIdProperty() {

		return this.sampleId;
	}

	public String getTest() {

		return this.test.get();
	}

	public void setTest(String test) {

		this.test.set(test);
	}

	public SimpleStringProperty testProperty() {

		return this.test;
	}

	public String getCustomer() {

		return this.customer.get();
	}

	public void setCustomer(String customer) {

		this.customer.set(customer);
	}

	public SimpleStringProperty customerProperty() {

		return this.customer;
	}

	public String getEquipments() {

		return this.equipments.get();
	}

	public void setEquipments(String equipments) {

		this.equipments.set(equipments);
	}

	public SimpleStringProperty equipmentsProperty() {

		return this.equipments;
	}

	public String getLaboratory() {

		return this.laboratory.get();
	}

	public void setLaboratory(String laboratory) {

		this.laboratory.set(laboratory);
	}

	public SimpleStringProperty laboratoryProperty() {

		return this.laboratory;
	}

	public String getReportNumber() {

		return this.reportNumber.get();
	}

	public void setReportNumber(String reportNumber) {

		this.reportNumber.set(reportNumber);
	}

	public SimpleStringProperty reportNumberProperty() {

		return this.reportNumber;
	}

	public String getReportDate() {

		return this.reportDate.get();
	}

	public void setReportDate(String reportDate) {

		this.reportDate.set(reportDate);
	}

	public SimpleStringProperty reportDateProperty() {

		return this.reportDate;
	}

	public String getResult() {

		return this.result.get();
	}

	public void setResult(String result) {

		this.result.set(result);
	}

	public SimpleStringProperty resultProperty() {

		return this.result;
	}

	public String getRemarks() {

		return this.remarks.get();
	}

	public void setRemarks(String remarks) {

		this.remarks.set(remarks);
	}

	public SimpleStringProperty remarksProperty() {

		return this.remarks;
	}

	public String getWitnessedBy() {

		return this.witnessedBy.get();
	}

	public void setWitnessedBy(String witnessedBy) {

		this.witnessedBy.set(witnessedBy);
	}

	public SimpleStringProperty witnessedByProperty() {

		return this.witnessedBy;
	}

	public String getFailureReason() {

		return this.failureReason.get();
	}

	public void setFailureReason(String failureReason) {

		this.failureReason.set(failureReason);
	}

	public SimpleStringProperty failureReasonProperty() {

		return this.failureReason;
	}

	public String getStatus() {

		return this.status;
	}

	public void setStatus(String status) {

		this.status = status;
	}

	public String getCreatedBy() {

		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {

		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {

		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {

		this.createdDate = createdDate;
	}

	public static List<MaterialRegister> getMaterialRegisterList(
			MaterialMaster master) {
		List<MaterialRegister> registerList = new ArrayList<>();

		for (MaterialTests test : master.getMaterialTests()) {
			MaterialRegister register = new MaterialRegister();

			register.setMaterialCode(master.getMaterialCode());
			register.setCtNumber(master.getCtNumber());
			register.setInspectionAgency(master.getInspectionAgency().getName());
			register.setSpecification(master.getSpecification().getName());
			register.setItem(master.getItem().getName());
			register.setSize(master.getSize());
			register.setHeatNumber(master.getHeatNumber());
			register.setPlateNumber(master.getPlateNumber());

			register.setTestCode(test.getTestCode());
			register.setSampleId(test.getSampleId());
			register.setTest(test.getTest().getName());
			register.setCustomer(test.getCustomer().getName());
			register.setEquipments(test.getEquipments());
			register.setLaboratory(test.getLaboratory().getName());
			register.setReportNumber(test.getReportNumber());
			register.setReportDate(test.getReportDate());
			register.setResult(test.getResult().getName());
			register.setRemarks(test.getRemarks());
			register.setWitnessedBy(test.getWitnessedBy());
			register.setFailureReason(test.getFailureReason());

			registerList.add(register);
		}

		return registerList;
	}

	public static List<MaterialRegister> getMaterialRegisterList(
			List<MaterialMaster> masters) {
		List<MaterialRegister> registerList = new ArrayList<>();

		for (MaterialMaster master : masters) {

			registerList.addAll(getMaterialRegisterList(master));
		}

		return registerList;
	}
}
