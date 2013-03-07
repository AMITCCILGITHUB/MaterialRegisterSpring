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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.map.constants.RecordStatus;
import org.map.constants.ValidationType;
import org.map.hibernate.property.ValidationProperty;
import org.map.utils.Context;

@Entity
@Table(name = "MATERIAL_MASTER")
public class MaterialMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty materialCode;
	private SimpleStringProperty ctNumber;
	private ValidationProperty inspectionAgency;
	private ValidationProperty specification;
	private ValidationProperty item;
	private SimpleStringProperty size;
	private SimpleIntegerProperty quantity;
	private SimpleStringProperty heatNumber;
	private SimpleStringProperty plateNumber;

	private RecordStatus status;
	private String createdBy;
	private Date createdDate;

	private List<MaterialTests> materialTests = new ArrayList<>();

	private StringBuilder validationMessage;

	public MaterialMaster() {

		this.materialCode = new SimpleIntegerProperty(1);
		this.ctNumber = new SimpleStringProperty("");
		this.inspectionAgency = new ValidationProperty(ValidationType.AGENCY);
		this.specification = new ValidationProperty(
				ValidationType.SPECIFICATION);
		this.item = new ValidationProperty(ValidationType.ITEM);
		this.size = new SimpleStringProperty("");
		this.quantity = new SimpleIntegerProperty(1);
		this.heatNumber = new SimpleStringProperty("");
		this.plateNumber = new SimpleStringProperty("");

		materialTests = new ArrayList<>();

		this.status = RecordStatus.TRUE;
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public MaterialMaster(MaterialMaster material) {

		this.materialCode = new SimpleIntegerProperty(
				material.getMaterialCode());
		this.ctNumber = new SimpleStringProperty(material.getCtNumber());
		this.inspectionAgency = new ValidationProperty(
				material.getInspectionAgency());
		this.specification = new ValidationProperty(material.getSpecification());
		this.item = new ValidationProperty(material.getItem());
		this.size = new SimpleStringProperty(material.getSize());
		this.quantity = new SimpleIntegerProperty(material.getQuantity());
		this.heatNumber = new SimpleStringProperty(material.getHeatNumber());
		this.plateNumber = new SimpleStringProperty(material.getPlateNumber());

		this.materialTests = new ArrayList<>(material.getMaterialTests());

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
	}

	@Id
	@GeneratedValue(generator = "MATERIAL_CODE_GENERATOR")
	@GenericGenerator(name = "MATERIAL_CODE_GENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "MATERIAL_CODE_SEQUENCE"),
			@Parameter(name = "optimizer", value = "hilo"),
			@Parameter(name = "initial_value", value = "1000"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "MATERIAL_CODE", unique = true, nullable = false, length = 11)
	public int getMaterialCode() {

		return this.materialCode.get();
	}

	public void setMaterialCode(int materialCode) {

		this.materialCode.set(materialCode);
	}

	public SimpleIntegerProperty MaterialCodeProperty() {

		return this.materialCode;
	}

	@Column(name = "CT_NUMBER")
	public String getCtNumber() {

		return this.ctNumber.get();
	}

	public void setCtNumber(String ctNumber) {

		this.ctNumber.set(ctNumber);
	}

	public SimpleStringProperty ctNumberProperty() {

		return this.ctNumber;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODE", insertable = false, updatable = false)
	public ValidationMaster getInspectionAgency() {

		return this.inspectionAgency.get();
	}

	public void setInspectionAgency(ValidationMaster inspectionAgency) {

		this.inspectionAgency.set(inspectionAgency);
	}

	public ValidationProperty inspectionAgencyProperty() {

		return this.inspectionAgency;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODE", insertable = false, updatable = false)
	public ValidationMaster getItem() {

		return this.item.get();
	}

	public void setItem(ValidationMaster item) {

		this.item.set(item);
	}

	public ValidationProperty itemProperty() {

		return this.item;
	}

	@Column(name = "SIZE")
	public String getSize() {

		return this.size.get();
	}

	public void setSize(String size) {

		this.size.set(size);
	}

	public SimpleStringProperty sizeProperty() {

		return this.size;
	}

	@Column(name = "QUANTITY")
	public int getQuantity() {

		return this.quantity.get();
	}

	public void setQuantity(int quantity) {

		this.quantity.set(quantity);
	}

	public SimpleIntegerProperty quantityProperty() {

		return this.quantity;
	}

	@Column(name = "HEAT_NUMBER")
	public String getHeatNumber() {

		return this.heatNumber.get();
	}

	public void setHeatNumber(String heatNumber) {

		this.heatNumber.set(heatNumber);
	}

	public SimpleStringProperty heatNumberProperty() {

		return this.heatNumber;
	}

	@Column(name = "PLATE_NUMBER")
	public String getPlateNumber() {

		return this.plateNumber.get();
	}

	public void setPlateNumber(String plateNumber) {

		this.plateNumber.set(plateNumber);
	}

	public SimpleStringProperty plateNumberProperty() {

		return this.plateNumber;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CODE", insertable = false, updatable = false)
	public ValidationMaster getSpecification() {

		return this.specification.get();
	}

	public void setSpecification(ValidationMaster specification) {

		this.specification.set(specification);
	}

	public ValidationProperty specificationProperty() {

		return this.specification;
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
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "materialMaster")
	public List<MaterialTests> getMaterialTests() {
		return this.materialTests;
	}

	public void setMaterialTests(List<MaterialTests> materialTests) {
		materialTests.clear();
		this.materialTests.addAll(materialTests);
	}

	public void resetTo(MaterialMaster material) {

		this.materialCode.set(material.getMaterialCode());
		this.ctNumber.set(material.getCtNumber());
		this.inspectionAgency.set(material.getInspectionAgency());
		this.specification.set(material.getSpecification());
		this.item.set(material.getItem());
		this.size.set(material.getSize());
		this.quantity.set(material.getQuantity());
		this.heatNumber.set(material.getHeatNumber());
		this.plateNumber.set(material.getPlateNumber());

		this.materialTests = new ArrayList<>(material.getMaterialTests());

		this.status = material.getStatus();
		this.createdBy = material.getCreatedBy();
		this.createdDate = material.getCreatedDate();
	}

	public void clean() {

		this.materialCode.set(0);
		this.inspectionAgency.get().clean();
		this.specification.get().clean();
		this.item.get().clean();
		this.size.set("");
		this.quantity.set(1);
		this.heatNumber.set("");
		this.plateNumber.set("");

		for (MaterialTests tests : this.materialTests) {

			tests.clean();
		}

		this.status = RecordStatus.TRUE;
		this.createdBy = Context.getLoggedUser().getUserName();
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Transient
	public boolean isInvalid() {

		validationMessage = new StringBuilder();

		if (this.inspectionAgency.get().getName().trim().length() <= 0) {

			validationMessage.append("* Inspection agency is empty." + "\n");
		}

		if (this.specification.get().getName().trim().length() <= 0) {

			validationMessage.append("* Specification is empty." + "\n");
		}

		if (this.item.get().getName().trim().length() <= 0) {

			validationMessage.append("* Item is empty." + "\n");
		}

		if (this.quantity.get() == 0) {

			validationMessage.append("* Quantity is equal to zero." + "\n");
		}

		if (validationMessage.length() <= 0) {

			for (MaterialTests tests : this.materialTests) {

				if (tests.getSampleId().trim().length() > 0) {

					if (tests.isInvalid()) {

						validationMessage.append("Sample ID: "
								+ tests.getSampleId() + "\n"
								+ tests.getValidationMessage());
					}
				} else {

					validationMessage.append("* Sample ID is empty.");
				}
			}
		}

		return (validationMessage.length() > 0);
	}

	@Transient
	public String getValidationMessage() {

		return validationMessage.toString();
	}
}
