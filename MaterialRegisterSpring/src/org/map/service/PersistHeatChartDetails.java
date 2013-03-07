package org.map.service;

import javafx.concurrent.Task;

import javax.annotation.Resource;

import org.map.constants.PersistType;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.utils.HeatChartData;
import org.springframework.stereotype.Repository;

@Repository("PersistHeatChartDetails")
public class PersistHeatChartDetails extends AbstractService<HeatChartMaster, Void> {

	@Resource(name = "HeatChartData")
	private HeatChartData heatChartData;

	HeatChartMaster heatChart;
	PersistType persistType;

	public PersistHeatChartDetails() {

		this.heatChart = new HeatChartMaster();
		this.persistType = PersistType.NONE;
	}

	public PersistHeatChartDetails(HeatChartMaster heatChart,
			PersistType persistType) {

		this.heatChart = heatChart;
		this.persistType = persistType;
	}

	@Override
	public Task<Void> createTask() {

		return new Task<Void>() {

			@Override
			protected Void call() {

				switch (persistType) {
				case INSERT:
					heatChartData.insertHeatChart(heatChart);
					break;
				case UPDATE:
					heatChartData.updateHeatChart(heatChart);
					break;
				case DELETE:
					heatChartData.deleteHeatChart(heatChart);
					break;
				default:
					break;
				}

				return null;
			}
		};
	}

	public HeatChartData getHeatChartData() {
		return heatChartData;
	}

	public void setHeatChartData(HeatChartData heatChartData) {
		this.heatChartData = heatChartData;
	}

	public HeatChartMaster getHeatChart() {
		return heatChart;
	}

	public void setHeatChart(HeatChartMaster heatChart) {
		this.heatChart = heatChart;
	}

	public PersistType getPersistType() {
		return persistType;
	}

	@Override
	public void setPersistType(PersistType persistType) {
		this.persistType = persistType;
	}

	@Override
	public void setPersistEntity(HeatChartMaster entity) {
		this.heatChart = entity;
	}
}