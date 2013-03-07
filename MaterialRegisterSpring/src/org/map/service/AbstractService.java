package org.map.service;

import org.map.constants.PersistType;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public abstract class AbstractService<T, V> extends Service<V> {

	public abstract Task<V> createTask();
	
	public abstract void setPersistEntity(T entity);
	
	public abstract void setPersistType(PersistType persistType);
}
