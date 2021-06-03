package com.clicklist.model.entity;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.clicklist.model.AbstractEntity;

@Entity
@Named("minhaLista")
@Table(name = "minhaLista")
public class MinhaLista extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_minha_lista", nullable = false)
	private String idMinhaLista;

	@Column(name = "nome_lista", length = 100)
	private String nome;

	@Column(name = "valor_total", precision = 18, scale = 2)
	private Double valorTotal;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_usuario")
	private Usuario usuario;

	/* Construtores */
	public MinhaLista() {
	}

	public String getIdMinhaLista() {
		return idMinhaLista;
	}

	public void setIdMinhaLista(String idMinhaLista) {
		this.idMinhaLista = idMinhaLista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Long getId() {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((idMinhaLista == null) ? 0 : idMinhaLista.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result
				+ ((valorTotal == null) ? 0 : valorTotal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MinhaLista other = (MinhaLista) obj;
		if (idMinhaLista == null) {
			if (other.idMinhaLista != null)
				return false;
		} else if (!idMinhaLista.equals(other.idMinhaLista))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		return true;
	}

}
