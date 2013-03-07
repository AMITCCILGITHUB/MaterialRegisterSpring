package org.map.hibernate.dao;

import org.map.hibernate.ddo.HeatChartMaster;
import org.springframework.stereotype.Repository;

@Repository("HeatChartDao")
public class HeatChartDao extends HibernateDaoImpl<HeatChartMaster, Long>{

	public HeatChartDao() {
		
		super(HeatChartMaster.class);
	}
}
