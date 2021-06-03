package com.clicklist.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.clicklist.model.AbstractEntity;
import com.clicklist.util.JpaUtil;
import com.clicklist.util.ListUtil;

public class AbstractDAO<E extends AbstractEntity> implements Dao<E> {

	private static final long serialVersionUID = 1L;

	EntityManager em;

	protected final EntityManager getEntityManager() {
		if (em == null || !em.isOpen()) {
			em = JpaUtil.getInstance().criarEntityManager();
		}
		return em;
	}

	@Override
	public E save(E entity) {
		try {
			getEntityManager().getTransaction().begin();
			E entitySaved = getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();
			return entitySaved;
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			return null;
		} finally {
			JpaUtil.getInstance().fecharEntityManager(getEntityManager());
		}
	}
	
	@Override
	public void remove(Long id) {
		try {
			getEntityManager().getTransaction().begin();
			E entity = getEntityManager().find(getEntityClass(), id);
			getEntityManager().remove(entity);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
		} finally {
			JpaUtil.getInstance().fecharEntityManager(getEntityManager());
		}
	}

	@Override
	public List<E> listar() {
		String jpql = "FROM " + getEntityClass().getSimpleName() + " e";
		TypedQuery<E> query = getEntityManager().createQuery(jpql, getEntityClass());
		return query.getResultList();
	}

	@Override
	public List<E> saveAll(List<E> entities) {
		List<E> mergedEntities = null;
		if (!ListUtil.isEmpty(entities)) {
			mergedEntities = new ArrayList<>();
			for (E entity : entities) {
				mergedEntities.add(save(entity));
			}
		}
		return mergedEntities;
	}

	@Override
	public void removeAll(List<E> entities) {
		for (E entity : entities) {
			remove(entity.getId());
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Class<E> getEntityClass() {
		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<E>) type.getActualTypeArguments()[0];
	}


}
