package com.rhcloud.pugmg.cinetheatrosys.dao.daobase;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Dao genérica para persistir/consultar objetos no banco
 * @author Daniel Severo Estrázulas
 *
 */
@SuppressWarnings("unchecked")
public class GenericDao<T> implements DaoBaseMethods<T> 
{

	@Autowired
	private Session session;
	
	private Class<T> tipoPersistencia;


	public GenericDao(){
		tipoPersistencia = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private Session getSession() {
		return session;
	}

	
	@Override
	public List<T> getAll() {
		return  Collections.unmodifiableList(session.createCriteria(getTipoPersistencia()).list());
	}

	@Override
	public T save(T o) {
		this.session.beginTransaction();
		Serializable obj = this.session.save(o);
		this.session.getTransaction().commit();
		return (T) obj;
	}

	@Override
	public void delete(T object) {
		this.session.beginTransaction();
		session.delete(object);
		this.session.getTransaction().commit();
	}

	@Override
	public T get(int id) {
		return (T) session.get(getTipoPersistencia(), id);
	}

	@Override
	public T merge(T o) {
		this.session.beginTransaction();
		Object obj = session.merge(o);
		this.session.getTransaction().commit();
		return (T) obj;
	}

	@Override
	public void saveOrUpdate(T o) {
		this.session.beginTransaction();
		session.saveOrUpdate(o);
		this.session.getTransaction().commit();
	}
	
	
	public Collection<T> findTheRange(int start, int length,String OrderBy, String OrderByColumn, Criterion criterion) 
	{
		Criteria crit = this.session.createCriteria(getTipoPersistencia()).setFirstResult(start).setMaxResults(length).
				addOrder(
						OrderBy.toLowerCase().equals("asc") ? 
								Order.asc(OrderByColumn) : 
									Order.desc(OrderByColumn)
									);
		
		if(criterion!=null){
			crit.add(criterion);
		}
		
		return crit.list();
	}	
	
	
	public Collection<T> findIntervalByAttribute(String columnName, String search, int start, int length, String OrderBy, String OrderByColumn, Criterion criterion)
	{	
		Criteria criteria = this.session.createCriteria(getTipoPersistencia()).
				add(Restrictions.ilike(columnName, search, MatchMode.ANYWHERE)).
				setFirstResult(start).
				setMaxResults(length).
				addOrder(
						OrderBy.toLowerCase().equals("asc") ? 
								Order.asc(OrderByColumn) : 
									Order.desc(OrderByColumn)
									);
		if(criterion!=null){
			criteria.add(criterion);
		}
		
		return criteria.list();
	}	
	
	
	public Long totalRecords(String column, Criterion crit) 
	{
		Criteria criteria = session.createCriteria(getTipoPersistencia()).setProjection(Projections.countDistinct(column));//.uniqueResult();
		if(crit!=null){
			criteria.add(crit);
		}
		return (Long) criteria.uniqueResult();
	}	
	
	
	public Class<T> getTipoPersistencia() {
		return tipoPersistencia;
	}

	public Query criaQuery(String hql) {
		return this.getSession().createQuery(hql);
	}

	public Criteria criaCriteria() {
		return this.getSession().createCriteria(getTipoPersistencia());
	}

	
	
}
