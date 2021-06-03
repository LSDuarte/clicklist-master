package com.clicklist.model;

public class Dashboard {

	private Integer quantidadeProdutos;
	private Integer quantidadeListas;

	private Double valorProdutos;
	private Double valorListas;

	public Dashboard() {
	}

	public Integer getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	public void setQuantidadeProdutos(Integer quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

	public Integer getQuantidadeListas() {
		return quantidadeListas;
	}

	public void setQuantidadeListas(Integer quantidadeListas) {
		this.quantidadeListas = quantidadeListas;
	}

	public Double getValorProdutos() {
		return valorProdutos;
	}

	public void setValorProdutos(Double valorProdutos) {
		this.valorProdutos = valorProdutos;
	}

	public Double getValorListas() {
		return valorListas;
	}

	public void setValorListas(Double valorListas) {
		this.valorListas = valorListas;
	}

}
