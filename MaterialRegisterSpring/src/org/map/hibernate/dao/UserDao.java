package org.map.hibernate.dao;

import org.map.hibernate.ddo.UserMaster;
import org.springframework.stereotype.Repository;

@Repository("UserDao")
public class UserDao extends HibernateDaoImpl<UserMaster, Long>{

	public UserDao() {
		
		super(UserMaster.class);
	}
}
