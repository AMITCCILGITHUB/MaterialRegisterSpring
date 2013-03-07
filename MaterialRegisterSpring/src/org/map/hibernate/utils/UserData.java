package org.map.hibernate.utils;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.map.hibernate.dao.HibernateDao;
import org.map.hibernate.ddo.UserMaster;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository("UserData")
@Scope("prototype")
public class UserData {

	@Resource(name = "UserDao")
	private HibernateDao<UserMaster, Integer> hibernateDao;
	
	public HibernateDao<UserMaster, Integer> getHibernateDao() {
		return hibernateDao;
	}

	public void setHibernateDao(HibernateDao<UserMaster, Integer> hibernateDao) {
		this.hibernateDao = hibernateDao;
	}
	
	public int getNextUserCode() throws HibernateException {

		return hibernateDao.nextCode(Projections.max("userCode"));
	}
	
	public boolean validateUser(UserMaster user) {

		int rowCount = 0;
		try {
			rowCount = hibernateDao
					.list(Restrictions
							.conjunction()
							.add(Restrictions.eq("userName", user.getUserName()))
							.add(Restrictions.eq("password", user.getPassword())))
					.size();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ((rowCount > 0) ? true : false);
	}

	public UserMaster getUserDetails(String userName) {

		return hibernateDao.get(Restrictions.eq("userName", userName));
	}

	public UserMaster getUserDetails(int userCode) {

		return hibernateDao.get(userCode);
	}
	
	public List<UserMaster> getUserList() {

		return (List<UserMaster>) hibernateDao.list();
	}

	public void insertUser(UserMaster user) {

		hibernateDao.save(user);
	}

	public void updateUser(UserMaster user) {

		hibernateDao.update(user);
	}
	
	public void deleteUser(UserMaster user) {

		hibernateDao.delete(user);
	}
}
