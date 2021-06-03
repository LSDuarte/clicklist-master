package com.clicklist.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Transaction;

import com.clicklist.model.entity.ItensDaLista;
import com.clicklist.util.JpaUtil;

public class ItensDaListaDAO extends AbstractDAO<ItensDaLista> {
	private static final long serialVersionUID = 1L;

	
	@SuppressWarnings("unchecked")
	public List<ItensDaLista> itensDaListaById(String idMinhaLista){
		try {
			Transaction tr = (Transaction) getEntityManager().getTransaction();
			if (!tr.isActive()) {
				tr.begin();
			}
			return (List<ItensDaLista>) em.createQuery("SELECT u from ItensDaLista u where u.minhaLista = :minhaLista").setParameter("minhaLista", idMinhaLista).getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			JpaUtil.getInstance().fecharEntityManager(getEntityManager());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Double somarValorProduto() {
		try {
			Transaction tr = (Transaction) getEntityManager().getTransaction();
			if (!tr.isActive()) {
				tr.begin();
			}

			String hql = "Select sum(total) from ItensDaLista ";
			
			Query query = em.createQuery(hql);
			
			List results = query.getResultList();
			Number number = (Number) results.get(0);
			
			return (Double) number;
					
		} catch (NoResultException e) {
			return null;
		} finally {
			JpaUtil.getInstance().fecharEntityManager(getEntityManager());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Integer contarProdutos() {
		try {
			Transaction tr = (Transaction) getEntityManager().getTransaction();
			if (!tr.isActive()) {
				tr.begin();
			}
			
			String hql = "Select count(minhaLista) from ItensDaLista ";
			
			Query query = em.createQuery(hql);
			
			List results = query.getResultList();
			Number number = (Number) results.get(0);
			
			return (Integer) (number.intValue());
					
		} catch (NoResultException e) {
			return null;
		} finally {
			JpaUtil.getInstance().fecharEntityManager(getEntityManager());
		}
	}
	
}

