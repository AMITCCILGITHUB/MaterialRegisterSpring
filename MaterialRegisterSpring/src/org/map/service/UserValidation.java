package org.map.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.annotation.Resource;

import org.map.hibernate.ddo.UserMaster;
import org.map.hibernate.utils.UserData;
import org.springframework.stereotype.Repository;

@Repository("UserValidation")
public class UserValidation extends Service<Boolean> {

	@Resource(name = "UserData")
	private UserData userData;

	private UserMaster user;

	public UserValidation() {

		this.user = new UserMaster();
	}

	public UserValidation(UserMaster user) {

		this.user = user;
	}

	@Override
	protected Task<Boolean> createTask() {

		return new Task<Boolean>() {

			@Override
			protected Boolean call() {

				return Boolean.valueOf(userData.validateUser(user));
			}
		};
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public UserMaster getUser() {
		return user;
	}

	public void setUser(UserMaster user) {
		this.user = user;
	}
}