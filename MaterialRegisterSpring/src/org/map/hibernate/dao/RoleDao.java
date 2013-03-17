package org.map.hibernate.dao;

import org.map.hibernate.ddo.RoleMaster;
import org.springframework.stereotype.Repository;

@Repository("RoleDao")
public class RoleDao extends HibernateDaoImpl<RoleMaster, Long> {

	public RoleDao() {

		super(RoleMaster.class);
	}
}
