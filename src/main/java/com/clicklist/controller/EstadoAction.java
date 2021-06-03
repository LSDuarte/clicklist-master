package com.clicklist.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.clicklist.dao.EstadoDAO;
import com.clicklist.model.entity.Estado;
import com.clicklist.util.JSFUtil;
import com.clicklist.util.UtilRelatorio;

@Named
@ViewScoped
public class EstadoAction implements Serializable {

	private static final long serialVersionUID = 1L;

	private Estado estado;
	private List<Estado> estados = new ArrayList<>();

	@Inject
	private EstadoDAO estadoDAO;

	public void salvarEstado() {
		try {
			estadoDAO.save(estado);
			JSFUtil.adicionarMensagemSucesso("Sucesso", "Salvo com Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro", "Erro ao realizar a Inclusão!");
		}
	}

	public void excluirEstado(ActionEvent event) {
		try {
			estado = (Estado) event.getComponent().getAttributes().get("estadoSelecionado");
			estadoDAO.remove(estado.getId());
			estadoDAO.listar();
			JSFUtil.adicionarMensagemSucesso("Sucesso", "Exclusão Realizada com Sucesso!");
		} catch (RuntimeException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro", "Erro ao realizar a Exclusão!");
		}
	}
	
	
	public void editarEstado(ActionEvent event) {
		try {
			estado = (Estado) event.getComponent().getAttributes().get("estadoSelecionado");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro", "Erro ao realizar a Edição!");
		}
	}
	
	
	public List<Estado> getListarEstados() {
		return new EstadoDAO().listar();
	}
	
	public void relatorioEstado() {
		Map<String, Object> parametros = new HashMap<>();
		UtilRelatorio.imprimirRelatorio("relatorioEstados", parametros, estadoDAO.listar());
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void prepararNovo() {
		estado = new Estado();
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

}