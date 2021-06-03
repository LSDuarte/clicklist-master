package com.clicklist.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class JpaUtil {

	private static JpaUtil instance;

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

	private JpaUtil() {
	}

	public static JpaUtil getInstance() {
		if (instance == null) {
			instance = new JpaUtil();
		}
		return instance;
	}

	public EntityManager criarEntityManager() {
		return emf.createEntityManager();
	}

	public void fecharEntityManager(EntityManager em) {
		if (em != null && !em.getTransaction().isActive()) {
			em.close();
		}
	}

	public static void main(String[] args) {
		JpaUtil.getInstance().criarEntityManager();

	}
}
