package org.map.hibernate.dao;

import org.map.hibernate.ddo.MaterialMaster;
import org.springframework.stereotype.Repository;

@Repository("MaterialDao")
public class MaterialDao extends HibernateDaoImpl<MaterialMaster, Long>{

	public MaterialDao() {
		
		super(MaterialMaster.class);
	}
}
