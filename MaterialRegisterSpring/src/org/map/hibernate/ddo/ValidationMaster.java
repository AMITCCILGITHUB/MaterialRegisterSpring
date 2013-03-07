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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.map.constants.RecordStatus;
import org.map.constants.ValidationType;
import org.map.utils.Context;

@Entity
@Table(name = "VALIDATION_MASTER")
public class ValidationMaster implements Serializable,
		Comparable<ValidationMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty code;
	private SimpleStringProperty name;
	private ValidationType type;
	private SimpleStringProperty displayText;
	private List<MenuMaster> menu;

	private RecordStatus status;
	private String createdBy;
	private Date createdDate;

	public ValidationMaster() {

		this.code = new SimpleIntegerProperty(0);
		this.name = new SimpleStringProperty("");
		this.type = ValidationType.NONE;
		this.displayText = new SimpleStringProperty("");
		this.menu = new ArrayList<>();

		status = RecordStatus.TRUE;
		createdBy = "SYSTEM";
		createdDate = Calendar.getInstance().getTime();
	}

	public ValidationMaster(ValidationType type) {

		this.code = new SimpleIntegerProperty(0);
		this.name = new SimpleStringProperty("");
		this.type = type;
		this.displayText = new SimpleStringProperty("");
		this.menu = new ArrayList<>();

		status = RecordStatus.TRUE;
		createdBy = "SYSTEM";
		createdDate = Calendar.getInstance().getTime();
	}

	public ValidationMaster(ValidationMaster validation) {

		this.code = new SimpleIntegerProperty(validation.getCode());
		this.name = new SimpleStringProperty(validation.getName());
		this.type = validation.getType();
		this.displayText = new SimpleStringProperty(validation.getDisplayText());
		this.menu = new ArrayList<>(validation.getMenu());

		status = validation.getStatus();
		createdBy = validation.getCreatedBy();
		createdDate = validation.getCreatedDate();
	}

	public SimpleIntegerProperty codeProperty() {
		return code;
	}

	@Id
	@GeneratedValue(generator = "VALIDATION_CODE_GENERATOR")
	@GenericGenerator(name = "VALIDATION_CODE_GENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "VALIDATION_CODE_SEQUENCE"),
			@Parameter(name = "optimizer", value = "hilo"),
			@Parameter(name = "initial_value", value = "1000"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "CODE", unique = true, nullable = false, length = 11)
	public int getCode() {
		return code.get();
	}

	public void setCode(int Code) {
		this.code.set(Code);
	}

	public SimpleStringProperty nameProperty() {
		return name;
	}

	@Column(name = "NAME")
	public String getName() {
		return name.get();
	}

	public void setName(String Name) {
		this.name.set(Name);
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "TYPE")
	public ValidationType getType() {
		return type;
	}

	public void setType(ValidationType Type) {
		this.type = Type;
	}

	public SimpleStringProperty displayTextProperty() {
		return displayText;
	}

	@Column(name = "DISPLAY_TEXT")
	public String getDisplayText() {
		return displayText.get();
	}

	public void setDisplayText(String displayText) {
		this.displayText.set(displayText);
	}

	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "role")
	public List<MenuMaster> getMenu() {
		return this.menu;
	}

	public void setMenu(List<MenuMaster> childMenu) {
		this.menu.clear();
		this.menu.addAll(childMenu);
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

	public void clean() {

		this.code.set(0);
		this.name.set("");
		this.displayText.set("");
		this.menu.clear();

		status = RecordStatus.TRUE;
		createdBy = Context.getLoggedUser().getUserName();
		createdDate = Calendar.getInstance().getTime();
	}

	@Override
	public int compareTo(ValidationMaster o) {

		if (o instanceof ValidationMaster && o != null) {
			return code.getValue().compareTo(o.codeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
