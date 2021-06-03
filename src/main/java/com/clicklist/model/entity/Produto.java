package com.clicklist.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.clicklist.model.AbstractEntity;

@Entity
@Table(name = "produto")
public class Produto extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_produto", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;

	@Column(name = "nome_produto", nullable = false)
	private String nome;

	@Column(name = "preco", nullable = false)
	private Double preco;

	@ManyToOne
	@JoinColumn(name = "fk_mercado", nullable = false)
	private Mercado mercado;

	@Lob
	@Column(name = "imagem_produto")
	private byte[] imagem_produto;

	@Transient
	private double total;

	@Transient
	private int quantidade;

	/* Contrutores */
	public Produto() {
	}

	public Produto(Long id) {
		this.idProduto = id;
	}

	/* Sets e Gets */

	public Mercado getMercado() {
		return mercado;
	}

	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getImagem_produto() {
		return imagem_produto;
	}

	public void setImagem_produto(byte[] imagem_produto) {
		this.imagem_produto = imagem_produto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public Long getId() {
		return idProduto;
	}

	public void setId(Long id) {
		this.idProduto = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
