package org.map.hibernate.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.map.hibernate.OrderBySqlFormula;
import org.map.hibernate.dao.HibernateDao;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.HeatChartSheets;
import org.springframework.stereotype.Repository;

@Repository("HeatChartData")
public class HeatChartData {

	@Resource(name = "HeatChartDao")
	private HibernateDao<HeatChartMaster, Integer> hibernateDao;

	@Resource(name = "CodeData")
	private CodeData codeData;

	public HibernateDao<HeatChartMaster, Integer> getHibernateDao() {
		return hibernateDao;
	}

	public void setHibernateDao(
			HibernateDao<HeatChartMaster, Integer> hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	public CodeData getCodeData() {

		return codeData;
	}

	public void setCodeData(CodeData codeData) {

		this.codeData = codeData;
	}

	public void insertHeatChart(HeatChartMaster heatChart) {

		heatChart.setHeatChartCode(getNextHeatChartCode());

		Integer nextSheetCode = getNextHeatChartSheetCode();
		for (HeatChartSheets sheet : heatChart.getHeatChartSheets()) {

			if (sheet.getHeatChartSheetCode() == 0) {

				sheet.setHeatChartSheetCode(nextSheetCode);
				sheet.setHeatchartmaster(heatChart);
				nextSheetCode++;
			}
		}

		hibernateDao.save(heatChart);
	}

	public void updateHeatChart(HeatChartMaster heatChart) {

		hibernateDao.update(heatChart);
	}

	public void deleteHeatChart(HeatChartMaster heatChart) {

		hibernateDao.delete(heatChart);
	}

	public String getNextChartNumber() throws HibernateException {

		String currentYear = codeData.getCode("Default_Year").getCodeValue()
				.substring(2);

		Integer nextNumber = Integer
				.parseInt(hibernateDao.get(
						Restrictions
								.sqlRestriction(
										"substring(substring_index({alias}.HEATCHART_NUMBER, '-', 1), 3, 2) = ?",
										currentYear, StringType.INSTANCE),
						Projections
								.sqlProjection(
										"ifnull(max(cast(substring(substring_index({alias}.HEATCHART_NUMBER, '-', 1), 6) as unsigned)), 0) HEATCHART_NUMBER",
										new String[] { "HEATCHART_NUMBER" },
										new Type[] { StringType.INSTANCE }))) + 1;

		return "HC" + currentYear + "/" + nextNumber;
	}

	public HeatChartMaster searchHeatChartDetailsByHcNumber(
			String heatChartNumber) throws ParseException {

		return hibernateDao.get(Restrictions.like("chartNumber",
				heatChartNumber, MatchMode.ANYWHERE));
	}

	public List<HeatChartMaster> searchHeatChartDetailsHc(String hcNumberFrom,
			String hcNumberTo) throws ParseException {

		List<HeatChartMaster> heatCharts = null;
		if (hcNumberTo == null || hcNumberTo.trim().length() == 0) {
			heatCharts = (List<HeatChartMaster>) hibernateDao
					.list(Restrictions
							.conjunction()
							.add(Restrictions
									.sqlRestriction(
											"cast(substring(substring_index(HEATCHART_NUMBER, '-', 1), 6) as unsigned) >= cast(substring(substring_index(?, '-', 1), 6) as unsigned)",
											hcNumberFrom, StringType.INSTANCE))
							.add(Restrictions
									.sqlRestriction(
											"substring(substring_index(HEATCHART_NUMBER, '-', 1), 3,  2) = substring(substring_index(?, '-', 1), 3, 2)",
											hcNumberFrom, StringType.INSTANCE)),
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Chart_Number, '-', 1), 6) as unsigned) asc"));
		} else {
			heatCharts = (List<HeatChartMaster>) hibernateDao
					.list(Restrictions
							.conjunction()
							.add(Restrictions
									.sqlRestriction(
											"cast(substring(substring_index(HEATCHART_NUMBER, '-', 1), 6) as unsigned) between cast(substring(substring_index(?, '-', 1), 6) as unsigned) and cast(substring(substring_index(?, '-', 1), 6) as unsigned)",
											new String[] { hcNumberFrom,
													hcNumberTo }, new Type[] {
													StringType.INSTANCE,
													StringType.INSTANCE }))
							.add(Restrictions
									.sqlRestriction(
											"substring(substring_index(HEATCHART_NUMBER, '-', 1), 3,  2) = substring(substring_index(?, '-', 1), 3, 2)",
											hcNumberFrom, StringType.INSTANCE)),
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Chart_Number, '-', 1), 6) as unsigned) asc"));
		}

		return heatCharts;
	}

	public List<HeatChartMaster> searchHeatChartDetailsDt(Date fromDate,
			Date toDate) throws ParseException {

		List<HeatChartMaster> heatCharts = null;
		if (toDate == null) {

			heatCharts = (List<HeatChartMaster>) hibernateDao
					.list(Restrictions.ge("createdDate", fromDate),
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Chart_Number, '-', 1), 6) as unsigned) asc"));
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(toDate);
			cal.add(Calendar.DATE, 1);

			heatCharts = (List<HeatChartMaster>) hibernateDao
					.list(Restrictions.between("createdDate", fromDate,
							cal.getTime()),
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Chart_Number, '-', 1), 6) as unsigned) asc"));
		}

		return heatCharts;
	}

	public Integer getNextHeatChartCode() {

		return hibernateDao.nextCode(Projections.max("heatChartCode"));
	}

	public Integer getNextHeatChartSheetCode() {

		return hibernateDao.nextCode(Projections.max("heatChartSheetCode"));
	}
}
