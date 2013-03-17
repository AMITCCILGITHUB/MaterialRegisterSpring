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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.map.constants.RecordStatus;
import org.map.constants.ValidationType;
import org.map.utils.Context;

@Entity
@Table(name = "ROLE_MASTER")
public class RoleMaster implements Serializable, Comparable<RoleMaster> {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty code;
	private SimpleStringProperty name;
	private SimpleStringProperty displayText;
	private List<MenuMaster> menu;

	private RecordStatus status;
	private String createdBy;
	private Date createdDate;

	public RoleMaster() {

		this.code = new SimpleIntegerProperty(0);
		this.name = new SimpleStringProperty("");
		this.displayText = new SimpleStringProperty("");
		this.menu = new ArrayList<>();

		status = RecordStatus.TRUE;
		createdBy = "SYSTEM";
		createdDate = Calendar.getInstance().getTime();
	}

	public RoleMaster(ValidationType type) {

		this.code = new SimpleIntegerProperty(0);
		this.name = new SimpleStringProperty("");
		this.displayText = new SimpleStringProperty("");
		this.menu = new ArrayList<>();

		status = RecordStatus.TRUE;
		createdBy = "SYSTEM";
		createdDate = Calendar.getInstance().getTime();
	}

	public RoleMaster(RoleMaster validation) {

		this.code = new SimpleIntegerProperty(validation.getCode());
		this.name = new SimpleStringProperty(validation.getName());
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
	@TableGenerator(name = "ROLE_CODE_GENERATOR", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "ROLE_CODE", initialValue = 1000, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ROLE_CODE_GENERATOR")
	@Column(name = "ROLE_CODE", unique = true, nullable = false, length = 11)
	public Integer getCode() {
		return code.get();
	}

	public void setCode(Integer Code) {
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
	public int compareTo(RoleMaster o) {

		if (o instanceof RoleMaster && o != null) {
			return code.getValue().compareTo(o.codeProperty().getValue());
		} else {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}
