package org.map.service;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

import javax.annotation.Resource;

import org.map.utils.Context;
import org.map.utils.PageUtil;
import org.map.view.AbstractPageView;
import org.map.view.ErrorView;
import org.springframework.stereotype.Repository;

@Repository("LoadPageView")
public class LoadPageView extends Service<Void> {

	private String pageName;

	@Resource(name = "PageUtil")
	private PageUtil pageUtil;

	public LoadPageView() {

	}

	@Override
	protected Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				try {
					Platform.runLater(new Runnable() {

						@Override
						public void run() {

							Timeline timeline = new Timeline();

							final Node startPage = getPageView("LoadingView").showView();
							final Node finishPage = getPageView(getPageName()).showView();
							
							EventHandler<ActionEvent> onStart = new EventHandler<ActionEvent>() {
								public void handle(ActionEvent t) {

									Context.getPageArea().getChildren()
											.setAll(startPage);
								}
							};

							EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>() {
								public void handle(ActionEvent t) {

									Context.getPageArea().getChildren()
											.setAll(finishPage);
								}
							};

							timeline.getKeyFrames().addAll(
									new KeyFrame(Duration.ZERO, onStart,
											new KeyValue(startPage
													.opacityProperty(), 1),
											new KeyValue(finishPage
													.opacityProperty(), 0)),
									new KeyFrame(Duration.seconds(1),
											onFinished, new KeyValue(startPage
													.opacityProperty(), 0),
											new KeyValue(finishPage
													.opacityProperty(), 1)));
							timeline.play();
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

	private AbstractPageView getPageView(String pageName) {

		if (pageName.equalsIgnoreCase("LoadingView")) {

			return pageUtil.getLoadingView();
		} else if (pageName.equalsIgnoreCase("Add User")) {

			return pageUtil.getAddUser();
		} else if (pageName.equalsIgnoreCase("View User")) {

			return pageUtil.getViewUser();
		} else if (pageName.equalsIgnoreCase("Edit User")) {

			return pageUtil.getEditUser();
		} else if (pageName.equalsIgnoreCase("Add Material")) {

			return pageUtil.getAddMaterial();
		} else if (pageName.equalsIgnoreCase("View Material")) {

			return pageUtil.getViewMaterial();
		} else if (pageName.equalsIgnoreCase("Edit Material")) {

			return pageUtil.getEditMaterial();
		} else if (pageName.equalsIgnoreCase("Add Validation")) {

			return pageUtil.getAddValidation();
		} else if (pageName.equalsIgnoreCase("View Validation")) {

			return pageUtil.getViewValidation();
		} else if (pageName.equalsIgnoreCase("Edit Validation")) {

			return pageUtil.getEditValidation();
		} else if (pageName.equalsIgnoreCase("View Material Register")) {

			return pageUtil.getViewReport();
		} else if (pageName.equalsIgnoreCase("Add Heat Chart")) {

			return pageUtil.getAddHeatChart();
		} else if (pageName.equalsIgnoreCase("View Heat Chart")) {

			return pageUtil.getViewHeatChart();
		} else if (pageName.equalsIgnoreCase("Edit Heat Chart")) {

			return pageUtil.getEditHeatChart();
		} else if (pageName.equalsIgnoreCase("Change Password")) {

			return pageUtil.getChangePassword();
		} else if (pageName.equalsIgnoreCase("Settings")) {

			return pageUtil.getSettings();
		} else if (pageName.equalsIgnoreCase("Backup")) {

			return pageUtil.getDatabaseBackup();
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
