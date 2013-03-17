package org.map.hibernate.utils;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.map.hibernate.dao.HibernateDao;
import org.map.hibernate.ddo.CodeMaster;
import org.springframework.stereotype.Repository;

@Repository("CodeData")
public class CodeData {

	@Resource(name = "CodeDao")
	private HibernateDao<CodeMaster, Integer> hibernateDao;

	public HibernateDao<CodeMaster, Integer> getHibernateDao() {

		return hibernateDao;
	}

	public void setHibernateDao(HibernateDao<CodeMaster, Integer> hibernateDao) {

		this.hibernateDao = hibernateDao;
	}

	public List<CodeMaster> getCodes() {

		return (List<CodeMaster>) hibernateDao.list();
	}

	public List<CodeMaster> getCodes(String codeName) {

		return (List<CodeMaster>) hibernateDao.list(Restrictions.like(
				"codeName", codeName, MatchMode.ANYWHERE));
	}

	public Integer getNextCodeNumber() throws HibernateException {

		return hibernateDao.nextCode(Projections.max("codeNumber"));
	}

	public CodeMaster getCode(String codeName) {

		return hibernateDao.get(Restrictions.like("codeName", codeName));
	}

	public void insertCode(CodeMaster code) {

		code.setCodeNumber(getNextCodeNumber());
		hibernateDao.save(code);
	}

	public void updateCode(CodeMaster code) {

		hibernateDao.update(code);
	}

	public void deleteCode(CodeMaster code) {

		hibernateDao.delete(code);
	}

	public List<String> getCodeNameList() {

		return (List<String>) hibernateDao.list(Projections
				.distinct(Projections.property("codeName")));
	}
}
