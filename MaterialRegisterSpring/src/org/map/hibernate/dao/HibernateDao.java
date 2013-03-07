package org.map.hibernate.dao;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

public interface HibernateDao<T, ID extends Serializable> {

	public T get(ID id);

	public T get(Criterion criterion);

	public String get(Criterion criterion, Projection projection);

	public Collection<T> list();

	public Collection<T> list(Order order);

	public Collection<T> list(Criterion criterion);

	public Collection<T> list(Criterion criterion, Order order);

	public Collection<String> list(Projection projection);

	public Collection<String> list(Criterion criterion, Projection projection);

	public Collection<String> list(Projection projection, Order order);

	public int nextCode(Projection projection);

	public T save(T o);

	public void update(T o);

	public void delete(T o);

	public void saveOrUpdate(T o);

	public SessionFactory getSessionFactory();
}
