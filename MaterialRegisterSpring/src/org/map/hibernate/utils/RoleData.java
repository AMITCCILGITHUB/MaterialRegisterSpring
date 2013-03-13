package org.map.hibernate.utils;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.map.hibernate.dao.HibernateDao;
import org.map.hibernate.ddo.RoleMaster;
import org.springframework.stereotype.Repository;

@Repository("RoleData")
public class RoleData {

	@Resource(name = "RoleDao")
	private HibernateDao<RoleMaster, Integer> hibernateDao;

	public HibernateDao<RoleMaster, Integer> getHibernateDao() {
		return hibernateDao;
	}

	public void setHibernateDao(HibernateDao<RoleMaster, Integer> hibernateDao) {
		this.hibernateDao = hibernateDao;
	}

	public List<RoleMaster> getRoleList() {

		return (List<RoleMaster>) hibernateDao.list();
	}

	public List<RoleMaster> getRoleList(String name) {

		return (List<RoleMaster>) hibernateDao.list(Restrictions.like("name",
				name, MatchMode.ANYWHERE));
	}

	public List<String> getRoleNameList() {

		return (List<String>) hibernateDao.list(Projections
				.distinct(Projections.property("name")));
	}

	public List<String> getRoleNameList(String roleName) {

		return (List<String>) hibernateDao.list(
				Restrictions.like(roleName, MatchMode.ANYWHERE),
				Projections.distinct(Projections.property("name")));
	}

	public int getNextRoleNumber() throws HibernateException {

		return hibernateDao.nextCode(Projections.max("code"));
	}

	public void insertRole(RoleMaster role) {

		if (role.getCode() <= 1000) {

			role.setCode(getNextRoleNumber());
		}

		if (role.getDisplayText().trim().length() <= 0) {

			role.setDisplayText(role.getName());
		}

		hibernateDao.save(role);
	}

	public void updateRole(RoleMaster role) {

		hibernateDao.update(role);
	}

	public void deleteRole(RoleMaster role) {

		hibernateDao.delete(role);
	}
}
