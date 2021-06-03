package com.clicklist.dao;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.clicklist.model.entity.Usuario;
import com.clicklist.util.JpaUtil;

public class UsuarioDAO extends AbstractDAO<Usuario> {

	private static final long serialVersionUID = 1L;

	public Usuario getUsuario(String nomeUsuario, String senha) {
		try {
			Transaction tr = (Transaction) getEntityManager().getTransaction();
			if (!tr.isActive()) {
				tr.begin();
			}
			Usuario usuario = (Usuario) em
					.createQuery(
							"SELECT u from Usuario u where u.login = :name and u.senhaCliente = :senha")
					.setParameter("name", nomeUsuario)
					.setParameter("senha", senha).getSingleResult();

			return usuario;
		} catch (NoResultException e) {
			return null;
		} finally {
			JpaUtil.getInstance().fecharEntityManager(getEntityManager());
		}
	}

	public Session getSession() {
		return getEntityManager().unwrap(Session.class);
	}

	public Usuario listByUsername(String nomeUsuario) {
		try {
			Transaction tr = (Transaction) getEntityManager().getTransaction();
			if (!tr.isActive()) {
				tr.begin();
			}
			Usuario usuarioByUsername = (Usuario) em.createQuery("SELECT u from Usuario u where u.login = :name")
					.setParameter("name", nomeUsuario).getSingleResult();
			return usuarioByUsername;
		} catch (NoResultException e) {
			return null;
		} finally {
			JpaUtil.getInstance().fecharEntityManager(getEntityManager());
		}
	}
	
	
}