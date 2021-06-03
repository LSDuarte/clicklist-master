package com.clicklist.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.clicklist.model.AbstractEntity;

@Entity
@Table(name = "cidade")
public class Cidade extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_cidade", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome_cidade", length = 100, nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "fk_estado", nullable = false)
	private Estado estado;

	/* Contrutores */
	public Cidade() {
	}

	public Cidade(Long id) {
		this.id = id;
	}

	/* Sets e Gets */
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

}
