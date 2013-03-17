package org.map.hibernate.register;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import org.map.hibernate.ddo.HeatChartSheets;

public class HeatChartSheetRegister implements Serializable,
		Comparable<HeatChartSheetRegister> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty heatChartSheetCode;
	private SimpleIntegerProperty sequenceNumber;
	private SimpleIntegerProperty sheetNumber;

	private SimpleStringProperty partNumber;
	private SimpleStringProperty partName;
	private SimpleStringProperty specifiedSize;
	private SimpleStringProperty specifiedGrade;

	private SimpleStringProperty utilizedSize;
	private SimpleStringProperty utilizedGrade;
	private SimpleStringProperty ctNumber;
	private SimpleStringProperty reportNumber;
	private SimpleStringProperty reportDate;
	private SimpleStringProperty laboratory;

	private String status;
	private String createdBy;
	private Date createdDate;

	public HeatChartSheetRegister() {

		this.heatChartSheetCode = new SimpleIntegerProperty(0);
		this.sheetNumber = new SimpleIntegerProperty(1);
		this.sequenceNumber = new SimpleIntegerProperty(1);

		this.partNumber = new SimpleStringProperty("");
		this.partName = new SimpleStringProperty("");
		this.specifiedSize = new SimpleStringProperty("");
		this.specifiedGrade = new SimpleStringProperty("");

		this.utilizedSize = new SimpleStringProperty("");
		this.utilizedGrade = new SimpleStringProperty("");
		this.ctNumber = new SimpleStringProperty("");
		this.reportNumber = new SimpleStringProperty("");
		this.reportDate = new SimpleStringProperty("");
		this.laboratory = new SimpleStringProperty("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public HeatChartSheetRegister(HeatChartSheetRegister hSheets) {

		this.heatChartSheetCode = new SimpleIntegerProperty(
				hSheets.getHeatChartSheetCode());

		this.sheetNumber = new SimpleIntegerProperty(hSheets.getSheetNumber());
		this.sequenceNumber = new SimpleIntegerProperty(
				hSheets.getSequenceNumber());

		this.partNumber = new SimpleStringProperty(hSheets.getPartNumber());
		this.partName = new SimpleStringProperty(hSheets.getPartName());
		this.specifiedSize = new SimpleStringProperty(
				hSheets.getSpecifiedSize());
		this.specifiedGrade = new SimpleStringProperty(
				hSheets.getSpecifiedGrade());

		this.utilizedSize = new SimpleStringProperty(hSheets.getUtilizedSize());
		this.utilizedGrade = new SimpleStringProperty(
				hSheets.getUtilizedGrade());
		this.ctNumber = new SimpleStringProperty(hSheets.getCtNumber());
		this.reportNumber = new SimpleStringProperty(hSheets.getReportNumber());
		this.reportDate = new SimpleStringProperty(hSheets.getReportDate());
		this.laboratory = new SimpleStringProperty(hSheets.getLaboratory());

		this.status = hSheets.getStatus();
		this.createdBy = hSheets.getCreatedBy();
		this.createdDate = hSheets.getCreatedDate();
	}

	public Integer getHeatChartSheetCode() {

		return this.heatChartSheetCode.get();
	}

	public void setHeatChartSheetCode(Integer heatChartSheetCode) {

		this.heatChartSheetCode.set(heatChartSheetCode);
	}

	public SimpleIntegerProperty heatChartSheetCodeProperty() {

		return this.heatChartSheetCode;
	}

	public Integer getSheetNumber() {

		return this.sheetNumber.get();
	}

	public void setSheetNumber(Integer sheetNumber) {

		this.sheetNumber.set(sheetNumber);
	}

	public SimpleIntegerProperty sheetNumberProperty() {

		return this.sheetNumber;
	}

	public Integer getSequenceNumber() {

		return this.sequenceNumber.get();
	}

	public void setSequenceNumber(Integer sequenceNumber) {

		this.sequenceNumber.set(sequenceNumber);
	}

	public SimpleIntegerProperty sequenceNumberProperty() {

		return this.sequenceNumber;
	}

	public String getPartNumber() {

		return this.partNumber.get();
	}

	public void setPartNumber(String partNumber) {

		this.partNumber.set(partNumber);
	}

	public SimpleStringProperty partNumberProperty() {

		return this.partNumber;
	}

	public String getPartName() {

		return this.partName.get();
	}

	public void setPartName(String partName) {

		this.partName.set(partName);
	}

	public SimpleStringProperty partNameProperty() {

		return this.partName;
	}

	public String getSpecifiedSize() {

		return this.specifiedSize.get();
	}

	public void setSpecifiedSize(String specifiedSize) {

		this.specifiedSize.set(specifiedSize);
	}

	public SimpleStringProperty specifiedSizeProperty() {

		return this.specifiedSize;
	}

	public String getSpecifiedGrade() {

		return this.specifiedGrade.get();
	}

	public void setSpecifiedGrade(String specifiedGrade) {

		this.specifiedGrade.set(specifiedGrade);
	}

	public SimpleStringProperty specifiedGradeProperty() {

		return this.specifiedGrade;
	}

	public String getUtilizedSize() {

		return this.utilizedSize.get();
	}

	public void setUtilizedSize(String utilizedSize) {

		this.utilizedSize.set(utilizedSize);
	}

	public SimpleStringProperty utilizedSizeProperty() {

		return this.specifiedSize;
	}

	public String getUtilizedGrade() {

		return this.utilizedGrade.get();
	}

	public void setUtilizedGrade(String utilizedGrade) {

		this.utilizedGrade.set(utilizedGrade);
	}

	public SimpleStringProperty utilizedGradeProperty() {

		return this.specifiedGrade;
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

	public String getLaboratory() {

		return this.laboratory.get();
	}

	public void setLaboratory(String laboratory) {

		this.laboratory.set(laboratory);
	}

	public SimpleStringProperty laboratoryProperty() {

		return this.laboratory;
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

	public void resetTo(HeatChartSheetRegister hSheets) {

		this.heatChartSheetCode = new SimpleIntegerProperty(
				hSheets.getHeatChartSheetCode());

		this.sheetNumber = new SimpleIntegerProperty(hSheets.getSheetNumber());
		this.sequenceNumber = new SimpleIntegerProperty(
				hSheets.getSequenceNumber());

		this.partNumber = new SimpleStringProperty(hSheets.getPartNumber());
		this.partName = new SimpleStringProperty(hSheets.getPartName());
		this.specifiedSize = new SimpleStringProperty(
				hSheets.getSpecifiedSize());
		this.specifiedGrade = new SimpleStringProperty(
				hSheets.getSpecifiedGrade());

		this.utilizedSize = new SimpleStringProperty(hSheets.getUtilizedSize());
		this.utilizedGrade = new SimpleStringProperty(
				hSheets.getUtilizedGrade());
		this.ctNumber = new SimpleStringProperty(hSheets.getCtNumber());
		this.reportNumber = new SimpleStringProperty(hSheets.getReportNumber());
		this.reportDate = new SimpleStringProperty(hSheets.getReportDate());
		this.laboratory = new SimpleStringProperty(hSheets.getLaboratory());

		this.status = hSheets.getStatus();
		this.createdBy = hSheets.getCreatedBy();
		this.createdDate = hSheets.getCreatedDate();
	}

	public void clean() {

		this.heatChartSheetCode.set(0);

		this.sheetNumber.set(1);
		this.sequenceNumber.set(1);

		this.partNumber.set("");
		this.partName.set("");
		this.specifiedSize.set("");
		this.specifiedGrade.set("");

		this.utilizedSize.set("");
		this.utilizedGrade.set("");
		this.ctNumber.set("");
		this.reportNumber.set("");
		this.reportDate.set("");
		this.laboratory.set("");

		this.status = "TRUE";
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public static HeatChartSheetRegister getMaterialRegister(
			HeatChartSheets heatChartSheet) {
		HeatChartSheetRegister register = new HeatChartSheetRegister();

		register.setHeatChartSheetCode(heatChartSheet.getHeatChartSheetCode());

		register.setSequenceNumber(heatChartSheet.getSequenceNumber());
		register.setSheetNumber(heatChartSheet.getSheetNumber());
		register.setPartNumber(heatChartSheet.getPartNumber());
		register.setPartName(heatChartSheet.getPartName());
		register.setSpecifiedSize(heatChartSheet.getSpecifiedSize());
		register.setSpecifiedGrade(heatChartSheet.getSpecifiedGrade());

		register.setUtilizedSize(heatChartSheet.getMaterialmaster().getSize());
		register.setUtilizedGrade(heatChartSheet.getMaterialmaster()
				.getSpecification().getName());
		register.setCtNumber(heatChartSheet.getMaterialmaster().getCtNumber());

		register.setReportNumber(heatChartSheet.getMaterialtests()
				.getReportNumber());
		register.setReportDate(heatChartSheet.getMaterialtests()
				.getReportDate());
		register.setLaboratory(heatChartSheet.getMaterialtests()
				.getLaboratory().getName());

		return register;
	}

	public static List<HeatChartSheetRegister> getHeatChartSheetList(
			List<HeatChartSheets> heatChartSheets) {
		List<HeatChartSheetRegister> registerList = new ArrayList<>();

		for (HeatChartSheets sheets : heatChartSheets) {

			registerList.add(getMaterialRegister(sheets));
		}

		return registerList;
	}

	@Override
	public int compareTo(HeatChartSheetRegister o) {

		if (o instanceof HeatChartSheetRegister && o != null) {
			return this.heatChartSheetCode.getValue().compareTo(
					o.getHeatChartSheetCode());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
