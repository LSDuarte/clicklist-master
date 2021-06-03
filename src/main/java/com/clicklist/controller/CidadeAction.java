package com.clicklist.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.clicklist.dao.CidadeDAO;
import com.clicklist.model.entity.Cidade;
import com.clicklist.util.JSFUtil;
import com.clicklist.util.UtilRelatorio;

@Named
@ViewScoped
public class CidadeAction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CidadeDAO cidadeDAO;
	
	private Cidade cidade;
	
	public List<Cidade> getListaCidades() {
		return new CidadeDAO().listar();	
	}
	
	public void salvarCidade() {
		try {
			cidadeDAO.save(cidade);
			JSFUtil.adicionarMensagemSucesso("Sucesso", "Salvo com Sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro", "Erro ao realizar a Inclusão!");
		}
	}
	
	public void prepararNovo() {
		cidade = new Cidade();
	}
	
	public void relatorioCidades() {
		Map<String, Object> parametros = new HashMap<>();
		UtilRelatorio.imprimirRelatorio("relatorioCidades", parametros, cidadeDAO.listar());
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}