package org.map.hibernate.ddo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.map.constants.RecordStatus;
import org.map.utils.Context;

@Entity
@Table(name = "HEATCHART_MASTER")
public class HeatChartMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty heatChartCode;
	private SimpleStringProperty chartNumber;
	private SimpleStringProperty equipment;
	private SimpleStringProperty customer;
	private SimpleStringProperty poDetails;
	private SimpleStringProperty drawingNumber;
	private SimpleStringProperty surveyor;
	private SimpleStringProperty tagNumber;

	private RecordStatus status;
	private String createdBy;
	private Date createdDate;

	private List<HeatChartSheets> heatChartSheets;

	public HeatChartMaster() {

		this.heatChartCode = new SimpleIntegerProperty(0);
		this.chartNumber = new SimpleStringProperty("");
		this.equipment = new SimpleStringProperty("");
		this.customer = new SimpleStringProperty("");
		this.poDetails = new SimpleStringProperty("");
		this.drawingNumber = new SimpleStringProperty("");
		this.surveyor = new SimpleStringProperty("");
		this.tagNumber = new SimpleStringProperty("");

		heatChartSheets = new ArrayList<>();

		this.status = RecordStatus.TRUE;
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public HeatChartMaster(HeatChartMaster hcMaster) {

		this.heatChartCode = new SimpleIntegerProperty(
				hcMaster.getHeatChartCode());
		this.chartNumber = new SimpleStringProperty(hcMaster.getChartNumber());
		this.equipment = new SimpleStringProperty(hcMaster.getEquipment());
		this.customer = new SimpleStringProperty(hcMaster.getCustomer());
		this.poDetails = new SimpleStringProperty(hcMaster.getPoDetails());
		this.drawingNumber = new SimpleStringProperty(
				hcMaster.getDrawingNumber());
		this.surveyor = new SimpleStringProperty(hcMaster.getDrawingNumber());
		this.tagNumber = new SimpleStringProperty(hcMaster.getTagNumber());

		this.heatChartSheets = new ArrayList<>(hcMaster.getHeatChartSheets());

		this.status = hcMaster.getStatus();
		this.createdBy = hcMaster.getCreatedBy();
		this.createdDate = hcMaster.getCreatedDate();
	}

	@Id
	@GeneratedValue(generator = "HEATCHART_CODE_GENERATOR")
	@GenericGenerator(name = "HEATCHART_CODE_GENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "HEATCHART_CODE_SEQUENCE"),
			@Parameter(name = "optimizer", value = "hilo"),
			@Parameter(name = "initial_value", value = "1000"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "HEATCHART_CODE", unique = true, nullable = false, length = 11)
	public int getHeatChartCode() {
		return this.heatChartCode.get();
	}

	public void setHeatChartCode(int heatChartCode) {
		this.heatChartCode.set(heatChartCode);
	}

	public SimpleIntegerProperty heatChartCodeProperty() {
		return this.heatChartCode;
	}

	@Column(name = "HEATCHART_NUMBER")
	public String getChartNumber() {
		return this.chartNumber.get();
	}

	public void setChartNumber(String chartNumber) {

		this.chartNumber.set(chartNumber);
	}

	public SimpleStringProperty chartNumberProperty() {
		return this.chartNumber;
	}

	@Column(name = "EQUIPMENT")
	public String getEquipment() {
		return this.equipment.get();
	}

	public void setEquipment(String equipment) {
		this.equipment.set(equipment);
	}

	public SimpleStringProperty equipmentProperty() {
		return this.equipment;
	}

	@Column(name = "CUSTOMER")
	public String getCustomer() {

		return this.customer.get();
	}

	public void setCustomer(String customer) {
		this.customer.set(customer);
	}

	public SimpleStringProperty customerProperty() {
		return this.customer;
	}

	@Column(name = "PO_DETAILS")
	public String getPoDetails() {
		return this.poDetails.get();
	}

	public void setPoDetails(String poDetails) {
		this.poDetails.set(poDetails);
	}

	public SimpleStringProperty poDetailsProperty() {
		return this.poDetails;
	}

	@Column(name = "DRAWING_NUMBER")
	public String getDrawingNumber() {
		return this.drawingNumber.get();
	}

	public void setDrawingNumber(String drawingNumber) {
		this.drawingNumber.set(drawingNumber);
	}

	public SimpleStringProperty drawingNumberProperty() {
		return this.drawingNumber;
	}

	@Column(name = "SURVEYOR")
	public String getSurveyor() {
		return this.surveyor.get();
	}

	public void setSurveyor(String surveyor) {
		this.surveyor.set(surveyor);
	}

	public SimpleStringProperty surveyorProperty() {
		return this.surveyor;
	}

	@Column(name = "TAG_NUMBER")
	public String getTagNumber() {
		return this.tagNumber.get();
	}

	public void setTagNumber(String tagNumber) {
		this.tagNumber.set(tagNumber);
	}

	public SimpleStringProperty tagNumberProperty() {
		return this.tagNumber;
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

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "heatchartmaster")
	public List<HeatChartSheets> getHeatChartSheets() {
		return this.heatChartSheets;
	}

	public void setHeatChartSheets(List<HeatChartSheets> heatChartSheets) {
		this.heatChartSheets = heatChartSheets;
	}

	public void resetTo(HeatChartMaster hcMaster) {

		this.heatChartCode.set(hcMaster.getHeatChartCode());
		this.chartNumber.set(hcMaster.getChartNumber());
		this.equipment.set(hcMaster.getEquipment());
		this.customer.set(hcMaster.getCustomer());
		this.poDetails.set(hcMaster.getPoDetails());
		this.drawingNumber.set(hcMaster.getDrawingNumber());
		this.surveyor.set(hcMaster.getDrawingNumber());
		this.tagNumber.set(hcMaster.getTagNumber());

		this.heatChartSheets = new ArrayList<>(hcMaster.getHeatChartSheets());

		this.status = hcMaster.getStatus();
		this.createdBy = hcMaster.getCreatedBy();
		this.createdDate = hcMaster.getCreatedDate();
	}

	public void clean() {

		this.heatChartCode.set(0);
		this.chartNumber.set("");
		this.equipment.set("");
		this.customer.set("");
		this.poDetails.set("");
		this.drawingNumber.set("");
		this.surveyor.set("");
		this.tagNumber.set("");

		this.heatChartSheets = new ArrayList<>();

		this.status = RecordStatus.TRUE;
		this.createdBy = Context.getLoggedUser().getUserName();
		this.createdDate = Calendar.getInstance().getTime();
	}
}
