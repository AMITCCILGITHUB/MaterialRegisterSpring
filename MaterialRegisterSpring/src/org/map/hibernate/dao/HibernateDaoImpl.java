package org.map.hibernate.dao;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class HibernateDaoImpl<T, ID extends Serializable> implements
		HibernateDao<T, ID> {

	private Class<T> type;

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			throw new IllegalStateException(
					"SessionFactory has not been set on DAO before usage");

		return sessionFactory;
	}

	public Class<T> getType() {
		return type;
	}

	public void setType(Class<T> type) {
		this.type = type;
	}

	public HibernateDaoImpl(Class<T> type) {
		this.type = type;
	}

	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public T get(ID id) {
		return (T) getSessionFactory().getCurrentSession().get(type, id);
	}

	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public T get(Criterion criterion) {

		return (T) getSessionFactory().getCurrentSession().createCriteria(type)
				.add(criterion).uniqueResult();
	}

	@Transactional(readOnly = true)
	@Override
	public String get(Criterion criterion, Projection projection) {

		return (String) getSessionFactory().getCurrentSession()
				.createCriteria(type).add(criterion).setProjection(projection)
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public Collection<T> list() {
		return (Collection<T>) getSessionFactory().getCurrentSession()
				.createCriteria(type).list();
	}

	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public Collection<T> list(Order order) {

		return (Collection<T>) getSessionFactory().getCurrentSession()
				.createCriteria(type).addOrder(order).list();
	}

	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public Collection<T> list(Criterion criterion) {

		return (Collection<T>) getSessionFactory().getCurrentSession()
				.createCriteria(type).add(criterion).list();
	}

	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public Collection<T> list(Criterion criterion, Order order) {

		return (Collection<T>) getSessionFactory().getCurrentSession()
				.createCriteria(type).add(criterion).addOrder(order).list();
	}

	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public Collection<String> list(Projection projection) {

		return (Collection<String>) getSessionFactory().getCurrentSession()
				.createCriteria(type).setProjection(projection).list();
	}

	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public Collection<String> list(Criterion criterion, Projection projection) {

		return (Collection<String>) getSessionFactory().getCurrentSession()
				.createCriteria(type).add(criterion).setProjection(projection)
				.list();
	}

	@Transactional(readOnly = true)
	@Override
	@SuppressWarnings("unchecked")
	public Collection<String> list(Projection projection, Order order) {

		return (Collection<String>) getSessionFactory().getCurrentSession()
				.createCriteria(type).setProjection(projection).addOrder(order)
				.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Integer nextCode(Projection projection) {

		Object result = getSessionFactory().getCurrentSession()
				.createCriteria(type).setProjection(projection).uniqueResult();

		Integer codeNumber = 1001;
		if (result != null)
			codeNumber = (Integer) result + 1;

		return codeNumber;
	}

	@Transactional
	@Override
	@SuppressWarnings("unchecked")
	public T save(T o) {
		return (T) getSessionFactory().getCurrentSession().save(o);
	}

	@Transactional
	@Override
	public void update(T o) {
		getSessionFactory().getCurrentSession().update(o);
	}

	@Transactional
	@Override
	public void delete(T o) {
		getSessionFactory().getCurrentSession().delete(o);
	}

	@Transactional
	@Override
	public void saveOrUpdate(T o) {
		getSessionFactory().getCurrentSession().saveOrUpdate(o);
	}
}
