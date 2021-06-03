package com.clicklist.dao;

import java.io.Serializable;
import java.util.List;

/* É uma interface, que contem os métodos genericos*/

public interface Dao<E> extends Serializable {

	public E save(E entity);
	public List<E> saveAll(List<E> entities);
	public void remove(Long id);
	public void removeAll(List<E> entities);
	public List<E> listar();
	
}