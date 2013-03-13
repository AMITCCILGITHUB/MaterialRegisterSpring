package org.map.hibernate.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.map.hibernate.OrderBySqlFormula;
import org.map.hibernate.dao.HibernateDao;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.hibernate.ddo.MaterialTests;
import org.springframework.stereotype.Repository;

@Repository("MaterialData")
public class MaterialData {

	@Resource(name = "MaterialDao")
	private HibernateDao<MaterialMaster, Integer> hibernateDao;

	@Resource(name = "CodeData")
	private CodeData codeData;

	@Resource(name = "ValidationData")
	private ValidationData validationData;

	public HibernateDao<MaterialMaster, Integer> getHibernateDao() {

		return hibernateDao;
	}

	public void setHibernateDao(
			HibernateDao<MaterialMaster, Integer> hibernateDao) {

		this.hibernateDao = hibernateDao;
	}

	public CodeData getCodeData() {

		return codeData;
	}

	public void setCodeData(CodeData codeData) {

		this.codeData = codeData;
	}

	public MaterialMaster getMaterialDetails(String ctNumber) {

		return hibernateDao.get(Restrictions.eq("ctNumber", ctNumber));
	}

	public int getNextMaterialCode() throws HibernateException {

		return hibernateDao.nextCode(Projections.max("materialCode"));
	}

	public String getNextCtNumber() {

		String currentYear = codeData.getCode("Default_Year").getCodeValue()
				.substring(2);

		int nextNumber = Integer
				.parseInt(hibernateDao.get(
						Restrictions
								.sqlRestriction(
										"substring(substring_index({alias}.CT_NUMBER, '-', 1), 3, 2) = ?",
										currentYear, StringType.INSTANCE),
						Projections
								.sqlProjection(
										"ifnull(max(cast(substring(substring_index({alias}.CT_NUMBER, '-', 1), 6) as unsigned)), 0) CT_NUMBER",
										new String[] { "CT_NUMBER" },
										new Type[] { StringType.INSTANCE }))) + 1;

		return "MP" + currentYear + "/" + nextNumber;
	}

	public int getNextTestCode() {

		return hibernateDao.nextCode(Projections.max("testCode"));
	}

	public void insertMaterial(MaterialMaster material) {

		material.setMaterialCode(getNextMaterialCode());

		if (material.getInspectionAgency().getCode() < 1000) {

			validationData.insertValidation(material.getInspectionAgency());
		}

		if (material.getSpecification().getCode() < 1000) {

			validationData.insertValidation(material.getSpecification());
		}

		if (material.getItem().getCode() < 1000) {

			validationData.insertValidation(material.getItem());
		}

		int nextTestCode = getNextTestCode();
		for (MaterialTests materialTest : material.getMaterialTests()) {

			if (materialTest.getTestCode() < 1000) {

				materialTest.setTestCode(nextTestCode);
				materialTest.setMaterialMaster(material);

				if (materialTest.getTest().getCode() < 1000) {

					validationData.insertValidation(materialTest.getTest());
				}

				if (materialTest.getCustomer().getCode() < 1000) {

					validationData.insertValidation(materialTest.getCustomer());
				}

				if (materialTest.getLaboratory().getCode() < 1000) {

					validationData.insertValidation(materialTest
							.getLaboratory());
				}

				if (materialTest.getResult().getCode() < 1000) {

					validationData.insertValidation(materialTest.getResult());
				}

				nextTestCode++;
			}
		}

		hibernateDao.save(material);
	}

	public void updateMaterial(MaterialMaster material) {

		if (material.getInspectionAgency().getCode() < 1000) {

			validationData.insertValidation(material.getInspectionAgency());
		}

		if (material.getSpecification().getCode() < 1000) {

			validationData.insertValidation(material.getSpecification());
		}

		if (material.getItem().getCode() < 1000) {

			validationData.insertValidation(material.getItem());
		}

		int nextTestCode = getNextTestCode();
		for (MaterialTests materialTest : material.getMaterialTests()) {

			if (materialTest.getTestCode() == 0) {

				materialTest.setTestCode(nextTestCode);
				materialTest.setMaterialMaster(material);

				if (materialTest.getTest().getCode() < 1000) {

					validationData.insertValidation(materialTest.getTest());
				}

				if (materialTest.getCustomer().getCode() < 1000) {

					validationData.insertValidation(materialTest.getCustomer());
				}

				if (materialTest.getLaboratory().getCode() < 1000) {

					validationData.insertValidation(materialTest
							.getLaboratory());
				}

				if (materialTest.getResult().getCode() < 1000) {

					validationData.insertValidation(materialTest.getResult());
				}

				nextTestCode++;
			}
		}

		hibernateDao.update(material);
	}

