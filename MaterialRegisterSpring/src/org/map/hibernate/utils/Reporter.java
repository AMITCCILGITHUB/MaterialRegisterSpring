package org.map.hibernate.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.register.HeatChartSheetRegister;
import org.map.hibernate.register.MaterialRegister;
import org.map.utils.AppProperties;
import org.springframework.stereotype.Repository;

@Repository("Reporter")
public class Reporter {

	public static String printMaterialRegisterReport(List<MaterialRegister> data)
			throws JRException, IOException {

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				data);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

		Map<String, Object> parameters = new HashMap<String, Object>();

		File matFile = new File("resources/ireport/MaterialRegister.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				matFile.getAbsolutePath(), parameters, beanColDataSource);

		File pdf = new File(AppProperties.getValue("report.pdf.path")
				+ "/MaterialRegister_"
				+ sdf.format(Calendar.getInstance().getTime()) + ".pdf");

		JasperExportManager.exportReportToPdfFile(jasperPrint,
				pdf.getAbsolutePath());

		return pdf.getAbsolutePath();
	}

	public static String printHeatChartReport(HeatChartMaster data)
			throws JRException, IOException, URISyntaxException {

		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
				HeatChartSheetRegister.getHeatChartSheetList(data
						.getHeatChartSheets()));
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("equipment", data.getEquipment());
		parameters.put("customer", data.getCustomer());
		parameters.put("poDetails", data.getPoDetails());
		parameters.put("drawingNumber", data.getDrawingNumber());
		parameters.put("surveyor", data.getSurveyor());
		parameters.put("tagNumber", data.getTagNumber());

		File heatFile = new File("resources/ireport/HeatChart.jasper");
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				heatFile.getAbsolutePath(), parameters, beanColDataSource);

		File pdf = new File(AppProperties.getValue("report.pdf.path")
				+ "/HeatChart_" + sdf.format(Calendar.getInstance().getTime())
				+ ".pdf");

		JasperExportManager.exportReportToPdfFile(jasperPrint,
				pdf.getAbsolutePath());

		return pdf.getAbsolutePath();
	}
}
