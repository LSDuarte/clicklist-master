package com.clicklist.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.clicklist.dao.PessoaDAO;
import com.clicklist.model.entity.Pessoa;

@Named
@ViewScoped
public class PessoaFilterBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Pessoa> getListarPessoas() {
		return new PessoaDAO().listar();
	}

}