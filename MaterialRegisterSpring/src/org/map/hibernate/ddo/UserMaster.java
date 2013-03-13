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
import org.map.hibernate.property.RoleProperty;
import org.map.utils.Context;

@Entity
@Table(name = "USER_MASTER")
public class UserMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private SimpleIntegerProperty userCode;
	private SimpleStringProperty userName;
	private SimpleStringProperty password;
	private SimpleStringProperty confirmPassword;
	private RoleProperty role;

	private RecordStatus status;
	private String createdBy;
	private Date createdDate;

	private StringBuilder validationMessage;

	public UserMaster() {

		this.userCode = new SimpleIntegerProperty(0);
		this.userName = new SimpleStringProperty("");
		this.password = new SimpleStringProperty("");
		this.confirmPassword = new SimpleStringProperty("");
		this.role = new RoleProperty();

		this.status = RecordStatus.TRUE;
		this.createdBy = "SYSTEM";
		this.createdDate = Calendar.getInstance().getTime();
	}

	public UserMaster(UserMaster master) {

		this.userCode = new SimpleIntegerProperty(master.getUserCode());
		this.userName = new SimpleStringProperty(master.getUserName());
		this.password = new SimpleStringProperty(master.getPassword());
		this.confirmPassword = new SimpleStringProperty(master.getPassword());
		this.role = new RoleProperty(master.getRole());

		this.status = master.getStatus();
		this.createdBy = master.getCreatedBy();
		this.createdDate = master.getCreatedDate();
	}

	@Id
	@GeneratedValue(generator = "USER_CODE_GENERATOR")
	@GenericGenerator(name = "USER_CODE_GENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "USER_CODE_SEQUENCE"),
			@Parameter(name = "optimizer", value = "hilo"),
			@Parameter(name = "initial_value", value = "1000"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "USER_CODE", unique = true, nullable = false, length = 11)
	public int getUserCode() {
		return this.userCode.get();
	}

	public void setUserCode(int userCode) {
		this.userCode.set(userCode);
	}

	public SimpleIntegerProperty userCodeProperty() {
		return this.userCode;
	}

	@Column(name = "NAME")
	public String getUserName() {
		return this.userName.get();
	}

	public void setUserName(String userName) {

		this.userName.set(userName);
	}

	public SimpleStringProperty userNameProperty() {
		return this.userName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public SimpleStringProperty passwordProperty() {
		return this.password;
	}

	@Transient
	public String getConfirmPassword() {
		return confirmPassword.get();
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword.set(confirmPassword);
	}

	public SimpleStringProperty confirmPasswordProperty() {
		return this.confirmPassword;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE")
	public RoleMaster getRole() {
		return this.role.get();
	}

	public void setRole(RoleMaster role) {
		this.role.set(role);
	}

	public RoleProperty roleProperty() {
		return this.role;
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

	public void resetTo(UserMaster um) {

		this.userName.set(um.getUserName());
		this.password.set(um.getPassword());
		this.confirmPassword.set(um.getConfirmPassword());
		this.role = new RoleProperty(um.getRole());

		this.status = um.getStatus();
		this.createdBy = um.getCreatedBy();
		this.createdDate = um.getCreatedDate();
	}

	public void clean() {

		this.userCode.set(0);
		this.userName.set("");
		this.password.set("");
		this.confirmPassword.set("");
		this.role.get().clean();

		this.status = RecordStatus.TRUE;
		this.createdBy = Context.getLoggedUser().getUserName();
		this.createdDate = Calendar.getInstance().getTime();
	}

	@Transient
	public boolean isInvalid() {

		validationMessage = new StringBuilder();

		if (this.userName.get().trim().length() <= 0) {

			validationMessage.append("* User name is empty." + "\n");
		}

		if (this.password.get().trim().length() <= 0) {

			validationMessage.append("* Password is empty." + "\n");
		}

		if (this.confirmPassword.get().trim().length() <= 0) {

			validationMessage.append("* Confirm password is empty." + "\n");
		}

		if (this.password.get().trim().length() > 0
				&& this.confirmPassword.get().trim().length() > 0
				&& !this.password.get().trim()
						.equals(this.confirmPassword.get().trim())) {

			validationMessage
					.append("* Password and confirm password does not match."
							+ "\n");
		}

		if (this.role.get().getName().trim().length() <= 0) {
			validationMessage.append("* Role is empty." + "\n");
		}

		return (validationMessage.length() > 0);
	}

	@Transient
	public String getValidationMessage() {

		return validationMessage.toString();
	}
}
