package com.clicklist.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.clicklist.dao.UsuarioDAO;
import com.clicklist.model.entity.Usuario;

@Named
@ViewScoped
public class UsuarioFilterBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public List<Usuario> getListarUsuarios() {
		return new UsuarioDAO().listar();
	}
	
}