package com.clicklist.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.clicklist.model.AbstractEntity;

@Entity
@Table(name = "mercado")
public class Mercado extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_mercado", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_mercado", length = 100, nullable = false)
	private String nome;

	/* Construtores */

	public Mercado() {
	}

	public Mercado(Long id) {
		this.id = id;
	}

	/* Sets e Gets */

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
