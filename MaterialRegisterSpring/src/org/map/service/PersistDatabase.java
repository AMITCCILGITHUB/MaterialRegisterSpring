package org.map.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import org.map.utils.AppProperties;
import org.springframework.stereotype.Repository;

@Repository("PersistDatabase")
public class PersistDatabase extends Service<Void> {

	private String fileName;

	public PersistDatabase() {

		fileName = null;
	}

	public PersistDatabase(String fileName) {

		this.fileName = fileName;
	}

	@Override
	public Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				backupDB();

				return null;
			}
		};
	}

	private boolean backupDB() {

		String executeCmd = "mysqldump -u "
				+ AppProperties.getJdbcValue("jdbc.username") + " -p"
				+ AppProperties.getJdbcValue("jdbc.password")
				+ " --add-drop-database -B " + "metalplantsspring" + " -r "
				+ fileName;
		
		Process runtimeProcess;
		try {
			runtimeProcess = Runtime.getRuntime().exec(executeCmd);
			int processComplete = runtimeProcess.waitFor();

			if (processComplete == 0) {
				System.out.println("Backup created successfully");
				return true;
			} else {
				System.out.println("Could not create the backup");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
