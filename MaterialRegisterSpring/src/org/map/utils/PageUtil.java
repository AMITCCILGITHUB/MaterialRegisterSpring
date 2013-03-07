package org.map.utils;

import javax.annotation.Resource;

import org.map.view.AddHeatChart;
import org.map.view.AddMaterial;
import org.map.view.AddUser;
import org.map.view.AddValidation;
import org.map.view.ChangePassword;
import org.map.view.DatabaseBackup;
import org.map.view.EditHeatChart;
import org.map.view.EditMaterial;
import org.map.view.EditUser;
import org.map.view.EditValidation;
import org.map.view.Settings;
import org.map.view.ViewHeatChart;
import org.map.view.ViewMaterial;
import org.map.view.ViewReport;
import org.map.view.ViewUser;
import org.map.view.ViewValidation;
import org.springframework.stereotype.Repository;

@Repository("PageUtil")
public class PageUtil {

	@Resource(name = "AddUser")
	private AddUser addUser;

	@Resource(name = "ViewUser")
	private ViewUser viewUser;

	@Resource(name = "EditUser")
	private EditUser editUser;

	@Resource(name = "AddMaterial")
	private AddMaterial addMaterial;

	@Resource(name = "ViewMaterial")
	private ViewMaterial viewMaterial;

	@Resource(name = "EditMaterial")
	private EditMaterial editMaterial;

	@Resource(name = "AddValidation")
	private AddValidation addValidation;

	@Resource(name = "ViewValidation")
	private ViewValidation viewValidation;

	@Resource(name = "EditValidation")
	private EditValidation editValidation;

	@Resource(name = "ViewReport")
	private ViewReport viewReport;

	@Resource(name = "AddHeatChart")
	private AddHeatChart addHeatChart;

	@Resource(name = "ViewHeatChart")
	private ViewHeatChart viewHeatChart;

	@Resource(name = "EditHeatChart")
	private EditHeatChart editHeatChart;

	@Resource(name = "ChangePassword")
	private ChangePassword changePassword;

	@Resource(name = "Settings")
	private Settings settings;

	@Resource(name = "DatabaseBackup")
	private DatabaseBackup databaseBackup;

	public AddUser getAddUser() {
		return addUser;
	}

	public void setAddUser(AddUser addUser) {
		this.addUser = addUser;
	}

	public ViewUser getViewUser() {
		return viewUser;
	}

	public void setViewUser(ViewUser viewUser) {
		this.viewUser = viewUser;
	}

	public EditUser getEditUser() {
		return editUser;
	}

	public void setEditUser(EditUser editUser) {
		this.editUser = editUser;
	}

	public AddMaterial getAddMaterial() {
		return addMaterial;
	}

	public void setAddMaterial(AddMaterial addMaterial) {
		this.addMaterial = addMaterial;
	}

	public ViewMaterial getViewMaterial() {
		return viewMaterial;
	}

	public void setViewMaterial(ViewMaterial viewMaterial) {
		this.viewMaterial = viewMaterial;
	}

	public EditMaterial getEditMaterial() {
		return editMaterial;
	}

	public void setEditMaterial(EditMaterial editMaterial) {
		this.editMaterial = editMaterial;
	}

	public AddValidation getAddValidation() {
		return addValidation;
	}

	public void setAddValidation(AddValidation addValidation) {
		this.addValidation = addValidation;
	}

	public ViewValidation getViewValidation() {
		return viewValidation;
	}

	public void setViewValidation(ViewValidation viewValidation) {
		this.viewValidation = viewValidation;
	}

	public EditValidation getEditValidation() {
		return editValidation;
	}

	public void setEditValidation(EditValidation editValidation) {
		this.editValidation = editValidation;
	}

	public ViewReport getViewReport() {
		return viewReport;
	}

	public void setViewReport(ViewReport viewReport) {
		this.viewReport = viewReport;
	}

	public AddHeatChart getAddHeatChart() {
		return addHeatChart;
	}

	public void setAddHeatChart(AddHeatChart addHeatChart) {
		this.addHeatChart = addHeatChart;
	}

	public ViewHeatChart getViewHeatChart() {
		return viewHeatChart;
	}

	public void setViewHeatChart(ViewHeatChart viewHeatChart) {
		this.viewHeatChart = viewHeatChart;
	}

	public EditHeatChart getEditHeatChart() {
		return editHeatChart;
	}

	public void setEditHeatChart(EditHeatChart editHeatChart) {
		this.editHeatChart = editHeatChart;
	}

	public ChangePassword getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(ChangePassword changePassword) {
		this.changePassword = changePassword;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public DatabaseBackup getDatabaseBackup() {
		return databaseBackup;
	}

	public void setDatabaseBackup(DatabaseBackup databaseBackup) {
		this.databaseBackup = databaseBackup;
	}
}
