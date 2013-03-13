package org.map.hibernate.dao;

import org.map.hibernate.ddo.RoleMaster;
import org.springframework.stereotype.Repository;

@Repository("RolaDao")
public class RoleDao extends HibernateDaoImpl<RoleMaster, Long> {

	public RoleDao() {

		super(RoleMaster.class);
	}
}
