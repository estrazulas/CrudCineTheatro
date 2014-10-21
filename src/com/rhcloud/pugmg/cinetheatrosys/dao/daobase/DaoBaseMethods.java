package com.rhcloud.pugmg.cinetheatrosys.dao.daobase;

import java.util.List;

/**
 * Interface de métodos obrigatórios para as Daos
 * @author Daniel Severo Estrázulas
 */
public interface DaoBaseMethods<T> {
	
	List<T> getAll();	
	T save(final T o);
	void delete(final T object);
	T get(final int id);
	T merge(final T o);
	void saveOrUpdate(final T o);
	
}
