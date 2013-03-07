package org.map.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {
	
	private static final String appPropertiesFile = "resources/app.properties";
	private static final String jdbcPropertiesFile = "resources/jdbc.properties";

	private static final Properties appProp = new Properties();
	private static final Properties jdbcProp = new Properties();

	static {
		try {
			FileInputStream appPropStream = new FileInputStream(
					appPropertiesFile);
			appProp.load(appPropStream);

			FileInputStream jdbcPropStream = new FileInputStream(
					jdbcPropertiesFile);
			jdbcProp.load(jdbcPropStream);
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static String getValue(String key) {

		return appProp.get(key).toString().trim();
	}

	public static void setValue(String key, String value)
			throws FileNotFoundException, IOException {

		appProp.setProperty(key, value);
		appProp.store(new FileOutputStream(appPropertiesFile), null);
	}

	public static String getJdbcValue(String key) {

		return jdbcProp.get(key).toString().trim();
	}
}
