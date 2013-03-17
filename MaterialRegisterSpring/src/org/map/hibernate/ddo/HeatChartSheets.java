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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.map.constants.RecordStatus;
import org.map.utils.Context;

@Entity
@Table(name = "HEATCHART_SHEET")
public class HeatChartSheets implements Serializable,
		Comparable<HeatChartSheets> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty heatChartSheetCode;
	private SimpleIntegerProperty sheetNumber;
	private SimpleIntegerProperty sequenceNumber;
	private MaterialMaster materialmaster;
	private MaterialTests materialtests;
	private HeatChartMaster heatchartmaster;
	private SimpleStringProperty partNumber;
	private SimpleStringProperty partName;
	private SimpleStringProperty specifiedSize;
	private SimpleStringProperty specifiedGrade;

	private RecordStatus status;
	private String createdBy;
	private Date createdDate;

	public HeatChartSheets() {

		this.heatChartSheetCode = new SimpleIntegerProperty(0);
		this.sheetNumber = new SimpleIntegerProperty(1);
		this.sequenceNumber = new SimpleIntegerProperty(1);

		this.materialmaster = new MaterialMaster();
		this.materialtests = new MaterialTests();
		this.heatchartmaster = new HeatChartMaster();

		this.partNumber = new SimpleStringProperty("");
		this.partName = new SimpleStringProperty("");
		this.specifiedSize = new SimpleStringProperty("");
		this.specifiedGrade = new SimpleStringProperty("");

		this.status = RecordStatus.TRUE;
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public HeatChartSheets(HeatChartSheets hSheets) {

		this.heatChartSheetCode = new SimpleIntegerProperty(
				hSheets.getHeatChartSheetCode());

		this.sheetNumber = new SimpleIntegerProperty(hSheets.getSheetNumber());
		this.sequenceNumber = new SimpleIntegerProperty(
				hSheets.getSequenceNumber());

		this.materialmaster = new MaterialMaster(hSheets.getMaterialmaster());
		this.materialtests = new MaterialTests(hSheets.getMaterialtests());
		this.heatchartmaster = new HeatChartMaster(hSheets.getHeatchartmaster());

		this.partNumber = new SimpleStringProperty(hSheets.getPartNumber());
		this.partName = new SimpleStringProperty(hSheets.getPartName());
		this.specifiedSize = new SimpleStringProperty(
				hSheets.getSpecifiedSize());
		this.specifiedGrade = new SimpleStringProperty(
				hSheets.getSpecifiedGrade());

		this.status = hSheets.getStatus();
		this.createdBy = hSheets.getCreatedBy();
		this.createdDate = hSheets.getCreatedDate();
	}

	@Id
	@TableGenerator(name = "HEATCHARTSHEET_CODE_GENERATOR", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "HEATCHARTSHEET_CODE", initialValue = 1000, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "HEATCHARTSHEET_CODE_GENERATOR")
	@Column(name = "HEATCHARTSHEET_CODE", unique = true, nullable = false, length = 11)
	public Integer getHeatChartSheetCode() {
		return this.heatChartSheetCode.get();
	}

	public void setHeatChartSheetCode(Integer heatChartSheetCode) {

		this.heatChartSheetCode.set(heatChartSheetCode);
	}

	public SimpleIntegerProperty heatChartSheetCodeProperty() {
		return this.heatChartSheetCode;
	}

	@Column(name = "HEATCHART_SHEET_NUMBER")
	public Integer getSheetNumber() {
		return this.sheetNumber.get();
	}

	public void setSheetNumber(Integer sheetNumber) {
		this.sheetNumber.set(sheetNumber);
	}

	public SimpleIntegerProperty sheetNumberProperty() {
		return this.sheetNumber;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MATERIAL_CODE")
	public MaterialMaster getMaterialmaster() {
		return this.materialmaster;
	}

	public void setMaterialmaster(MaterialMaster materialmaster) {
		this.materialmaster = materialmaster;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TEST_CODE")
	public MaterialTests getMaterialtests() {
		return materialtests;
	}

	public void setMaterialtests(MaterialTests materialtests) {
		this.materialtests = materialtests;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "HEATCHART_CODE")
	public HeatChartMaster getHeatchartmaster() {
		return this.heatchartmaster;
	}

	public void setHeatchartmaster(HeatChartMaster heatchartmaster) {
		this.heatchartmaster = heatchartmaster;
	}

	@Column(name = "SEQUENCE_NUMBER")
	public Integer getSequenceNumber() {
		return this.sequenceNumber.get();
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber.set(sequenceNumber);
	}

	public SimpleIntegerProperty sequenceNumberProperty() {
		return this.sequenceNumber;
	}

	@Column(name = "PART_NUMBER")
	public String getPartNumber() {
		return this.partNumber.get();
	}

	public void setPartNumber(String partNumber) {
		this.partNumber.set(partNumber);
	}

	public SimpleStringProperty partNumberProperty() {
		return this.partNumber;
	}

	@Column(name = "PART_NAME")
	public String getPartName() {
		return this.partName.get();
	}

	public void setPartName(String partName) {
		this.partName.set(partName);
	}

	public SimpleStringProperty partNameProperty() {
		return this.partName;
	}

	@Column(name = "SPECIFIED_SIZE")
	public String getSpecifiedSize() {
		return this.specifiedSize.get();
	}

	public void setSpecifiedSize(String specifiedSize) {
		this.specifiedSize.set(specifiedSize);
	}

	public SimpleStringProperty specifiedSizeProperty() {
		return this.specifiedSize;
	}

	@Column(name = "SPECIFIED_GRADE")
	public String getSpecifiedGrade() {
		return this.specifiedGrade.get();
	}

	public void setSpecifiedGrade(String specifiedGrade) {
		this.specifiedGrade.set(specifiedGrade);
	}

	public SimpleStringProperty specifiedGradeProperty() {
		return this.specifiedGrade;
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

	public void resetTo(HeatChartSheets hSheets) {

		this.heatChartSheetCode = new SimpleIntegerProperty(
				hSheets.getHeatChartSheetCode());

		this.sheetNumber = new SimpleIntegerProperty(hSheets.getSheetNumber());
		this.sequenceNumber = new SimpleIntegerProperty(
				hSheets.getSequenceNumber());

		this.materialmaster = new MaterialMaster(hSheets.getMaterialmaster());
		this.materialtests = new MaterialTests(hSheets.getMaterialtests());
		this.heatchartmaster = new HeatChartMaster(hSheets.getHeatchartmaster());

		this.partNumber = new SimpleStringProperty(hSheets.getPartNumber());
		this.partName = new SimpleStringProperty(hSheets.getPartName());
		this.specifiedSize = new SimpleStringProperty(
				hSheets.getSpecifiedSize());
		this.specifiedGrade = new SimpleStringProperty(
				hSheets.getSpecifiedGrade());

		this.status = hSheets.getStatus();
		this.createdBy = hSheets.getCreatedBy();
		this.createdDate = hSheets.getCreatedDate();
	}

	public void clean() {

		this.heatChartSheetCode.set(0);

		this.sheetNumber.set(1);
		this.sequenceNumber.set(1);

		this.materialmaster.clean();
		this.materialtests.clean();
		this.heatchartmaster.clean();

		this.partNumber.set("");
		this.partName.set("");
		this.specifiedSize.set("");
		this.specifiedGrade.set("");

		this.status = RecordStatus.TRUE;
		this.createdBy = Context.getLoggedUser().getUserName();
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(HeatChartSheets o) {

		if (o instanceof HeatChartSheets && o != null) {
			return this.heatChartSheetCode.getValue().compareTo(
					o.getHeatChartSheetCode());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
