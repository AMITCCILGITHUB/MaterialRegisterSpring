package org.map.hibernate.dao;

import org.map.hibernate.ddo.ValidationMaster;
import org.springframework.stereotype.Repository;

@Repository("ValidationDao")
public class ValidationDao extends HibernateDaoImpl<ValidationMaster, Long>{

	public ValidationDao() {
		
		super(ValidationMaster.class);
	}
}
