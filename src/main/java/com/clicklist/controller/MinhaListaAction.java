package com.clicklist.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.clicklist.dao.ItensDaListaDAO;
import com.clicklist.dao.MinhaListaDAO;
import com.clicklist.dao.UsuarioDAO;
import com.clicklist.model.entity.ItensDaLista;
import com.clicklist.model.entity.MinhaLista;
import com.clicklist.model.entity.Usuario;
import com.clicklist.util.JSFUtil;
import com.clicklist.util.Session;
import com.clicklist.util.UtilRelatorio;

@Named
@ViewScoped
public class MinhaListaAction implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioDAO usuarioDao;

	@Inject
	MinhaListaDAO minhaListaDao;

	@Inject
	ItensDaListaDAO itensDaListaDAO;

	private MinhaLista listaSelecionada;

	@PostConstruct
	public void init() {
		listaSelecionada = new MinhaLista();
	}

	public List<MinhaLista> getMinhaListaBy() {
		Usuario user = usuarioDao.listByUsername(Session.getUserName());
		List<MinhaLista> minhaLista = minhaListaDao.minhaListaByUser(user);
		return minhaLista;
	}

	public void listaSelecionada(MinhaLista listaSelecionada) {
		this.listaSelecionada = listaSelecionada;
	}
	
	public void deletarLista(MinhaLista idRemover){
		minhaListaDao.deletarMinhaLista(idRemover);
		JSFUtil.adicionarMensagemSucesso("Lista Deletada!", null);;
	}

	public List<ItensDaLista> getItensDaListaById() {
		if (this.listaSelecionada.getIdMinhaLista() != null) {
			List<ItensDaLista> newItensDaLista = new ArrayList<ItensDaLista>();
			newItensDaLista = itensDaListaDAO.itensDaListaById(listaSelecionada.getIdMinhaLista());
			return newItensDaLista;
		}
		return null;
	}
	
	public void relatorioMinhaLista() {
		Map<String, Object> parametros = new HashMap<>();
		UtilRelatorio.imprimirRelatorio("relatorioMinhaLista", parametros, minhaListaDao.listar());
	}

}
