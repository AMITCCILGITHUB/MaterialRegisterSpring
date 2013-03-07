package org.map.hibernate.utils;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.map.hibernate.dao.HibernateDao;
import org.map.hibernate.ddo.ValidationMaster;
import org.springframework.stereotype.Repository;

@Repository("ValidationData")
public class ValidationData {

	@Resource(name = "ValidationDao")
	private HibernateDao<ValidationMaster, Integer> hibernateDao;

	public HibernateDao<ValidationMaster, Integer> getHibernateDao() {
		return hibernateDao;
	}

	public void setHibernateDao(
			HibernateDao<ValidationMaster, Integer> hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	public List<ValidationMaster> getRoleList() {

		return (List<ValidationMaster>) hibernateDao.list();
	}

	public List<ValidationMaster> getValidationList(String name) {

		return (List<ValidationMaster>) hibernateDao.list(Restrictions.like(
				"name", name, MatchMode.ANYWHERE));
	}

	public List<String> getValidationNameList() {

		return (List<String>) hibernateDao.list(Projections
				.distinct(Projections.property("name")));
	}

	public List<String> getValidationNameList(String validationName) {

		return (List<String>) hibernateDao.list(
				Restrictions.like(validationName, MatchMode.ANYWHERE),
				Projections.distinct(Projections.property("name")));
	}

	public int getNextValidationNumber() throws HibernateException {

		return hibernateDao.nextCode(Projections.max("code"));
	}

	public void insertValidation(ValidationMaster validation) {

		if (validation.getCode() <= 1000) {

			validation.setCode(getNextValidationNumber());
		}

		if (validation.getDisplayText().trim().length() <= 0) {

			validation.setDisplayText(validation.getName());
		}

		hibernateDao.save(validation);
	}

	public void updateValidation(ValidationMaster validation) {

		hibernateDao.update(validation);
	}

	public void deleteValidation(ValidationMaster validation) {

		hibernateDao.delete(validation);
	}

	public List<ValidationMaster> getValidationList() {
		return (List<ValidationMaster>) hibernateDao.list();
	}
}
