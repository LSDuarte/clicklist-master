package com.clicklist.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.clicklist.dao.MercadoDAO;
import com.clicklist.model.entity.Mercado;
import com.clicklist.util.JSFUtil;

@Named
@ViewScoped
public class MercadoAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private Mercado mercado;
	private List<Mercado> mercados;

	@Inject
	private MercadoDAO mercadoDAO;
	
	public void prepararNovo() {
		mercado = new Mercado();
	}
	
	public void salvarMercado() {
		try {
			mercadoDAO.save(mercado);
			JSFUtil.adicionarMensagemSucesso("Sucesso", "Salvo com Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro", "Erro ao realizar a Inclusão!");
		}
	}
	
	public List<Mercado> getListarMercados() {
		return new MercadoDAO().listar();
	}
	
	public Mercado getMercado() {
		return mercado;
	}

	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
	}

	public List<Mercado> getMercados() {
		return mercados;
	}

	public void setMercados(List<Mercado> mercados) {
		this.mercados = mercados;
	}
}