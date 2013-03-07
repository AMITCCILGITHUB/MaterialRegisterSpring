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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.map.constants.RecordStatus;
import org.map.utils.Context;

@Entity
@Table(name = "MENU_MASTER")
public class MenuMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty menuCode;
	private SimpleStringProperty menuName;
	private SimpleStringProperty displayText;
	private MenuMaster parentMenu;
	private List<MenuMaster> childMenu;
	private List<ValidationMaster> role;
	private SimpleIntegerProperty menuOrder;

	private RecordStatus status;
	private String createdBy;
	private Date createdDate;

	public MenuMaster() {

		this.menuCode = new SimpleIntegerProperty(0);
		this.menuName = new SimpleStringProperty("");
		this.displayText = new SimpleStringProperty("");
		this.childMenu = new ArrayList<>();
		this.role = new ArrayList<>();
		this.menuOrder = new SimpleIntegerProperty(0);

		this.status = RecordStatus.TRUE;
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public MenuMaster(MenuMaster master) {

		this.menuCode = new SimpleIntegerProperty(master.getMenuCode());
		this.menuName = new SimpleStringProperty(master.getMenuName());
		this.displayText = new SimpleStringProperty(master.getDisplayText());
		this.childMenu = new ArrayList<>(master.getChildMenu());
		this.role = new ArrayList<>(master.getRole());
		this.menuOrder = new SimpleIntegerProperty(master.getMenuOrder());

		this.status = master.getStatus();
		this.createdBy = master.getCreatedBy();
		this.createdDate = master.getCreatedDate();
	}

	@Id
	@TableGenerator(name = "MENU_CODE_GENERATOR", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MENU_CODE", initialValue = 10000, allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MENU_CODE_GENERATOR")
	/*
	 * @GeneratedValue(generator = "MENU_CODE_GENERATOR")
	 * 
	 * @GenericGenerator(name = "MENU_CODE_GENERATOR", strategy =
	 * "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
	 * 
	 * @Parameter(name = "sequence_name", value = "MENU_CODE_SEQUENCE"),
	 * 
	 * @Parameter(name = "optimizer", value = "hilo"),
	 * 
	 * @Parameter(name = "initial_value", value = "1000"),
	 * 
	 * @Parameter(name = "increment_size", value = "1"),
	 * 
	 * @Parameter(name = "table", value = "MENU_MASTER"),
	 * 
	 * @Parameter(name = "column", value = "MENU_CODE") })
	 */
	@Column(name = "MENU_CODE", unique = true, nullable = false, length = 11)
	public Integer getMenuCode() {
		return this.menuCode.get();
	}

	public void setMenuCode(Integer menuCode) {
		this.menuCode.set(menuCode);
	}

	public SimpleIntegerProperty menuCodeProperty() {
		return this.menuCode;
	}

	@Column(name = "NAME")
	public String getMenuName() {
		return this.menuName.get();
	}

	public void setMenuName(String menuName) {
		this.menuName.set(menuName);
	}

	public SimpleStringProperty menuNameProperty() {
		return this.menuName;
	}

	@Column(name = "DISPLAY_TEXT")
	public String getDisplayText() {
		return displayText.get();
	}

	public void setDisplayText(String displayText) {
		this.displayText.set(displayText);
	}

	public SimpleStringProperty displayTextProperty() {
		return displayText;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_MENU")
	public MenuMaster getParentMenu() {
		return this.parentMenu;
	}

	public void setParentMenu(MenuMaster parentMenu) {
		this.parentMenu = parentMenu;
	}

	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parentMenu")
	public List<MenuMaster> getChildMenu() {
		return this.childMenu;
	}

	public void setChildMenu(List<MenuMaster> childMenu) {
		this.childMenu.clear();
		this.childMenu.addAll(childMenu);
	}

	@Column(name = "MENU_ORDER")
	public Integer getMenuOrder() {
		return this.menuOrder.get();
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder.set(menuOrder);
	}

	public SimpleIntegerProperty menuOrderProperty() {
		return this.menuOrder;
	}

	@Fetch(FetchMode.SUBSELECT)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "MENU_ROLE_MASTER", joinColumns = { @JoinColumn(name = "MENU_CODE") }, inverseJoinColumns = { @JoinColumn(name = "CODE") })
	public List<ValidationMaster> getRole() {
		return this.role;
	}

	public void setRole(List<ValidationMaster> role) {
		this.role.clear();
		this.role.addAll(role);
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

	public void resetTo(MenuMaster um) {

		this.menuCode.set(um.getMenuCode());
		this.menuName.set(um.getMenuName());
		this.displayText.set(um.getDisplayText());
		this.childMenu.clear();
		this.childMenu.addAll(um.getChildMenu());
		this.menuOrder.set(um.getMenuOrder());

		this.status = um.getStatus();
		this.createdBy = um.getCreatedBy();
		this.createdDate = um.getCreatedDate();
	}

	public void clean() {

		this.menuCode.set(0);
		this.menuName.set("");
		this.displayText.set("");
		this.childMenu.clear();
		this.menuOrder.set(0);

		this.status = RecordStatus.TRUE;
		this.createdBy = Context.getLoggedUser().getUserName();
		this.createdDate = Calendar.getInstance().getTime();
	}
}
