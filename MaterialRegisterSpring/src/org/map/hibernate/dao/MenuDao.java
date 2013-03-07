package org.map.hibernate.dao;

import org.map.hibernate.ddo.MenuMaster;
import org.springframework.stereotype.Repository;

@Repository("MenuDao")
public class MenuDao extends HibernateDaoImpl<MenuMaster, Long>{

	public MenuDao() {
		
		super(MenuMaster.class);
	}
}
