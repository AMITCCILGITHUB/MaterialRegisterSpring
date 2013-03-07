package org.map.service;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.sf.jasperreports.engine.JRException;

import org.map.hibernate.register.MaterialRegister;
import org.map.hibernate.utils.Reporter;
import org.map.logger.LoggerUtil;
import org.springframework.stereotype.Repository;

@Repository("PrintMaterialRegister")
public class PrintMaterialRegister extends Service<String> {

	ObservableList<MaterialRegister> data;
	
	public PrintMaterialRegister(){
		
		this.data = FXCollections
				.observableArrayList();
	}
	
	public PrintMaterialRegister(List<MaterialRegister> data){
		
		this.data = FXCollections
				.observableArrayList(data);
	}
	
	@Override
	protected Task<String> createTask() {

		return new Task<String>() {

			@Override
			protected String call() {

				String reportPath = null;
				try {
					reportPath = Reporter
							.printMaterialRegisterReport(data);
				} catch (JRException | IOException ex) {
					LoggerUtil.getLogger().debug(ex);
				}
				return reportPath;
			}
		};
	}

	public ObservableList<MaterialRegister> getData() {
		return data;
	}

	public void setData(ObservableList<MaterialRegister> data) {
		this.data = data;
	}
}