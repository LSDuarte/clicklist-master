package com.clicklist.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Transaction;

import com.clicklist.model.entity.MinhaLista;
import com.clicklist.model.entity.Usuario;
import com.clicklist.util.JpaUtil;


public class MinhaListaDAO extends AbstractDAO<MinhaLista> {

	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unchecked")
	public List<MinhaLista> minhaListaByUser(Usuario user){
		try {
			Transaction tr = (Transaction) getEntityManager().getTransaction();
			if (!tr.isActive()) {
				tr.begin();
			}
			return (List<MinhaLista>) em.createQuery("SELECT m from MinhaLista m where m.usuario = :idUser").setParameter("idUser", user).getResultList();
		} catch (NoResultException e) {
			return null;
		} finally {
			JpaUtil.getInstance().fecharEntityManager(getEntityManager());
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Integer contarLista() {
		try {
			Transaction tr = (Transaction) getEntityManager().getTransaction();
			if (!tr.isActive()) {
				tr.begin();
			}
			
			String hql = "Select count(nome_lista) from MinhaLista ";
			
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
	
	@SuppressWarnings("rawtypes")
	public Double somarValorLista() {
		try {
			Transaction tr = (Transaction) getEntityManager().getTransaction();
			if (!tr.isActive()) {
				tr.begin();
			}
			
			String hql = "Select sum(valor_total) from MinhaLista ";
			
			Query query = em.createQuery(hql);
			
			List results = query.getResultList();
			Double number = (Double) results.get(0);
			
			return (Double) number;
					
		} catch (NoResultException e) {
			return null;
		} finally {
			JpaUtil.getInstance().fecharEntityManager(getEntityManager());
		}
		
	}
	
	public void deletarMinhaLista(MinhaLista minhaLista){
		Query query = em.createNativeQuery("DELETE FROM minhaLista WHERE id_minha_lista = '" + minhaLista.getIdMinhaLista()+ "'");
		query.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	
}