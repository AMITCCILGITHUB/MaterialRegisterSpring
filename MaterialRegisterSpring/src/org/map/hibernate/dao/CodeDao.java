package org.map.hibernate.dao;

import org.map.hibernate.ddo.CodeMaster;
import org.springframework.stereotype.Repository;

@Repository("CodeDao")
public class CodeDao extends HibernateDaoImpl<CodeMaster, Long>{

	public CodeDao() {
		
		super(CodeMaster.class);
	}
}
