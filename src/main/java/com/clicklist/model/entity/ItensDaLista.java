package com.clicklist.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.clicklist.model.AbstractEntity;

@Entity
@Table(name = "itens_da_lista")
public class ItensDaLista extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_itens_da_lista", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItensDaLista;

	@JoinColumn(name = "fk_minhaLista")
	private String minhaLista;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_produtos")
	private Produto produtos;

	@Column(name = "qtd", precision = 18, scale = 2, nullable = false)
	private double qtd;

	@Column(name = "total", nullable = false)
	private double total;

	public double getQtd() {
		return qtd;
	}

	public void setQtd(double qtd) {
		this.qtd = qtd;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getMinhaLista() {
		return minhaLista;
	}

	public void setMinhaLista(String minhaLista) {
		this.minhaLista = minhaLista;
	}

	public Produto getProdutos() {
		return produtos;
	}

	public void setProdutos(Produto produtos) {
		this.produtos = produtos;
	}

	@Override
	public Long getId() {
		return idItensDaLista;
	}


	public void setIdItensDaLista(Long idItensDaLista) {
		this.idItensDaLista = idItensDaLista;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idItensDaLista == null) ? 0 : idItensDaLista.hashCode());
		result = prime * result
				+ ((minhaLista == null) ? 0 : minhaLista.hashCode());
		result = prime * result
				+ ((produtos == null) ? 0 : produtos.hashCode());
		long temp;
		temp = Double.doubleToLongBits(qtd);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensDaLista other = (ItensDaLista) obj;
		if (idItensDaLista == null) {
			if (other.idItensDaLista != null)
				return false;
		} else if (!idItensDaLista.equals(other.idItensDaLista))
			return false;
		if (minhaLista == null) {
			if (other.minhaLista != null)
				return false;
		} else if (!minhaLista.equals(other.minhaLista))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (Double.doubleToLongBits(qtd) != Double.doubleToLongBits(other.qtd))
			return false;
		if (Double.doubleToLongBits(total) != Double
				.doubleToLongBits(other.total))
			return false;
		return true;
	}


}
