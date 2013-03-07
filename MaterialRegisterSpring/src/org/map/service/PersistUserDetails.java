package org.map.service;

import javafx.concurrent.Task;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.hibernate.ddo.UserMaster;
import org.map.hibernate.utils.UserData;
import org.springframework.stereotype.Repository;

@Repository("PersistUserDetails")
public class PersistUserDetails extends AbstractService<UserMaster, Void> {

	@Resource(name = "UserData")
	private UserData userData;

	UserMaster user;
	PersistType persistType;

	public PersistUserDetails() {

		this.user = new UserMaster();
		this.persistType = PersistType.NONE;
	}

	public PersistUserDetails(UserMaster user, PersistType persistType) {

		this.user = user;
		this.persistType = persistType;
	}

	@Override
	public Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				switch (persistType) {
				case INSERT:
					userData.insertUser(user);
					break;
				case UPDATE:
					userData.updateUser(user);
					break;
				case DELETE:
					userData.deleteUser(user);
					break;
				default:
					break;
				}

				return null;
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

	public PersistType getPersistType() {
		return persistType;
	}

	@Override
	public void setPersistType(PersistType persistType) {
		this.persistType = persistType;
	}

	@Override
	public void setPersistEntity(UserMaster entity) {
		this.user = entity;
	}

}