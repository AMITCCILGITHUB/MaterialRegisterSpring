package org.map.service;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Node;

import javax.annotation.Resource;

import org.map.controls.ErrorView;
import org.map.utils.Context;
import org.map.utils.PageUtil;
import org.springframework.stereotype.Repository;

@Repository("LoadPageView")
public class LoadPageView extends Service<Void> {

	private String pageName;

	@Resource(name = "PageUtil")
	private PageUtil pageUtil;

	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				try {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							Node pageView = getPageView(getPageName());

							Context.getPageArea().getChildren()
									.setAll(pageView);
						}
					});

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				return null;
			}
		};
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	private Node getPageView(String pageName) {

		if (pageName.equalsIgnoreCase("Add User")) {

			return pageUtil.getAddUser().showView();
		} else if (pageName.equalsIgnoreCase("View User")) {

			return pageUtil.getViewUser().showView();
		} else if (pageName.equalsIgnoreCase("Edit User")) {

			return pageUtil.getEditUser().showView();
		} else if (pageName.equalsIgnoreCase("Add Material")) {

			return pageUtil.getAddMaterial().showView();
		} else if (pageName.equalsIgnoreCase("View Material")) {

			return pageUtil.getViewMaterial().showView();
		} else if (pageName.equalsIgnoreCase("Edit Material")) {

			return pageUtil.getEditMaterial().showView();
		} else if (pageName.equalsIgnoreCase("Add Validation")) {

			return pageUtil.getAddValidation().showView();
		} else if (pageName.equalsIgnoreCase("View Validation")) {

			return pageUtil.getViewValidation().showView();
		} else if (pageName.equalsIgnoreCase("Edit Validation")) {

			return pageUtil.getEditValidation().showView();
		} else if (pageName.equalsIgnoreCase("View Material Register")) {

			return pageUtil.getViewReport().showView();
		} else if (pageName.equalsIgnoreCase("Add Heat Chart")) {

			return pageUtil.getAddHeatChart().showView();
		} else if (pageName.equalsIgnoreCase("View Heat Chart")) {

			return pageUtil.getViewHeatChart().showView();
		} else if (pageName.equalsIgnoreCase("Edit Heat Chart")) {

			return pageUtil.getEditHeatChart().showView();
		} else if (pageName.equalsIgnoreCase("Change Password")) {

			return pageUtil.getChangePassword().showView();
		} else if (pageName.equalsIgnoreCase("Settings")) {

			return pageUtil.getSettings().showView();
		} else if (pageName.equalsIgnoreCase("Backup")) {

			return pageUtil.getDatabaseBackup().showView();
		} else {
			return new ErrorView();
		}
	}

	public PageUtil getPageUtil() {
		return pageUtil;
	}

	public void setPageUtil(PageUtil pageUtil) {
		this.pageUtil = pageUtil;
	}
}
