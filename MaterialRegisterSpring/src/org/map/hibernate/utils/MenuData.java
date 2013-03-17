package org.map.hibernate.utils;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.map.hibernate.dao.HibernateDao;
import org.map.hibernate.ddo.MenuMaster;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("MenuData")
@Scope("prototype")
public class MenuData {

	@Resource(name = "MenuDao")
	private HibernateDao<MenuMaster, Integer> hibernateDao;

	public HibernateDao<MenuMaster, Integer> getHibernateDao() {

		return hibernateDao;
	}

	public void setHibernateDao(HibernateDao<MenuMaster, Integer> hibernateDao) {

		this.hibernateDao = hibernateDao;
	}

	public List<MenuMaster> getMenu() {

		return (List<MenuMaster>) hibernateDao.list();
	}

	public List<MenuMaster> getMenus(Integer roleCode) {

		return (List<MenuMaster>) hibernateDao.list(Restrictions.eq(
				"role.code", roleCode));
	}

}