	public MaterialMaster searchMaterialDetailsByCtNumber(String ctNumber) {

		return hibernateDao.get(Restrictions.eq("ctNumber", ctNumber));
	}

	public List<MaterialMaster> getMaterialList() {

		return (List<MaterialMaster>) hibernateDao
				.list(OrderBySqlFormula
						.sqlFormula("cast(substring(substring_index(Ct_Number, '-', 1), 6) as unsigned) asc"));
	}

	public List<String> searchMaterialDetails(String searchText) {

		Session session = hibernateDao.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		searchText = "%" + searchText + "%";
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.like("ctNumber", searchText,
				MatchMode.ANYWHERE));
		/*
		 * disjunction.add(Restrictions.like("inspectionAgency.agencyName",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("item.itemName", searchText,
		 * MatchMode.ANYWHERE));
		 */
		disjunction.add(Restrictions.like("size", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("heatNumber", searchText,
				MatchMode.ANYWHERE));
		disjunction.add(Restrictions.like("plateNumber", searchText,
				MatchMode.ANYWHERE));
		/*
		 * disjunction.add(Restrictions.like("specification.specificationName",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.test", searchText,
		 * MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.customer",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.equipments",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.laboratory",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.reportNumber",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.reportDate",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.result", searchText,
		 * MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.remarks",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.witnessedBy",
		 * searchText, MatchMode.ANYWHERE));
		 * disjunction.add(Restrictions.like("materialTests.failureReason",
		 * searchText, MatchMode.ANYWHERE));
		 */

		List<String> ctNumbers = (List<String>) hibernateDao.list(disjunction,
				Projections.distinct(Projections.property("ctNumber")));

		transaction.commit();
		session.close();

		return ctNumbers;
	}

	public List<MaterialMaster> searchMaterialDetailsCt(String ctNumberFrom,
			String ctNumberTo) throws ParseException {

		List<MaterialMaster> materials = null;
		if (ctNumberTo == null || ctNumberTo.trim().length() == 0) {
			materials = (List<MaterialMaster>) hibernateDao
					.list(Restrictions
							.conjunction()
							.add(Restrictions
									.sqlRestriction(
											"cast(substring(substring_index(CT_NUMBER, '-', 1), 6) as unsigned) >= cast(substring(substring_index(?, '-', 1), 6) as unsigned)",
											ctNumberFrom, StringType.INSTANCE))
							.add(Restrictions
									.sqlRestriction(
											"substring(substring_index(CT_NUMBER, '-', 1), 3,  2) = substring(substring_index(?, '-', 1), 3, 2)",
											ctNumberFrom, StringType.INSTANCE)),
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Chart_Number, '-', 1), 6) as unsigned) asc"));
		} else {
			materials = (List<MaterialMaster>) hibernateDao
					.list(Restrictions
							.conjunction()
							.add(Restrictions
									.sqlRestriction(
											"cast(substring(substring_index(CT_NUMBER, '-', 1), 6) as unsigned) between cast(substring(substring_index(?, '-', 1), 6) as unsigned) and cast(substring(substring_index(?, '-', 1), 6) as unsigned)",
											new String[] { ctNumberFrom,
													ctNumberTo }, new Type[] {
													StringType.INSTANCE,
													StringType.INSTANCE }))
							.add(Restrictions
									.sqlRestriction(
											"substring(substring_index(CT_NUMBER, '-', 1), 3,  2) = substring(substring_index(?, '-', 1), 3, 2)",
											ctNumberFrom, StringType.INSTANCE)),
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Chart_Number, '-', 1), 6) as unsigned) asc"));
		}

		return materials;
	}

	public List<MaterialMaster> searchMaterialDetailsDt(Date fromDate,
			Date toDate) throws ParseException {

		List<MaterialMaster> materials = null;
		if (toDate == null) {
			materials = (List<MaterialMaster>) hibernateDao
					.list(Restrictions.ge("createdDate", fromDate),
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Ct_Number, '-', 1), 6) as unsigned) asc"));

		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(toDate);
			cal.add(Calendar.DATE, 1);

			materials = (List<MaterialMaster>) hibernateDao
					.list(Restrictions.between("createdDate", fromDate,
							cal.getTime()),
							OrderBySqlFormula
									.sqlFormula("cast(substring(substring_index(Ct_Number, '-', 1), 6) as unsigned) asc"));
		}

		return materials;
	}

	public void deleteMaterial(MaterialMaster material) {

		hibernateDao.delete(material);
	}

	public ValidationData getValidationData() {
		return validationData;
	}

	public void setValidationData(ValidationData validationData) {
		this.validationData = validationData;
	}
}
