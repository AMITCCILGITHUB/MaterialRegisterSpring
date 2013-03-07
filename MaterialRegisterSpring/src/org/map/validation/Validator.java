package org.map.validation;

import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.UserMaster;
import org.map.utils.Alert;
import org.map.utils.Context;

public class Validator {

	public static boolean validateUserLogin(UserMaster user) {
		if (user.getUserName().trim().length() > 0
				&& user.getPassword().trim().length() > 0) {

			return true;
		} else {

			Alert.showAlert(Context.getLoginStage(), "Invalid Login", "Error",
					"Either user name or password is empty.\nPlease try again.");
			return false;
		}
	}

	public static boolean validateUserData(UserMaster user) {

		if (user.isInvalid()) {

			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					user.getValidationMessage());

			return false;
		}

		return true;
	}

	public static boolean validateMaterialData(MaterialMaster material) {

		if (material.isInvalid()) {

			Alert.showAlert(Context.getWindowStage(), "Error", "Error",
					material.getValidationMessage());

			return false;
		}

		return true;
	}
}
