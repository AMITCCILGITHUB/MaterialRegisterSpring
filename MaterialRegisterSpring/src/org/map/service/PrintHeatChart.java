package org.map.service;

import java.io.IOException;
import java.net.URISyntaxException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.sf.jasperreports.engine.JRException;

import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.utils.Reporter;
import org.map.logger.LoggerUtil;
import org.springframework.stereotype.Repository;

@Repository("PrintHeatChart")
public class PrintHeatChart extends Service<String> {

	protected HeatChartMaster heatChart = null;

    public PrintHeatChart() {
		
		this.heatChart = new HeatChartMaster();
	}

	public PrintHeatChart(HeatChartMaster heatChart) {
		
		this.heatChart = heatChart;
	}

	@Override
	protected Task<String> createTask() {

		return new Task<String>() {

			@Override
			protected String call() {

				String reportPath = null;
				try {
					reportPath = Reporter.printHeatChartReport(heatChart);
				} catch (JRException | IOException | URISyntaxException ex) {
					LoggerUtil.getLogger().debug(ex);
				}
				return reportPath;
			}
		};
	}

	public HeatChartMaster getHeatChart() {
		return heatChart;
	}

	public void setHeatChart(HeatChartMaster heatChart) {
		this.heatChart = heatChart;
	}
}